package com.example.DAO;

import com.example.Conexion.PostgreConexion;
import com.example.IDAO.IOrigenDAO;
import com.example.pojos.Origen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrigenDAO implements IOrigenDAO {
    @Override
    public void guardarOrigen(Origen origen) {
        try (Connection conexion = PostgreConexion.getConexion()){
            String consulta = "Insert into public.\"Origen\" (\"idAlumno\", departamento, ciudad, activo) values (?,?,?,?);";
            PreparedStatement sentence = conexion.prepareStatement(consulta);
            sentence.setInt(1, origen.getIdAlumno());
            sentence.setString(2, origen.getDepartamento());
            sentence.setString(3, origen.getCiudad());
            sentence.setBoolean(4, true);
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void eliminarOrigen(Origen origen) {
        try (Connection connection = PostgreConexion.getConexion()){
            String consulta = "UPDATE public.\"Origen\" SET activo=false WHERE \"idAlumno\" = ? and departamento = ? and ciudad = ?;";
            PreparedStatement sentence = connection.prepareStatement(consulta);
            sentence.setInt(1, origen.getIdAlumno());
            sentence.setString(2, origen.getDepartamento());
            sentence.setString(3, origen.getCiudad());
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Origen> getTablaOrigen() {
        Connection connection = PostgreConexion.getConexion();
        List<Origen> origenes = new ArrayList<>();
        String consulta = "SELECT CONCAT(a.\"primerNombre\", ' ', a.\"segundoNombre\", ' ', a.\"apellidoPaterno\", ' ', a.\"apellidoMaterno\") AS nombreCompleto, " +
                "\"idOrigen\", public.\"Origen\".\"idAlumno\", departamento, ciudad, public.\"Origen\".activo FROM public.\"Origen\" " +
                "LEFT JOIN public.\"Alumno\" a ON public.\"Origen\".\"idAlumno\" = a.\"idAlumno\" WHERE public.\"Origen\".activo = true;";
        ResultSet queryResult = null;
        try{
            PreparedStatement sentence = connection.prepareStatement(consulta);
            queryResult = sentence.executeQuery();
            Origen origenDAO;
            while (queryResult.next()){
                origenDAO = new Origen();
                origenDAO.setIdOrigen(queryResult.getInt("idOrigen"));
                origenDAO.setIdAlumno(queryResult.getInt("idAlumno"));
                origenDAO.setDepartamento(queryResult.getString("departamento"));
                origenDAO.setCiudad(queryResult.getString("ciudad"));
                origenDAO.setNombreCompletoAlumno(queryResult.getString("nombreCompleto"));
                origenes.add(origenDAO);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return origenes;
    }
}
