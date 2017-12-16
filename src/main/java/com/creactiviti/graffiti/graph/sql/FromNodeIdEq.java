package com.creactiviti.graffiti.graph.sql;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public class FromNodeIdEq implements SelectClause {

  @Override
  public void apply (SelectBuilder aSelectBuilder) {
    aSelectBuilder.where("from_node_id = ?");
  }

}
