package com.sise.portalempleo.Adapters;

import com.sise.portalempleo.entities.Candidato;
import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.entities.RegistroPostulacion;
import com.sise.portalempleo.pyload.requests.RequestInserts.RegistroPostulacionRequestInsert;
import com.sise.portalempleo.pyload.requests.RequestUpdates.RegistroPostulacionRequestUpdate;
import com.sise.portalempleo.utils.AdapterTemplate;

public class RegistroPostulacionAdapter implements AdapterTemplate<RegistroPostulacion,RegistroPostulacionRequestInsert,RegistroPostulacionRequestUpdate> {

    @Override
    public RegistroPostulacion insertToEntity(RegistroPostulacionRequestInsert insert) {
        RegistroPostulacion entity = new RegistroPostulacion();

        Candidato candidato = new Candidato();
        candidato.setIdCandidato(insert.getIdCandidato());

        OfertaTrabajo ofertaTrabajo = new OfertaTrabajo();
        ofertaTrabajo.setIdOfertaTrabajo(insert.getIdOfertaTrabajo());

        entity.setCandidato(candidato);
        entity.setOfertaTrabajo(ofertaTrabajo);
        entity.setCurriculumVitae(insert.getCurriculumVitae());
        entity.setEstadoPostulacion(insert.getEstadoPostulacion());
        
        return entity;
    }

    @Override
    public RegistroPostulacion updateToEntity(RegistroPostulacionRequestUpdate update) {
        
        RegistroPostulacion entity = new RegistroPostulacion();
        entity.setCurriculumVitae(update.getCurriculumVitae());
        entity.setEstadoPostulacion(update.getEstadoPostulacion());
        
        return entity;
    }
    


}
