package com.creactiviti.graffiti;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.creactiviti.graffiti.graph.sql.SqlGraph;

@Configuration
public class SqlGraphConfiguration {

  @Bean
  public SqlGraph sqlGraph (DataSource aDataSource) {
    return new SqlGraph(aDataSource);
  }
  
}
