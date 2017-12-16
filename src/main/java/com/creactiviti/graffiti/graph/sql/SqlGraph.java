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
    
    return SimpleEdge.builder(this)
                  .id(id)
                  .type(aEdge.type())
                  .properties(aEdge.properties())
                  .createdAt(aEdge.createtAt())
                  .modifiedAt(aEdge.modifiedAt())
                  .fromNodeId(aEdge.from().id())
                  .toNodeId(aEdge.to().id())
                  .build();
  }
  
  Iterator<Node> nodes (List<SelectClause> aWhere, List<Object> aArgs) {
    SelectBuilder sbuilder = new SelectBuilder("node");
    for(SelectClause where : aWhere) {
      where.apply(sbuilder);
    }
    return jdbc.queryForList(sbuilder.build(), aArgs.toArray())
               .stream()
               .map(r-> {
                 String id = (String) r.get("id");
                 String type = (String) r.get("node_type");
                 PGobject properties = (PGobject) r.get("properties");
                 String propertiesValue = properties.getValue();
                 
                 return SimpleNode.builder(this)
                               .id(id)
                               .type(type)
                               .properties(JSON.read(propertiesValue, Map.class))
                               .build(); 
               })
               .collect(Collectors.toList())
               .iterator();  // FIXME: lazy loaded
  }

  @Override
  public Traversal<Node> nodes() {
    return new SqlGraphTraversal<>(this);
  }

  @Override
  public Traversal<Edge> edges() {
    return null;
  }
  
  private String generateId () {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
