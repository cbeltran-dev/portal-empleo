package com.sise.portalempleo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.portalempleo.entities.Empleador;

import jakarta.transaction.Transactional;

@Repository
public interface EmpleadorRepository extends JpaRepository<Empleador, Integer>{
    
    List<Empleador> findByEstadoAuditoria(String categoriaEmpleo);
    Empleador findOneByIdEmpleadorAndEstadoAuditoria(Integer idEmpleador, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Empleador e SET e.nombre = :nombre, e.descripcion=:descripcion," +
            "e.ruc =:ruc, e.direccion = :direccion, e.telefono = :telefono," +
            "e.fotoPerfil = :fotoPerfil, e.fechaModificacion = CURRENT_TIMESTAMP " +
            "WHERE e.idEmpleador = :id")
    void actualizarEmpleador(@Param("id") Integer id, @Param("nombre") String nombre,
                            @Param("ruc") String ruc, @Param("descripcion") String descripcion,
                            @Param("direccion") String direccion,@Param("telefono") String telefono,
                            @Param("fotoPerfil") String fotoPerfil);

    @Modifying
    @Transactional
    @Query("UPDATE Empleador e SET e.estadoAuditoria = '0', e.fechaModificacion = CURRENT_TIMESTAMP WHERE e.idEmpleador = :id")
    void darBajaEmpleador(@Param("id") Integer id);
}
