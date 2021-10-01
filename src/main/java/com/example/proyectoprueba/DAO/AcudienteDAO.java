package com.example.proyectoprueba.DAO;

import com.example.proyectoprueba.Conexion.PostgreConexion;
import com.example.proyectoprueba.IDAO.IAcudienteDAO;
import com.example.proyectoprueba.pojo.Acudiente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AcudienteDAO implements IAcudienteDAO {

    @Override
    public void guardarAcudiente(Acudiente acudiente) {
        try (Connection conexion = PostgreConexion.getConexion()){
            String consulta = "Insert into public.\"Acudiente\" (\"idAlumno\", rfcAcudiente, nombreAcudiente, telefono, activo) values (?,?,?,?,?);";
            PreparedStatement sentence = conexion.prepareStatement(consulta);
            sentence.setInt(1, acudiente.getIdAlumno());
            sentence.setString(2, acudiente.getRfcAcudiente());
            sentence.setString(3, acudiente.getNombreAcudiente());
            sentence.setString(4,acudiente.getTelefono());
            sentence.setBoolean(5, true);
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void eliminarAcudiente(Acudiente acudiente) {
        try (Connection connection = PostgreConexion.getConexion()){
            String consulta = "UPDATE public.\"Acudiente\" SET activo=false WHERE \"idAlumno\" = ? and rfAcudiente = ? and nombreAcudiente = ? and telefono = ?;";
            PreparedStatement sentence = connection.prepareStatement(consulta);
            sentence.setInt(1, acudiente.getIdAlumno());
            sentence.setString(2, acudiente.getRfcAcudiente());
            sentence.setString(3, acudiente.getNombreAcudiente());
            sentence.setString(4, acudiente.getTelefono());
            sentence.execute();
            sentence.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Acudiente> getTablaOrigen() {
        Connection connection = PostgreConexion.getConexion();
        List<Acudiente> acudientes = new ArrayList<>();
        String consulta = "SELECT CONCAT(a.\"primerNombre\", ' ', a.\"segundoNombre\", ' ', a.\"apellidoPaterno\", ' ', a.\"apellidoMaterno\") AS nombreCompleto, " +
                "\"idAcudiente\", public.\"Acudiente\".\"idAlumno\", rfcAcudiente, nombreAcudiente, telefono, public.\"Acudiente\".activo FROM public.\"Acudiente\" " +
                "LEFT JOIN public.\"Alumno\" a ON public.\"Acudiente\".\"idAlumno\" = a.\"idAlumno\" WHERE public.\"Acudiente\".activo = true;";
        ResultSet queryResult = null;
        try{
            PreparedStatement sentence = connection.prepareStatement(consulta);
            queryResult = sentence.executeQuery();
            Acudiente acudienteDao;
            while (queryResult.next()){
                acudienteDao = new Acudiente();
                acudienteDao.setIdAcudiente(queryResult.getInt("idAcudiente"));
                acudienteDao.setIdAlumno(queryResult.getInt("idAlumno"));
                acudienteDao.setRfcAcudiente(queryResult.getString("rcfAcudiente"));
                acudienteDao.setNombreAcudiente(queryResult.getString("nombreAcudiente"));
                acudienteDao.setTelefono(queryResult.getString("telefono"));
                acudienteDao.setNombreCompletoAlumno(queryResult.getString("nombreCompleto"));
                acudientes.add(acudienteDao);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return acudientes;
    }
}