package com.miraflorescarwash.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ClienteComboPorModelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date fechaRegistro;

    // bi-directional many-to-one association to Cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    // bi-directional many-to-one association to ComboPorModelo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "combopormodeloid")
    private ComboPorModelo combopormodelo;

    public ClienteComboPorModelo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ComboPorModelo getCombopormodelo() {
        return this.combopormodelo;
    }

    public void setCombopormodelo(ComboPorModelo combopormodelo) {
        this.combopormodelo = combopormodelo;
    }

    @Override
    public String toString() {
        return "ClienteComboPorModelo{" + "id=" + id + ", fechaRegistro=" + fechaRegistro + ", cliente=" + cliente + ", combopormodelo=" + combopormodelo + '}';
    }
    
}
