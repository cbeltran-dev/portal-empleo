package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.Candidato;

public interface CandidatoService {
    
    List<Candidato> listaCandidatos();
    Candidato listaCandidatoPorId(Integer idCandidato);
    Candidato insertarCandidato(Candidato candidato);
    Candidato actualizarCandidato(Candidato candidato);
    void darbajaCandidato(Integer idCandidato);
}
