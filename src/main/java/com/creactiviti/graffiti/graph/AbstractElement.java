package com.creactiviti.graffiti.graph;

import java.util.Date;
import java.util.Map;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public abstract class AbstractElement implements Element {

  private final String id; 
  private final Date createdAt;
  private final Date modifiedAt;
  private final String type;
  private final Map<String, Object> properties;
  
  public AbstractElement(String aId, String aType, Date aCreatedAt, Date aModifiedAt, Map<String, Object> aProperties) {
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

}
