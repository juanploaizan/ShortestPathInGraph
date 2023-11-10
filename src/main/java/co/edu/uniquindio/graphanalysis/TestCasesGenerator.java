package co.edu.uniquindio.graphanalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestCasesGenerator {

    private static final System.Logger LOGGER = System.getLogger(TestCasesGenerator.class.getName());

    /**
     * Main method
     */
    public static void main(String[] args) {

       TestCasesGenerator testCasesGenerator = new TestCasesGenerator(1000); // Tamaño del grafo
        int[][] graph = testCasesGenerator.generateGraph();
        testCasesGenerator.saveGraphToFile(graph, "graph1000.txt"); // Nombre del archivo

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
                double prob = random.nextDouble(); // Probabilidad entre 0 y 1
                if (prob < 0.85) {
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                } else {
                int weight = random.nextInt(100) + 1;
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                }
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

    // Esta función recibe un long y un int como parámetros
    // y guarda el valor del long en la línea indicada por el int
    // en un archivo txt, sin modificar las otras líneas
    public static void saveResult(long valor, int linea, String nombreArchivo) {
        // Se crea un objeto File para el archivo txt
        File archivo = new File("results/" + nombreArchivo);
        // Se crea una lista para almacenar las líneas del archivo
        List<String> lineas = new ArrayList<>();
        try {
            // Se crea un objeto Scanner para leer el archivo
            Scanner sc = new Scanner(archivo);
            // Se lee cada línea del archivo y se añade a la lista
            while (sc.hasNextLine()) {
                lineas.add(sc.nextLine());
            }
            // Se cierra el Scanner
            sc.close();
            // Se verifica que la línea indicada sea válida
            if (linea >= 1 && linea <= lineas.size()) {
                // Se convierte el valor del long a String
                String valorStr = String.valueOf(valor);
                // Se reemplaza la línea indicada por el valor
                lineas.set(linea - 1, valorStr);
                // Se crea un objeto PrintWriter para escribir el archivo
                PrintWriter pw = new PrintWriter(archivo);
                // Se escribe cada línea de la lista en el archivo
                for (String l : lineas) {
                    pw.println(l);
                }
                // Se cierra el PrintWriter
                pw.close();
                // Se muestra un mensaje de éxito
                System.out.println("Valor guardado correctamente.");
            } else {
                // Se muestra un mensaje de error
                System.out.println("Línea inválida.");
            }
        } catch (FileNotFoundException e) {
            // Se muestra un mensaje de excepción
            System.out.println("Archivo no encontrado.");
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
