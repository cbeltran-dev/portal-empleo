package com.sise.portalempleo.entities;

import java.sql.Date;

import com.sise.portalempleo.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "registro_postulaciones")
public class RegistroPostulacion extends BaseEntity {
    
    @Id
    @Column(name = "id_registro_postulaciones")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRegistroPostulacion;

    @Column(name = "candidato")
    private Candidato candidato;

    @Column(name = "oferta_trabajo")
    private OfertaTrabajo ofertaTrabajo;

    @Column(name = "fecha_postulacion")
    private Date fechaPostulacion;

    @Column(name = "estado_postulacion")
    private String estadoPostulacion;

}
