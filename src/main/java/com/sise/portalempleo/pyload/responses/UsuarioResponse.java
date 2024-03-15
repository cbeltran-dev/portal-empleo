package com.sise.portalempleo.pyload.responses;
import lombok.Data;

@Data
public class UsuarioResponse {
    private Integer idUsuario;
    private Integer idTipoUsuario;
    private String nombreUsuario;
    private String email;
    private String clave;
}


