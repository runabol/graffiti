package com.creactiviti.graffiti.graph;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class NodeImpl extends AbstractElement implements Node {
  
  private final Graph g;

  public NodeImpl(Graph aGraph, String aId, String aType, Date aCreatedAt, Date aModifiedAt, Map<String, Object> aProperties) {
    super(aId, aType, aCreatedAt, aModifiedAt, aProperties);
    g = aGraph;
  }

  @Override
  public Iterator<Edge> to (String aEdgeType) {
    return null;
  }

  @Override
  public Iterator<Edge> from (String aEdgeType) {
    return null;
  }

}
