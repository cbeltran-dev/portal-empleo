package com.sise.portalempleo.utils;

import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.UsuarioRequest;
import com.sise.portalempleo.pyload.responses.UsuarioResponse;

public class MappedUtils {

    public static Usuario toUsuarioEntity(UsuarioRequest usuarioRequest){

        Usuario usuario = new Usuario();
        usuario.setIdTipoUsuario(usuarioRequest.getIdTipoUsuario());
        usuario.setNombreUsuario(usuarioRequest.getNombreUsuario());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setClave(usuarioRequest.getClave());
        return usuario;
    }

    public static UsuarioResponse toUsuarioResponse(Usuario usuario){

        UsuarioResponse response = new UsuarioResponse();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setIdTipoUsuario(usuario.getIdTipoUsuario());
        response.setNombreUsuario(usuario.getNombreUsuario());
        response.setEmail(usuario.getEmail());
        response.setClave(usuario.getClave());
        return response;
        
    }
}
