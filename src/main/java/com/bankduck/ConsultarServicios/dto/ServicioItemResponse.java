package com.bankduck.ConsultarServicios.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class ServicioItemResponse {

    @ApiModelProperty(name = "id", required = true,example = "2548975",value = "Identificador unico del servicio")
    private long id;
    @ApiModelProperty(name = "nombre", required = true,example = "Cuenta de ahorros", value = "Nombre del servicio")
    private String nombre;
    @ApiModelProperty(name = "descripcion", required = true,example = "Cuenta de ahorros", value = "Descripcion del servicio")
    private String descripcion;

    public ServicioItemResponse(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
