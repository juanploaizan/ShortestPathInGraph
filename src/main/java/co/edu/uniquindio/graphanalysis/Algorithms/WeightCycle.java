package co.edu.uniquindio.graphanalysis.Algorithms;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;

import java.util.Vector;

// 9.- Karp’s minimum mean (or average) weight cycle algorithm
//Obtenido: https://www.geeksforgeeks.org/karps-minimum-mean-average-weight-cycle-algorithm/
//Modificado con ChatGPT

public class WeightCycle {
    static int V; // Número de vértices (será determinado por el tamaño de la matriz)

    // a struct to represent edges
    static class Edge {
        int from, weight;

        Edge(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }
    }

    // vector to store edges
    @SuppressWarnings("unchecked")
    static Vector<Edge>[] edges;

    static void addedge(int u, int v, int w) {
        edges[v].add(new Edge(u, w));
    }

    static void shortestpath(int[][] dp) {
        // initializing all distances as -1
        for (int i = 0; i <= V; i++)
            for (int j = 0; j < V; j++)
                dp[i][j] = -1;

        // shortest distance from the first vertex
        // to itself consisting of 0 edges
        dp[0][0] = 0;

        // filling up the dp table
        for (int i = 1; i <= V; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < edges[j].size(); k++) {
                    if (dp[i - 1][edges[j].elementAt(k).from] != -1) {
                        int curr_wt = dp[i - 1][edges[j].elementAt(k).from] +
                                edges[j].elementAt(k).weight;
                        if (dp[i][j] == -1)
                            dp[i][j] = curr_wt;
                        else
                            dp[i][j] = Math.min(dp[i][j], curr_wt);
                    }
                }
            }
        }
    }

    static double minAvgWeight() {
        int[][] dp = new int[V + 1][V];
        shortestpath(dp);

        // array to store the avg values
        double[] avg = new double[V];
        for (int i = 0; i < V; i++)
            avg[i] = -1;

        // Compute average values for all vertices using
        // weights of shortest paths stored in dp.
        for (int i = 0; i < V; i++) {
            if (dp[V][i] != -1) {
                for (int j = 0; j < V; j++)
                    if (dp[j][i] != -1)
                        avg[i] = Math.max(avg[i],
                                ((double) dp[V][i] -
                                        dp[j][i]) / (V - j));
            }
        }

        // Find the minimum value in avg[]
        double result = avg[0];
        for (int i = 0; i < V; i++)
            if (avg[i] != -1 && avg[i] < result)
                result = avg[i];

        return result;
    }

    public static void main(String[] args) {

        int[][] adjacencyMatrix = TestCasesGenerator.loadGraphFromFile("graph8192.txt",8192);

        long tiempoInicio = System.currentTimeMillis();

        V = adjacencyMatrix.length;
        edges = new Vector[V];
        for (int i = 0; i < V; i++) {
            edges[i] = new Vector<>();
        }

        // Initialize the edges based on the adjacency matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    addedge(i, j, adjacencyMatrix[i][j]);
                }
            }
        }

        System.out.printf("%.5f", minAvgWeight());

        long tiempoFin = System.currentTimeMillis();
        long tiempoTotal = tiempoFin - tiempoInicio;

        System.out.println("Tiempo total para la ejecución: " + tiempoTotal);

        TestCasesGenerator.saveResult(tiempoTotal, 9, "1000.txt");

    }
}
