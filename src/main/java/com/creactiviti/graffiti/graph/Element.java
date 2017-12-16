package com.creactiviti.graffiti.graph;

import java.util.Date;

/**
 * The base interface for all elements 
 * of the {@link Graph} -- i.e. nodes 
 * and edges.
 * 
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public interface Element {

  /**
   * The globally unique ID of this element. 
   * 
   * @return String
   */
  String id ();
  
  /**
   * The date-time when this element 
   * was created.
   * 
   * @return Date
   */
  Date createtAt ();
  
  /**
   * The date-time when this element
   * was last modified.
   * 
   * @return Date
   */
  Date modifiedAt ();
  
  /**
   * A string representing this element's 
   * type. Used for grouping elements.
   * 
   * @return String
   */
  String type ();
  
  /**
   * Return the value of a property.
   * 
   * @param aName
   *          The name of the property which value
   *          needs to be returned.
   * @return T The value of the property or <code>null</code>
   *           if not value is associated with the given
   *           property name.  
   */
  <T> T property (String aName);
  
}
