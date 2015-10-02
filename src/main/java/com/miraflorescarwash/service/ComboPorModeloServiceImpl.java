/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.ComboPorModeloDAO;
import com.miraflorescarwash.model.ComboPorModelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("comboPorModeloService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ComboPorModeloServiceImpl implements ComboPorModeloService {

    @Autowired
    private ComboPorModeloDAO comboPorModeloDAO;
    
    @Override
    @Transactional
    public ComboPorModelo findById(Long id) {
        return comboPorModeloDAO.findById(id);
    }

    @Override
    @Transactional
    public List<ComboPorModelo> findAll() {
        return comboPorModeloDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ComboPorModelo comboPorModelo) {
        comboPorModeloDAO.save(comboPorModelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ComboPorModelo comboPorModelo) {
        comboPorModeloDAO.update(comboPorModelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        comboPorModeloDAO.delete(id);
    }
    
}
