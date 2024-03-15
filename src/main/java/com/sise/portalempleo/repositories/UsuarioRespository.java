package com.sise.portalempleo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sise.portalempleo.entities.Usuario;

public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {

    
}
