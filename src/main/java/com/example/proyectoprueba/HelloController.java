package com.example.proyectoprueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloController {
    public Label informacion;
    public Button btn_conectar;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onBtn_conectarClick(ActionEvent actionEvent) {
        Connection conn = null;

        try {
            String url = "jdbc:postgresql://maisonbleue2020.ddns.net/rewwan";
            String user = "rewwan";
            String password = "ggnojg";

            conn = DriverManager.getConnection(url, user, password);

            // Do something with the Connection
            if (conn != null){
                this.informacion.setText("Conexión establecida");
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            this.informacion.setText("¡ERROR. No hay conexión!");
        }
    }
}