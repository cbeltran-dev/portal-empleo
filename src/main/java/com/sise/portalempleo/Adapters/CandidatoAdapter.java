package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.Candidato;
import com.sise.portalempleo.entities.Usuario;
import com.sise.portalempleo.pyload.requests.RequestInserts.CandidatoRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.CandidatoRequestUpdate;
import com.sise.portalempleo.utils.AdapterTemplate;

public class CandidatoAdapter implements AdapterTemplate<Candidato,CandidatoRequestInsert,CandidatoRequestUpdate> {

    @Override
    public Candidato insertToEntity(CandidatoRequestInsert insert) {
        Candidato entity = new Candidato();

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(insert.getIdUsuario());

                entity.setUsuario(usuario);
                entity.setNombre(insert.getNombre());
                entity.setApellidos(insert.getApellidos());
                entity.setTipoDocumento(insert.getTipoDocumento());
                entity.setNumeroDocumento(insert.getNumeroDocumento());
                entity.setDescripcion(insert.getDescripcion());              
                entity.setTelefono(insert.getTelefono());
                entity.setDisponibilidad(insert.getDisponibilidad());
                entity.setFotoPerfil(insert.getFotoPerfil());
                return entity;
    }

    @Override
    public Candidato updateToEntity(CandidatoRequestUpdate update) {
        Candidato entity = new Candidato();

        
                entity.setNombre(update.getNombre());
                entity.setApellidos(update.getApellidos());
                entity.setTipoDocumento(update.getTipoDocumento());
                entity.setNumeroDocumento(update.getNumeroDocumento());
                entity.setDescripcion(update.getDescripcion());              
                entity.setTelefono(update.getTelefono());
                entity.setDisponibilidad(update.getDisponibilidad());
                entity.setFotoPerfil(update.getFotoPerfil());
                return entity;
    }
    
}
