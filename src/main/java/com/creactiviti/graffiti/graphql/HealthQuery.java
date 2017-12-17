package com.creactiviti.graffiti.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.stereotype.Component;

import graphql.schema.GraphQLObjectType.Builder;

@Component
public class HealthQuery implements QueryBuilder {

  @Autowired private HealthEndpoint healthEndpoint;

  @Override
  public void build (Builder aBuilder) {
    aBuilder.field(Fields.field("health")
                         .type(Health.REF)
                         .dataFetcher((env) -> {
                           return healthEndpoint.invoke();
                         }));
  }

}
