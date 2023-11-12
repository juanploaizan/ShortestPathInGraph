package co.edu.uniquindio.graphanalysis;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TestCasesGenerator {

    private static final System.Logger LOGGER = System.getLogger(TestCasesGenerator.class.getName());

    /**
     * Main method
     */
    public static void main(String[] args) {
    	int tam = 4096;
       TestCasesGenerator testCasesGenerator = new TestCasesGenerator(tam); // Tamaño del grafo
        int[][] graph = testCasesGenerator.generateBinaryGraph();
        testCasesGenerator.saveGraphToFile(graph, "graph"+tam+".txt"); // Nombre del archivo

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
                } else if (j % 2 == 0){
                int weight = random.nextInt(1);
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                } else {
                	int weight = random.nextInt(100) + 1;
                    graph[i][j] = weight;
                    graph[j][i] = weight;
                }
            }
        }
        return graph;
    }
    public int[][] generateBinaryGraph() {
        int[][] graph = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            graph[i][i] = 0;
            for (int j = i + 1; j < size; j++) {
            	double prob = random.nextDouble();
            	if (prob < 0.002) {
                    graph[i][j] = 0;
                    graph[j][i] = 0;
                }else {
                	graph[i][j] = 1;
                	graph[j][i] = 1;
                }
            }
        }
        return graph;
    }
    
    public int[][] generateGraphWeight() {
        int[][] graph = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            graph[i][i] = 0;
            for (int j = i + 1; j < size; j++) {
                	int weight = random.nextInt(100)+6;
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
    
    public static int contarVertices(int[][] matriz) {
    	int limite = matriz.length - (matriz.length % 3); //hasta dónde itera
    	ArrayList<Integer> numeros = new ArrayList<Integer>();
    	int cantidad = 0;
    	
    	for (int i = 0; i < limite; i+=3){
            for (int j = 0; j < limite; j+=3){
            	boolean flag1 = false;
            	boolean flag2 = false;
            	int aux1 = matriz[i][j];
            	int aux2 = matriz[i+2][j+2];
            	//comprobar que el vertice no está ya
            	for(int x = 0; x < numeros.size(); x++) { 
            		if(aux1 == numeros.get(x) ) { //evalua si el from es un nodo existente
                		flag1 = true;
            		}
            		if(aux2 == numeros.get(x) ) { //evalua si el to es un nodo existente
            			flag2 = true;
            		}
            	}
            	if(flag1 != true) { //evalua si el from es un nodo existente
            		numeros.add(aux1);
            		cantidad++;
        		}
        		if(flag2 != true) { //evalua si el to es un nodo existente
        			numeros.add(aux2);
        			cantidad++;
        		}
            }
        }
    	return cantidad;
    }
    
    public static int contarVertices2(int[] matriz) {
    	int limite = matriz.length - (matriz.length % 3); //hasta dónde itera
    	ArrayList<Integer> numeros = new ArrayList<Integer>();
    	int cantidad = 0;
    	
    	for (int i = 0; i < limite; i+=3){
            	boolean flag1 = false;
            	boolean flag2 = false;
            	int aux1 = matriz[i];
            	int aux2 = matriz[i+1];
            	//comprobar que el vertice no está ya
            	for(int x = 0; x < numeros.size(); x++) { 
            		if(aux1 == numeros.get(x) ) { //evalua si el from es un nodo existente
                		flag1 = true;
            		}
            		if(aux2 == numeros.get(x) ) { //evalua si el to es un nodo existente
            			flag2 = true;
            		}
            	}
            	if(flag1 != true) { //evalua si el from es un nodo existente
            		numeros.add(aux1);
            		cantidad++;
        		}
        		if(flag2 != true) { //evalua si el to es un nodo existente
        			numeros.add(aux2);
        			cantidad++;
        		}
            }
    	return cantidad;
    }
    
    public static int encontrarMayorPeso(int[][] matriz) {
    	int limite = matriz.length; //hasta dónde itera
    	int mayor = 0;
    	for (int i = 0; i < limite; i++){
            for (int j = 0; j < limite; j++){
            	if(matriz[i][j] > mayor) {
            		mayor = matriz[i][j];
            	}
            }
        }
    	System.err.println(mayor);
    	return mayor;
    }
}
