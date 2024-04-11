package com.sise.portalempleo.entities;
import java.sql.Date;
import com.sise.portalempleo.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "registro_postulaciones")
public class RegistroPostulacion extends BaseEntity {
    
    @Id
    @Column(name = "id_registro_postulacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistroPostulacion;

    @ManyToOne
    @JoinColumn(name = "id_candidato")
    private Candidato candidato;

    @ManyToOne
    @JoinColumn(name = "id_oferta_trabajo")
    private OfertaTrabajo ofertaTrabajo;

    @Column(name = "fecha_postulacion")
    private Date fechaPostulacion;

    @Column(name = "curriculum_vitae")
    private String curriculumVitae;

    @Column(name = "estado_postulacion")
    private String estadoPostulacion;
}


