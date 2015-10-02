package com.miraflorescarwash.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"dni"}))
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(unique = true, nullable = false, length = 8)
    private String dni;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(nullable = false, length = 9)
    private String telefono;

    // bi-directional many-to-one association to Carro
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Carro> carros;

    // bi-directional many-to-one association to ClienteComboPorModelo
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<ClienteComboPorModelo> clientecombopormodelos;

    // bi-directional many-to-one association to LavadaDisponible
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<LavadaDisponible> lavadadisponibles;

    public Cliente() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Carro> getCarros() {
        return this.carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public Carro addCarro(Carro carro) {
        getCarros().add(carro);
        carro.setCliente(this);

        return carro;
    }

    public Carro removeCarro(Carro carro) {
        getCarros().remove(carro);
        carro.setCliente(null);

        return carro;
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
        clientecombopormodelo.setCliente(this);

        return clientecombopormodelo;
    }

    public ClienteComboPorModelo removeClientecombopormodelo(
            ClienteComboPorModelo clientecombopormodelo) {
        getClientecombopormodelos().remove(clientecombopormodelo);
        clientecombopormodelo.setCliente(null);

        return clientecombopormodelo;
    }

    public List<LavadaDisponible> getLavadadisponibles() {
        return this.lavadadisponibles;
    }

    public void setLavadadisponibles(List<LavadaDisponible> lavadadisponibles) {
        this.lavadadisponibles = lavadadisponibles;
    }

    public LavadaDisponible addLavadadisponible(
            LavadaDisponible lavadadisponible) {
        getLavadadisponibles().add(lavadadisponible);
        lavadadisponible.setCliente(this);

        return lavadadisponible;
    }

    public LavadaDisponible removeLavadadisponible(
            LavadaDisponible lavadadisponible) {
        getLavadadisponibles().remove(lavadadisponible);
        lavadadisponible.setCliente(null);

        return lavadadisponible;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + '}';
    }

    
}
