package com.creactiviti.graffiti.graph.sql;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.postgresql.util.PGobject;
import org.springframework.jdbc.core.JdbcTemplate;

import com.creactiviti.graffiti.graph.Edge;
import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.SimpleEdge;
import com.creactiviti.graffiti.graph.SimpleNode;
import com.creactiviti.graffiti.graph.Traversal;
import com.creactiviti.graffiti.utils.JSON;

public class SqlGraph implements Graph {
  
  private final JdbcTemplate jdbc;
  
  public SqlGraph(DataSource aDatasource) {
    jdbc = new JdbcTemplate(aDatasource);
  }

  @Override
  public Node add (Node aNode) {
    String id = generateId();
    
    String propertiesAsString = JSON.write(aNode.properties());
    
    String sql = "insert into node (id,node_type,properties) values (?,?,?::jsonb)";
    
    jdbc.update(sql,id,aNode.type(),propertiesAsString);
    
    return nodes().hasId(id).next();
    
//    return SqlNode.builder(this)
//                  .id(id)
//                  .type(aNode.type())
//                  .properties(aNode.properties())
//                  .createdAt(aNode.createtAt())
//                  .modifiedAt(aNode.modifiedAt())
//                  .build();
  }

  @Override
  public Edge add(Edge aEdge) {
    String id = generateId();
    
    String propertiesAsString = JSON.write(aEdge.properties());
    
    String sql = "insert into edge (id,edge_type,properties,from_node_id,to_node_id) values (?,?,?::jsonb,?,?)";
    
    jdbc.update(sql,id,aEdge.type(),propertiesAsString,aEdge.from().id(),aEdge.to().id());
    
    return edges().hasId(id).next();
  }
  
  Iterator<Edge> edges (List<SelectClause> aWhere, List<Object> aArgs) {
    SelectBuilder sbuilder = new SelectBuilder("edge");
    for(SelectClause where : aWhere) {
      where.apply(sbuilder);
    }
    return jdbc.queryForList(sbuilder.build(), aArgs.toArray())
               .stream()
               .map(this::toEdge)
               .collect(Collectors.toList())
               .iterator();  // FIXME: lazy loaded
  }
  
  Iterator<Node> nodes (List<SelectClause> aWhere, List<Object> aArgs) {
    SelectBuilder sbuilder = new SelectBuilder("node");
    for(SelectClause where : aWhere) {
      where.apply(sbuilder);
    }
    return jdbc.queryForList(sbuilder.build(), aArgs.toArray())
               .stream()
               .map(this::toNode)
               .collect(Collectors.toList())
               .iterator();  // FIXME: lazy loaded
  }
  
  private Edge toEdge (Map<String, Object> aRecord) {
    String id = (String) aRecord.get("id");
    String type = (String) aRecord.get("edge_type");
    String fromNodeId = (String) aRecord.get("from_node_id");
    String toNodeId = (String) aRecord.get("to_node_id");
    PGobject properties = (PGobject) aRecord.get("properties");
    String propertiesValue = properties.getValue();
    return SimpleEdge.builder(this)
                     .id(id)
                     .type(type)
                     .properties(JSON.read(propertiesValue, Map.class))
                     .fromNodeId(fromNodeId)
                     .toNodeId(toNodeId)
                     .build(); 
  }
  
  private Node toNode (Map<String, Object> aRecord) {
    String id = (String) aRecord.get("id");
    String type = (String) aRecord.get("node_type");
    PGobject properties = (PGobject) aRecord.get("properties");
    String propertiesValue = properties.getValue();
    return SimpleNode.builder(this)
                     .id(id)
                     .type(type)
                     .properties(JSON.read(propertiesValue, Map.class))
                     .build(); 
  }

  @Override
  public Traversal<Node> nodes() {
    return new SqlNodeTraversal(this);
  }

  @Override
  public Traversal<Edge> edges() {
    return new SqlEdgeTraversal(this);
  }
  
  private String generateId () {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
