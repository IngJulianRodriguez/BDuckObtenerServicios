package com.bankduck.ConsultarServicios.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    private long id;

    private String nombre;

    private String descripcion;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
    @OrderColumn(name = "requisito_order")
    private List<RequisitosServicio> requisitosServicio;

    public Servicio() {
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
