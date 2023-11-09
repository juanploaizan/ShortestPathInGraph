package co.edu.uniquindio.graphanalysis.controllers;

import co.edu.uniquindio.graphanalysis.TestCasesGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
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
    private StackedBarChart<String, Number> resultsBarChart;

    @FXML
    private Button BtnEjecutar;

    @FXML
    private ComboBox<String> comBoxTamanio;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<String> tamanios = FXCollections.observableArrayList(
                "250", "500","750","1000");
        // TODO - Get the results from the test cases and add them to the chart
        comBoxTamanio.setItems(tamanios);
        // TODO - Remove this dummy data, it's just for testing purposes

    }


    @FXML
    void onEjecutar(ActionEvent event) {
        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();

        String name = comBoxTamanio.getValue();
        int[] resultados = TestCasesGenerator.deserializarArreglo(name+".txt");

        series1.setName("TC1");
        series1.getData().clear();

        resultsBarChart.getData().clear();

        series1.getData().add(new XYChart.Data<>("Dijkstra", resultados[0]));
        series1.getData().add(new XYChart.Data<>("Bellman-Ford", resultados[1]));
        series1.getData().add(new XYChart.Data<>("Floyd-Warshall", resultados[2]));
        series1.getData().add(new XYChart.Data<>("Johnson", resultados[3]));
        series1.getData().add(new XYChart.Data<>("Directed Acyclic", resultados[4]));
        series1.getData().add(new XYChart.Data<>("Dials", resultados[5]));
        series1.getData().add(new XYChart.Data<>("Multistage", resultados[6]));
        series1.getData().add(new XYChart.Data<>("Unweighted", resultados[7]));
        series1.getData().add(new XYChart.Data<>("Weight Cycle", resultados[8]));
        series1.getData().add(new XYChart.Data<>("BFS", resultados[9]));
        series1.getData().add(new XYChart.Data<>("Find Minimum", resultados[10]));
        series1.getData().add(new XYChart.Data<>("Bidirectional Search", resultados[11]));

        resultsBarChart.getData().addAll(series1); // Add the series to your chart

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



}
