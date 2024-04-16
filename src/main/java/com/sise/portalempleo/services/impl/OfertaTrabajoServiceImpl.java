package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.repositories.OfertaTrabajoRepository;
import com.sise.portalempleo.services.OfertaTrabajoService;



@Service
public class OfertaTrabajoServiceImpl implements OfertaTrabajoService {

    @Autowired
    OfertaTrabajoRepository ofertaTrabajoRepository;

    @Override
    public List<OfertaTrabajo> listaOfertaTrabajo() {
        return ofertaTrabajoRepository.findByEstadoAuditoria("1");
    }

    @Override
    public OfertaTrabajo listaOfertaTrabajoPorId(Integer idOfertaTrabajo) {
        return ofertaTrabajoRepository.findOneByIdOfertaTrabajoAndEstadoAuditoria(idOfertaTrabajo, "1");
    }

    @Override
    public OfertaTrabajo insertarOfertaTrabajo(OfertaTrabajo ofertaTrabajo) {
        return ofertaTrabajoRepository.save(ofertaTrabajo);
    }

    @Override
    public OfertaTrabajo actualizarOfertaTrabajo(OfertaTrabajo ofertaTrabajo) {
        ofertaTrabajoRepository.actualizarOfertaTrabajo(ofertaTrabajo.getIdOfertaTrabajo(),
                                                        ofertaTrabajo.getTitulo(),
                                                        ofertaTrabajo.getDescripcion(),
                                                        ofertaTrabajo.getDisponibilidadHoraria(), 
                                                        ofertaTrabajo.getTurno(), 
                                                        ofertaTrabajo.getModalidad(), 
                                                        ofertaTrabajo.getSalario(), 
                                                        ofertaTrabajo.getDepartamento(), 
                                                        ofertaTrabajo.getProvincia(), 
                                                        ofertaTrabajo.getDistrito(), 
                                                        ofertaTrabajo.getFechaVencimiento(), 
                                                        ofertaTrabajo.getRequisitos(), 
                                                        ofertaTrabajo.getEstadoOferta());
        return ofertaTrabajoRepository.findOneByIdOfertaTrabajoAndEstadoAuditoria(ofertaTrabajo.getIdOfertaTrabajo(), "1");
    }

    @Override
    public void darbajaOfertaTrabajo(Integer idOfertaTrabajo) {
        ofertaTrabajoRepository.darBajaOfertaTrabajo(idOfertaTrabajo);
    }

    @Override
    public List<OfertaTrabajo> buscarPorCategoriaEmpleo(Integer idCategoriaEmpleo) {
        return ofertaTrabajoRepository.findByCategoriaEmpleoIdCategoriaEmpleo(idCategoriaEmpleo);
    }

    @Override
    public OfertaTrabajo activarDesactivarOferta(OfertaTrabajo ofertaTrabajo) {
        
        if (ofertaTrabajo == null) {
            return null;
        }
        OfertaTrabajo ofertaExistente = ofertaTrabajoRepository.findOneByIdOfertaTrabajoAndEstadoAuditoria(ofertaTrabajo.getIdOfertaTrabajo(), "1");
        switch (ofertaExistente.getEstadoOferta()) {
            case "Activa":
                ofertaTrabajoRepository.desactivarOferta(ofertaExistente.getIdOfertaTrabajo());
                ofertaExistente.setEstadoOferta("Inactiva");
                break;
            case "Inactiva":
                ofertaTrabajoRepository.activarOferta(ofertaExistente.getIdOfertaTrabajo());
                ofertaExistente.setEstadoOferta("Activa");
                break;
        }
        return ofertaTrabajoRepository.findOneByIdOfertaTrabajoAndEstadoAuditoria(ofertaTrabajo.getIdOfertaTrabajo(), "1");
    }
}
