package com.sise.portalempleo.entities;
import com.sise.portalempleo.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "candidatos")
public class Candidato extends BaseEntity  {

    @Id
    @Column(name = "id_candidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCandidato;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "disponibilidad")
    private String disponibilidad;

    @Column(name = "foto_perfil")
    private String fotoPerfil;
}


