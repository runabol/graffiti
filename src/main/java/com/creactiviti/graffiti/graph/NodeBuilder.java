package com.creactiviti.graffiti.graph;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
  
  public Node build () {
    return new NodeImpl(g,id, type,createdAt, modifiedAt, properties);
  }
  
}
