/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Modelo;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface ModeloDAO {
           
    public Modelo findById(Long id);

    public List<Modelo> findAll();

    public void save(Modelo modelo);

    public void update(Modelo modelo);

    public void delete(Long id);
    
}
