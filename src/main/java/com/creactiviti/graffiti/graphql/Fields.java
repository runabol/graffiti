package com.creactiviti.graffiti.graphql;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition.Builder;
import graphql.schema.GraphQLNonNull;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class Fields {

  public static Builder stringField (String aName) {
    return create(aName).type(Scalars.GraphQLString);
  }
  
  public static Builder notNullStringField (String aName) {
    return create(aName).type(new GraphQLNonNull(Scalars.GraphQLString));
  }
  
  public static Builder field (String aName) {
    return create(aName);
  }
  
  private static Builder create (String aName) {
    return new Builder().name(aName).dataFetcher(new SmartDataFetcher(aName));
  }
  
}
