/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.ComboPorModelo;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface ComboPorModeloDAO {

    public ComboPorModelo findById(Long id);

    public List<ComboPorModelo> findAll();

    public void save(ComboPorModelo comboPorModelo);

    public void update(ComboPorModelo comboPorModelo);

    public void delete(Long id);

}
