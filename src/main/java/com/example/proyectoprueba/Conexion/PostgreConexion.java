package com.example.proyectoprueba.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConexion {
    private static String driver = "org.postgresql.Driver";
    private static String database = "rewwan";
    private static String host = "jdbc:postgresql://maisonbleue2020.ddns.net/rewwan";
    private static String user = "rewwan";
    private static String password = "ggnojg";
    private static Connection connection;

    public static Connection getConexion(){
        connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(host + database + user + password);
            System.out.println("Connection established");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException ex){
            System.out.println("Ha ocurrido un error: " + ex.getMessage());
        }
        return connection;
    }
}
