/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.model;

/**
 *
 * @author ty
 */
public class ClienteReporte {
    
    private Long id;
    private String nombre;
    private String apellido;
    private Double total;

    public ClienteReporte() {
    }

    public ClienteReporte(Long id, String nombre, String apellido, Double total) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    
}
