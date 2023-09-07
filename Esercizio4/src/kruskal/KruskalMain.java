package kruskal;

import graph.Graph;
import graph.Edge;
import graph.GraphException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KruskalMain{
	  public static void main (String[]args)throws IOException, GraphException, KruskalException{
		  
		  try {
			  String path = "italian_dist_graph.csv";
		      Graph<String,Double> graph = loadgraph(path);
		      
		      Kruskal<String,Double> kruskal = new Kruskal <>(graph);
		      ArrayList<Edge<String,Double>> ar = kruskal.mstKruskal();

              long timestampBeforeExecution = System.currentTimeMillis();

              // Execute the algorithm
              System.out.println("Kruskal algorithm is working..");

              long timestampAfterExecution = System.currentTimeMillis();

		      System.out.println("number of vertex: " + graph.sizeVertex());
		      System.out.println("number of edge: " + graph.sizeEdge()/2);
		      System.out.println("total weight: "+  String.format("%.3f", countWeight(ar)/1000) + " km");

              System.out.println("\nExecution ended in " + 
            		  (timestampAfterExecution - timestampBeforeExecution) + " milliseconds.\n");
		           
          } catch (IOException e) {
              System.err.println("CSV file loading error: " + e.getMessage());
          }

	  }
	  

	  private static Graph<String,Double> loadgraph (String path)throws IOException, GraphException{
	    Graph<String, Double> g = new Graph<String, Double>(false); 
	    System.out.println("\nLoading data from file...\n");
	    
	    try (BufferedReader reader = new BufferedReader( new FileReader (path))){
		    String line;
		    while((line = reader.readLine())!=null){
		      String param[] = line.split(",");	
		      String start = param[0].trim();
		      String end = param[1].trim();
		      g.addVertex (start);
		      g.addVertex (end);
		      Double weight = Double.parseDouble(param[2].trim());
		      g.addEdge (start,end,weight);
		    }
		    reader.close();
	    } catch (Exception e) {
	    }
	    System.out.println("\nData loaded\n");
	    return g;
	  }
	  
	  private static double countWeight(ArrayList<Edge<String, Double>> array){
	    double weight = 0.0f;
	    for(int i = 0; i < array.size(); i++)
	    	weight += array.get(i).weight();
	    return weight;
	  }
}