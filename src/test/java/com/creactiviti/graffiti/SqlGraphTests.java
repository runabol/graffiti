package com.creactiviti.graffiti;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.creactiviti.graffiti.graph.sql.SqlGraph;
import com.creactiviti.graffiti.graph.sql.SqlNode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlGraphTests {
  
  @Autowired private SqlGraph g;

	@Test
	public void testAdd1() {
	  g.add(SqlNode.builder(g).type("Movie").property("title", "Home Alone").build());
	}

}
