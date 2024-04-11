package com.sise.portalempleo.services;

import java.util.List;

import com.sise.portalempleo.entities.OfertaTrabajo;

public interface OfertaTrabajoService  {
    
    List<OfertaTrabajo> listaOfertaTrabajo();
    OfertaTrabajo listaOfertaTrabajoPorId(Integer idOfertaTrabajo);
    OfertaTrabajo insertarOfertaTrabajo(OfertaTrabajo ofertaTrabajo);
    OfertaTrabajo actualizarOfertaTrabajo(OfertaTrabajo ofertaTrabajo);
    void darbajaOfertaTrabajo(Integer idOfertaTrabajo);

}
