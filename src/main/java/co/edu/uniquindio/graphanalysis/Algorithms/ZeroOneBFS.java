package co.edu.uniquindio.graphanalysis.Algorithms;

// Java Program to implement 0-1 BFS
import co.edu.uniquindio.graphanalysis.TestCasesGenerator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class ZeroOneBFS {
    private static class Node {
        int to; // the ending vertex
        int weight; // the weight of the edge

        public Node(int to, int wt) {
            this.to = to;
            this.weight = wt;
        }
    }

    private static final int numVertex = 10000;
    private ArrayList<Node>[] edges = new ArrayList[numVertex];

    public ZeroOneBFS() {
        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<Node>();
        }
    }

    public void addEdge(int u, int v, int wt) {
        edges[u].add(edges[u].size(), new Node(v, wt));
        edges[v].add(edges[v].size(), new Node(u, wt));
    }

    public void zeroOneBFS(int src, int numVertices) {

        // initialize distances from given source
        int[] dist = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        // double ended queue to do BFS
        Deque<Integer> queue = new ArrayDeque<Integer>();
        dist[src] = 0;
        queue.addLast(src);

        while (!queue.isEmpty()) {
            int v = queue.removeFirst();
            for (int i = 0; i < edges[v].size(); i++) {

                // checking for optimal distance
                if (dist[edges[v].get(i).to] >
                        dist[v] + edges[v].get(i).weight) {

                    // update the distance
                    dist[edges[v].get(i).to] =
                            dist[v] + edges[v].get(i).weight;

                    // put 0 weight edges to front and 1
                    // weight edges to back so that vertices
                    // are processed in increasing order of weight
                    if (edges[v].get(i).weight == 0) {
                        queue.addFirst(edges[v].get(i).to);
                    } else {
                        queue.addLast(edges[v].get(i).to);
                    }
                }
            }
        }

        //imprime las distancias del origen a todos
        for (int i = 0; i < dist.length; i++) {
            System.out.print(dist[i] + " \n");
        }
    }

    public static void main(String[] args) {
//    	Random random = new Random();
//        int[][] matriz = new int[10][10];
//        for (int i = 0; i < 10; i++){
//            for (int j = 0; j < 10; j++){
//                matriz[i][j] = random.nextInt(100) + 1;
//            }
//        }
    	
//    	int limite = loadedGraph.length - (loadedGraph.length % 3);
//      ZeroOneBFS graph = new ZeroOneBFS();
//      int[] listafrom = new int[loadedGraph.length/3];
//      int[] listato = new int[loadedGraph.length/3];
//      int[] listawt = new int[loadedGraph.length/3];
//      int contador1 = 0;
//      int contador2 = 0;
//      int contador3 = 0;
//
//    //crea las asociaciones entre nodos
//      for (int i = 0; i < limite; i++){
//      	for (int j = 0; j < limite; j++){
//      		if(j % 2 == 0) {
//      			listawt[contador3] = loadedGraph[i][j];
//      			contador3++;
//      			break;
//      		}
//      		if(j % 3 == 0) {
//      			listato[contador2] = loadedGraph[i][j];
//      			contador2++;
//      			break;
//      		}
//      		else {
//      			listafrom[contador1] = loadedGraph[i][j];
//      			contador1++;
//      		}
//      	}
//      }
        
        int tam = 1300;
    	int[][] loadedGraph = TestCasesGenerator.loadGraphFromFile("graph" + tam +".txt", tam);
    	int v = loadedGraph.length;
    	ZeroOneBFS graph = new ZeroOneBFS();
    	
    	for(int i = 0; i < v; i++) {
    		for(int j = 0; j < v; j++) {
    			graph.addEdge(i, j, loadedGraph[i][j]);
        	}
    	}
    	
        long startTime = System.currentTimeMillis();
        graph.zeroOneBFS(0, tam); 
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.err.println("Tiempo de ejecución: " + duration + " ms");
        
        // Save the result
        // Change the file name, according to the size of the graph
        TestCasesGenerator.saveResult(duration, 10, tam + ".txt");
    }
}