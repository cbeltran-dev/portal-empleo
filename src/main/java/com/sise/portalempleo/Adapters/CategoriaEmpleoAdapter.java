package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.CategoriaEmpleo;
import com.sise.portalempleo.pyload.requests.CategoriaEmpleoRequest;

public class CategoriaEmpleoAdapter {
    
    public CategoriaEmpleo toEntity(CategoriaEmpleoRequest categoriaEmpleoRequest){
        CategoriaEmpleo entity = new CategoriaEmpleo();
        entity.setNombre(categoriaEmpleoRequest.getNombre());
        entity.setDescripcion(categoriaEmpleoRequest.getDescripcion());
        return entity;
    }
}
