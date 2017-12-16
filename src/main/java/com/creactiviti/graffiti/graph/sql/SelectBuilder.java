package com.creactiviti.graffiti.graph.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
public class SelectBuilder {
  
  private String table;
  
  private final List<String> where = new ArrayList<>();
  
  public SelectBuilder (String aTable) {
    table = aTable;
  }
  
  public void where (String aClause) {
    where.add(aClause);
  }
  
  public String build () {
    StringBuilder sbuilder = new StringBuilder();
    
    sbuilder.append("select * from")
            .append(" ")
            .append(table)
            .append(" ");
    
    if(where.size() > 0) {
      sbuilder.append("WHERE");
      
      for(String clause : where) {
        sbuilder.append(" ")
                .append(clause)
                .append(" ");
      }
    }
    
    return sbuilder.toString();
  }
  
}
