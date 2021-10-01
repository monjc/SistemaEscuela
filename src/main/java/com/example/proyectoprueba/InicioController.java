package com.example.proyectoprueba;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.Dialogo.Dialogo.mostrarDialogoAlgoSalioMal;

public class InicioController {
    public AnchorPane container;

    public void avanzarMenuEstudiante(ActionEvent actionEvent) {
        mostrarDialogoAlgoSalioMal();
    }

    public void avanzarMenuOrigenes(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("IGestionOrigen.fxml"));
            GestionOrigenController controller = new GestionOrigenController();
            loader.setController(controller);
            Stage stage = new Stage();
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void avanzarMenuColegios(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("IGestionColegio.fxml"));
            GestionColegioController controller = new GestionColegioController();
            loader.setController(controller);
            Stage stage = new Stage();
            Parent parent = loader.load();
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void avanzarMenuHistoriales(ActionEvent actionEvent) {
        mostrarDialogoAlgoSalioMal();
    }

    public void avanzarMenuInfoMedica(ActionEvent actionEvent) {
        mostrarDialogoAlgoSalioMal();
    }

    public void avanzarMenuAcudientes(ActionEvent actionEvent) {
        mostrarDialogoAlgoSalioMal();
    }
}
