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
       TestCasesGenerator testCasesGenerator = new TestCasesGenerator(10000); // Tamaño del grafo
        int[][] graph = testCasesGenerator.generateGraph();
        testCasesGenerator.saveGraphToFile(graph, "graph10000.txt"); // Nombre del archivo

       /* int[] arregloOriginal = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120};

        // Serialización del arreglo y escritura en un archivo de texto
        serializarArreglo("500.txt", arregloOriginal);*/
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

    public static void serializarArreglo(String nombreArchivo, int[] arreglo) {
        try {
            FileWriter fileWriter = new FileWriter("resultados/"+nombreArchivo);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int numero : arreglo) {
                bufferedWriter.write(Integer.toString(numero));
                bufferedWriter.newLine(); // Agregar una nueva línea para separar los números
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] deserializarArreglo(String nombreArchivo) {
        int[] arregloLeido = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("resultados/"+nombreArchivo));
            String linea;
            int indice = 0;

            while ((linea = bufferedReader.readLine()) != null) {
                if (arregloLeido == null) {
                    arregloLeido = new int[1];
                } else {
                    int[] nuevoArreglo = new int[indice + 1];
                    System.arraycopy(arregloLeido, 0, nuevoArreglo, 0, arregloLeido.length);
                    arregloLeido = nuevoArreglo;
                }

                arregloLeido[indice] = Integer.parseInt(linea);
                indice++;
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arregloLeido;
    }


}
