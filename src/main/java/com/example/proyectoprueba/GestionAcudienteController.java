package com.example.proyectoprueba;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.proyectoprueba.DAO.AlumnoDAO;
import com.example.proyectoprueba.DAO.AcudienteDAO;
import com.example.proyectoprueba.IDAO.IAcudienteDAO;
import com.example.proyectoprueba.pojo.Alumno;
import com.example.proyectoprueba.pojo.Acudiente;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.proyectoprueba.Dialogo.Dialogo.*;

public class GestionAcudienteController implements Initializable {
    public ComboBox comboAlumno;
    public TextField txtRfcAcudiente;
    public TextField txtNombreAcudiente;
    public TextField txtTelefono;
    public TableView<Acudiente> tablaAcudiente;
    public TableColumn<Acudiente, String> columnaAlumno;
    public TableColumn<Acudiente, String> columnaRfcAcudiente;
    public TableColumn<Acudiente, String> columnaNombreAcudiente;
    public TableColumn<Acudiente, String> columnTelefonoAcudiente;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enlazarColumnas();
        actualizarTabla();
        llenarCombobox();
    }

    private ObservableList<Acudiente> listaAcudiente;
    private List<Acudiente> acudientes;

    public void registrarAcudiente(ActionEvent actionEvent) {
        if(validarCamposVacios()) {
            Acudiente acudiente = new Acudiente();
            Alumno alumno = (Alumno) comboAlumno.getSelectionModel().getSelectedItem();
            acudiente.setIdAlumno(alumno.getIdAlumno());
            acudiente.setRfcAcudiente(txtRfcAcudiente.getText());
            acudiente.setNombreAcudiente(txtNombreAcudiente.getText());
            acudiente.setTelefono(txtTelefono.getText());
            IAcudienteDAO iAcudienteDAO = new AcudienteDAO();
            if (mostrarDialogoRegistroConfirmacion()){
                iAcudienteDAO.guardarAcudiente(acudiente);
                mostrarDialogoRegistroExitoso();
                actualizarTabla();
            }
        } else {
            mostrarDialogoCamposVacios();
        }
    }

    public void eliminarAcudiente(ActionEvent actionEvent) {
        Acudiente acudiente = tablaAcudiente.getSelectionModel().getSelectedItem();
        if ( acudiente != null ){
            if(mostrarDialogoEliminacionConfirmacion()) {
                IAcudienteDAO iacudienteDAO = new AcudienteDAO();
                iacudienteDAO.eliminarAcudiente(acudiente);
                mostrarDialogoEliminacionExitosa();
                actualizarTabla();
            }
        } else {
            mostrarDialogoSeleccionarRegistro();
        }
    }

    public boolean validarCamposVacios() {
        return (!txtRfcAcudiente.getText().isEmpty() && !txtNombreAcudiente.getText().isEmpty() && !txtTelefono.getText().isEmpty() && !comboAlumno.getSelectionModel().isEmpty());
    }

    private void actualizarTabla(){
        acudientes = AcudienteDAO.getTablaOrigen();
        listaAcudiente = FXCollections.observableArrayList(acudientes);
        tablaAcudiente.setItems(listaAcudiente);
    }

    public void enlazarColumnas(){
        columnaAlumno.setCellValueFactory(new PropertyValueFactory<Acudiente, String>("nombreCompletoAlumno"));
        columnaRfcAcudiente.setCellValueFactory(new PropertyValueFactory<Acudiente, String>("rfcAcudiente"));
        columnaNombreAcudiente.setCellValueFactory(new PropertyValueFactory<Acudiente, String>("nombreAcudiente"));
        columnTelefonoAcudiente.setCellValueFactory(new PropertyValueFactory<Acudiente, String>("telefono"));
    }

    private void llenarCombobox(){
        List<Alumno> alumnos;
        alumnos = AlumnoDAO.obtenerAlumnos();
        ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList(alumnos);
        comboAlumno.setItems(listaAlumnos);
    }
}
