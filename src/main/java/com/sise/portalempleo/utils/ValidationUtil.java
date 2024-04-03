package com.sise.portalempleo.utils;

import java.util.List;

import org.springframework.validation.FieldError;

public class ValidationUtil {
    public static String getOneMessageFromErrors(List<FieldError>errors){
        String message = "";
        for (FieldError error : errors){
            if(!message.isEmpty()){
                message += ". ";
            }
            message += "Error en el campo "+ error.getField() + ", " + error.getDefaultMessage();
        }
        return message+".";

    }
}
