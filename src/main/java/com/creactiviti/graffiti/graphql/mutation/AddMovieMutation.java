package com.creactiviti.graffiti.graphql.mutation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.SimpleNode;
import com.creactiviti.graffiti.graphql.Arguments;
import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.MutationBuilder;
import com.creactiviti.graffiti.graphql.type.Movie;

import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
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
