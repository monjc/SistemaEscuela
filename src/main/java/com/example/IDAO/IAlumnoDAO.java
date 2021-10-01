package com.example.IDAO;

import com.example.pojos.Alumno;

import java.util.List;

public interface IAlumnoDAO {
    static void eliminarAlumno(Alumno alumno) {}
    static void registrarAlumno(Alumno alumno) {}
    static List<Alumno> obtenerAlumnos() {
        return null;
    }
}
