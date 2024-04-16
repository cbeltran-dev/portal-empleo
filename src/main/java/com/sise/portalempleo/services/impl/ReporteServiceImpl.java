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
        htmlBuilder.append("<h2 style=\"font-family: 'Arial Narrow', sans-serif; color: ").append(COLOR_TITLE)
                .append(";\">TrabajoCompu</h2>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<div id=\"center-section\">");
        htmlBuilder.append("<h1 style=\"font-family: 'Montserrat', sans-serif; color: ").append(COLOR_TITLE)
                .append(";\">Ofertas de Trabajo</h1>");
        htmlBuilder.append("<p style=\"font-family: 'Roboto', sans-serif; color: ").append(COLOR_TITLE)
                .append(";\">www.trabajocompu.com</p>");
        htmlBuilder.append("</div>");
        htmlBuilder.append("<div id=\"right-section\">");
        htmlBuilder.append("<p style=\"font-family: 'Tahoma', sans-serif; color: ").append(COLOR_TITLE)
                .append(";\">Fecha: ").append(LocalDate.now()).append("</p>");
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
            htmlBuilder.append("<h2 style=\"font-family: 'Arial', sans-serif; color: ").append(COLOR_CATEGORY)
                    .append(";\">").append(entry.getKey()).append("</h2>");
            htmlBuilder.append("<div class=\"ofertas\">");

            for (OfertaTrabajo oferta : entry.getValue()) {
                htmlBuilder.append("<div class=\"card\">"); // Cambio a diseño de tarjeta
                htmlBuilder.append("<h3 style=\"font-family: 'Roboto', sans-serif; color: ").append(COLOR_PRIMARY)
                        .append(";\">").append(oferta.getTitulo()).append("</h3>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR)
                        .append(";\">").append("<strong>Empleador:</strong> ").append(oferta.getEmpleador().getNombre())
                        .append("</p>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR)
                        .append(";\">").append("<strong>Fecha de Publicación:</strong> ")
                        .append(oferta.getFechaPublicacion()).append("</p>");
                htmlBuilder.append("<p style=\"font-family: 'Arial', sans-serif; color: ").append(CARD_COLOR)
                        .append(";\">").append("<strong>Fecha de Vencimiento:</strong> ")
                        .append(oferta.getFechaVencimiento()).append("</p>");
                htmlBuilder.append("</div>");
            }

            htmlBuilder.append("</div>");
            htmlBuilder.append("</div>");
        }

        htmlBuilder.append("<div id=\"footer\" style=\"background-color: ").append(COLOR_FOOTER_BG).append("; color: ")
                .append(COLOR_FOOTER_TEXT).append(";\">");
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
        cssBuilder.append(
                "#header-content { max-width: 1200px; margin: 0 auto; display: flex; justify-content: space-between; align-items: center; }");
        cssBuilder.append("#left-section, #right-section { flex-basis: 20%; }");
        cssBuilder.append("#center-section { flex-grow: 1; text-align: center; }");
        cssBuilder.append("#left-section h2 { margin: 5px 0; font-size: 24px; }");
        cssBuilder.append("#left-section p { margin: 5px 0; font-size: 14px; }");
        cssBuilder.append("#center-section h1 { margin: 5px 0; font-size: 28px; margin-bottom: 10px; }");
        cssBuilder.append(".categoria { margin-bottom: 30px; }");
        cssBuilder.append(".categoria h2 { margin-bottom: 10px; font-size: 20px; }");
        cssBuilder.append(".ofertas { display: flex; flex-wrap: wrap; justify-content: space-around; }");
        cssBuilder.append(".card { width: calc(33.33% - 20px); background-color: ").append(CARD_BACKGROUND).append(
                "; border-radius: 8px; padding: 20px; margin-bottom: 20px; box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); }"); // Estilo
                                                                                                                            // de
                                                                                                                            // tarjeta
        cssBuilder.append(".card h3 { font-size: 18px; margin-bottom: 10px; }");
        cssBuilder.append(".card p { font-size: 14px; margin-bottom: 8px; }");
        cssBuilder.append("#right-section p { font-size: 14px; margin: 5px 0; text-align: right; }");
        cssBuilder.append("#footer { text-align: center; font-size: 12px; margin-top: 20px; padding: 10px 0; }");

        return cssBuilder.toString();
    }

    @Override
    public byte[] generarOfertaTrabajoPdf(Integer idOfertaTrabajo) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        OfertaTrabajo ofertaTrabajo = ofertaTrabajoRepository.findById(idOfertaTrabajo).orElse(null);

        HtmlConverter.convertToPdf(new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<hmtl>")
                .append("<head>")
                .append("<style>")
                .append(generateCSS())
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append(generateHeaderPdf())
                .append(generateOfertaTrabajoCabecera(ofertaTrabajo))
                .append(generateOfertaTrabajoDetalle(ofertaTrabajo))
                .append(generateFooter())
                .append("</body>")
                .append("</html>")
                .toString(), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private String generateHeaderPdf() {
        StringBuilder builder = new StringBuilder();

        builder.append("<div id=\"main-container-header-pdf\">");

        builder.append("<div id=\"container-title-header-pdf\">");
        builder.append("<p class=titulo>TRABAJO COMPU</p>");
        builder.append("</div>");

        builder.append("<div id=\"container-datos-header-pdf\">");
        builder.append("<p>OFERTA DE TRABAJO</p>");
        builder.append("<p>Lima, Perú</p>");
        builder.append("</div>");

        builder.append("</div>");

        return builder.toString();
    }

    private String generateOfertaTrabajoCabecera(OfertaTrabajo ofertaTrabajo) {
        StringBuilder builder = new StringBuilder();
        builder.append("")

                .append("<div id=\"main-container-procab\">")
                .append("<div id=\"container\">")

                .append("<ul class=list-group")
                .append("<li class=list-group-item id=\"id_oferta_trabajo\">Id de Oferta de Trabajo</li>")
                .append(": " + ofertaTrabajo.getIdOfertaTrabajo())
                .append("<li class=list-group-item id=\"id_empleador\">Id del Empleador</li>")
                .append("Para: " + ofertaTrabajo.getEmpleador().getIdEmpleador())
                .append("<li class=list-group-item id=\"id_categoria_empleo\">Id de la Categoría Empleado</li>")
                .append("Para: " + ofertaTrabajo.getCategoriaEmpleo().getIdCategoriaEmpleo())
                .append("<li class=list-group-item id=\"titulo\">Titulo</li>")
                .append("Para: " + ofertaTrabajo.getTitulo())
                .append("<li class=list-group-item id=\"descripcion\">Descripción</li>")
                .append("Para: " + ofertaTrabajo.getDescripcion())
                .append("<li class=list-group-item id=\"disponibilidad_horaria\">Disponibilidad Horaria</li>")
                .append("Para: " + ofertaTrabajo.getDisponibilidadHoraria())
                .append("<li class=list-group-item id=\"turno\">Turno</li>")
                .append("Para: " + ofertaTrabajo.getTurno())
                .append("<li class=list-group-item id=\"modalidad\">Modalidad</li>")
                .append("Para: " + ofertaTrabajo.getModalidad())
                .append("<li class=list-group-item id=\"salario\">Salario</li>")
                .append("Para: " + ofertaTrabajo.getSalario())
                .append("</ul>")
                .append("</div>")

                .append("");
        return builder.toString();
    }

    private String generateOfertaTrabajoDetalle(OfertaTrabajo ofertaTrabajo) {
        StringBuilder builder = new StringBuilder();
        builder.append("")

                .append("<div id=\"main-container-prodet\">")

                .append("<table class=tabla-reportes id=\"table\">")
                .append("<tr>")
                .append("<th>Departamento </th>")
                .append("<th>Provincia </th>")
                .append("<th>Distrito</th>")
                .append("<th>Fecha Publicación </th>")
                .append("<th>Fecha Vencimiento </th>")
                .append("<th>Requisitos </th>")
                .append("<th>Estado Oferta </th>")

                .append("</tr>")
                //.append("<td style=\"text-align: left;\">")
                .append("<td>" + ofertaTrabajo.getDepartamento() + "</td>")
                .append("<td>" + ofertaTrabajo.getProvincia() + "</td>")
                .append("<td>" + ofertaTrabajo.getDistrito() + "</td>")
                .append("<td>" + ofertaTrabajo.getFechaPublicacion() + "</td>")
                .append("<td>" + ofertaTrabajo.getFechaVencimiento() + "</td>")
                .append("<td>" + ofertaTrabajo.getRequisitos() + "</td>")
                .append("<td>" + ofertaTrabajo.getEstadoOferta() + "</td>")
                .append("</tr>")

                .append("</table>")

                .append("</div>")

                .append("");
        return builder.toString();
    }

    private String generateFooter() {
        StringBuilder builder = new StringBuilder();

        builder.append("<footer class=parte-baja id=\"footer\">");
        builder.append("<p><p>Dirección web: www.TRABAJOCOMPU.com | País de origen: Perú | Año: 2024</p>");
        builder.append("</footer>");

        return builder.toString();
    }

    private String generateCSS() {

        StringBuilder builder = new StringBuilder();

        builder.append("")

                .append("@page {")
                .append("margin: 0px;")
                .append("}")

                .append("body{")
                .append(" background-color: white;")
                .append("width:300px;")
                .append("}")

                /***********************
                 * generateHeaderPdf
                 **********************/

                .append("#main-container-header-pdf {")
                .append("background-color: #FF0080;")
                .append("color: white;")
                .append("display:flex;")
                .append("}");

        builder.append("#container-datos-header-pdf {")
                .append("width:200%;")
                .append("display:flex;")
                .append("color: white;")
                .append("flex-direction:column;")
                .append("align-items: center;")
                .append("justify-content: space-between;")
                .append("}");

        builder.append("#container-title-header-pdf p {")
                .append("color:#000000;")
                .append("font-size:25px;")
                .append("font-family:Copperplate;")
                .append("text-align: center;")
                .append("margin-bottom: 3px;")
                .append("font-weight: bold;")
                .append("}");

        builder.append("#container-datos-header-pdf p {")
                .append("color:#000000;")
                .append("margin: 0px;")
                .append("text-align: right;")
                .append("margin-right: 30px;")
                .append("}");

        builder.append("#container-datos-header-pdf img {")
                .append("width: 200px;")
                .append("height: 60px;")
                .append("margin-top: 30px;")
                .append("margin-right: 130px;")
                .append("}");

        builder.append("#table {")
                .append("background-color: #FFFF00;")
                .append("font-family: Copperplate;")
                .append("width: 90%;")
                .append("text-align: center;")
                .append("margin-color: #000000;")
                .append("margin: 20% auto;")
                .append("margin-top: 2%;")
                .append("}");

        builder.append("#.tabla-reportes th, .tabla-reportes td {")
                .append("border: 1px solid #000;")
                .append("padding: 8px;")

                .append("}");

        builder.append("#.titulo {")
                .append("font-family: 'Roboto', sans-serif;")
                .append("font-size: 24px;")

                .append("}");

        builder.append("#.parte-baja {")
                .append("background-color: #Ff5b00;")
                .append("color: white;")
                .append("padding: 10px;")
                .append("text-align: center;")
                .append("width: 100%;")
                .append("position: fixed;")
                .append("bottom: 0;")

                .append("}");

        return builder.toString();

    }
}