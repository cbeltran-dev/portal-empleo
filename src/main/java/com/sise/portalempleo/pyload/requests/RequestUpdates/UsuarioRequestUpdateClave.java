package com.sise.portalempleo.pyload.requests.RequestUpdates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestUpdateClave {
    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    private String clave;
}
