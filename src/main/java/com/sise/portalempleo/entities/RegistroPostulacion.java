package com.sise.portalempleo.entities;

import java.sql.Date;


import lombok.Data;

@Data

public class RegistroPostulacion {
    

    private Integer idRegistroPostulacion;


    private Candidato candidato;


    private OfertaTrabajo ofertaTrabajo;


    private Date fechaPostulacion;


    private String estadoPostulacion;

}
