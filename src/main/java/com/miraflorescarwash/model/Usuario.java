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

@Entity()
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false,length = 30)
    private String password;

    @Column(nullable = false, length = 80)
    private String nombreCompleto;

    @Column(unique = true, nullable = false, length = 30)
    private String username;
    
    @Column(nullable = false)
    private Boolean enabled;
    
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<UsuarioRol> roles;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<UsuarioRol> getRoles() {
        return roles;
    }

    public void setRoles(List<UsuarioRol> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", password=" + password + ", nombreCompleto=" + nombreCompleto + ", username=" + username + ", enabled=" + enabled + ", roles=" + roles + '}';
    }

}
