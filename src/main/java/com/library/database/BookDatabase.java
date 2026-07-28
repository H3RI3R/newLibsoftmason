package com.library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookDatabase {

    private static final ResourceBundle rb = ResourceBundle.getBundle("db");

    private static final String URL = rb.getString("db.url");
    private static final String USER = rb.getString("db.username");
    private static final String PASSWORD = rb.getString("db.password");

    public static Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed");
            e.printStackTrace();
        }

        return connection;
    }
}