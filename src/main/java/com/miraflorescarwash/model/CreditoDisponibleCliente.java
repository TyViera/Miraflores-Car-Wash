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
public class CreditoDisponibleCliente {
    
    private Long id;
    private String nombres;
    private String apellidos;
    private String dni;
    private Integer lavadas;
    private String modeloCarro;

    public CreditoDisponibleCliente() {
    }

    public CreditoDisponibleCliente(Long id, String nombres, String apellidos, String dni, Integer lavadas, String modeloCarro) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.lavadas = lavadas;
        this.modeloCarro = modeloCarro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getLavadas() {
        return lavadas;
    }

    public void setLavadas(Integer lavadas) {
        this.lavadas = lavadas;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    @Override
    public String toString() {
        return "CreditoDisponibleCliente{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni + ", lavadas=" + lavadas + ", modeloCarro=" + modeloCarro + '}';
    }
    
}
