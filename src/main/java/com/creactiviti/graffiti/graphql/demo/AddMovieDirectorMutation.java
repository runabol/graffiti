package com.creactiviti.graffiti.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creactiviti.giraphe.graph.Edge;
import com.creactiviti.giraphe.graph.Graph;
import com.creactiviti.giraphe.graph.SimpleEdge;
import com.creactiviti.giraphe.graphql.Arguments;
import com.creactiviti.giraphe.graphql.Fields;
import com.creactiviti.giraphe.graphql.MutationBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
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
