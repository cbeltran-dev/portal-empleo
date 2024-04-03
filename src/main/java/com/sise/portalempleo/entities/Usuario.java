package com.sise.portalempleo.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuario tipoUsuario;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "email")
    private String email;

    @Column(name = "clave")
    @JsonIgnore
    private String clave;
}
