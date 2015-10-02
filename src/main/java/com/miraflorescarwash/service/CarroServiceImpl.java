/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.CarroDAO;
import com.miraflorescarwash.model.Carro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("carroService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroDAO carroDAO;
    
    @Override
    @Transactional
    public Carro findById(Long id) {
        return carroDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Carro> findAll() {
        return carroDAO.findAll();
    }

    @Override
    @Transactional
    public Carro findByPlaca(String placa) {
        return carroDAO.findByPlaca(placa);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Carro carro) {
        carroDAO.save(carro);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Carro carro) {
        carroDAO.update(carro);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        carroDAO.delete(id);
    }
    
}
