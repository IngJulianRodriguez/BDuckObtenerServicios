package com.bankduck.ConsultarServicios.dto;

import com.bankduck.ConsultarServicios.entities.RequisitosServicio;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel()
public class ServicioResponse {

    @ApiModelProperty(name = "id", required = true,example = "2548975",value = "Identificador unico del servicio")
    private long id;
    @ApiModelProperty(name = "nombre", required = true,example = "Cuenta de ahorros", value = "Nombre del servicio")
    private String nombre;
    @ApiModelProperty(name = "descripcion", required = false,example = "Cuenta de ahorros", value = "Descripcion del servicio")
    private String descripcion;

    @ApiModelProperty(name = "requisitosServicio", required = true,example = "", value = "")
    private List<RequisitosServicio> requisitosServicio;

    public ServicioResponse(){
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

    public List<RequisitosServicio> getRequisitosServicio() {
        return requisitosServicio;
    }

    public void setRequisitosServicio(List<RequisitosServicio> requisitosServicio) {
        this.requisitosServicio = requisitosServicio;
    }
}
