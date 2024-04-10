package com.sise.portalempleo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.portalempleo.entities.TipoUsuario;
import com.sise.portalempleo.entities.Usuario;
import jakarta.transaction.Transactional;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.estadoAuditoria = '0', u.fechaModificacion = CURRENT_TIMESTAMP WHERE u.idUsuario = :id")
    void darbajaUsuario(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET  u.tipoUsuario =:tipoUsuario, u.nombreUsuario =:nombreUsuario, u.email=:email, u.fechaModificacion = CURRENT_TIMESTAMP WHERE u.idUsuario = :id")
    void actualizarUsuario(@Param("id") Integer id, 
                            @Param("tipoUsuario") TipoUsuario tipoUsuario,
                            @Param("nombreUsuario") String nombreUsuario,
                            @Param("email") String email );
    
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.clave = :clave, u.fechaModificacion = CURRENT_TIMESTAMP WHERE u.idUsuario = :id")
    void cambiarClaveUsuario(@Param("id")Integer id, @Param("clave") String clave);

    List<Usuario> findByEstadoAuditoria(String estadoAuditoria);
    Usuario findOneByIdUsuarioAndEstadoAuditoria(Integer idUsuario, String estadoAuditoria);
}




