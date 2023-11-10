package co.edu.uniquindio.graphanalysis.Algorithms;

//12
// Java program for Bidirectional BFS search
// to check path between two vertices
import java.io.*;
import java.util.*;

// class representing undirected graph
// using adjacency list
class Graph {
    // number of nodes in graph
    private int V;

    // Adjacency list
    private LinkedList<Integer>[] adj;

    // Constructor
    @SuppressWarnings("unchecked") public Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
            adj[i] = new LinkedList<Integer>();
    }

    // Method for adding undirected edge
    public void addEdge(int u, int v)
    {
        adj[u].add(v);
        adj[v].add(u);
    }

    // Method for Breadth First Search
    public void bfs(Queue<Integer> queue, Boolean[] visited,
                    int[] parent)
    {
        int current = queue.poll();
        for (int i : adj[current]) {
            // If adjacent vertex is not visited earlier
            // mark it visited by assigning true value
            if (!visited[i]) {
                // set current as parent of this vertex
                parent[i] = current;

                // Mark this vertex visited
                visited[i] = true;

                // Push to the end of queue
                queue.add(i);
            }
        }
    }

    // check for intersecting vertex
    public int isIntersecting(Boolean[] s_visited,
                              Boolean[] t_visited)
    {
        for (int i = 0; i < V; i++) {
            // if a vertex is visited by both front
            // and back BFS search return that node
            // else return -1
            if (s_visited[i] && t_visited[i])
                return i;
        }
        return -1;
    }

    // Print the path from source to target
    public void printPath(int[] s_parent, int[] t_parent,
                          int s, int t, int intersectNode)
    {
        LinkedList<Integer> path
                = new LinkedList<Integer>();
        path.add(intersectNode);
        int i = intersectNode;
        while (i != s) {
            path.add(s_parent[i]);
            i = s_parent[i];
        }
        Collections.reverse(path);
        i = intersectNode;
        while (i != t) {
            path.add(t_parent[i]);
            i = t_parent[i];
        }

        System.out.println("*****Path*****");
        for (int it : path)
            System.out.print(it + " ");
        System.out.println();
    }

    // Method for bidirectional searching
    public int biDirSearch(int s, int t)
    {
        // Booleanean array for BFS started from
        // source and target(front and backward BFS)
        // for keeping track on visited nodes
        Boolean[] s_visited = new Boolean[V];
        Boolean[] t_visited = new Boolean[V];

        // Keep track on parents of nodes
        // for front and backward search
        int[] s_parent = new int[V];
        int[] t_parent = new int[V];

        // queue for front and backward search
        Queue<Integer> s_queue = new LinkedList<Integer>();
        Queue<Integer> t_queue = new LinkedList<Integer>();

        int intersectNode = -1;

        // necessary initialization
        for (int i = 0; i < V; i++) {
            s_visited[i] = false;
            t_visited[i] = false;
        }

        s_queue.add(s);
        s_visited[s] = true;

        // parent of source is set to -1
        s_parent[s] = -1;

        t_queue.add(t);
        t_visited[t] = true;

        // parent of target is set to -1
        t_parent[t] = -1;

        while (!s_queue.isEmpty() && !t_queue.isEmpty()) {
            // Do BFS from source and target vertices
            bfs(s_queue, s_visited, s_parent);
            bfs(t_queue, t_visited, t_parent);

            // check for intersecting vertex
            intersectNode
                    = isIntersecting(s_visited, t_visited);

            // If intersecting vertex is found
            // that means there exist a path
            if (intersectNode != -1) {
                System.out.printf(
                        "Path exist between %d and %d\n", s, t);
                System.out.printf("Intersection at: %d\n",
                        intersectNode);

                // print the path and exit the program
                printPath(s_parent, t_parent, s, t,
                        intersectNode);
                System.exit(0);
            }
        }
        return -1;
    }
}

public class Bidirectional {
    // Driver code
    public static void main(String[] args)
    {
        // no of vertices in graph
        int n = 15;

        // source vertex
        int s = 0;

        // target vertex
        int t = 14;

        // create a graph given in above diagram
        Graph g = new Graph(n);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(3, 5);
        g.addEdge(4, 6);
        g.addEdge(5, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(8, 10);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(10, 13);
        g.addEdge(10, 14);
        if (g.biDirSearch(s, t) == -1)
            System.out.printf(
                    "Path don't exist between %d and %d", s, t);
    }
}

// This code is contributed by cavi4762.

