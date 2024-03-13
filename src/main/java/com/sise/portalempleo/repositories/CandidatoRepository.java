package com.sise.portalempleo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sise.portalempleo.entities.Candidato;

import jakarta.transaction.Transactional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Integer>{
    
    @Modifying
    @Transactional
    @Query("UPDATE Candidato c SET c.estado = '0' WHERE c.idCandidato = :id")
    void desactivarCandidato(@Param("id") Integer id);
}
