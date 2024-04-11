package com.sise.portalempleo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sise.portalempleo.entities.RegistroPostulacion;

import jakarta.transaction.Transactional;

@Repository
public interface RegistroPostulacionRepository extends JpaRepository<RegistroPostulacion, Integer> {
    
    List<RegistroPostulacion> findByEstadoAuditoria(String registroPostulacion);
    RegistroPostulacion findOneByIdRegistroPostulacionAndEstadoAuditoria(Integer idRegistroPostulacion, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE RegistroPostulacion rp SET rp.curriculumVitae = :curriculumVitae, " +
            "rp.estadoPostulacion = :estadoPostulacion, " +
            "rp.fechaModificacion = CURRENT_TIMESTAMP " +
            "WHERE rp.idRegistroPostulacion = :id")
    void actualizarRegistroPostulacion (@Param("id") Integer id,
                                    @Param("curriculumVitae") String curriculumVitae,
                                    @Param("estadoPostulacion") String estadoPostulacion);

    @Modifying
    @Transactional
    @Query("UPDATE RegistroPostulacion rp SET rp.estadoAuditoria = '0', rp.fechaModificacion = CURRENT_TIMESTAMP WHERE rp.idRegistroPostulacion = :id")
    void darBajaRegistroPostulacion(@Param("id") Integer id);

}
