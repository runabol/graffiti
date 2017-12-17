package com.creactiviti.graffiti.graphql;

import org.springframework.stereotype.Component;

import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
public class Health implements TypeBuilder {

  public static final String NAME = "Health";
  public static final GraphQLTypeReference REF = Types.ref(NAME);
  
  @Override
  public GraphQLType build() {
    return Types.objectTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("status"))
                .build();
  }

}
