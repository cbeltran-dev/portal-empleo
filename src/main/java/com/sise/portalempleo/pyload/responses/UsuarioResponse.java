package com.sise.portalempleo.pyload.responses;

import com.sise.portalempleo.entities.TipoUsuario;

import lombok.Data;

@Data
public class UsuarioResponse {
    private TipoUsuario idTipoUsuario;
    private String nombre;
    private String email;
    private String clave;
}
