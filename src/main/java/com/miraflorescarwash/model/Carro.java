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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"placa"}))
public class Carro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(unique = true, nullable = false, length = 6)
    private String placa;

    // bi-directional many-to-one association to Cliente
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteid")
    private Cliente cliente;

    // bi-directional many-to-one association to Modelo
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modeloid")
    private Modelo modelo;

    // bi-directional many-to-one association to Lavada
    @OneToMany(mappedBy = "carro")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Lavada> lavadas;

    public Carro() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public List<Lavada> getLavadas() {
        return this.lavadas;
    }

    public void setLavadas(List<Lavada> lavadas) {
        this.lavadas = lavadas;
    }

    public Lavada addLavada(Lavada lavada) {
        getLavadas().add(lavada);
        lavada.setCarro(this);

        return lavada;
    }

    public Lavada removeLavada(Lavada lavada) {
        getLavadas().remove(lavada);
        lavada.setCarro(null);

        return lavada;
    }

    @Override
    public String toString() {
        return "Carro{" + "id=" + id + ", marca=" + marca + ", placa=" + placa + ", cliente=" + cliente + ", modelo=" + modelo + '}';
    }

}
