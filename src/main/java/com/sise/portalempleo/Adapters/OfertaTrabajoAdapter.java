package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.CategoriaEmpleo;
import com.sise.portalempleo.entities.Empleador;
import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.pyload.requests.RequestInserts.OfertaTrabajoRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.OfertaTrabajoRequestUpdate;
import com.sise.portalempleo.utils.AdapterTemplate;

public class OfertaTrabajoAdapter implements AdapterTemplate<OfertaTrabajo,OfertaTrabajoRequestInsert,OfertaTrabajoRequestUpdate>{

    @Override
    public OfertaTrabajo insertToEntity(OfertaTrabajoRequestInsert insert) {
        
        OfertaTrabajo entity = new OfertaTrabajo();

        Empleador empleador = new Empleador();
        empleador.setIdEmpleador(insert.getIdEmpleador());

        CategoriaEmpleo categoriaEmpleo = new CategoriaEmpleo();
        categoriaEmpleo.setIdCategoriaEmpleo(insert.getIdCategoriaEmpleo());

        entity.setEmpleador(empleador);
        entity.setCategoriaEmpleo(categoriaEmpleo);
        entity.setTitulo(insert.getTitulo());
        entity.setDescripcion(insert.getDescripcion());
        entity.setDisponibilidadHoraria(insert.getDisponibilidadHoraria());
        entity.setTurno(insert.getTurno());
        entity.setModalidad(insert.getModalidad());
        entity.setSalario(insert.getSalario());
        entity.setDepartamento(insert.getDepartamento());
        entity.setProvincia(insert.getProvincia());
        entity.setDistrito(insert.getDistrito());
        entity.setFechaVencimiento(insert.getFechaVencimiento());
        entity.setRequisitos(insert.getRequisitos());
        entity.setEstadoOferta(insert.getEstadoOferta());
        return entity;
    }

    @Override
    public OfertaTrabajo updateToEntity(OfertaTrabajoRequestUpdate update) {
        OfertaTrabajo entity = new OfertaTrabajo();
        
        entity.setTitulo(update.getTitulo());
        entity.setDescripcion(update.getDescripcion());
        entity.setDisponibilidadHoraria(update.getDisponibilidadHoraria());
        entity.setTurno(update.getTurno());
        entity.setModalidad(update.getModalidad());
        entity.setSalario(update.getSalario());
        entity.setDepartamento(update.getDepartamento());
        entity.setProvincia(update.getProvincia());
        entity.setDistrito(update.getDistrito());
        entity.setFechaVencimiento(update.getFechaVencimiento());
        entity.setRequisitos(update.getRequisitos());
        entity.setEstadoOferta(update.getEstadoOferta());
        return entity;
    }
    
}
