package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.RegistroPostulacion;

public interface RegistroPostulacionService {
    
    List<RegistroPostulacion> listaRegistroPostulacion();
    RegistroPostulacion listaRegistroPostulacionPorId(Integer idRegistroPostulacion);
    RegistroPostulacion insertarRegistroPostulacion(RegistroPostulacion registroPostulacion);
    RegistroPostulacion actualizarRegistroPostulacion(RegistroPostulacion registroPostulacion);
    void darbajaRegistroPostulacion(Integer idRegistroPostulacion);

}
