package com.sise.portalempleo.entities;

import java.util.Date;

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
@Table(name = "oferta_trabajo")
public class OfertaTrabajo extends BaseEntity {
    

    @Id
    @Column(name = "id_oferta_trabajo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOfertaTrabajo;

    @Column(name = "id_categoria_empleo")
    private CategoriaEmpleo categoriaEmpleo;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "disponibilidad_horaria")
    private String disponibilidadHoraria;

    @Column(name = "modalidad")
    private String modalidad;

    @Column(name = "salarios")
    private String salario;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "distrito")
    private String distrito;

    @Column(name = "fecha_publicacion")
    private Date fechaPublicacion;

    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "estado_oferta")
    private String estadoOferta;

}
