package com.creactiviti.graffiti.graph;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.creactiviti.graffiti.utils.Assert;

public class NodeBuilder {

  private final Graph g;
  private String id;
  private String type;
  private Date createdAt;
  private Date modifiedAt;
  private Map<String, Object> properties = new HashMap<>();
  
  NodeBuilder (Graph aGraph) {
    g = aGraph;
  }
  
  public NodeBuilder id (String aId) {
    id = aId;
    return this;
  }
  
  public NodeBuilder type (String aType) {
    type = aType;
    return this;
  }
  
  public NodeBuilder properties (Map<String, Object> aProperties) {
    properties = aProperties;
    return this;
  }
  
  public NodeBuilder property (String aName, Object aValue) {
    properties.put(aName, aValue);
    return this;
  }
  
  public NodeBuilder createdAt (Date aCreateAt) {
    createdAt = aCreateAt;
    return this;
  }
  
  public NodeBuilder modifiedAt (Date aModifiedAt) {
    modifiedAt = aModifiedAt;
    return this;
  }
  
  public Node build () {
    Assert.isTrue(type != null, "node type must not be null");
    return new NodeImpl(g,id, type,createdAt, modifiedAt, properties);
  }
  
}
