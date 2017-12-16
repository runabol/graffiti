package com.creactiviti.graffiti;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.sql.SqlGraph;
import com.creactiviti.graffiti.graph.sql.SqlNode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlGraphTests {
  
  @Autowired private SqlGraph g;

	@Test
	public void testAddNode () {
	  Node node = SqlNode.builder(g)
      	                .type("Movie")
      	                .property("title", "Home Alone")
      	                .build();
    Node added = g.add(node);
    Assert.assertNotNull(added.id());
    Node get = g.nodes().hasId(added.id()).next();
    Assert.assertEquals(get.id(), added.id());
	}
	

}
