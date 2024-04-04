package com.sise.portalempleo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sise.portalempleo.entities.TipoUsuario;

import jakarta.transaction.Transactional;



@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    
    List<TipoUsuario> findByEstadoAuditoria(String estadoAuditoria);
    TipoUsuario findOneByIdTipoUsuarioAndEstadoAuditoria(Integer idTipoUsuario, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE TipoUsuario t SET t.descripcion = :descripcion, t.fechaModificacion = CURRENT_TIMESTAMP WHERE t.idTipoUsuario = :id")
    void actualizarTipoUsuario (@Param("id") Integer id, @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query("UPDATE TipoUsuario t SET t.estadoAuditoria = '0', t.fechaModificacion = CURRENT_TIMESTAMP WHERE t.idTipoUsuario = :id")
    void darbajaUsuario(@Param("id") Integer id);
}
