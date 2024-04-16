package com.sise.portalempleo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.sise.portalempleo.entities.OfertaTrabajo;
import com.sise.portalempleo.repositories.OfertaTrabajoRepository;
import com.sise.portalempleo.services.ReporteService;

@Service
public class ReporteServiceImpl implements ReporteService {

    private static final String COLOR_PRIMARY = "#3498db";
    private static final String COLOR_HEADER = "#4a90e2"; 
    private static final String COLOR_TITLE = "#ffffff";
    private static final String COLOR_CATEGORY = "#27ae60";
    private static final String COLOR_FOOTER_BG = "#34495e";
    private static final String COLOR_FOOTER_TEXT = "#ffffff";
    private static final String CARD_BACKGROUND = "#f5f5f5"; 
    private static final String CARD_COLOR = "#333"; 

    @Autowired
    private OfertaTrabajoRepository ofertaTrabajoRepository;

    @Override
    public byte[] generarInformeOfertasTrabajoPdf() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        List<OfertaTrabajo> ofertasActivas = ofertaTrabajoRepository.findByEstadoOfertaOrderByCategoriaEmpleo("Activa");

        String htmlContent = generarHtmlOfertasTrabajo(ofertasActivas);

        HtmlConverter.convertToPdf(htmlContent, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }

    private String generarHtmlOfertasTrabajo(List<OfertaTrabajo> ofertas) {
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<!DOCTYPE html>");
        htmlBuilder.append("<html>");
        htmlBuilder.append("<head>");
        htmlBuilder.append("<style>");
        htmlBuilder.append(generateCss());
        htmlBuilder.append("</style>");
        htmlBuilder.append("</head>");
        htmlBuilder.append("<body>");

        htmlBuilder.append("<div id=\"header\" style=\"background-color: ").append(COLOR_HEADER).append(";\">");
        htmlBuilder.append("<div id=\"header-content\">");
        htmlBuilder.append("<div id=\"left-section\">");
        htmlBuilder.append("<h2 style=\"font-family: 'Arial Narrow', sans-serif; color: ").append(COLOR_TITLE).append(";\">Laboralia</h2>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<div id=\"center-section\">");
        htmlBuilder.append("<h1 style=\"font-family: 'Montserrat', sans-serif; color: ").append(COLOR_TITLE).append(";\">Ofertas de Trabajo</h1>");
        htmlBuilder.append("<p style=\"font-family: 'Roboto', sans-serif; color: ").append(COLOR_TITLE).append(";\">www.laboralia.com</p>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<div id=\"right-section\">");
        htmlBuilder.append("<p style=\"font-family: 'Tahoma', sans-serif; color: ").append(COLOR_TITLE).append(";\">Fecha: ").append(LocalDate.now()).append("</p>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("</div>");

        Map<String, List<OfertaTrabajo>> ofertasPorCategoria = new LinkedHashMap<>();
        for (OfertaTrabajo oferta : ofertas) {
            String categoria = oferta.getCategoriaEmpleo().getNombre();
            ofertasPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(oferta);
        }

        for (Map.Entry<String, List<OfertaTrabajo>> entry : ofertasPorCategoria.entrySet()) {
            htmlBuilder.append("<div class=\"categoria\">");
            htmlBuilder.append("<h2 style=\"font-family: 'Arial', sans-serif; color: ").append(COLOR_CATEGORY).append(";\">").append(entry.getKey()).append("</h2>");
            htmlBuilder.append("<div class=\"ofertas\">");

            for (OfertaTrabajo oferta : entry.getValue()) {
                htmlBuilder.append("<div class=\"card\">"); // Cambio a diseño de tarjeta
                htmlBuilder.append("<h3 style=\"font-family: 'Roboto', sans-serif; color: ").append(COLOR_PRIMARY).append(";\">").append(oferta.getTitulo()).append("</h3>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR).append(";\">").append("<strong>Empleador:</strong> ").append(oferta.getEmpleador().getNombre()).append("</p>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR).append(";\">").append("<strong>Fecha de Publicación:</strong> ").append(oferta.getFechaPublicacion()).append("</p>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR).append(";\">").append("<strong>Fecha de Vencimiento:</strong> ").append(oferta.getFechaVencimiento()).append("</p>");
                htmlBuilder.append("</div>");
            }

            htmlBuilder.append("</div>");
            htmlBuilder.append("</div>");
        }


        htmlBuilder.append("<div id=\"footer\" style=\"background-color: ").append(COLOR_FOOTER_BG).append("; color: ").append(COLOR_FOOTER_TEXT).append(";\">");
        htmlBuilder.append("<p>Dirección web: www.TRABAJOCOMPU.com | País de origen: Perú | Año: 2024</p>");
        htmlBuilder.append("</div>");

    
        htmlBuilder.append("</body>");
        htmlBuilder.append("</html>");

        return htmlBuilder.toString();
    }

    private String generateCss() {
        StringBuilder cssBuilder = new StringBuilder();

        cssBuilder.append("body { font-family: 'Arial', sans-serif; margin: 0; padding: 0; }");
        cssBuilder.append("#header { background-color: ").append(COLOR_HEADER).append("; padding: 20px 0; }");
        cssBuilder.append("#header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }");
        cssBuilder.append("#left-section, #right-section { flex-basis: 20%; }");
        cssBuilder.append("#center-section { flex-grow: 1; text-align: center; }");
        cssBuilder.append("#left-section h2 { margin: 5px 0; font-size: 24px; }");
        cssBuilder.append("#left-section p { margin: 5px 0; font-size: 14px; }");
        cssBuilder.append("#center-section h1 { margin: 5px 0; font-size: 28px; margin-bottom: 10px; }");
        cssBuilder.append(".categoria { margin-bottom: 30px; }");
        cssBuilder.append(".categoria h2 { margin-bottom: 10px; font-size: 20px; }");
        cssBuilder.append(".ofertas { display: flex; flex-wrap: wrap; justify-content: space-around; }");
        cssBuilder.append(".card { width: calc(33.33% - 20px); background-color: ").append(CARD_BACKGROUND).append("; border-radius: 8px; padding: 20px; margin-bottom: 20px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }"); // Estilo de tarjeta
        cssBuilder.append(".card h3 { font-size: 18px; margin-bottom: 10px; }");
        cssBuilder.append(".card p { font-size: 14px; margin-bottom: 8px; }");
        cssBuilder.append("#right-section p { font-size: 14px; margin: 5px 0; text-align: right; }");
        cssBuilder.append("#footer { text-align: center; font-size: 12px; margin-top: 20px; padding: 10px 0; }");

        return cssBuilder.toString();
    }
}