package com.example.proyectoprueba.IDAO;

import com.example.proyectoprueba.pojo.HistoriaAcademica;
import java.util.List;

public interface IHistoriaAcademicaDAO {
    void guardarHistoriaAcademica(HistoriaAcademica historiaAcademica);
    void eliminarHistoriaAcademica(HistoriaAcademica historiaAcademica);
    static List<HistoriaAcademica> getTablaHistoriaAcademica(){
        return null;
    };
}