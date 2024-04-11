package com.sise.portalempleo.pyload.requests.RequestUpdates;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroPostulacionRequestUpdate {
    
    

    @NotNull
    @NotBlank
    private String curriculumVitae;

    @NotNull
    @NotBlank
    private String  estadoPostulacion;
}
