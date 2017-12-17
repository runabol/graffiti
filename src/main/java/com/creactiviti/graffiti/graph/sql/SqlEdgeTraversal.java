package com.creactiviti.graffiti.graph.sql;

import java.util.Iterator;

import com.creactiviti.graffiti.graph.Edge;
import com.creactiviti.graffiti.graph.Traversal;

public class SqlEdgeTraversal extends SqlGraphTraversal<Edge> {

  private Iterator<Edge> iterator;
  
  public SqlEdgeTraversal(SqlGraph aGraph) {
    super(aGraph);
  }

  @Override
  public Traversal<Edge> type (String aType) {
    where.add(new EdgeTypeEq());
    arguments.add(aType);
    return this;
  }

  @Override
  public Iterator<Edge> iterator() {
    if(iterator != null) {
      return iterator;
    }
    return (iterator = graph.edges(where, arguments));
  }

  @Override
  public Traversal<Edge> from(String aEdgeType, String aToNodeId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Traversal<Edge> to(String aEdgeType, String aFromNodeId) {
    throw new UnsupportedOperationException();
  }

  
}
