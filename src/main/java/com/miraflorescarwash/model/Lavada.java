package com.miraflorescarwash.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lavada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    private Date fechaLavado;

    @Column(nullable = false, length = 3)
    private String estado;
    
    // bi-directional many-to-one association to Carro
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "carroid")
    private Carro carro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lavada")
    @Cascade({CascadeType.SAVE_UPDATE, 
        CascadeType.DELETE, 
        CascadeType.PERSIST, 
        CascadeType.MERGE
    })
    private List<ObjetoCustodia> objetosEnCustodia;
    
    public Lavada() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaLavado() {
        return this.fechaLavado;
    }

    public void setFechaLavado(Date fechaLavado) {
        this.fechaLavado = fechaLavado;
    }

    public Carro getCarro() {
        return this.carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ObjetoCustodia> getObjetosEnCustodia() {
        return objetosEnCustodia;
    }

    public void setObjetosEnCustodia(List<ObjetoCustodia> objetosEnCustodia) {
        this.objetosEnCustodia = objetosEnCustodia;
    }

    @Override
    public String toString() {
        return "Lavada{" + "id=" + id + ", fechaLavado=" + fechaLavado + ", estado=" + estado + ", carro=" + carro + '}';
    }

}
