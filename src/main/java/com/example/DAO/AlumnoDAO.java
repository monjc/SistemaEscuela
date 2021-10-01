package com.example.DAO;

import com.example.Conexion.PostgreConexion;
import com.example.IDAO.IAlumnoDAO;
import com.example.pojos.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO implements IAlumnoDAO {
    public static void eliminarAlumno(Alumno alumno) {
        try (Connection connection = PostgreConexion.getConexion()){
            String query = "UPDATE public.\"Alumno\" SET activo=false WHERE apellidoPaterno = ? and primerNombre = ? and segundoNombre = ? and apellidoMaterno = ?;";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, alumno.getApellidoPaterno());
            sentence.setString(2, alumno.getPrimerNombre());
            sentence.setString(3, alumno.getSegundoNombre());
            sentence.setString(4, alumno.getApellidoMaterno());
            sentence.execute();
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void registrarAlumno(Alumno alumno) {
        try(Connection connection = PostgreConexion.getConexion()){
            String query = "INSERT INTO public.\"Alumno\" (apellidoPaterno, primerNombre, segundoNombre, apellidoMaterno, activo) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, alumno.getApellidoPaterno());
            sentence.setString(2, alumno.getPrimerNombre());
            sentence.setString(3, alumno.getSegundoNombre());
            sentence.setString(4, alumno.getApellidoMaterno());
            sentence.setBoolean(5, true);
            sentence.execute();
            sentence.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Alumno> obtenerAlumnos() {
        Connection connection = PostgreConexion.getConexion();
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM public.\"Alumno\" WHERE activo = true;";
        ResultSet queryResult = null;
        try{
            PreparedStatement sentence = connection.prepareStatement(query);
            queryResult = sentence.executeQuery();
            Alumno alumnodao;
            while (queryResult.next()){
                alumnodao = new Alumno();
                alumnodao.setIdAlumno(queryResult.getInt("idAlumno"));
                alumnodao.setPrimerNombre(queryResult.getString("primerNombre"));
                alumnodao.setSegundoNombre(queryResult.getString("segundoNombre"));
                alumnodao.setApellidoPaterno(queryResult.getString("apellidoPaterno"));
                alumnodao.setApellidoMaterno(queryResult.getString("apellidoMaterno"));
                alumnos.add(alumnodao);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alumnos;
    }
}
