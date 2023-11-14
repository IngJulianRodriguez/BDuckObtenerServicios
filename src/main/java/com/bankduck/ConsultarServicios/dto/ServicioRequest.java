package com.bankduck.ConsultarServicios.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class ServicioRequest {


    @ApiModelProperty(name = "nombre", required = true,example = "Cuenta de ahorros", value = "Nombre del servicio")
    private String nombre;
    @ApiModelProperty(name = "descripcion", required = true,example = "Cuenta de ahorros", value = "Descripcion del servicio")
    private String descripcion;

    public ServicioRequest(){
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
