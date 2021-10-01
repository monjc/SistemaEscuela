package com.example.proyectoprueba;

import com.example.DAO.AlumnoDAO;
import com.example.DAO.OrigenDAO;
import com.example.IDAO.IOrigenDAO;
import com.example.pojos.Alumno;
import com.example.pojos.Origen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.Dialogo.Dialogo.*;
import static com.example.Dialogo.Dialogo.mostrarDialogoSeleccionarRegistro;

public class GestionOrigenController implements Initializable {
    public ComboBox comboAlumno;
    public TextField txtDepartamento;
    public TextField txtCiudad;
    public TableView<Origen> tablaOrigen;
    public TableColumn<Origen, String> columnaAlumno;
    public TableColumn<Origen, String> columnaDepto;
    public TableColumn<Origen, String> columnaCiudad;

    private ObservableList<Origen> listaOrigen;
    private List<Origen> origenes;

    public void registrarOrigen(ActionEvent actionEvent) {
        if(validarCamposVacios()) {
            Origen origen = new Origen();
            Alumno alumno = (Alumno) comboAlumno.getSelectionModel().getSelectedItem();
            origen.setIdAlumno(alumno.getIdAlumno());
            origen.setDepartamento(txtDepartamento.getText());
            origen.setCiudad(txtCiudad.getText());
            IOrigenDAO iorigendao = new OrigenDAO();
            if (mostrarDialogoRegistroConfirmacion()){
                iorigendao.guardarOrigen(origen);
                mostrarDialogoRegistroExitoso();
                actualizarTabla();
            }
        } else {
            mostrarDialogoCamposVacios();
        }
    }

    public void eliminarOrigen(ActionEvent actionEvent) {
        Origen origen = tablaOrigen.getSelectionModel().getSelectedItem();
        if ( origen != null ){
            if(mostrarDialogoEliminacionConfirmacion()) {
                IOrigenDAO origenDAO = new OrigenDAO();
                origenDAO.eliminarOrigen(origen);
                mostrarDialogoEliminacionExitosa();
                actualizarTabla();
            }
        } else {
            mostrarDialogoSeleccionarRegistro();
        }
    }

    public void regresarMenuPrincipal (ActionEvent actionEvent){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("Inicio.fxml"));
            InicioController controller = new InicioController();
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

    public boolean validarCamposVacios() {
        return (!txtDepartamento.getText().isEmpty() && !txtCiudad.getText().isEmpty() && !comboAlumno.getSelectionModel().isEmpty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enlazarColumnas();
        actualizarTabla();
        llenarCombobox();
    }

    public void enlazarColumnas(){
        columnaAlumno.setCellValueFactory(new PropertyValueFactory<Origen, String>("nombreCompletoAlumno"));
        columnaDepto.setCellValueFactory(new PropertyValueFactory<Origen, String>("departamento"));
        columnaCiudad.setCellValueFactory(new PropertyValueFactory<Origen, String>("ciudad"));
    }

    private void llenarCombobox(){
        List<Alumno> alumnos;
        alumnos = AlumnoDAO.obtenerAlumnos();
        ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList(alumnos);
        comboAlumno.setItems(listaAlumnos);
    }

    private void actualizarTabla(){
        origenes = OrigenDAO.getTablaOrigen();
        listaOrigen = FXCollections.observableArrayList(origenes);
        tablaOrigen.setItems(listaOrigen);
    }
}
