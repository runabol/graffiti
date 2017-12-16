package com.creactiviti.graffiti.graph.sql;

import com.creactiviti.graffiti.graph.Element;
import com.creactiviti.graffiti.graph.Traversal;

public class SqlGraphTraversal<E extends Element> implements Traversal<E> {

  private final SqlGraph graph;
  
  public SqlGraphTraversal(SqlGraph aGraph) {
    graph  = aGraph;
  }
  
  @Override
  public boolean hasNext() {
    return false;
  }

  @Override
  public E next () {
    return null;
  }

  @Override
  public Traversal<E> has(String aProperty, Object aValue) {
    return null;
  }

  @Override
  public Traversal<E> hasId(String aId) {
    return null;
  }

}
