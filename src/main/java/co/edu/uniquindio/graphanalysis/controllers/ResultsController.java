package co.edu.uniquindio.graphanalysis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {
    @FXML
    private StackedBarChart<String, Number> resultsBarChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO - Get the results from the test cases and add them to the chart

        // TODO - Remove this dummy data, it's just for testing purposes
        final XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("TC1");
        series1.getData().add(new XYChart.Data<>("Dijkstra", 25601.34));
        series1.getData().add(new XYChart.Data<>("Bellman-Ford", 10000.00));
        series1.getData().add(new XYChart.Data<>("Floyd-Warshall", 20148.82));
        series1.getData().add(new XYChart.Data<>("Johnson", 35407.15));
        series1.getData().add(new XYChart.Data<>("Dials", 12000.00));
        resultsBarChart.getData().addAll(series1); // Add the series to your chart
    }
}
