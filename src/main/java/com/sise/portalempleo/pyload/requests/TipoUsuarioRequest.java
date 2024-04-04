package com.sise.portalempleo.pyload.requests;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TipoUsuarioRequest {
    
    @NotBlank
    @NotNull
    private String descripcion;
}
