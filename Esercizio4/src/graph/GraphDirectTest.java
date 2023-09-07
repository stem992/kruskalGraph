package graph;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class GraphDirectTest {
  private Graph<String, Integer> directGraph;
  private Graph<String, Integer> undirectGraph;

  @Before
  public void initTest() {
    directGraph = new Graph<>(true);
    undirectGraph = new Graph<>(false);

    try {
      directGraph.addVertex("a");
      directGraph.addVertex("b");
      directGraph.addVertex("c");
      directGraph.addVertex("d");
      directGraph.addVertex("e");
      directGraph.addVertex("f");

      directGraph.addEdge("a", "b", 1); //(a b)
      directGraph.addEdge("b", "c", 2); //(b c)
      directGraph.addEdge("c", "d", 3); //(c d)
      directGraph.addEdge("a", "d", 4); //(a d)
      directGraph.addEdge("a", "c", 5); //(a c)
      directGraph.addEdge("a", "e", 6); //(a e)
      directGraph.addEdge("e", "f", 7); //(e f)
      
      undirectGraph.addVertex("x");
      undirectGraph.addVertex("y");
      undirectGraph.addEdge("x", "y", 8); //(x y)
    } catch (GraphException e) {
        System.err.println("Error: " + e.getMessage());
    }
  }

  @Test
  public void testGraphIsDirect() {
    assertTrue(directGraph.isDirect() == true);
    assertFalse(undirectGraph.isDirect() == true);
  }

  @Test
  public void testGraphContainsNodeIsTrue() {

    try {
      assertTrue(directGraph.containsVertex("a") == true);
      assertTrue(directGraph.containsVertex("b") == true);
      assertTrue(directGraph.containsVertex("c") == true);
      assertTrue(directGraph.containsVertex("d") == true);
      assertTrue(directGraph.containsVertex("e") == true);
      assertTrue(directGraph.containsVertex("f") == true);
      
    } catch (GraphException e) { 
    	System.err.println("testGraphContainsNodeIsTrue: "  + e.getMessage());
    	} 
  }

  @Test
  public void testGraphContainsNodeIsFalse() {

    try {
      assertFalse(directGraph.containsVertex("p") == true);
      assertFalse(directGraph.containsVertex("i") == true);
      
    } catch (GraphException e) { 
    	System.err.println("testGraphContainsNodeIsFalse: "  + e.getMessage()); } 
    
  }
   
  @Test
  public void testGraphContainsEdgeIsTrue() {

    try {
      assertTrue(directGraph.containsEdge("a", "b") == true);
      assertTrue(directGraph.containsEdge("a", "d") == true);
      assertTrue(directGraph.containsEdge("a", "c") == true);

    } catch (GraphException e) { 
    	System.err.println("testGraphContainsEdgeIsTrue: "  + e.getMessage()); 
    	}
  }

  @Test
  public void testGraphDeleteVertex() {

    try {
      directGraph.deleteVertex("a");
      assertFalse(directGraph.containsVertex("a"));
      assertFalse(directGraph.containsEdge("a", "b"));
      assertFalse(directGraph.containsEdge("c", "a"));

    } catch (GraphException e) { 
    	System.err.println("testGraphDeleteNode: "  + e.getMessage()); 
    	}
  }

  @Test
  public void testGraphDeleteEdge() {

    try { 
      directGraph.deleteEdge("a", "b");
      assertFalse(directGraph.containsEdge("a", "b"));

    } catch(GraphException e) { 
    	System.err.println("testGraphDeleteEdge: "  + e.getMessage()); 
    	}
  }

  @Test
  public void testGraphNumberOfVertex() {
    assertTrue(directGraph.sizeVertex() == 6);
  }

  @Test
  public void testGraphNumberOfEdges() {
    assertTrue(directGraph.sizeEdge() == 7);
  }

  @Test
  public void testGraphGetNodes()  {
    List<String> nodesDirectGraph = new ArrayList<String>();
    nodesDirectGraph.add("a");
    nodesDirectGraph.add("b");
    nodesDirectGraph.add("c");
    nodesDirectGraph.add("d");
    nodesDirectGraph.add("e");
    nodesDirectGraph.add("f");

    for (int i = 0; i < directGraph.sizeVertex(); i++) 
    	assertTrue(directGraph.getAllVertex().get(i).equals(nodesDirectGraph.get(i)));

    List<String> nodesUndirectGraph = new ArrayList<String>();
    nodesUndirectGraph.add("x");
    nodesUndirectGraph.add("y");
    
    for (int i = 0; i < undirectGraph.sizeVertex(); i++) 
    	assertTrue(undirectGraph.getAllVertex().get(i).equals(nodesUndirectGraph.get(i)));
   
  }

}
