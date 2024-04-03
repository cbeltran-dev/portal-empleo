package com.sise.portalempleo.pyload.requests.RequestInserts;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestInsert {

    @NotNull
    @Positive
    private Integer idTipoUsuario;

    @NotNull
    @NotBlank
    private String nombreUsuario;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 8, max = 20)
    private String clave;
}
