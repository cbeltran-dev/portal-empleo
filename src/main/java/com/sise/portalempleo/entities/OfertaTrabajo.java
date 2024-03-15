package com.sise.portalempleo.entities;

import java.util.Date;

import lombok.Data;

@Data

public class OfertaTrabajo  {
    

    private Integer idOfertaTrabajo;


    private CategoriaEmpleo categoriaEmpleo;


    private String titulo;


    private String descripcion;


    private String disponibilidadHoraria;


    private String modalidad;


    private String salario;


    private String departamento;


    private String provincia;


    private String distrito;


    private Date fechaPublicacion;


    private Date fechaVencimiento;

    private String requisitos;


    private String estadoOferta;

}
