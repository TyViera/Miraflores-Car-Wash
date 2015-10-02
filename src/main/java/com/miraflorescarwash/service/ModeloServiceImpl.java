/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.ModeloDAO;
import com.miraflorescarwash.model.Modelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("modeloService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private ModeloDAO modeloDAO;
    
    @Override
    @Transactional
    public Modelo findById(Long id) {
        return modeloDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Modelo> findAll() {
        return modeloDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Modelo modelo) {
        modeloDAO.save(modelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Modelo modelo) {
        modeloDAO.update(modelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        modeloDAO.delete(id);
    }
    
}
