package com.creactiviti.graffiti;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.creactiviti.graffiti.graphql.MutationBuilder;
import com.creactiviti.graffiti.graphql.QueryBuilder;

import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;
import graphql.schema.GraphQLSchema;

@Configuration
public class GraphQLConfiguration {
  
  @Autowired(required=false)
  private List<QueryBuilder> queryBuilders = new ArrayList<>();
  
  @Autowired(required=false)
  private List<MutationBuilder> mutationBuilders = new ArrayList<>();
  
  @Bean
  public GraphQL graphql () {
    // build the Query (Read) portion of the GraphQL schema
    Builder queryBuilder = GraphQLObjectType.newObject().name("Query");
    
    queryBuilders.forEach(qb->qb.build(queryBuilder));
    
    // build the Mutation (Write) portion of the GraphQL schema    
    
    Builder mutationBuilder = GraphQLObjectType.newObject().name("MutationQuery");
    
    mutationBuilders.forEach(mb->mb.build(mutationBuilder));
    
    // build the GraphQL schema
    
    GraphQLSchema.Builder schemaBuilder = GraphQLSchema.newSchema();
    
    if(queryBuilders.size() > 0) {
      schemaBuilder.query(queryBuilder);
    }
    
    if(mutationBuilders.size() > 0) {
      schemaBuilder.mutation(mutationBuilder);
    }
    
    return GraphQL.newGraphQL(schemaBuilder.build()).build();
  }
  
}
