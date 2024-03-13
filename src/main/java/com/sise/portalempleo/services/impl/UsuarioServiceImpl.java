package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.repositories.UsuarioRespository;
import com.sise.portalempleo.services.UsuarioService;
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Override
    public List<Usuario> listaUsuarios() {
        return usuarioRespository.findAll();
    }
    
}
