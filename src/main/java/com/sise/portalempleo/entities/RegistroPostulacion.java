package com.sise.portalempleo.entities;
import java.sql.Date;
import com.sise.portalempleo.shared.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class RegistroPostulacion extends BaseEntity {
    
    private Integer idRegistroPostulacion;
    private Candidato candidato;
    private OfertaTrabajo ofertaTrabajo;
    private Date fechaPostulacion;
    private String curriculumVitae;
    private String estadoPostulacion;
}


