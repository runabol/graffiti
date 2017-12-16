package com.creactiviti.graffiti.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

/**
 * @author Arik Cohen
 * @since Dec 16, 2017
 */
@CrossOrigin
@RestController
public class GraphQLController {

  @Autowired private GraphQL graphql;
  
  private final Logger log = LoggerFactory.getLogger(getClass());
  
  @PostMapping(value="/graphql",consumes="application/json")
  public ExecutionResult graphql (@RequestBody Map<String,Object> aQuery) {
    
    long now = System.currentTimeMillis();
    
    ExecutionResult result = graphql.execute(ExecutionInput.newExecutionInput()
                                    .query((String)aQuery.get("query"))
                                    .variables((Map<String, Object>) aQuery.get("variables"))
                                    .build());
    
    log.debug("{} [{}ms]",aQuery.get("query"),(System.currentTimeMillis()-now));
    
    return result;
  }
  
}
