package com.sise.portalempleo.pyload.responses;

import com.sise.portalempleo.entities.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioResponse {
    
    private Integer idUsuario;
    private TipoUsuario tipoUsuario;
    private String nombreUsuario;
    private String email;
    private String clave;
}
