package com.creactiviti.graffiti.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.QueryBuilder;
import com.creactiviti.graffiti.graphql.Types;

import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
@ConditionalOnProperty(name="graffiti.demo", havingValue="true")
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
