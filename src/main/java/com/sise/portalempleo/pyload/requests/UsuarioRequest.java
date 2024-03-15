package com.sise.portalempleo.pyload.requests;

import com.sise.portalempleo.entities.TipoUsuario;

import lombok.Data;

@Data

public class UsuarioRequest {
    private TipoUsuario tipoUsuario;
    private String nombreUsuario;
    private String email;
    private String clave;
}
