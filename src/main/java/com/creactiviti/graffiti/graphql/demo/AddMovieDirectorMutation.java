package com.creactiviti.graffiti.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graph.Edge;
import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.SimpleEdge;
import com.creactiviti.graffiti.graphql.Arguments;
import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.MutationBuilder;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
@ConditionalOnProperty(name="graffiti.demo", havingValue="true")
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
