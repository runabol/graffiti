package com.creactiviti.graffiti.graphql;

import graphql.schema.GraphQLType;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface TypeBuilder {

  GraphQLType build ();
  
}
