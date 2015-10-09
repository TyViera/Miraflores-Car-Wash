/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.ClienteComboPorModelo;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface ClienteComboPorModeloDAO {

    public ClienteComboPorModelo findById(Long id);

    public List<ClienteComboPorModelo> findAll();

    public void save(ClienteComboPorModelo clienteComboPorModelo);

    public void update(ClienteComboPorModelo clienteComboPorModelo);

    public void delete(Long id);

}
