package com.sise.portalempleo.pyload.requests.RequestInserts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CandidatoRequestInsert {
    @NotNull
    @Positive
    private Integer idUsuario;

    @NotNull
    @NotBlank
    private String nombre;

    @NotNull
    @NotBlank
    private String apellidos;

    @NotNull
    @NotBlank
    private String tipoDocumento;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 11)
    private String numeroDocumento;

    @NotNull
    @NotBlank
    private String descripcion;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    private String telefono;

    @NotNull
    @NotBlank
    private String disponibilidad;

    @NotNull
    @NotBlank
    private String fotoPerfil;

}
