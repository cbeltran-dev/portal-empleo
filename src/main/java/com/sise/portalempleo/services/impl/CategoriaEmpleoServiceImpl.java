package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.portalempleo.entities.CategoriaEmpleo;
import com.sise.portalempleo.repositories.CategoriaEmpleoRepository;
import com.sise.portalempleo.services.CategoriaEmpleoService;
@Service
public class CategoriaEmpleoServiceImpl implements CategoriaEmpleoService {

    @Autowired
    CategoriaEmpleoRepository categoriaEmpleoRepository;

    @Override
    public List<CategoriaEmpleo> listaCategoriaEmpleos() {
        return categoriaEmpleoRepository.findByEstadoAuditoria("1");
    }

    @Override
    public CategoriaEmpleo listaCategoriaEmpleoPorId(Integer idCategoriaEmpleo) {
        return categoriaEmpleoRepository.findOneByIdCategoriaEmpleoAndEstadoAuditoria(idCategoriaEmpleo, "1");
    }

    @Override
    public CategoriaEmpleo insertarCategoriaEmpleo(CategoriaEmpleo categoriaEmpleo) {
        return categoriaEmpleoRepository.save(categoriaEmpleo);
    }

    @Override
    public CategoriaEmpleo actualizarCategoriaEmpleo(CategoriaEmpleo categoriaEmpleo) {
        categoriaEmpleoRepository.actualizarCategoriaEmpleo(categoriaEmpleo.getIdCategoriaEmpleo(), categoriaEmpleo.getNombre(), categoriaEmpleo.getDescripcion());
        return categoriaEmpleoRepository.findOneByIdCategoriaEmpleoAndEstadoAuditoria(categoriaEmpleo.getIdCategoriaEmpleo(), "1");
    }

    @Override
    public void darbajaCategoriaEmpleo(Integer idCategoriaEmpleo) {
        categoriaEmpleoRepository.darBajaCategoriaEmpleo(idCategoriaEmpleo);
    }
    
}
