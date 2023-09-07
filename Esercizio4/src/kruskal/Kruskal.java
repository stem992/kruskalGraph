package kruskal;
import unionfindset.UnionFindSet;

import java.util.ArrayList;

import graph.Edge;
import graph.Graph;
import graph.GraphException;

public class Kruskal< V, E extends Comparable<E>>{
    public Graph<V, E> graph;

    public Kruskal (Graph<V, E> graph){
      this.graph = graph;
    }

    public ArrayList<Edge <V,E>> mstKruskal() throws GraphException, KruskalException{
        if(graph == null)
            throw new KruskalException("kruskal: graph parameter cannot be null");
        
      UnionFindSet<V> unionfindset = new UnionFindSet<>();
      ArrayList<Edge<V,E>> arrayEdges = new ArrayList<>(); 
      
      ArrayList<V> vertex = graph.getAllVertex();
      unionfindset.makeSet(vertex);
      
      ArrayList<Edge<V,E>> edge = graph.getAllEdge();
      edge.sort(null); 

       for(int i=0; i<edge.size(); i++){
        Edge<V,E> element = edge.get(i);
        V s = element.source();
        V d = element.destination();
        
        if(s == null || d == null)throw new KruskalException("Internal error\n");
        if((unionfindset.findSet(s)) != (unionfindset.findSet(d))){
          arrayEdges.add(element); 
          unionfindset.union(s,d);
        }
       
      }
      return arrayEdges;
    }
}