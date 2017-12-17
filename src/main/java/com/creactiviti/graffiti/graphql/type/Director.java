package com.creactiviti.graffiti.graphql.type;

import org.springframework.stereotype.Component;

import com.creactiviti.graffiti.graphql.Fields;
import com.creactiviti.graffiti.graphql.TypeBuilder;
import com.creactiviti.graffiti.graphql.Types;

import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@Component
public class Director implements TypeBuilder {

  public static final String NAME = "Director";
  public static final GraphQLTypeReference REF = Types.ref(NAME);
  
  @Override
  public GraphQLType build() {
    return Types.nodeTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("name"))
                .build();
  }

}
