package com.creactiviti.graffiti.graph.sql;

import java.util.Iterator;

import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.Traversal;

public class SqlNodeTraversal extends SqlGraphTraversal<Node> {
  
  private Iterator<Node> iterator;

  public SqlNodeTraversal(SqlGraph aGraph) {
    super(aGraph);
  }

  @Override
  public Traversal<Node> type (String aType) {
    where.add(new NodeTypeEq());
    arguments.add(aType);
    return this;
  }

  @Override
  public Iterator<Node> iterator() {
    if(iterator != null) {
      return iterator;
    }
    return (iterator = graph.nodes(where, arguments));
  }
  
  @Override
  public Traversal<Node> from(String aEdgeType, String aToNodeId) {
    where.add((sb) -> {
      sb.where(" node.id in ( select e.from_node_id from edge e where e.edge_type = ? and e.to_node_id = ?) ");
    });
    arguments.add(aEdgeType);
    arguments.add(aToNodeId);
    return this;
  }
  
  @Override
  public Traversal<Node> to(String aEdgeType, String aFromNodeId) {
    where.add((sb) -> {
      sb.where(" node.id in ( select e.to_node_id from edge e where e.edge_type = ? and e.from_node_id = ?) ");
    });
    arguments.add(aEdgeType);
    arguments.add(aFromNodeId);
    return this;
  }

}
