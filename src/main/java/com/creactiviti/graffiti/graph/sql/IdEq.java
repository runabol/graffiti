package com.creactiviti.graffiti.graph.sql;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public class IdEq implements SelectClause {

  @Override
  public void apply (SelectBuilder aSelectBuilder) {
    aSelectBuilder.where("id = ?");
  }

}
