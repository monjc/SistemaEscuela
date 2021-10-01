package com.example.DAO;

import com.example.Conexion.PostgreConexion;
import com.example.IDAO.IColegioDAO;
import com.example.pojos.Colegio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ColegioDAO implements IColegioDAO {
    @Override
    public void guardarColegio(Colegio colegio) {
        try (Connection conexion = PostgreConexion.getConexion()){
            String consulta = "Insert into public.\"Colegio\" (\"idAlumno\", distrital, privado, activo) values (?,?,?,?);";
            PreparedStatement sentence = conexion.prepareStatement(consulta);
            sentence.setInt(1, colegio.getIdAlumno());
            sentence.setString(2, colegio.getDistrital());
            sentence.setString(3, colegio.getPrivado());
            sentence.setBoolean(4, true);
            sentence.execute();
            sentence.close();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void eliminarColegio(Colegio colegio) {
        try (Connection connection = PostgreConexion.getConexion()){
            String consulta = "UPDATE public.\"Colegio\" SET activo=false WHERE \"idAlumno\" = ? and distrital = ? and privado = ?;";
            PreparedStatement sentence = connection.prepareStatement(consulta);
            sentence.setInt(1, colegio.getIdAlumno());
            sentence.setString(2, colegio.getDistrital());
            sentence.setString(3, colegio.getPrivado());
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Colegio> getTablaColegio() {
        Connection connection = PostgreConexion.getConexion();
        List<Colegio> colegios = new ArrayList<>();
        String consulta = "SELECT CONCAT(a.\"primerNombre\", ' ', a.\"segundoNombre\", ' ', a.\"apellidoPaterno\", ' ', a.\"apellidoMaterno\") AS nombreCompleto, " +
                "\"idColegio\", public.\"Colegio\".\"idAlumno\", distrital, privado, public.\"Colegio\".activo FROM public.\"Colegio\" " +
                "LEFT JOIN public.\"Alumno\" a ON public.\"Colegio\".\"idAlumno\" = a.\"idAlumno\" WHERE public.\"Colegio\".activo = true;";
        ResultSet queryResult = null;
        try{
            PreparedStatement sentence = connection.prepareStatement(consulta);
            queryResult = sentence.executeQuery();
            Colegio colegioDAO;
            while (queryResult.next()){
                colegioDAO = new Colegio();
                colegioDAO.setIdAlumno(queryResult.getInt("idAlumno"));
                colegioDAO.setIdColegio(queryResult.getInt("idColegio"));
                colegioDAO.setDistrital(queryResult.getString("distrital"));
                colegioDAO.setPrivado(queryResult.getString("privado"));
                colegioDAO.setNombreCompletoAlumno(queryResult.getString("nombreCompleto"));
                colegios.add(colegioDAO);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return colegios;
    }
}
