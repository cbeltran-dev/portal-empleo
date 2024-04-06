package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.Empleador;

public interface EmpleadorService {
    
    List<Empleador> listaEmpleadores();
    Empleador listaEmpleadorPorId(Integer idEmpleador);
    Empleador insertarEmpleador(Empleador empleador);
    Empleador actualizarEmpleador(Empleador empleador);
    void darbajaEmpleador(Integer idEmpleador);
}
