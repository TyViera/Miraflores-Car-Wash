/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author ty
 */
@Entity
public class ObjetoCustodia implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(length = 80,nullable = false)
    private String nombre;
    
    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 1000)
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lavadaid")
    private Lavada lavada;
    
    public ObjetoCustodia() {
    }

    public ObjetoCustodia(String nombre, Integer cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public ObjetoCustodia(Long id, String nombre, Integer cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Lavada getLavada() {
        return lavada;
    }

    public void setLavada(Lavada lavada) {
        this.lavada = lavada;
    }

    @Override
    public String toString() {
        return "ObjetoCustodia{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", lavada=" + lavada + '}';
    }

}
