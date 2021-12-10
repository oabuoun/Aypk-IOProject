package com.sparta.iomanager.controller;

import com.sparta.iomanager.view.Report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {


    private Connection theConnection  = null;

    public Connection getConnection() throws SQLException, IOException {
        /* Reading the properties file for user and database Connection, database should be created with the name "Employees"*/
        if (theConnection==null || theConnection.isClosed()) {
            Properties properties = new Properties();
            properties.load(new FileReader("connection.properties"));
            String url = properties.getProperty("dbUrl");
            String userId = properties.getProperty("dbUserName");
            String userPassword = properties.getProperty("dbUserPassword");
            theConnection = DriverManager.getConnection(url, userId, userPassword);
            //theConnection = DriverManager.getConnection(url +"?&rewriteBatchedStatements=true", userId, userPassword);
        }
        return theConnection;
    }


    public static Connection getConnection2() throws SQLException, IOException {
        Properties props;
        String url = null;
        String userId = null;
        String password = null;
        try (FileReader props_file = new FileReader("connection.properties")) {
            props = new Properties();
            props.load(props_file);
            url = props.getProperty("dbUrl");
            userId = props.getProperty("dbUserName");
            password = props.getProperty("dbUserPassword");
        } catch (IOException e){
            e.printStackTrace();
        }

        try (Connection theConn = DriverManager.getConnection(url, userId, password))
        {
            return theConn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




   public static void closeConnection() throws SQLException, IOException {
        ConnectionFactory theConnection = new ConnectionFactory();
        if (theConnection.getConnection() !=null) theConnection.getConnection().close();
    }

}
