package com.sise.portalempleo.entities;
import com.sise.portalempleo.shared.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "categoria_empleo")
public class CategoriaEmpleo extends BaseEntity {

    @Id
    @Column(name = "id_categoria_empleo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoriaEmpleo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;


}


