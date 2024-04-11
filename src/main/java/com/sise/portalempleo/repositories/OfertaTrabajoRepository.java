package com.sise.portalempleo.repositories;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sise.portalempleo.entities.OfertaTrabajo;

import jakarta.transaction.Transactional;
@Repository
public interface OfertaTrabajoRepository extends JpaRepository<OfertaTrabajo, Integer> {
    
    List<OfertaTrabajo> findByEstadoAuditoria(String estadoAuditoria);
    OfertaTrabajo findOneByIdOfertaTrabajoAndEstadoAuditoria(Integer idOfertaTrabajo, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE OfertaTrabajo ot SET ot.titulo = :titulo, ot.descripcion=:descripcion, " +
            "ot.disponibilidadHoraria = :disponibilidadHoraria, ot.turno =:turno, " +
            "ot.modalidad = :modalidad, ot.salario = :salario, ot.departamento = :departamento, " +
            "ot.provincia = :provincia, ot.distrito = :distrito, " + "ot.fechaVencimiento = :fechaVencimiento, "+
            "ot.requisitos = :requisitos, ot.estadoOferta = :estadoOferta, "+
            "ot.fechaModificacion = CURRENT_TIMESTAMP WHERE ot.idOfertaTrabajo = :id")
    void actualizarOfertaTrabajo(@Param("id") Integer id,
                                @Param("titulo") String titulo,
                                @Param("descripcion") String descripcion,
                                @Param("disponibilidadHoraria") String disponibilidadHoraria,
                                @Param("turno") String turno,
                                @Param("modalidad") String modalidad,
                                @Param("salario") Double salario,
                                @Param("departamento") String departamento,
                                @Param("provincia") String provincia,
                                @Param("distrito") String distrito,
                                @Param("fechaVencimiento") LocalDateTime fechaVencimiento,
                                @Param("requisitos") String requisitos,
                                @Param("estadoOferta") String estadoOferta);

    @Modifying
    @Transactional
    @Query("UPDATE OfertaTrabajo e SET e.estadoAuditoria = '0', e.fechaModificacion = CURRENT_TIMESTAMP WHERE e.idOfertaTrabajo = :id")
    void darBajaOfertaTrabajo(@Param("id") Integer id);
}
