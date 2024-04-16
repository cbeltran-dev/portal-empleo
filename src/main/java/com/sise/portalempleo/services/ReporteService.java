package com.sise.portalempleo.services;

public interface ReporteService {
    public byte[] generarInformeOfertasTrabajoPdf();
    public byte[] generarOfertaTrabajoPdf(Integer idOfertaTrabajo);
    
}
