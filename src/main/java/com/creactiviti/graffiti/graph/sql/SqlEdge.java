package com.creactiviti.graffiti.graph.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.creactiviti.graffiti.graph.Edge;
import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.utils.Assert;

public class SqlEdge extends SqlElement implements Edge {
  
  private final Graph g;
  private final String fromNodeId; 
  private final String toNodeId;

  public SqlEdge(Graph aGraph, String aId, String aType, Date aCreatedAt, Date aModifiedAt, Map<String, Object> aProperties, String aFromNodeId, String aToNodeId) {
    super(aId, aType, aCreatedAt, aModifiedAt, aProperties);
    g = aGraph;
    fromNodeId = aFromNodeId;
    toNodeId = aToNodeId;
  }

  @Override
  public Node from() {
    return null;
  }

  @Override
  public Node to() {
    return null;
  }

  static class Builder {

    private final Graph g;
    private String id;
    private String type;
    private Date createdAt;
    private Date modifiedAt;
    private Map<String, Object> properties = new HashMap<>();
    private String fromNodeId;
    private String toNodeId;
    
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
    
    public Builder fromNodeId (String aFromNodeId) {
      fromNodeId = aFromNodeId;
      return this;
    }
    
    public Builder toNodeId (String aToNodeId) {
      toNodeId = aToNodeId;
      return this;
    }
    
    public Edge build () {
      Assert.isTrue(type != null, "node type must not be null");
      Assert.isTrue(fromNodeId != null, "from node id must not be null");
      Assert.isTrue(toNodeId != null, "to node id must not be null");
      return new SqlEdge(g,id, type,createdAt, modifiedAt, properties,fromNodeId,toNodeId);
    }
    
  }

}
