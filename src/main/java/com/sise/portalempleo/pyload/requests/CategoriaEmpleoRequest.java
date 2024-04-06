package com.sise.portalempleo.pyload.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoriaEmpleoRequest {
    
    @NotNull
    @NotBlank
    private String nombre;

    @NotBlank
    @NotNull
    private String descripcion;
}
