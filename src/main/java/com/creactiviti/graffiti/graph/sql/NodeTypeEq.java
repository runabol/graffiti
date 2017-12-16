package com.creactiviti.graffiti.graph.sql;

public class NodeTypeEq implements SelectClause {

  @Override
  public void apply(SelectBuilder aSelectBuilder) {
    aSelectBuilder.where("node_type = ?");
  }

}
