package com.example.IDAO;

import com.example.pojos.Origen;

import java.util.List;

public interface IOrigenDAO {
    void guardarOrigen(Origen origen);
    void eliminarOrigen(Origen origen);
    static List<Origen> getTablaOrigen(){
        return null;
    };
}
