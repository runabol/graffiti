package com.creactiviti.graffiti.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.SimpleNode;
import com.creactiviti.graffiti.graphql.Arguments;
import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.MutationBuilder;

import graphql.schema.GraphQLObjectType.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
@ConditionalOnProperty(name="graffiti.demo", havingValue="true")
public class AddDirectorMutation implements MutationBuilder {
  
  @Autowired private Graph g;

  @Override
  public void build (Builder aBuilder) {
    aBuilder.field(Fields.field("addDirector")
                         .type(Director.REF)
                         .argument(Arguments.notNullStringArgument("name"))
                         .dataFetcher((env) -> {
                           Node node = SimpleNode.builder(g) 
                                                 .type(Director.NAME)
                                                 .properties(env.getArguments())
                                                 .build();
                           return g.add(node);
                         }));
  }

}
