package com.sise.portalempleo.services;
import java.util.List;
import com.sise.portalempleo.entities.Usuario;

public interface UsuarioService {
    List<Usuario> listaUsuarios();
    Usuario listaUsuarioPorId(Integer idUsuario);
    Usuario insertarUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    void darbajaUsuario(Integer idUsuario);
    void cambiarClave(Usuario usuario);
}



