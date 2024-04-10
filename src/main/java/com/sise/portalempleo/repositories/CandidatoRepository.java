package com.sise.portalempleo.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sise.portalempleo.entities.Candidato;
import jakarta.transaction.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Integer>{
    List<Candidato> findByEstadoAuditoria(String estadoAuditoria);
    Candidato findOneByIdCandidatoAndEstadoAuditoria(Integer idCandidato, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Candidato c SET c.nombre = :nombre, c.apellidos=:apellidos," +
            "c.tipoDocumento = :tipoDocumento, c.numeroDocumento =:numeroDocumento," +
            " c.descripcion = :descripcion, c.telefono = :telefono, c.disponibilidad = :disponibilidad, " +
            "c.fotoPerfil = :fotoPerfil, c.fechaModificacion = CURRENT_TIMESTAMP " +
            "WHERE c.idCandidato = :id")
    void actualizarCandidato(@Param("id") Integer id,
                            @Param("nombre") String nombre,
                            @Param("apellidos") String apellidos,
                            @Param("tipoDocumento") String tipoDocumento,
                            @Param("numeroDocumento") String numeroDocumento,
                            @Param("descripcion") String descripcion,
                            @Param("telefono") String telefono,
                            @Param("disponibilidad") String disponibilidad,
                            @Param("fotoPerfil") String fotoPerfil);

    @Modifying
    @Transactional
    @Query("UPDATE Candidato e SET e.estadoAuditoria = '0', e.fechaModificacion = CURRENT_TIMESTAMP WHERE e.idCandidato = :id")
    void darBajaCandidato(@Param("id") Integer id);
}
