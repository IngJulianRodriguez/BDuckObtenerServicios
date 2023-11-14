package com.bankduck.ConsultarServicios.entities;

import javax.persistence.*;

import lombok.Data;
@Entity
public class RequisitosServicio {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @SequenceGenerator(name = "hibernate_sequence", sequenceName = "hibernate_sequence", allocationSize = 1)
    private long id;

    private String nombre;

    private String tipoInput;

    @ManyToOne
    @JoinColumn(name = "servicioId")
    private Servicio servicio;

    public RequisitosServicio() {
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


    public String gettipoInput() {
        return tipoInput;
    }

    public void settipoInput(String tipoInput) {
        this.tipoInput = tipoInput;
    }
}
