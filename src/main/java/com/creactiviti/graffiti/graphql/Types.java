package com.creactiviti.graffiti.graphql;

import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

public abstract class Types {
  
  public static GraphQLInterfaceType.Builder interfaceTypeBuilder () {
    return new GraphQLInterfaceType.Builder ();
  }
  
  public static GraphQLObjectType.Builder elementTypeBuilder () {
    return objectTypeBuilder().withInterface(IElement.REF)
                              .field(Fields.notNullStringField("id"));
  }
  
  public static GraphQLObjectType.Builder objectTypeBuilder () {
    return new GraphQLObjectType.Builder();
  }
  
  public static GraphQLTypeReference ref (String aName) {
    return new GraphQLTypeReference(aName);
  }
  
  public static GraphQLList list (GraphQLType aWrappedType) {
    return new GraphQLList(aWrappedType);
  }
  
}
