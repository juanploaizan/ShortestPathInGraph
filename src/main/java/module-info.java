module co.edu.uniquindio.graphanalysis {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens co.edu.uniquindio.graphanalysis to javafx.fxml;
    exports co.edu.uniquindio.graphanalysis;
    exports co.edu.uniquindio.graphanalysis.controllers;
    opens co.edu.uniquindio.graphanalysis.controllers to javafx.fxml;
}