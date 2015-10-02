/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.ClienteComboPorModeloDAO;
import com.miraflorescarwash.model.ClienteComboPorModelo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("clienteComboPorModeloService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClienteComboPorModeloServiceImpl implements ClienteComboPorModeloService {

    @Autowired
    private ClienteComboPorModeloDAO clienteComboPorModeloDAO;
    
    @Override
    @Transactional
    public ClienteComboPorModelo findById(Long id) {
        return clienteComboPorModeloDAO.findById(id);
    }

    @Override
    @Transactional
    public List<ClienteComboPorModelo> findAll() {
        return clienteComboPorModeloDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(ClienteComboPorModelo clienteComboPorModelo) {
        clienteComboPorModeloDAO.save(clienteComboPorModelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(ClienteComboPorModelo clienteComboPorModelo) {
        clienteComboPorModeloDAO.update(clienteComboPorModelo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        clienteComboPorModeloDAO.delete(id);
    }
    
}
