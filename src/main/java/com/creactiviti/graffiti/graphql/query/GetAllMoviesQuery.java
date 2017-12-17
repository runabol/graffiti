package com.creactiviti.graffiti.graphql.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.QueryBuilder;
import com.creactiviti.graffiti.graphql.Types;
import com.creactiviti.graffiti.graphql.type.Movie;

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
                                   .hasType(Movie.NAME);
                         }));
  }

}
