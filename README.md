# Graffiti

Graffiti is a headless CMS Framework written in Java designed to be dead simple.

If you are familiar with Spring Boot then you should be able to get up and running quickly by importing the project to your IDE and customize it to your needs.

# Why, god why?! 

Traditionally, CMS products such as Wordpress, Drupal and the like were heavily focused on the web. They were eseentially an HTML rendering engine. But today, you can't really just exist on the web. With the advent of Smart Phones, Smart TVs, Smart Watches, [Smart Toilets](https://www.cnet.com/how-to/smart-toilets-make-your-bathroom-high-tech) and the rest of them you can't rely anymore on rendering HTML exclusively for your app exprience. 

What you really need is a platform-agnostic data exchange format which can be consumed by any of your apps. Be it web, smart devices, whatever. 

Luckily, the bright folks in Facebook created a standard called [GraphQL](http://graphql.org/) which brings the much needed civilization to the world of web services. While REST APIs are great and I am personally a big fan of them, they suffer from a few major drawbacks which GraphQL seeks to correct: 

1. Fine-grained control over what you get from the server. In a traditional REST API you might make a call like so: 

```
GET /person/1234
```

And you might get something like:

```
{
   "firstName":"Joe",
   "lastName":"Jones",
   "favorites":[..],
   "friends":[...],
   "prefs":[...],
   ... ---
   ...   |  < a bunch of other data
   ... --- 
}
```

Then if you need to add additional piece of data to your response you just add that and hope to god that no client will decide to break because you added a new property. Moreover, some clients much want only a sliver of this response -- say just the first name of the person -- but you are giving them ALL the data whether they like it or not. Aside from the bandwidth cost, you might also have to make multiple roundrips to your database to fetch all that unnecessary data. Doesn't sound too efficient now does it? 

Enter GraphQL. 

You want the person's name and nothing else. No problem :

```
POST /graphql

{
   getPerson (id:"1234") {
      firstName
      lastName
   }
}
```

Which will give you back something like:

```
  {
     "data": {
        "getPerson": {
           "firstName":"Joe",
           "lastName":"Jones"
        }
     }
  }
```

Now, that's a whole lot better. No extra calls to the database to fetch his `favorites`, his `friends` etc.

2. Versioning

In REST world, when you need to add new properties to your response you can either take the chance of adding them and hoping nothing breaks or you can add `/v2/person/:id` endpoint and add your properties there. The problem is that now you have two endpoints which are semantically very similar -- one being a superset of the other. In GraphQL, since clients are *explicitly* asking for what they want back from the server they are not going to break when you add any new properties.

3. Schema

Because the shape of a GraphQL query closely matches the result, you can predict what the query will return without knowing that much about the server. But it's useful to have an exact description of the data we can ask for - what fields can we select? What kinds of objects might they return? What fields are available on those sub-objects? That's where the schema comes in.

Every GraphQL service defines a set of types which completely describe the set of possible data you can query on that service. Then, when queries come in, they are validated and executed against that schema.

4. Documentation

A nice outcome of having to define your schema is that you sort of get the documentation of your API for free. Client developers can now interrogate your API and find what you have available without you having to explicitly write seperate API docs.

5. Tooling 

Another nice outcome of having a schema is having tooling that lets you visualize your API and interact with it in real-time. One such tool is [GraphiQL](https://github.com/graphql/graphiql) which supports auto-completion and other nifty features.
 
# Weapons of Choice

1. Java 1.8
2. Spring Boot
3. GraphQL-Java
4. PostgreSQL

# Concepts

Graffiti relies on a handful of core data structures: 

## Graph

A Graph is actually an extermely simple yet powerful data structure:

![alt text](graph.png "Graph")

It is composed of only two basic units:

### Node

A Node (the round elements in the Graph above) is the basic unit of data. Another word for it could be Object or an Entity but Node is the standard jargon in the Graph world.

Nodes are composed of key-value pairs.

Nodes are grouped by a type which is just a simple String.

Almost anything can be modelled as a Node: people, cars, invoices, transactions, movies, app pages etc.  

### Edge

An edge represents a link between two nodes. 

Again, this could be called a Link or a Connection but we'll stick with Edge.

Edges also have a type they can also have key-value pairs associated with them.

### That's it!

And between these basic primitive data structures you can represent just about anything.

# Getting Started

## Installation

1. Install PostgreSQL

If you have Docker and you want to spin one up easily run:

```
docker run --name postgres -e POSTGRES_DB=graffiti -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:9.6.5-alpine
```

2. Initialize the database:

```
mvn clean spring-boot:run -Dspring.datasource.initialize=true -Dspring.datasource.url=jdbc:postgresql://localhost:5432/graffiti -Dspring.datasource.username=postgres -Dspring.datasource.password=password
```

## Usage 

```
@Component
public class SomeComponent {
  
  @Autowired private Graph g;

  public void doStuff () {
  
    // building a Movie node instance
    Node movie = SimpleNode.builder(g)
                           .type("Movie")
                           .property("title", "Terminator")
                           .build();
                        
    // adding the movie to the Graph
    movie = g.add(movie);
    
    // building a director node instance
    Node director = SimpleNode.builder(g)
                              .type("Director")
                              .property("name", "James Cameron")
                              .build();
                        
    // adding the director to the graph.
    director = g.add(director);
    
    // creating a "directed" link between the director
    // and the movie
    Edge directed = SimpleEdge.builder(g)
                              .fromNodeId(director.id())
                              .type("directed")
                              .toNodeId(movie.id())
                              .build();
    
    directed = g.add(directed);
    
  }

}

```

## GraphQL

Now we can expose our data in a fine-grained manner using GraphQL:

1. Define the GraphQL Schema:

```
@Component
public class Movie implements TypeBuilder {
  
  public static final String NAME = "Movie";
  public static final GraphQLTypeReference REF = Types.ref(NAME);

  @Override
  public GraphQLType build() {
    return Types.elementTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("title"))
                .field(Fields.spelField("directors", "${source.from('directed')}") // SPEL Expression
                             .type(Types.list(Director.REF)))
                .build();
  }

}
```

```
@Component
public class Director implements TypeBuilder {

  public static final String NAME = "Director";
  public static final GraphQLTypeReference REF = Types.ref(NAME);
  
  @Override
  public GraphQLType build() {
    return Types.nodeTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("name"))
                .build();
  }

}
```

```
@Component
public class AddMovieMutation implements MutationBuilder {
  
  @Autowired private Graph g;

  @Override
  public void build (Builder aBuilder) {
    aBuilder.field(Fields.field("addMovie")
                         .type(Movie.REF)
                         .argument(Arguments.notNullStringArgument("title"))
                         .dataFetcher((env) -> {
                           Node node = SimpleNode.builder(g) 
                                                 .type(Movie.NAME)
                                                 .properties(env.getArguments())
                                                 .build();
                           return g.add(node);
                         }));
  }

}
```

```
@Component
public class AddDirectorMutation implements MutationBuilder {
  
  @Autowired private Graph g;

  @Override
  public void build (Builder aBuilder) {
    aBuilder.field(Fields.field("addDirector")
                         .type(Director.REF)
                         .argument(Arguments.notNullStringArgument("name"))
                         .dataFetcher((env) -> {
                           Node node = SimpleNode.builder(g) 
                                                 .type(Director.NAME)
                                                 .properties(env.getArguments())
                                                 .build();
                           return g.add(node);
                         }));
  }

}
```

```
@Component
public class AddMovieDirectorMutation implements MutationBuilder {
  
  @Autowired private Graph g;

  @Override
  public void build (Builder aBuilder) {
    aBuilder.field(Fields.field("addMovieDirector")
                         .type(Scalars.GraphQLString)
                         .argument(Arguments.notNullStringArgument("movieId"))
                         .argument(Arguments.notNullStringArgument("directorId"))
                         .dataFetcher((env) -> {
                           Edge edge = SimpleEdge.builder(g) 
                                                 .type("directed")
                                                 .fromNodeId(env.getArgument("directorId"))
                                                 .toNodeId(env.getArgument("movieId"))
                                                 .build();
                           g.add(edge);
                           
                           return "OK";
                         }));
  }

}
```

```
@Component
public class GetAllMoviesQuery implements QueryBuilder {

  @Autowired private Graph g;
  
  @Override
  public void build(Builder aBuilder) {
    aBuilder.field(Fields.field("getAllMovies")
                         .type(Types.list(Movie.REF))
                         .dataFetcher((env) -> {
                           return g.nodes()
                                   .hasType(Movie.NAME);
                         }));
  }

}
```

Now let's add a movie through GraphQL: 

```
$ curl -s -X POST -H "Content-Type:application/json" -d '{"query":"mutation { addMovie (title:\"Titanic\") { id title } }"}' http://localhost:8080/graphql | jq .
```

Next, let's add a director:

```
curl -s -X POST -H "Content-Type:application/json" -d '{"query":"mutation { addDirector(name:\"James Cameron\"){ id name } }"}' http://localhost:8080/graphql | jq .
```

Finally, let's link the movie and the director:

```
curl -s -X POST -H "Content-Type:application/json" -d '{"query":"mutation { addMovieDirector(movieId:\"<MOVIE_ID_GOES_HERE>\" directorId:\"<DIRECTOR_ID_GOES_HERE>\") }"}' http://localhost:8080/graphql | jq .
```

Now let's query for all our movies:

```
$  curl -s -X POST -H "Content-Type:application/json" -d '{"query":"{ getAllMovies { id title directors { name } } }"}' http://localhost:8080/graphql | jq .
```

Which should give you back something like:

``` 
{
  "data": {
    "getAllMovies": [
      {
        "id": "035fec100e6c4e228237eebca84ea257",
        "title": "Titanic",
        "directors": [
           {
              "name":"James Cameron"
           }
        }
      }
    ]
  },
  "errors": [],
  "extensions": null
}
```

## GraphiQL

If you want to interact with the GraphQL server using a visual query builder try:

```
docker run --name=graphiql -p 9100:8080 -d -e GRAPHQL_SERVER=http://<YOUR_IP_GOES_HERE>:8080/graphql creactiviti/graphiql
```

And the go to [http://localhost:9100](http://localhost:9100)

# License

Graffiti is released under version 2.0 of the Apache License.
