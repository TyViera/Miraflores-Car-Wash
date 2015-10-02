/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.UsuarioDAO;
import com.miraflorescarwash.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;
    
    @Override
    @Transactional
    public Usuario findById(Long id) {
        return usuarioDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }

    @Override
    @Transactional
    public Usuario findByCredenciales(Usuario usuario) {
        return usuarioDAO.findByCredenciales(usuario);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Usuario usuario) {
        usuarioDAO.save(usuario);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        usuarioDAO.delete(id);
    }

}
