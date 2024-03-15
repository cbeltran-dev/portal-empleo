package com.sise.portalempleo.pyload.requests;


import lombok.Data;

@Data

public class UsuarioRequest {
    private Integer idTipoUsuario;
    private String nombreUsuario;
    private String email;
    private String clave;
}
