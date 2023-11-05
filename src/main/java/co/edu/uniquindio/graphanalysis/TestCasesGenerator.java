package co.edu.uniquindio.graphanalysis;

import java.io.*;
import java.util.Random;

public class TestCasesGenerator {

    private static final System.Logger LOGGER = System.getLogger(TestCasesGenerator.class.getName());

    /**
     * Main method
     * @param args the arguments
     */
    public static void main(String[] args) {
        TestCasesGenerator testCasesGenerator = new TestCasesGenerator(100); // Tamaño del grafo
        int[][] graph = testCasesGenerator.generateGraph();
        testCasesGenerator.saveGraphToFile(graph, "graph1.txt"); // Nombre del archivo
    }

    private final int size;

    /**
     * Constructor
     * @param size the size of the graph
     */
    public TestCasesGenerator(int size) {
        this.size = size;
    }

    /**
     * Generates a graph with the given size, with random weights between 0 and 100
     * @return the graph
     */
    public int[][] generateGraph() {
        int[][] graph = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            graph[i][i] = 0;
            for (int j = i + 1; j < size; j++) {
                int weight = random.nextInt(101);
                graph[i][j] = weight;
                graph[j][i] = weight;
            }
        }
        return graph;
    }

    /**
     * Saves the graph to a file
     * @param graph the graph to save
     * @param fileName the name of the file
     */
    public void saveGraphToFile(int[][] graph, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("test_cases/"+fileName))) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.print(graph[i][j]);
                    if (j != size - 1) {
                        writer.print(" ");
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Error saving graph to file", e);
        }
    }

    /**
     * Loads a graph from a file
     * @param fileName the name of the file
     * @param size the size of the graph
     * @return the graph
     */
    public static int[][] loadGraphFromFile(String fileName, int size) {
        int[][] graph = new int[size][size];
        try (BufferedReader br = new BufferedReader(new FileReader("test_cases/"+fileName))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < size) {
                String[] values = line.split(" ");
                for (int col = 0; col < size && col < values.length; col++) {
                    graph[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
        } catch (IOException e) {
            LOGGER.log(System.Logger.Level.ERROR, "Error loading graph from file", e);
        }
        return graph;
    }

}
