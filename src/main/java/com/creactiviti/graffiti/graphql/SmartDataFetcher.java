package com.creactiviti.graffiti.graphql;

import com.creactiviti.graffiti.graph.Element;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.PropertyDataFetcher;

public class SmartDataFetcher extends PropertyDataFetcher<Object> {
  
  private final String propertyName;
  
  public SmartDataFetcher(String aPropertyName) {
    super(aPropertyName);
    propertyName = aPropertyName;
  }

  @Override
  public Object get (DataFetchingEnvironment aEnvironment) {
    Object source = aEnvironment.getSource();
    
    if(source == null) {
      return null;
    }
    
    if(source instanceof Element) {
      
      Element element = (Element) source;
      
      if(propertyName.equals("id")) {
        return element.id();
      }
      else {
        return element.property(propertyName);
      }
      
    }
    
    return super.get(aEnvironment);
  }

}
