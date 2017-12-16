package com.creactiviti.graffiti.graph;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.creactiviti.graffiti.utils.Assert;

public class SimpleNode extends SimpleElement implements Node {
  
  private final Graph g;

  public SimpleNode(Graph aGraph, String aId, String aType, Date aCreatedAt, Date aModifiedAt, Map<String, Object> aProperties) {
    super(aId, aType, aCreatedAt, aModifiedAt, aProperties);
    g = aGraph;
  }

  @Override
  public Iterator<Edge> to (String aEdgeType) {
    return g.edges().hasType(aEdgeType).toNodeId(id());
  }

  @Override
  public Iterator<Edge> from (String aEdgeType) {
    return g.edges().hasType(aEdgeType).fromNodeId(id());
  }
  
  public static Builder builder (Graph aGraph) {
    return new Builder(aGraph);
  }
  
  public static class Builder {

    private final Graph g;
    private String id;
    private String type;
    private Date createdAt;
    private Date modifiedAt;
    private Map<String, Object> properties = new HashMap<>();
    
    Builder (Graph aGraph) {
      g = aGraph;
    }
    
    public Builder id (String aId) {
      id = aId;
      return this;
    }
    
    public Builder type (String aType) {
      type = aType;
      return this;
    }
    
    public Builder properties (Map<String, Object> aProperties) {
      properties = aProperties;
      return this;
    }
    
    public Builder property (String aName, Object aValue) {
      properties.put(aName, aValue);
      return this;
    }
    
    public Builder createdAt (Date aCreateAt) {
      createdAt = aCreateAt;
      return this;
    }
    
    public Builder modifiedAt (Date aModifiedAt) {
      modifiedAt = aModifiedAt;
      return this;
    }
    
    public Node build () {
      Assert.isTrue(type != null, "node type must not be null");
      return new SimpleNode(g,id, type,createdAt, modifiedAt, properties);
    }
    
  }


}
