package com.sise.portalempleo.Adapters;
import com.sise.portalempleo.entities.TipoUsuario;
import com.sise.portalempleo.pyload.requests.TipoUsuarioRequest;

public class TipoUsuarioAdapter {
    
    public TipoUsuario toEntity(TipoUsuarioRequest tipoUsuarioRequest){
        TipoUsuario entity = new TipoUsuario();
        entity.setDescripcion(tipoUsuarioRequest.getDescripcion());
        return entity;
    }
}
