package org.example.DBconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBhandler extends Configs {


    Connection dbconnection;

    public Connection getConnection() {

        String connectionString = "jdbc:mysql://" + Configs.dbhost + ":"
                + Configs.dbport + "/" + Configs.dbname + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
               /* + "?autoReconnect=true&useSSL=false"*/;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dbconnection;
    }
}
