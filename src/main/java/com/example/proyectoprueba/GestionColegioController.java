package com.example.proyectoprueba;

import com.example.DAO.AlumnoDAO;
import com.example.DAO.ColegioDAO;
import com.example.IDAO.IColegioDAO;
import com.example.pojos.Alumno;
import com.example.pojos.Colegio;
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

public class GestionColegioController implements Initializable {
    public ComboBox comboAlumno;
    public TextField txtPrivado;
    public TextField txtDistrital;
    public TableView<Colegio> tablaColegio;
    public TableColumn<Colegio, String> columnaAlumno;
    public TableColumn<Colegio, String> columnaPrivado;
    public TableColumn<Colegio, String> columnaDistrital;

    private ObservableList<Colegio> listaColegio;
    private List<Colegio> colegios;

    public void registrarColegio(ActionEvent actionEvent) {
        if(validarCamposVacios()) {
            Colegio colegio = new Colegio();
            Alumno alumno = (Alumno) comboAlumno.getSelectionModel().getSelectedItem();
            colegio.setIdAlumno(alumno.getIdAlumno());
            colegio.setPrivado(txtPrivado.getText());
            colegio.setDistrital(txtDistrital.getText());
            IColegioDAO icolegiodao = new ColegioDAO();
            if (mostrarDialogoRegistroConfirmacion()){
                icolegiodao.guardarColegio(colegio);
                mostrarDialogoRegistroExitoso();
                actualizarTabla();
            }
        } else {
            mostrarDialogoCamposVacios();
        }
    }

    public void eliminarColegio(ActionEvent actionEvent) {
        Colegio colegio = tablaColegio.getSelectionModel().getSelectedItem();
        if ( colegio != null ){
            if(mostrarDialogoEliminacionConfirmacion()) {
                IColegioDAO colegioDAO = new ColegioDAO();
                colegioDAO.eliminarColegio(colegio);
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
        return (!txtDistrital.getText().isEmpty() && !txtPrivado.getText().isEmpty() && !comboAlumno.getSelectionModel().isEmpty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enlazarColumnas();
        actualizarTabla();
        llenarCombobox();
    }

    public void enlazarColumnas(){
        columnaAlumno.setCellValueFactory(new PropertyValueFactory<Colegio, String>("nombreCompletoAlumno"));
        columnaPrivado.setCellValueFactory(new PropertyValueFactory<Colegio, String>("privado"));
        columnaDistrital.setCellValueFactory(new PropertyValueFactory<Colegio, String>("distrital"));
    }

    private void llenarCombobox(){
        List<Alumno> alumnos;
        alumnos = AlumnoDAO.obtenerAlumnos();
        ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList(alumnos);
        comboAlumno.setItems(listaAlumnos);
    }

    private void actualizarTabla(){
        colegios = ColegioDAO.getTablaColegio();
        listaColegio = FXCollections.observableArrayList(colegios);
        tablaColegio.setItems(listaColegio);
    }
}
