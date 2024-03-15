package com.sise.portalempleo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "tipo_usuarios")
public class TipoUsuario  {
    @Id
    @Column(name = "id_tipo_usuario")
    private Integer idTipoUsuario;

    @Column(name = "descripcion")
    private String descripcion;
}
