package com.sise.portalempleo.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sise.portalempleo.entities.Candidato;
import com.sise.portalempleo.repositories.CandidatoRepository;
import com.sise.portalempleo.services.CandidatoService;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    CandidatoRepository candidatoRepository;

    @Override
    public Candidato actualizarCandidato(Candidato candidato) {
        candidatoRepository.actualizarCandidato(candidato.getIdCandidato(), 
                                                candidato.getNombre(), 
                                                candidato.getApellidos(), 
                                                candidato.getTipoDocumento(), 
                                                candidato.getNumeroDocumento(), 
                                                candidato.getDescripcion(), 
                                                candidato.getTelefono(), 
                                                candidato.getDisponibilidad(), 
                                                candidato.getFotoPerfil());
        return candidatoRepository.findOneByIdCandidatoAndEstadoAuditoria(candidato.getIdCandidato(), "1");
        
    }

    @Override
    public void darbajaCandidato(Integer idCandidato) {
        candidatoRepository.darBajaCandidato(idCandidato);
        
    }

    @Override
    public Candidato insertarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public Candidato listaCandidatoPorId(Integer idCandidato) {
        return candidatoRepository.findOneByIdCandidatoAndEstadoAuditoria(idCandidato, "1");
    }

    @Override
    public List<Candidato> listaCandidatos() {
        return candidatoRepository.findByEstadoAuditoria("1");
    }
    
}
