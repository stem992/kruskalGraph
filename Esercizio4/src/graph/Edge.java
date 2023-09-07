package graph;

public class Edge<V, E extends Comparable<E>> implements Comparable<Edge<V, E>>{
	private V source;
	private V destination;
	private E weight;
	private boolean crossed;

/*
* This method is the constructor of the edges
*
* @param  source from which edge starts
* @param  destination where the edge arrives
* @param  weight generic weight of the edge
*
 */
	public Edge(V source, V destination, E weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public V source(){ return source; }

	public V destination(){ return destination; }

	public E weight(){ return weight; }

	public void crossed(boolean t){ crossed = t; }

	public boolean crossed(){ return crossed; }
	
	
	public boolean equals(Object obj){
		boolean output = obj != null && obj instanceof Edge<?, ?>;
		if(output){
			Edge<?, ?> tmp = (Edge<?, ?>) obj;
			output = weight.equals(tmp.weight()) && source.equals(tmp.source()) &&
					destination.equals(tmp.destination());
		}
		return output;
	}

	public int compareTo(Edge<V, E> obj){
		return weight.compareTo(obj.weight());
	}

}