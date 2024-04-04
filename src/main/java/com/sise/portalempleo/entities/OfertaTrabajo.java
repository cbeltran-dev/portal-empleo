package com.sise.portalempleo.entities;
import java.time.LocalDateTime;
import java.util.Date;
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
@Table(name = "oferta_trabajo")
public class OfertaTrabajo extends BaseEntity  {

    @Id
    @Column(name = "id_oferta_trabajo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOfertaTrabajo;

    @ManyToOne
    @JoinColumn(name = "id_categoria_empleo")
    private CategoriaEmpleo categoriaEmpleo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "disponibilidad_horaria")
    private String disponibilidadHoraria;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "estado_oferta")
    private String estadoOferta;

}


