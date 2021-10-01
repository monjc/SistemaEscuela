package com.example.proyectoprueba;

import com.example.proyectoprueba.DAO.AlumnoDAO;
import com.example.proyectoprueba.DAO.HistoriaAcademicaDAO;
import com.example.proyectoprueba.IDAO.IHistoriaAcademicaDAO;
import com.example.proyectoprueba.pojo.HistoriaAcademica;
import com.example.proyectoprueba.pojo.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.proyectoprueba.Dialogo.Dialogo.*;

public class GestionHistoriaAcademicaController implements Initializable {
    public ComboBox comboAlumno;
    public TextField txtColegioAnterior;
    public TextField txtAño;
    public TextField txtGrado;
    public TableView<HistoriaAcademica> tablaHistoriaAcademica;
    public TableColumn<HistoriaAcademica, String> columnaAlumno;
    public TableColumn<HistoriaAcademica, String> columnaColegioAnterior;
    public TableColumn<HistoriaAcademica, String> columnaAño;
    public TableColumn<HistoriaAcademica, String> columnaGrado;

    private ObservableList<HistoriaAcademica> listaHistoriaAcademica;
    private List<HistoriaAcademica> historiasAcademicas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        enlazarColumnas();
        actualizarTabla();
        llenarCombobox();
    }

    public void registrarHistoriaAcademica(ActionEvent actionEvent) {
        if(validarCamposVacios()) {
            HistoriaAcademica historiaAcademica = new HistoriaAcademica();
            Alumno alumno = (Alumno) comboAlumno.getSelectionModel().getSelectedItem();
            historiaAcademica.setIdAlumno(alumno.getIdAlumno());
            historiaAcademica.setColegioAnterior(txtColegioAnterior.getText());
            historiaAcademica.setAño(txtAño.getText());
            historiaAcademica.setGrado(txtGrado.getText());
            IHistoriaAcademicaDAO iHistoriaAcademicaDAO = new HistoriaAcademicaDAO();
            if (mostrarDialogoRegistroConfirmacion()){
                iHistoriaAcademicaDAO.guardarHistoriaAcademica(historiaAcademica);
                mostrarDialogoRegistroExitoso();
                actualizarTabla();
            }
        } else {
            mostrarDialogoCamposVacios();
        }
    }

    public void eliminarHistoriaAcademica(ActionEvent actionEvent) {
        HistoriaAcademica historiaAcademica = tablaHistoriaAcademica.getSelectionModel().getSelectedItem();
        if ( historiaAcademica != null ){
            if(mostrarDialogoEliminacionConfirmacion()) {
                IHistoriaAcademicaDAO iHistoriaAcademicaDAO = new HistoriaAcademicaDAO();
                iHistoriaAcademicaDAO.eliminarHistoriaAcademica(historiaAcademica);
                mostrarDialogoEliminacionExitosa();
                actualizarTabla();
            }
        } else {
            mostrarDialogoSeleccionarRegistro();
        }
    }

    public boolean validarCamposVacios() {
        return (!txtColegioAnterior.getText().isEmpty() && !txtAño.getText().isEmpty() && !txtGrado.getText().isEmpty() && !comboAlumno.getSelectionModel().isEmpty());
    }

    private void actualizarTabla(){
        historiasAcademicas = HistoriaAcademicaDAO.getTablaHistoriaAcademica();
        listaHistoriaAcademica = FXCollections.observableArrayList(historiasAcademicas);
        tablaHistoriaAcademica.setItems(listaHistoriaAcademica);
    }

    public void enlazarColumnas(){
        columnaAlumno.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("nombreCompletoAlumno"));
        columnaColegioAnterior.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("colegioAnterior"));
        columnaAño.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("año"));
        columnaGrado.setCellValueFactory(new PropertyValueFactory<HistoriaAcademica, String>("grado"));
    }

    private void llenarCombobox(){
        List<Alumno> alumnos;
        alumnos = AlumnoDAO.obtenerAlumnos();
        ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList(alumnos);
        comboAlumno.setItems(listaAlumnos);
    }
}
