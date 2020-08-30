package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.example.DBconnection.DBhandler;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {


    @FXML
    private Button changeTyres;

    private DBhandler dBhandler;
    private Connection connection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dBhandler = new DBhandler();
    }


    @FXML
    public void changeTyres(ActionEvent event) {

        String insert = "INSERT INTO bookedvisits(service, price, customerid)"
                + "VALUES (?,?,?)";

        connection = dBhandler.getConnection();

        try {
            pst = connection.prepareStatement(insert);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            pst.setString(1,"wymiana opon");
            pst.setString(2, "150 zl");
            pst.setLong(3, AuthService.getAuthentication());

            pst.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
