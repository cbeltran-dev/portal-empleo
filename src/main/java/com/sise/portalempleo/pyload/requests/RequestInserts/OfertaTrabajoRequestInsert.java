package com.sise.portalempleo.pyload.requests.RequestInserts;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
@Data
public class OfertaTrabajoRequestInsert {

    @NotNull
    @Positive
    private Integer idEmpleador;

    @NotNull
    @Positive
    private Integer idCategoriaEmpleo;

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

    /*@NotNull
    @NotBlank
    private LocalDateTime fechaPublicacion;*/

    @NotNull
    private LocalDateTime fechaVencimiento;
    
    @NotNull
    @NotBlank
    private String requisitos;

    @NotNull
    @NotBlank
    private String estadoOferta;
}
