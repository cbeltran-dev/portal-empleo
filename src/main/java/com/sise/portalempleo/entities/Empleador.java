package com.sise.portalempleo.entities;
import com.sise.portalempleo.shared.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Empleador extends BaseEntity {

    private Integer idEmpleador;
    private Usuario usuario;
    private String nombre;
    private String ruc;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String fotoPerfil;
}
