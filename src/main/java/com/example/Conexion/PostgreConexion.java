package com.example.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConexion {
    private static String driver = "org.postgresql.Driver";
    private static String user = "rewwan";
    private static String password = "ggnojg";
    private static String host = "jdbc:postgresql://maisonbleue2020.ddns.net/";
    private static String database = "rewwan";
    private static Connection connection;

    public static Connection getConexion(){
        connection = null;
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(host + database, user, password);
            System.out.println("Connection established");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        return connection;
    }
}
