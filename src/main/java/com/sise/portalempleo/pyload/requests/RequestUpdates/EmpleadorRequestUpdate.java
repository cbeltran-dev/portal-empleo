package com.sise.portalempleo.pyload.requests.RequestUpdates;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpleadorRequestUpdate {
    
    /*@NotNull
    @NotBlank
    private Usuario usuario;*/

    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @NotBlank
    private String descripcion;

    @NotNull
    @NotBlank
    private String ruc;

    @NotNull
    @NotBlank
    private String direccion;

    @NotNull
    @NotBlank
    private String telefono;

    @NotNull
    @NotBlank
    private String fotoPerfil;

}
