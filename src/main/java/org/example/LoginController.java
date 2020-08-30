package org.example;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.DBconnection.DBhandler;

public class LoginController implements Initializable {
    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Button forgotPassword;

    @FXML
    private TextField username;

    @FXML
    private ImageView progress;

    private DBhandler dBhandler;
    private Connection connection;
    private PreparedStatement pst;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        progress.setVisible(false);

        dBhandler = new DBhandler();
    }


 /*   @FXML
    private void switchToMainWindow() throws IOException {
        App.setRoot("mainWindow");
    }*/


    @FXML
    public void loginAction(ActionEvent e) {
        progress.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1.5));
        pt.setOnFinished(ev -> {

            //Retrieve data from DataBase
            connection = dBhandler.getConnection();

            String q1 = "SELECT * from customers where name=? and password=?";

            try {
                pst = connection.prepareStatement(q1);
                pst.setString(1, username.getText());
                pst.setString(2, password.getText());
                ResultSet rs = pst.executeQuery();



                if (rs.next()) {
                    long idcustomer = rs.getLong("idcustomer");
                    AuthService.setAuthenticated(idcustomer);
                    login.getScene().getWindow().hide();
                    Stage home = new Stage();
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                        Scene scene = new Scene(root);
                        home.setScene(scene);
                        home.show();

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }


                } else {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Username or password is not correct");
                    alert.show();
                    progress.setVisible(false);
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }


        });
        pt.play();



    }

    @FXML
    public void register(ActionEvent e1) throws IOException {

        login.getScene().getWindow().hide();
        Stage register = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene scene = new Scene(root);

        register.setScene(scene);
        register.show();
        register.setResizable(false);
    }


}