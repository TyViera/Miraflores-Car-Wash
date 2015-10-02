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
import javax.validation.constraints.Min;

@Entity
public class LavadaDisponible implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    @Min(value = 0)
    private Integer numeroLavadas;

    // bi-directional many-to-one association to Cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    // bi-directional many-to-one association to Modelo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modeloid")
    private Modelo modelo;

    public LavadaDisponible() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroLavadas() {
        return this.numeroLavadas;
    }

    public void setNumeroLavadas(Integer numeroLavadas) {
        this.numeroLavadas = numeroLavadas;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "LavadaDisponible{" + "id=" + id + ", numeroLavadas=" + numeroLavadas + ", cliente=" + cliente + ", modelo=" + modelo + '}';
    }

}
