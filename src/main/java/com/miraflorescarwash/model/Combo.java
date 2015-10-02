package com.miraflorescarwash.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String descripcion;

    @Column(nullable = false, length = 30)
    private String nombre;

    @Column(nullable = false)
    @Min(value = 1)
    private Integer numeroLavadas;

    // bi-directional many-to-one association to ComboPorModelo
    @OneToMany(mappedBy = "combo", fetch = FetchType.LAZY)
    @Cascade({CascadeType.DELETE})
    private List<ComboPorModelo> combopormodelos;

    public Combo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroLavadas() {
        return this.numeroLavadas;
    }

    public void setNumeroLavadas(Integer numeroLavadas) {
        this.numeroLavadas = numeroLavadas;
    }

    public List<ComboPorModelo> getCombopormodelos() {
        return this.combopormodelos;
    }

    public void setCombopormodelos(List<ComboPorModelo> combopormodelos) {
        this.combopormodelos = combopormodelos;
    }

    public ComboPorModelo addCombopormodelo(ComboPorModelo combopormodelo) {
        getCombopormodelos().add(combopormodelo);
        combopormodelo.setCombo(this);

        return combopormodelo;
    }

    public ComboPorModelo removeCombopormodelo(ComboPorModelo combopormodelo) {
        getCombopormodelos().remove(combopormodelo);
        combopormodelo.setCombo(null);

        return combopormodelo;
    }

    @Override
    public String toString() {
        return "Combo{" + "id=" + id + ", descripcion=" + descripcion + ", nombre=" + nombre + ", numeroLavadas=" + numeroLavadas + '}';
    }
    
}
