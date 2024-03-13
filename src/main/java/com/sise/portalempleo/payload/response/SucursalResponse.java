package com.sise.lengavanzado.payload.response;

import lombok.Data;

@Data
public class SucursalResponse {
    private Integer idSucursal;
    private String nombre;
    private String direccion;
    private String telefono;
    private String descripcion;
    private String imagenUrl;
}
