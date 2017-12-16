package com.creactiviti.graffiti.graphql;

import org.springframework.stereotype.Component;

import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
public class HelloQuery implements QueryBuilder {

  @Override
  public void build(Builder aBuilder) {
    aBuilder.field(Fields.stringField("hello")
                         .dataFetcher((env) -> "hi!"));
  }

}
