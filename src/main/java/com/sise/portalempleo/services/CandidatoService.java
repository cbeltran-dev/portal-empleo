package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.Candidato;

public interface CandidatoService {
    List<Candidato> listarCandidato();
    Candidato listarCandidatoPorId(Integer id);
    Candidato insertarCandidato(Candidato candidato);
    Candidato actualizarCandidato(Candidato candidato);
    void desactivarCandidato(Integer id);
}
