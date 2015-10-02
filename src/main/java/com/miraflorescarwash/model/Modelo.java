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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre;

    // bi-directional many-to-one association to Carro
    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Carro> carros;

    // bi-directional many-to-one association to ComboPorModelo
    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    @Cascade({CascadeType.DELETE})
    private List<ComboPorModelo> combopormodelos;

    // bi-directional many-to-one association to LavadaDisponible
    @OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<LavadaDisponible> lavadadisponibles;

    public Modelo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Carro> getCarros() {
        return this.carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }

    public Carro addCarro(Carro carro) {
        getCarros().add(carro);
        carro.setModelo(this);

        return carro;
    }

    public Carro removeCarro(Carro carro) {
        getCarros().remove(carro);
        carro.setModelo(null);

        return carro;
    }

    public List<ComboPorModelo> getCombopormodelos() {
        return this.combopormodelos;
    }

    public void setCombopormodelos(List<ComboPorModelo> combopormodelos) {
        this.combopormodelos = combopormodelos;
    }

    public ComboPorModelo addCombopormodelo(ComboPorModelo combopormodelo) {
        getCombopormodelos().add(combopormodelo);
        combopormodelo.setModelo(this);

        return combopormodelo;
    }

    public ComboPorModelo removeCombopormodelo(ComboPorModelo combopormodelo) {
        getCombopormodelos().remove(combopormodelo);
        combopormodelo.setModelo(null);

        return combopormodelo;
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
        lavadadisponible.setModelo(this);

        return lavadadisponible;
    }

    public LavadaDisponible removeLavadadisponible(
            LavadaDisponible lavadadisponible) {
        getLavadadisponibles().remove(lavadadisponible);
        lavadadisponible.setModelo(null);

        return lavadadisponible;
    }

    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", nombre=" + nombre + '}';
    }

}
