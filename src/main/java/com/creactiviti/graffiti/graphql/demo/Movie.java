package com.creactiviti.graffiti.graphql.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(name="graffiti.demo", havingValue="true")
public class Movie implements TypeBuilder {
  
  public static final String NAME = "Movie";
  public static final GraphQLTypeReference REF = Types.ref(NAME);

  @Override
  public GraphQLType build() {
    return Types.elementTypeBuilder()
                .name(NAME)
                .field(Fields.stringField("title"))
                .field(Fields.spelField("directors", "${source.from('directed')}") // SPEL Expression
                             .type(Types.list(Director.REF)))
                .build();
  }

}
