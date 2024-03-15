package com.sise.portalempleo.entities;

import lombok.Data;

@Data

public class Empleador  {
    

    private Integer idEmpleador;


    private Usuario usuario;


    private String nombre;


    private String ruc;


    private String descripcion;


    private String direccion;


    private String telefono;

    private String fotoPerfil;
}
