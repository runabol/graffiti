package com.creactiviti.graffiti.graph.sql;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import com.creactiviti.graffiti.graph.Element;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class SqlElement implements Element {

  private final String id; 
  private final Date createdAt;
  private final Date modifiedAt;
  private final String type;
  private final Map<String, Object> properties;
  
  public SqlElement(String aId, String aType, Date aCreatedAt, Date aModifiedAt, Map<String, Object> aProperties) {
    id = aId;
    createdAt = aCreatedAt;
    modifiedAt = aModifiedAt;
    type = aType;
    properties = aProperties;
  }
  
  @Override
  public String id() {
    return id;
  }

  @Override
  public Date createtAt() {
    return createdAt;
  }

  @Override
  public Date modifiedAt() {
    return modifiedAt;
  }

  @Override
  public String type() {
    return type;
  }

  @Override
  public <T> T property (String aName) {
    return (T) properties.get(aName);
  }
  
  @Override
  public Map<String, Object> properties() {
    return Collections.unmodifiableMap(properties);
  }

}
