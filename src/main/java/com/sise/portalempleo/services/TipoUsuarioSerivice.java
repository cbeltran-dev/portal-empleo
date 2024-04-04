package com.sise.portalempleo.services;
import java.util.List;

import com.sise.portalempleo.entities.TipoUsuario;

public interface TipoUsuarioSerivice {
    
    List<TipoUsuario> listaTipoUsuarios();
    TipoUsuario listaTipoUsuarioPorId(Integer idTipoUsuario);
    TipoUsuario insertarTipoUsuario(TipoUsuario tipoUsuario);
    TipoUsuario actualizarTipoUsuario(TipoUsuario tipoUsuario);
    void darbajaTipoUsuario(Integer idTipoUsuario);
}
