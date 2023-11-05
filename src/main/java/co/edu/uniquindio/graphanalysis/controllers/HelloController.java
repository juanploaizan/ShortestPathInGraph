package co.edu.uniquindio.graphanalysis.controllers;

import co.edu.uniquindio.graphanalysis.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Pane helloPane;

    @FXML
    private Button executeButton;

    @FXML
    protected void executeTestCases() throws IOException {

        executeButton.setDisable(true);

        // TODO - Execute test cases

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("results-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Resultados de las pruebas");
        stage.setScene(scene);
        helloPane.getScene().getWindow().hide();
        stage.show();
    }
}