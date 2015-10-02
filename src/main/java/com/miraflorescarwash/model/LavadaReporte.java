/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.model;

import java.io.Serializable;

/**
 *
 * @author ty
 */
public class LavadaReporte implements Serializable {

    private Long id;
    private Double cantidad;

    public LavadaReporte() {
    }

    public LavadaReporte(Long id, Double cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

}
