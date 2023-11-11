package co.edu.uniquindio.graphanalysis.Algorithms;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;
// 7
// Java program to find shortest distance
// in a multistage graph.

class Multistage {

    static int N = 8192;
    static int INF = 99999;

    // Returns shortest distance from 0 to
    // N-1.
    public static int shortestDist(int[][] graph){
        // dist[i] is going to store shortest
        // distance from node i to node N-1.
        int[] dist = new int[N];

        dist[N - 1] = 0;

        // Calculating shortest path for
        // rest of the nodes
        for (int i = N - 2; i >= 0; i--) {

            // Initialize distance from i to
            // destination (N-1)
            dist[i] = INF;

            // Check all nodes of next stages
            // to find shortest distance from
            // i to N-1.
            for (int j = i; j < N; j++) {
                // Reject if no edge exists
                if (graph[i][j] == INF) {
                    continue;
                }

                // We apply recursive equation to
                // distance to target through j.
                // and compare with minimum distance
                // so far.
                dist[i] = Math.min(dist[i],
                        graph[i][j] + dist[j]);
            }
        }

        return dist[0];
    }

    // Driver code
    public static void main(String[] args) {
        int[][] loadedGraph = TestCasesGenerator.loadGraphFromFile("graph"+N+".txt", N);
        int[][] graph = new int[N][N];

        for(int i=0; i<N; i++) {
            graph[i][i] = 0;
            for (int j = i + 1; j < N; j++) {
                if (loadedGraph[i][j] == 0) {
                    graph[i][j] = INF;
                    graph[j][i] = INF;
                } else {
                    graph[i][j] = loadedGraph[i][j];
                    graph[j][i] = loadedGraph[j][i];
                }
            }
        }

        int[][] graph2 = new int[][] {
                { INF, 1, 2, 5, INF, INF, INF, INF },
                { INF, INF, INF, INF, 4, 11, INF, INF },
                { INF, INF, INF, INF, 9, 5, 16, INF },
                { INF, INF, INF, INF, INF, INF, 2, INF },
                { INF, INF, INF, INF, INF, INF, INF, 18 },
                { INF, INF, INF, INF, INF, INF, INF, 13 },
                { INF, INF, INF, INF, INF, INF, INF, 2 },
                { INF, INF, INF, INF, INF, INF, INF, INF }
        };

        long startTime = System.currentTimeMillis();
            System.out.println("Distancia mas corta del nodo 1 al "+N+": "+shortestDist(graph));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Duration: " + duration + "ms");

        // Save the result
        TestCasesGenerator.saveResult(duration, 7, N+".txt");
    }
}