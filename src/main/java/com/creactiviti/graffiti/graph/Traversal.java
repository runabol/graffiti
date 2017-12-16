package com.creactiviti.graffiti.graph;

import java.util.Iterator;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Traversal<E extends Element> extends Iterator<E> {
  
  Traversal<E> hasId (String aId);
  
  Traversal<E> has (String aProperty, Object aValue);
  
}
