package com.example.proyectoprueba.IDAO;

import com.example.proyectoprueba.pojo.Alumno;
import java.util.List;

public interface IAlumnoDAO {
    static void eliminarAlumno(Alumno alumno) {}
    static void registrarAlumno(Alumno alumno) {}
    static List<Alumno> obtenerAlumnos() {
        return null;
    }
}
