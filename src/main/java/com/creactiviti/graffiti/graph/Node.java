package com.creactiviti.graffiti.graph;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Node extends Element {

  Traversal<Node> from (String aEdgeType);
  
  Traversal<Node> to (String aEdgeType);
  
}
