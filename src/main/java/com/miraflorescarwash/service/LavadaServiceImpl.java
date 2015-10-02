/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.LavadaDAO;
import com.miraflorescarwash.model.Lavada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("lavadaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LavadaServiceImpl implements LavadaService {

    @Autowired
    private LavadaDAO lavadaDAO;
    
    @Override
    @Transactional
    public Lavada findById(Long id) {
        return lavadaDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Lavada> findAll() {
        return lavadaDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Lavada lavada) {
        lavadaDAO.save(lavada);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Lavada lavada) {
        lavadaDAO.update(lavada);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        lavadaDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Double> obtenerLista(int tipo, int cantidadRegistros) {
        return lavadaDAO.obtenerLista(tipo, cantidadRegistros);
    }
    
    @Override
    @Transactional
    public List<Lavada> verLavadasPendientes(){
        return lavadaDAO.verLavadasPendientes();
    }
    
    @Override
    @Transactional
    public void iniciarRelacionesLazy(Lavada lavada){
        lavadaDAO.iniciarRelacionesLazy(lavada);
    }
}
