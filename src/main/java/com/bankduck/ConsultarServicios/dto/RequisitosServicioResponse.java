package com.bankduck.ConsultarServicios.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class RequisitosServicioResponse {
    @ApiModelProperty(name = "nombre", required = true,example = "Cedula", value = "Cedula")
    private String nombre;
    @ApiModelProperty(name = "tipoInput", required = true,example = "Texto", value = "Texto")
    private String tipoInput;

    public RequisitosServicioResponse(){
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoInput() {
        return tipoInput;
    }

    public void setTipoInput(String tipoInput) {
        this.tipoInput = tipoInput;
    }
}
