package com.creactiviti.graffiti.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLNonNull;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class Arguments {

  public static GraphQLArgument.Builder stringArgument (String aName) {
    return GraphQLArgument.newArgument()
                          .name(aName)
                          .type(Scalars.GraphQLString);
  }
  
  public static GraphQLArgument.Builder notNullStringArgument (String aName) {
    return GraphQLArgument.newArgument()
                          .name(aName)
                          .type(new GraphQLNonNull(Scalars.GraphQLString));
  }
  
}
