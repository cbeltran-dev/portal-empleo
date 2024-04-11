package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.portalempleo.entities.RegistroPostulacion;
import com.sise.portalempleo.repositories.RegistroPostulacionRepository;
import com.sise.portalempleo.services.RegistroPostulacionService;

@Service
public class RegistroPostulacionServiceImpl implements RegistroPostulacionService {

    @Autowired
    RegistroPostulacionRepository registroPostulacionRepository;

    @Override
    public List<RegistroPostulacion> listaRegistroPostulacion() {
        return registroPostulacionRepository.findByEstadoAuditoria("1");
    }

    @Override
    public RegistroPostulacion listaRegistroPostulacionPorId(Integer idRegistroPostulacion) {
        return registroPostulacionRepository.findOneByIdRegistroPostulacionAndEstadoAuditoria(idRegistroPostulacion, "1");
    }

    @Override
    public RegistroPostulacion insertarRegistroPostulacion(RegistroPostulacion registroPostulacion) {
        return registroPostulacionRepository.save(registroPostulacion);
    }

    @Override
    public RegistroPostulacion actualizarRegistroPostulacion(RegistroPostulacion registroPostulacion) {
        registroPostulacionRepository.actualizarRegistroPostulacion(registroPostulacion.getIdRegistroPostulacion(), 
        registroPostulacion.getCurriculumVitae(),
        registroPostulacion.getEstadoPostulacion());
        return registroPostulacionRepository.findOneByIdRegistroPostulacionAndEstadoAuditoria(registroPostulacion.getIdRegistroPostulacion(), "1");
    }

    @Override
    public void darbajaRegistroPostulacion(Integer idRegistroPostulacion) {
        registroPostulacionRepository.darBajaRegistroPostulacion(idRegistroPostulacion);
    }
    
}
