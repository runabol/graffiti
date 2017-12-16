package com.creactiviti.graffiti.graph;

import java.util.Iterator;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Node extends Element {

  Iterator<Edge> to (String aEdgeType);
  
  Iterator<Edge> from (String aEdgeType);
  
  default NodeBuilder builder (Graph aGraph) {
    return new NodeBuilder(aGraph);
  }
  
}
