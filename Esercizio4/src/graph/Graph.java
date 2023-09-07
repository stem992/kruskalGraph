package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<V, E extends Comparable<E>>{
	private HashMap<V, ArrayList<Edge<V, E>>> graph;
	public  boolean direct;

/*
 * Creation of an empty graph
 * @param  direct: is TRUE if the Graph is direct, FALSE otherwise.
 * 
 */
	public Graph(boolean direct){
		graph = new HashMap<>();
		this.direct = direct;
	}

/*
 * Addition of a vertex
 * @param v: is TRUE if the parameter v is added to the graph, FALSE otherwise
 * 
 */
	public void addVertex(V v) throws GraphException {
		    if (!containsVertex(v))
		        graph.put(v, new ArrayList<>());  
	}

/*
 * Addition of a edge
 * @param a: source node of the edge
 * @param b : destination node of the edge
 * @param weight
 * 
 */
	
    public void addEdge(V a, V b, E weight) throws GraphException {

        if (a.equals(b)) 
        		throw new GraphException("method addEdge: error same parameter");
        
        if (!graph.containsKey(a)) 
            	throw new GraphException("method addEdge: error a parameter cannot be null");
            	
        if (!graph.containsKey(b)) 
        		throw new GraphException("method addEdge: error b parameter cannot be null");
        	
        	else {
                if (!containsEdge(a, b)) {
                    graph.get(a).add(new Edge<V, E>(a, b, weight));

                    if (!direct) {
                        graph.get(b).add(new Edge<V, E>(b, a, weight));
                    }
                }
            
        	}
    }
 
/*
 * Check if graph is directed
 * @return TRUE if the graph is Direct, FALSE otherwise
 */
	public boolean isDirect(){
		return this.direct;
	}

/*
 * Check if the graph contains a given vertex
 * @param a: return TRUE if the element is contained in the graph, FALSE otherwise
 * 
 */
	public boolean containsVertex(V a) throws GraphException{
		return graph.containsKey(a);
	}
	
/*
 * Check if the graph contains a given edge
 * @param a: source node of the edge
 * @param b : destination node of the edge
 * @return TRUE if the edge exists, FALSE otherwise
 * 
 */
	public boolean containsEdge(V a, V b) throws GraphException{
		ArrayList<Edge <V, E>> array = graph.get(a);
		
		boolean output = containsVertex(a) && containsVertex(b);
		if(output){
			boolean found = false;
			
			for(int i = 0; i < array.size() && !found; i++){
				V dest = array.get(i).destination();
				found = b.equals(dest);
			}
			output = found;
		}
		return output;
	}

/*
 * Deletion of a vertex
 * @param a: a is the element to delete
 * 
 */
	public void deleteVertex(V a) throws GraphException{
	
		if (!graph.containsKey(a)) 
			throw new GraphException("method deleteVertex: error same parameter"); 
		else {
			graph.remove(a);
			if (graph.size() > 0) {
		      for (V node : graph.keySet()) {
		        ArrayList<Edge<V,E>> list = graph.get(node);
		        
		        if (list != null) 
		          for (Edge<V,E> edge : list) 
		            if (edge.destination().equals(a)) list.remove(edge);
		
		      }
		    }
		}
	}
  
/*
 * Deletion of a edge
 * @param a: source of the edge to be deleted
 * @param b: destination of the edge to be deleted
 * 
 */
	public boolean deleteEdge(V a, V b) throws GraphException{
		if (a == null)
		      throw new GraphException("method deleteEdge: error a parameter cannot be null");
		if (b == null)
		      throw new GraphException("method deleteEdge: error b parameter cannot be null");
		
		boolean output = containsVertex(a) && containsVertex(b);
		if(output){
			output = rmvEdge(a, b);
			if(output && !direct){
				output = rmvEdge(b, a);
			}
		}
		return output;
	}

	private boolean rmvEdge(V a, V b) throws GraphException{
		if (a == null)
		      throw new GraphException("method rmvEdge: error a parameter cannot be null");
		if (b == null)
		      throw new GraphException("method rmvEdge: error b parameter cannot be null");
		
		ArrayList<Edge <V, E>> list = graph.get(a);
		boolean output = list == null;
		int i = 0;
		while(!output && i < list.size()){
			V tmp = list.get(i).destination();
			output = b.equals(tmp);
			if(!output)
				i++;
		}
		if(output && list != null)
			list.remove(i);
		return output;
	}

/*
 * Determine the number of vertex
 * 
 */
	public int sizeVertex(){
		return graph.size();
	}

/*
 * Determine the number of edge
 * 
 */
	public int sizeEdge(){
		int count = 0;
		for (V node : graph.keySet()) {
			ArrayList<Edge<V,E>> array = graph.get(node);
			count += array.size();
		}
		if(direct)
			return count;
		return count/2;
	  }

/*
 * Retrieving of graph vertex
 * @return list of all the vertex
 * 
 */
	public ArrayList<V> getAllVertex(){
		ArrayList<V> nodes = new ArrayList<>();
		if (graph.size() > 0)
		    for (V node : graph.keySet()) nodes.add(node);
		 return nodes;
		}	

/*
 * Retrieving of graph edge
 * @return list of all the edges
 * 
 */
	public ArrayList<Edge <V, E>> getAllEdge() throws GraphException{
		ArrayList<Edge<V, E>> out = new ArrayList<>();

        for (V node : graph.keySet()) {
            for (int i = 0; i < graph.get(node).size(); i++) {
                out.add(new Edge<V, E>(graph.get(node).get(i).source(), 
                		graph.get(node).get(i).destination(), graph.get(node).get(i).weight()));
            }
        }

        return out;
    }
		
/*
 * Retrieving adjacent vertex of a given vertex v
 * @return list of vertex to which the element is connected
 * 
 */
	public ArrayList<V> adjVertex(V v) throws GraphException{
		if (v == null)
		      throw new GraphException("method adjVertex: error a parameter cannot be null");
		
		ArrayList<V> out = null;
		if(v != null && containsVertex(v)){
			out = new ArrayList<>();
			ArrayList<Edge<V, E>> tmp = graph.get(v);
			for(int i = 0; i < tmp.size(); i++){
				V toAdd = tmp.get(i).destination();
				out.add(toAdd);
			}
		}
		return out;
	}

/*
 * Retrieving the label associated with a pair of edges (a,b)
 * @param a: source node of the edge
 * @param b : destination node of the edge
 * @return weight
 * 
 */
	public E getWeight(V a, V b) throws GraphException{
		if (a == null)
		      throw new GraphException("method getWeight: error a parameter cannot be null");
		if (b == null)
		      throw new GraphException("method getWeight: error b parameter cannot be null");
		
		boolean check = containsVertex(a) && containsVertex(b);
		E weight = null;
		if(check){
			ArrayList<Edge<V, E>> tmp = graph.get(a);
			boolean found = false;
			for(int i = 0; i < tmp.size() && !found; i++){
				found = tmp.get(i).destination() == b;
				if(found)
					weight = tmp.get(i).weight();
			}
		}
		return weight;
	}
}