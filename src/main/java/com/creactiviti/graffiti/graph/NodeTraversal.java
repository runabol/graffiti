package com.creactiviti.graffiti.graph;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface NodeTraversal extends Traversal<Edge> {
  
  Traversal<Edge> hasFromId (String aFromId);
  
  Traversal<Edge> hasToId (String aFromId);
  
}
