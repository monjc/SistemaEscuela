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
    @Override
    public List<HistoriaAcademica> getTablaistoriaAcademica() {
        Connection connection = PostgreConexion.getConnection();
        List<HistoriaAcademica> historiaAcademicas = new ArrayList<>();
        String query = "SELECT * FROM public.\"HistoriaAcademica\" WHERE activo = true";
        ResultSet queryResult = null;

        try{
            PreparedStatement sentence = connection.prepareStatement(query);
            queryResult = sentence.executeQuery();
            HistoriaAcademica historiaAcademicaDAO;
            while (queryResult.next()){
                historiaAcademicaDAO = new HistoriaAcademica();
                historiaAcademicaDAO.setIdHistoriaAcademica(queryResult.getInt("idHistoriaAcademica"));
                historiaAcademicaDAO.setIdAlumno(queryResult.getInt("idAlumno"));
                historiaAcademicaDAO.setColegioAnterior(queryResult.getString("rfcColegioAnterior"));
                historiaAcademicaDAO.setAño(queryResult.getString("año"));
                historiaAcademicaDAO.setGrado(queryResult.getString("grado"));
                historiaAcademicaDAO.setActivo(queryResult.getBoolean("activo"));
                historiaAcademicas.add(historiaAcademicaDAO);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return historiaAcademicas;
    }
}
