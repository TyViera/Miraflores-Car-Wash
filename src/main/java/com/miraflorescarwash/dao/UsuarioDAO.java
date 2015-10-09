/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Usuario;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface UsuarioDAO {
           
    public Usuario findById(Long id);

    public Usuario findByCredenciales(Usuario usuario);
    
    public List<Usuario> findAll();

    public void save(Usuario usuario);

    public void update(Usuario usuario);

    public void delete(Long id);
    
}
