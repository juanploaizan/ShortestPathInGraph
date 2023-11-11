package co.edu.uniquindio.graphanalysis.Algorithms;
// 5 - Shortest Path in Directed Acyclic Graph
//Obtenido: https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
//Modificado con ChatGPT

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;

import java.util.*;
public class DirectedAcyclic {

        static final int INF = Integer.MAX_VALUE;

        class Graph {
            private int V;
            private int[][] adjMatrix;

            Graph(int v) {
                V = v;
                adjMatrix = new int[V][V];
            }

            void setEdge(int u, int v, int weight) {
                adjMatrix[u][v] = weight;
            }

            void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
                visited[v] = true;
                for (int i = 0; i < V; i++) {
                    if (adjMatrix[v][i] != 0 && !visited[i]) {
                        topologicalSortUtil(i, visited, stack);
                    }
                }
                stack.push(v);
            }

            void shortestPath(int s) {
                Stack<Integer> stack = new Stack<>();
                int[] dist = new int[V];

                boolean[] visited = new boolean[V];
                for (int i = 0; i < V; i++) {
                    visited[i] = false;
                }

                for (int i = 0; i < V; i++) {
                    if (!visited[i]) {
                        topologicalSortUtil(i, visited, stack);
                    }
                }

                Arrays.fill(dist, INF);
                dist[s] = 0;

                while (!stack.isEmpty()) {
                    int u = stack.pop();

                    for (int v = 0; v < V; v++) {
                        if (dist[u] != INF && adjMatrix[u][v] != 0) {
                            if (dist[v] > dist[u] + adjMatrix[u][v]) {
                                dist[v] = dist[u] + adjMatrix[u][v];
                            }
                        }
                    }
                }

                System.out.println("Following are shortest distances from source " + s);
                for (int i = 0; i < V; i++) {
                    if (dist[i] == INF)
                        System.out.print("INF ");
                    else
                        System.out.print(dist[i] + " ");
                }
            }
        }

        Graph newGraph(int number) {
            return new Graph(number);
        }

        public static void main(String[] args) {
            DirectedAcyclic t = new DirectedAcyclic();


            int[][] adjacencyMatrix = TestCasesGenerator.loadGraphFromFile("graph8192.txt",8192);

            long tiempoInicio = System.currentTimeMillis();

            Graph g = t.newGraph(adjacencyMatrix.length);

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrix.length; j++) {
                    if (adjacencyMatrix[i][j] != 0) {
                        g.setEdge(i, j, adjacencyMatrix[i][j]);
                    }
                }
            }

            int s = 1;
            System.out.println("Following are shortest distances from source " + s);
            g.shortestPath(s);

            long tiempoFin = System.currentTimeMillis();
            long tiempoTotal = tiempoFin - tiempoInicio;

            System.out.println("Tiempo total para la ejecución: " + tiempoTotal);

            TestCasesGenerator.saveResult(tiempoTotal, 5, "1000.txt");

        }
    }

