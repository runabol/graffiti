package com.creactiviti.graffiti;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.creactiviti.graffiti.graph.Edge;
import com.creactiviti.graffiti.graph.Graph;
import com.creactiviti.graffiti.graph.Node;
import com.creactiviti.graffiti.graph.SimpleEdge;
import com.creactiviti.graffiti.graph.SimpleNode;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SqlGraphTests {
  
  @Autowired private Graph g;

	@Test
	public void test1 () {
	  
    // building a Movie node instance
    Node movie = SimpleNode.builder(g)
                           .type("Movie")
                           .property("title", "Terminator")
                           .build();
                        
    // adding the movie to the Graph
    movie = g.add(movie);
    
    // building a director node instance
    Node director = SimpleNode.builder(g)
                              .type("Director")
                              .property("name", "James Cameron")
                              .build();
                        
    // adding the director to the graph.
    director = g.add(director);
    
    // creating a "directed" link between the director
    // and the movie
    Edge directed = SimpleEdge.builder(g)
                              .fromNodeId(director.id())
                              .type("directed")
                              .toNodeId(movie.id())
                              .build();
    
    Assert.assertEquals(movie.id(), directed.to().id());
    Assert.assertEquals(director.id(), directed.from().id());
    
	}
	

}
