package com.creactiviti.graffiti.graph;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Node extends Element {

  Traversal<Edge> from (String aEdgeType);
  
  Traversal<Edge> to (String aEdgeType);
  
}
