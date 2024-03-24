package com.sise.portalempleo.entities;
import com.sise.portalempleo.shared.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CategoriaEmpleo extends BaseEntity {
    private Integer idCategoria;
    private String nombre;
    private String descripcion;

}


