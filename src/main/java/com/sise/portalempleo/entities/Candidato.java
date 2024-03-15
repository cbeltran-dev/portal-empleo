package com.sise.portalempleo.entities;
import com.sise.portalempleo.shared.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class Candidato extends BaseEntity  {

    private Integer idCandidato;
    private Usuario usuario;
    private String nombre;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private String descripcion;
    private String telefono;
    private String disponibilidad;
    private String fotoPerfil;
    private String categoriaInteres;
    private String curriculumVitae;
}


