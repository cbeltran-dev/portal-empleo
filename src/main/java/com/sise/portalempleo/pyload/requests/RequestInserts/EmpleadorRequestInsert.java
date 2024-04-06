package com.sise.portalempleo.pyload.requests.RequestInserts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EmpleadorRequestInsert {
    
    @NotNull
    @Positive
    private Integer idUsuario;

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
