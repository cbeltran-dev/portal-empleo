package com.sise.portalempleo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.portalempleo.entities.CategoriaEmpleo;

import jakarta.transaction.Transactional;

@Repository
public interface CategoriaEmpleoRepository extends JpaRepository<CategoriaEmpleo,Integer>{
    
    List<CategoriaEmpleo> findByEstadoAuditoria(String categoriaEmpleo);
    CategoriaEmpleo findOneByIdCategoriaEmpleoAndEstadoAuditoria(Integer idCategoriaEmpleo, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE CategoriaEmpleo c SET c.nombre = :nombre, " +
            "c.descripcion = :descripcion, " +
            "c.fechaModificacion = CURRENT_TIMESTAMP " +
            "WHERE c.idCategoriaEmpleo = :id")
    void actualizarCategoriaEmpleo (@Param("id") Integer id, 
                                    @Param("nombre") String nombre, 
                                    @Param("descripcion") String descripcion);

    @Modifying
    @Transactional
    @Query("UPDATE CategoriaEmpleo c SET c.estadoAuditoria = '0', c.fechaModificacion = CURRENT_TIMESTAMP WHERE c.idCategoriaEmpleo = :id")
    void darBajaCategoriaEmpleo(@Param("id") Integer id);
}
