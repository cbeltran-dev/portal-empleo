package com.sise.portalempleo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sise.portalempleo.entities.Candidato;
import com.sise.portalempleo.repositories.CandidatoRepository;
import com.sise.portalempleo.services.CandidatoService;


public class CandidatoServiceImpl implements CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public List<Candidato> listarCandidato() {
        return candidatoRepository.findAll();
    }

    @Override
    public Candidato listarCandidatoPorId(Integer id) {
        return candidatoRepository.findById(id).orElse(null);
    }

    @Override
    public Candidato insertarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public Candidato actualizarCandidato(Candidato candidato) {
        return candidatoRepository.save(candidato);
    }

    @Override
    public void desactivarCandidato(Integer id) {
        candidatoRepository.desactivarCandidato(id);
    }
    
}
