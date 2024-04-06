package com.sise.portalempleo.services.impl;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.sise.portalempleo.services.ReporteService;
@Service
public class ReporteServiceImpl implements ReporteService {

    private static String COLOR_GRAY_HEADER ="#7b7e84";
    private static String COLOR_PRIMARY ="blue";

    @Override   
    public byte[] generarProformaPdf() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); 
        HtmlConverter.convertToPdf( new StringBuilder()
                                        .append("<!DOCTYPE html>")
                                        .append("<hmtl>")
                                            .append("<head>")
                                            .append("</head>")
                                            .append("<body>")
                                                .append(generateHeaderPdf())
                                            .append("</body>")
                                        .append("</html>")
                                        .toString()
                                        ,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    private String generateHeaderPdf(){
        StringBuilder builder = new StringBuilder();
        builder.append("<div style=\"background-color:"+COLOR_GRAY_HEADER+"\">");
            builder.append("<div style=\"background-color:"+COLOR_PRIMARY+"\">");
            builder.append("<p>FACTURA PROFORMA</p>");
            builder.append("</div>");        
        builder.append("<p>TrabajoCompu</p>");
        builder.append("<p>Lima, Perú</p>");
        
        builder.append("</div>");
        return builder.toString();
    }

    private String generateCSS(){
        StringBuilder builder = new StringBuilder();

        builder.append("main-container-header-pdf");
        return builder.toString();
    }
}
