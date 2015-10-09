/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Kevin
 */
@Entity
public class UsuarioRol implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    
    @Column(nullable = false, length = 50)
    private String authority;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioid")
    private Usuario usuario;

    /*
    `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
    `USER_ID` INT(10) UNSIGNED NOT NULL,
    `AUTHORITY` VARCHAR(45) NOT NULL,
     */
    public UsuarioRol() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "UsuarioRol{" + "id=" + id + ", authority=" + authority + '}';
    }
    
}
