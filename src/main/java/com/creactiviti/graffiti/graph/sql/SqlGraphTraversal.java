package com.creactiviti.graffiti.graph.sql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.creactiviti.graffiti.graph.Element;
import com.creactiviti.graffiti.graph.Traversal;

public abstract class SqlGraphTraversal<E extends Element> implements Traversal<E> {

  protected final SqlGraph graph;
  protected final List<SelectClause> where = new ArrayList<>();
  protected final List<Object> arguments = new ArrayList<>();
  
  public SqlGraphTraversal(SqlGraph aGraph) {
    graph  = aGraph;
  }
  
  public abstract Iterator<E> iterator ();
  
  @Override
  public boolean hasNext() {
    return iterator().hasNext();
  }

  @Override
  public E next () {
    return iterator().next();
  }

  @Override
  public Traversal<E> has(String aProperty, Object aValue) {
    where.add(new PropertyEq());
    arguments.add(aProperty);
    arguments.add(aValue);
    return this;
  }

  @Override
  public Traversal<E> hasId (String aId) {
    where.add(new IdEq());
    arguments.add(aId);
    return this;
  }
  

}
