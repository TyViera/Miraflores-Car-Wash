/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Carro;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface CarroDAO {
 
    public Carro findById(Long id);

    public List<Carro> findAll();

    public Carro findByPlaca(String placa);
    
    public void save(Carro carro);

    public void update(Carro carro);

    public void delete(Long id);
    
}
