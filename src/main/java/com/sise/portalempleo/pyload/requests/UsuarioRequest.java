package com.sise.portalempleo.pyload.requests;

import com.sise.portalempleo.entities.TipoUsuario;

import lombok.Data;

@Data

public class UsuarioRequest {

    private Integer idUsuario;
    private TipoUsuario idTipoUsuario;
    private String nombre;
    private String email;
    private String clave;
}
