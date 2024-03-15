package com.sise.portalempleo.entities;
import lombok.Data;
@Data
public class Candidato  {

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
