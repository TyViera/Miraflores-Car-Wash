/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.model.Lavada;
import java.util.List;

/**
 *
 * @author ty
 */
public interface LavadaService {

    public Lavada findById(Long id);

    public List<Lavada> findAll();

    public void save(Lavada lavada);

    public void update(Lavada lavada);

    public void delete(Long id);

    public List<Double> obtenerLista(int tipo, int cantidadRegistros);
    
    public List<Lavada> verLavadasPendientes();
    
    public void iniciarRelacionesLazy(Lavada lavada);
    
}
