package com.sise.portalempleo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.portalempleo.services.ReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    ReporteService reporteService;

    @GetMapping("/generarOfertaTrabajoPdf")
    public ResponseEntity<byte[]> generarReporteOfertasTrabajoPdf() {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            // Para forzar la descarga del archivo (descomenta la línea siguiente)
            // headers.setContentDisposition(ContentDisposition.attachment().filename("proforma.pdf").build());

            // Para mostrar el PDF en el navegador (descomenta la línea siguiente)
            headers.setContentDisposition(ContentDisposition.inline().filename("ofertas_activas.pdf").build());

            return new ResponseEntity<>(
                    reporteService.generarInformeOfertasTrabajoPdf(),
                    headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(
                    new byte[] {},
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/generarOfertaTrabajoPdf/{idOfertaTrabajo}")
    public ResponseEntity<byte[]> generarProformaPdf(@PathVariable Integer idOfertaTrabajo) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);

            // Para forzar la descarga del archivo (descomenta la línea siguiente)
            // headers.setContentDisposition(ContentDisposition.attachment().filename("proforma.pdf").build());

            // Para mostrar el PDF en el navegador (descomenta la línea siguiente)
            headers.setContentDisposition(ContentDisposition.inline().filename("OfertaTrabajo.pdf").build());

            return new ResponseEntity<>(
                    reporteService.generarOfertaTrabajoPdf(idOfertaTrabajo),
                    headers,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<byte[]>(
                    new byte[] {},
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}