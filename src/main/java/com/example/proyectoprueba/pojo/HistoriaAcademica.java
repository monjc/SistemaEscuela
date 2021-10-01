package com.example.proyectoprueba.pojo;

public class HistoriaAcademica {
    private int idHistoriaAcademica;
    private int idAlumno;
    private String colegioAnterior;
    private String año;
    private String grado;
    private boolean activo;

    public int getIdHistoriaAcademica() {
        return idHistoriaAcademica;
    }

    public void setIdHistoriaAcademica(int historiaAcademica) {
        idHistoriaAcademica = historiaAcademica;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getColegioAnterior() {
        return colegioAnterior;
    }

    public void setColegioAnterior(String colegioAnterior) {
        this.colegioAnterior = colegioAnterior;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
