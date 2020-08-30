package org.example;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.DBconnection.DBhandler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {


    @FXML
    private TextField name;

    @FXML
    private TextField surname;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField password;

    @FXML
    private Button register;

    @FXML
    private ImageView progress;

    @FXML
    private Button login;

    private Connection connection;
    private DBhandler dBhandler;
    private PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        progress.setVisible(false);

        dBhandler = new DBhandler();
    }
   /* @FXML
    private void switchToLoginWindow() throws IOException {
        App.setRoot("login");
    }*/

    @FXML
    public void register(ActionEvent e) {
    progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(e1 -> {
            System.out.println("Register Successfull");
        });
        pt.play();


        // Saving data

        String insert = "INSERT INTO customers(name, surname, phoneNumber, password)"
                + "VALUES (?,?,?,?)";

        connection = dBhandler.getConnection();

        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            pst.setString(1, name.getText());
            pst.setString(2, surname.getText());
            pst.setString(3, phoneNumber.getText());
            pst.setString(4, password.getText());

            pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void login(ActionEvent e1) throws IOException {

        register.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);

        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }



}
