# Graffiti

a sample project utilizing the [Giraphe](https://github.com/creactiviti/giraphe) CMS Framework.

# Usage 

1. Clone the repo:

```
git clone https://github.com/creactiviti/graffiti.git
cd graffiti
```

2. Setup database: 

```
docker run --name postgres -e POSTGRES_DB=giraphe -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -d -p 5432:5432 postgres:9.6.5-alpine
```

3. Run it:

```
mvn clean spring-boot:run
```

4. From another terminal window, run a query:

```
curl -s -X POST -H "Content-Type:application/json" -d '{"query":"{ getAllMovies { id title directors { name } } }"}' http://localhost:8080/graphql
```

5. Optional: Interact with the API through GraphiQL:

```
docker run --name=graphiql -p 9100:8080 -d -e GRAPHQL_SERVER=http://<YOUR_IP_GOES_HERE>:8080/graphql creactiviti/graphiql
```

And the go to [http://localhost:9100](http://localhost:9100)

# How do I use it for my own project?

Check the [giraphe tutorial](https://github.com/creactiviti/giraphe#getting-started). 

# License

This project is released under version 2.0 of the Apache License.

