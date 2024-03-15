package com.sise.portalempleo.shared;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse {
    private String message;
    private Boolean error;
    private Object data;

    private BaseResponse(String message, boolean error, Object data) {

        this.message = message;
        this.error = error;
        this.data = data;

    }

    public static BaseResponse success(Object data) {
        return new BaseResponse("OK", false, data);
    }

    public static BaseResponse error(String message) {
        return new BaseResponse(message, true, null);
    }

    public static BaseResponse errorNotFound() {
        return new BaseResponse("Recurso no encontrado", true, null);
    }
}


