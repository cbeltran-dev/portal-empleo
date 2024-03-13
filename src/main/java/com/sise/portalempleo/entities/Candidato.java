
package com.sise.portalempleo.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "candidatos")
public class Candidato {

    @Id
    @Column(name = "id_candidato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCantidato;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private String estado;
}