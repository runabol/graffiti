package com.creactiviti.graffiti.graph.sql;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public class ToNodeIdEq implements SelectClause {

  @Override
  public void apply (SelectBuilder aSelectBuilder) {
    aSelectBuilder.where("to_node_id = ?");
  }

}
