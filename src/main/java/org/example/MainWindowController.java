package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainWindowController implements Initializable {

    @FXML
    private void switchToLoginWindow() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void switchToMechanicWindow() throws IOException {
        App.setRoot("mechanicWindow");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
