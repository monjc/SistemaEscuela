package com.example.proyectoprueba.pojo;

public class Acudiente {
    private int idAcudiente;
    private int idAlumno;
    private String rfcAcudiente;
    private String nombreAcudiente;
    private String telefono;
    private String nombreCompletoAlumno;

    public int getAcudiente(){return idAcudiente;}

    public void setIdAcudiente(int idAcudiente) {
        this.idAcudiente = idAcudiente;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getRfcAcudiente() {
        return rfcAcudiente;
    }

    public void setRfcAcudiente(String rfcAcudiente) {
        this.rfcAcudiente = rfcAcudiente;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNombreCompletoAlumno(String nombreCompletoAlumno) {
        this.nombreCompletoAlumno = nombreCompletoAlumno;
    }
}