
package com.sise.portalempleo.shared;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime fechaModificacion;
    
}


