package com.creactiviti.graffiti.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition.Builder;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class Fields {

  public static Builder stringField (String aName) {
    return create(aName).type(Scalars.GraphQLString);
  }
  
  private static Builder create (String aName) {
    return new Builder().name(aName);
  }
  
}
