/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.LavadaDisponible;
import java.util.List;

/**
 *
 * @author Kevin
 */
public interface LavadaDisponibleDAO {

    public LavadaDisponible findById(Long id);

    public List<LavadaDisponible> findAll();

    public void save(LavadaDisponible lavadaDisponible);

    public void update(LavadaDisponible lavadaDisponible);

    public void delete(Long id);

    public LavadaDisponible buscarPorAuto(LavadaDisponible carroLavar);
}
