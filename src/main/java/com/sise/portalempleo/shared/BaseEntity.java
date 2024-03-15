
package com.sise.portalempleo.shared;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    private String estadoAuditoria;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion", insertable = false, updatable = false)
    private Date fechaModificacion;
    
}


