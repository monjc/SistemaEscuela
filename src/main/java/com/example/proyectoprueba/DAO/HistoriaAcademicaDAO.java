package com.example.proyectoprueba.DAO;

import com.example.proyectoprueba.Conexion.PostgreConexion;
import com.example.proyectoprueba.IDAO.IHistoriaAcademicaDAO;
import com.example.proyectoprueba.pojo.HistoriaAcademica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoriaAcademicaDAO implements IHistoriaAcademicaDAO {
    public static List<HistoriaAcademica> getTablaHistoriaAcademica() {
        Connection connection = PostgreConexion.getConexion();
        List<HistoriaAcademica> historiaAcademicas = new ArrayList<>();
        String consulta = "SELECT CONCAT(a.\"primerNombre\", ' ', a.\"segundoNombre\", ' ', a.\"apellidoPaterno\", ' ', a.\"apellidoMaterno\") AS nombreCompleto, " +
                "\"idHistoriaAcademica\", public.\"HistoriaAcademica\".\"idAlumno\", colegioAnterior, año, grado, public.\"HistoriaAcademica\".activo FROM public.\"HistoriaAcademica\" " +
                "LEFT JOIN public.\"Alumno\" a ON public.\"HistoriaAcademica\".\"idAlumno\" = a.\"idAlumno\" WHERE public.\"HistoriaAcademica\".activo = true;";
        ResultSet queryResult = null;

        try{
            PreparedStatement sentence = connection.prepareStatement(consulta);
            queryResult = sentence.executeQuery();
            HistoriaAcademica historiaAcademicaDAO;
            while (queryResult.next()){
                historiaAcademicaDAO = new HistoriaAcademica();
                historiaAcademicaDAO.setIdHistoriaAcademica(queryResult.getInt("idHistoriaAcademica"));
                historiaAcademicaDAO.setIdAlumno(queryResult.getInt("idAlumno"));
                historiaAcademicaDAO.setColegioAnterior(queryResult.getString("colegioAnterior"));
                historiaAcademicaDAO.setAño(queryResult.getString("año"));
                historiaAcademicaDAO.setGrado(queryResult.getString("grado"));
                historiaAcademicaDAO.setNombreCompletoAlumno(queryResult.getString("nombreCompleto"));
                historiaAcademicas.add(historiaAcademicaDAO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return historiaAcademicas;
    }

    @Override
    public void guardarHistoriaAcademica(HistoriaAcademica historiaAcademica) {
        try (Connection conexion = PostgreConexion.getConexion()){
            String consulta = "Insert into public.\"HistoriaAcademica\" (\"idAlumno\", colegioAnterior, año, grado, activo) values (?,?,?,?,?);";
            PreparedStatement sentence = conexion.prepareStatement(consulta);
            sentence.setInt(1, historiaAcademica.getIdAlumno());
            sentence.setString(2, historiaAcademica.getColegioAnterior());
            sentence.setString(3, historiaAcademica.getAño());
            sentence.setString(4,historiaAcademica.getGrado());
            sentence.setBoolean(5, true);
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void eliminarHistoriaAcademica(HistoriaAcademica historiaAcademica) {
        try (Connection connection = PostgreConexion.getConexion()){
            String consulta = "UPDATE public.\"HistoriaAcademica\" SET activo=false WHERE \"idAlumno\" = ? and colegioAnterior = ? and año = ? and grado = ?;";
            PreparedStatement sentence = connection.prepareStatement(consulta);
            sentence.setInt(1, historiaAcademica.getIdAlumno());
            sentence.setString(2, historiaAcademica.getColegioAnterior());
            sentence.setString(3, historiaAcademica.getAño());
            sentence.setString(4, historiaAcademica.getGrado());
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
