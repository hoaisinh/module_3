package com.example.tekushop.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatabase {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/tekushop";
    private static final String USER = "root";
    private static final String PASS = "120193";

    public  BaseDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("---------------------------------------------------------------");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
