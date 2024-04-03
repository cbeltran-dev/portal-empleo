package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.TipoUsuario;
import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.RequestInserts.UsuarioRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.UsuarioRequestUpdate;
import com.sise.portalempleo.pyload.requests.RequestUpdates.UsuarioRequestUpdateClave;
import com.sise.portalempleo.utils.AdapterTemplate;

public class UsuarioAdapter implements AdapterTemplate<Usuario, UsuarioRequestInsert,UsuarioRequestUpdate> {

    @Override
    public Usuario insertToEntity(UsuarioRequestInsert insert) {
        Usuario entity = new Usuario();

        TipoUsuario tUsuario = new TipoUsuario();
        tUsuario.setIdTipoUsuario(insert.getIdTipoUsuario());

        entity.setTipoUsuario(tUsuario);
        entity.setNombreUsuario(insert.getNombreUsuario());
        entity.setEmail(insert.getEmail());
        entity.setClave(insert.getClave());
        return entity;
    }

    @Override
    public Usuario updateToEntity(UsuarioRequestUpdate update) {
        Usuario entity = new Usuario();

        TipoUsuario tUsuario = new TipoUsuario();
        tUsuario.setIdTipoUsuario(update.getIdTipoUsuario());

        entity.setTipoUsuario(tUsuario);
        entity.setNombreUsuario(update.getNombreUsuario());
        entity.setEmail(update.getEmail());
        
        return entity;
    }

    public Usuario updateClaveToEntity(UsuarioRequestUpdateClave updateClave){
        Usuario entity = new Usuario();
        entity.setClave(updateClave.getClave());
        return entity;
    }
    
}
