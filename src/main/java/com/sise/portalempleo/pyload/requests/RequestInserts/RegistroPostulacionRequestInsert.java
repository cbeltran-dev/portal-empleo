package com.sise.portalempleo.pyload.requests.RequestInserts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RegistroPostulacionRequestInsert {
    
    @NotNull
    @Positive
    private Integer idCandidato;

    @NotNull
    @Positive
    private Integer idOfertaTrabajo;

    @NotNull
    @NotBlank
    private String curriculumVitae;

    @NotNull
    @NotBlank
    private String  estadoPostulacion;

}
