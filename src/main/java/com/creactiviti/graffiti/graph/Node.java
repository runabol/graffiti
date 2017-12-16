package com.creactiviti.graffiti.graph;

import java.util.Iterator;

public interface Node extends Element {

  Iterator<Edge> to (String aEdgeType);
  
  Iterator<Edge> from (String aEdgeType);
  
}
