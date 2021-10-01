package com.example.proyectoprueba.IDAO;

import com.example.proyectoprueba.pojo.Acudiente;
import java.util.List;

public interface IAcudienteDAO {
    void guardarAcudiente(Acudiente acudiente);
    void eliminarAcudiente(Acudiente acudiente);
    static List<Acudiente> getTablaOrigen(){
        return null;
    }
}