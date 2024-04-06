package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.CategoriaEmpleo;


public interface CategoriaEmpleoService {
    
    List<CategoriaEmpleo> listaCategoriaEmpleos();
    CategoriaEmpleo listaCategoriaEmpleoPorId(Integer idCategoriaEmpleo);
    CategoriaEmpleo insertarCategoriaEmpleo(CategoriaEmpleo categoriaEmpleo);
    CategoriaEmpleo actualizarCategoriaEmpleo(CategoriaEmpleo categoriaEmpleo);
    void darbajaCategoriaEmpleo(Integer idCategoriaEmpleo);
}
