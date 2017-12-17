package com.creactiviti.graffiti.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creactiviti.giraphe.graph.Graph;
import com.creactiviti.giraphe.graphql.Fields;
import com.creactiviti.giraphe.graphql.QueryBuilder;
import com.creactiviti.giraphe.graphql.Types;

import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
public class GetAllMoviesQuery implements QueryBuilder {

  @Autowired private Graph g;
  
  @Override
  public void build(Builder aBuilder) {
    aBuilder.field(Fields.field("getAllMovies")
                         .type(Types.list(Movie.REF))
                         .dataFetcher((env) -> {
                           return g.nodes()
                                   .type(Movie.NAME);
                         }));
  }

}
