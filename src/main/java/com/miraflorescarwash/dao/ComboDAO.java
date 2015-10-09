/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Combo;
import com.miraflorescarwash.model.ComboReporte;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface ComboDAO {

    public Combo findById(Long id);

    public List<Combo> findAll();

    public void save(Combo combo);

    public void update(Combo combo);

    public void delete(Long id);
    
    public List<ComboReporte> ComboReporte();
}
