package com.example.pojos;

public class Colegio {
    private int idColegio;
    private int idAlumno;
    private String distrital;
    private String privado;
    private String nombreCompletoAlumno;

    public String getNombreCompletoAlumno() {
        return nombreCompletoAlumno;
    }

    public void setNombreCompletoAlumno(String nombreCompletoAlumno) {
        this.nombreCompletoAlumno = nombreCompletoAlumno;
    }

    public Colegio() {}

    public int getIdColegio() {
        return idColegio;
    }

    public void setIdColegio(int idColegio) {
        this.idColegio = idColegio;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getDistrital() {
        return distrital;
    }

    public void setDistrital(String distrital) {
        this.distrital = distrital;
    }

    public String getPrivado() {
        return privado;
    }

    public void setPrivado(String privado) {
        this.privado = privado;
    }
}
