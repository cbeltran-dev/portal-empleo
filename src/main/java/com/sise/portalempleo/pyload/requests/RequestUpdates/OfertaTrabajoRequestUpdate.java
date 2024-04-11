package com.sise.portalempleo.pyload.requests.RequestUpdates;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OfertaTrabajoRequestUpdate {
    

    @NotNull
    @NotBlank
    private String titulo;

    @NotNull
    @NotBlank
    private String descripcion;

    @NotNull
    @NotBlank
    private String disponibilidadHoraria;

    @NotNull
    @NotBlank
    private String turno;

    @NotNull
    @NotBlank
    private String modalidad;

    @NotNull
    @Positive
    private Double salario;

    @NotNull
    @NotBlank
    private String departamento;

    @NotNull
    @NotBlank
    private String provincia;

    @NotNull
    @NotBlank
    private String distrito;

    @NotNull
    private LocalDateTime fechaVencimiento;
    
    @NotNull
    @NotBlank
    private String requisitos;

    @NotNull
    @NotBlank
    private String estadoOferta;
}
