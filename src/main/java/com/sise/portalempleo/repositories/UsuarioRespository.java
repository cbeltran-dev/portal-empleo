package com.sise.portalempleo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.portalempleo.entities.Usuario;

import jakarta.transaction.Transactional;


@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Usuario s SET s.estadoAuditoria = '0' WHERE s.idUsuario = :id")
    void darbajaUsuario(@Param("id") Integer id); 


    List<Usuario> findByEstadoAuditoria(String estadoAuditoria);
    Usuario findOneByIdUsuarioAndEstadoAuditoria(Integer idUsuario, String estadoAuditoria);
}

