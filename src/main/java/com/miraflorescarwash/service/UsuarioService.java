/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.model.Usuario;
import java.util.List;

/**
 *
 * @author ty
 */
public interface UsuarioService {

    public Usuario findById(Long id);

    public List<Usuario> findAll();

    public Usuario findByCredenciales(Usuario usuario);
    
    public void save(Usuario usuario);

    public void update(Usuario usuario);

    public void delete(Long id);
}
