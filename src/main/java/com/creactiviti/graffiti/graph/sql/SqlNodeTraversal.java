package com.creactiviti.graffiti.graph.sql;

import java.util.Iterator;

import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.Traversal;

public class SqlNodeTraversal extends SqlGraphTraversal<Node> {
  
  private Iterator<Node> iterator;

  public SqlNodeTraversal(SqlGraph aGraph) {
    super(aGraph);
  }

  @Override
  public Traversal<Node> hasType (String aType) {
    where.add(new NodeTypeEq());
    arguments.add(aType);
    return this;
  }

  @Override
  public Iterator<Node> iterator() {
    if(iterator != null) {
      return iterator;
    }
    return (iterator = graph.nodes(where, arguments));
  }

  @Override
  public Traversal<Node> fromNodeId(String aFromNodeId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Traversal<Node> toNodeId(String aFromNodeId) {
    throw new UnsupportedOperationException();
  }

}
