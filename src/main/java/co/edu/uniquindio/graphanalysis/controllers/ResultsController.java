package co.edu.uniquindio.graphanalysis.controllers;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    @FXML
    private BarChart<String, Number> resultsBarChart;

    @FXML
    private Button BtnEjecutar;

    @FXML
    private ComboBox<String> comBoxTamanio;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> tamanios = FXCollections.observableArrayList(
                "1024", "2048","4096","8192");
        // TODO - Get the results from the test cases and add them to the chart
        comBoxTamanio.setItems(tamanios);
        // TODO - Remove this dummy data, it's just for testing purposes

    }

    @FXML
    void onEjecutar(ActionEvent event) {
        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        String name = comBoxTamanio.getValue();
        String[] nombres = {"Dijkstra", "Bellman-Ford", "Floyd-Warshall", "Johnson", "Directed Acyclic", "Dials",
                "Multistage", "Unweighted", "Weight Cycle (Karps)", "BFS", "Find Minimum", "Bidirectional Search"};
        int[] resultados = TestCasesGenerator.deserializarArreglo(name+".txt");
        int[] algoritmos = new int[24];
        int contador = 0;
        int contador2 = 0;

        //ciclo para inicializar por cada valor su algoritmo
        for (int i = 0; i < algoritmos.length; i++){
            if(i % 2 == 0){ //numero algoritmo
                algoritmos[i] = contador;
            }
            else{ //valor
                algoritmos[i] = resultados[contador2];
                contador++;
                contador2++;
            }
        }
        ordenar(resultados);

        series1.setName("TC1");
        series1.getData().clear();

        resultsBarChart.getData().clear();

        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[0]), resultados[0]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[1]), resultados[1]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[2]), resultados[2]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[3]), resultados[3]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[4]), resultados[4]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[5]), resultados[5]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[6]), resultados[6]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[7]), resultados[7]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[8]), resultados[8]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[9]), resultados[9]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[10]), resultados[10]));
        series1.getData().add(new XYChart.Data<>(obtenerAlgoritmo(algoritmos, nombres, resultados[11]), resultados[11]));

        resultsBarChart.getData().addAll(series1);
        // Add the series to your chart

        for (XYChart.Data<String, Number> data : series1.getData()) {
            Text dataText = new Text(data.getYValue().toString());
            StackPane.setAlignment(dataText, Pos.BOTTOM_CENTER);
            StackPane bar = (StackPane) data.getNode();
            bar.getChildren().add(dataText);
        }

        /*
        int minValue = Arrays.stream(resultados).min().getAsInt();
        int maxValue = Arrays.stream(resultados).max().getAsInt();

        resultsBarChart.getYAxis().setAutoRanging(false);
        ((NumberAxis) resultsBarChart.getYAxis()).setLowerBound(minValue);
        ((NumberAxis) resultsBarChart.getYAxis()).setUpperBound(maxValue);*/
    }

    public static String obtenerAlgoritmo(int[] algoritmos, String[] nombres, int valor) {
        int algoritmo = 0;
        for (int i = 0; i < algoritmos.length; i++){
            if(algoritmos[i] == valor){
                algoritmo = algoritmos[i-1];
            }
        }
        return nombres[algoritmo];
    }

        public static void ordenar(int[] arreglo) {
            int n = arreglo.length;

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (arreglo[j] > arreglo[j + 1]) {
                        int temp = arreglo[j];
                        arreglo[j] = arreglo[j + 1];
                        arreglo[j + 1] = temp;
                    }
                }
            }
        }
}
