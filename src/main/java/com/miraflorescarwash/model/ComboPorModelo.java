package com.miraflorescarwash.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ComboPorModelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    // bi-directional many-to-one association to ClienteComboPorModelo
    @OneToMany(mappedBy = "combopormodelo", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<ClienteComboPorModelo> clientecombopormodelos;

    // bi-directional many-to-one association to Combo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comboid")
    private Combo combo;

    // bi-directional many-to-one association to Modelo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modeloid")
    private Modelo modelo;

    public ComboPorModelo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<ClienteComboPorModelo> getClientecombopormodelos() {
        return this.clientecombopormodelos;
    }

    public void setClientecombopormodelos(
            List<ClienteComboPorModelo> clientecombopormodelos) {
        this.clientecombopormodelos = clientecombopormodelos;
    }

    public ClienteComboPorModelo addClientecombopormodelo(
            ClienteComboPorModelo clientecombopormodelo) {
        getClientecombopormodelos().add(clientecombopormodelo);
        clientecombopormodelo.setCombopormodelo(this);

        return clientecombopormodelo;
    }

    public ClienteComboPorModelo removeClientecombopormodelo(
            ClienteComboPorModelo clientecombopormodelo) {
        getClientecombopormodelos().remove(clientecombopormodelo);
        clientecombopormodelo.setCombopormodelo(null);

        return clientecombopormodelo;
    }

    public Combo getCombo() {
        return this.combo;
    }

    public void setCombo(Combo combo) {
        this.combo = combo;
    }

    public Modelo getModelo() {
        return this.modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "ComboPorModelo{" + "id=" + id + ", precio=" + precio + ", combo=" + combo + ", modelo=" + modelo + '}';
    }

}
