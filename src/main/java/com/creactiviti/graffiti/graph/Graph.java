package com.creactiviti.graffiti.graph;

/**
 * A {@link Graph} represents a collection of nodes (entities) 
 * and edges (links between entities).
 * 
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Graph {
  
  Node add (Node aNode);
  
  Edge add (Edge aEdge);

  Traversal<Node> nodes ();
  
  Traversal<Edge> edges ();
  
}
