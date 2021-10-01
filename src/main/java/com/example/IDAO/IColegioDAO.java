package com.example.IDAO;

import com.example.pojos.Colegio;

import java.util.List;

public interface IColegioDAO {
    void guardarColegio(Colegio colegio);
    void eliminarColegio(Colegio colegio);
    static List<Colegio> getTablaColegio(){
        return null;
    }
}
