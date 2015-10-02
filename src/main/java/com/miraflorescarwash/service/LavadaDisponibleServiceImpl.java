/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.LavadaDisponibleDAO;
import com.miraflorescarwash.model.LavadaDisponible;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("lavadaDisponibleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LavadaDisponibleServiceImpl implements LavadaDisponibleService {

    @Autowired
    private LavadaDisponibleDAO lavadaDisponibleDAO;

    @Override
    @Transactional
    public LavadaDisponible findById(Long id) {
        return lavadaDisponibleDAO.findById(id);
    }

    @Override
    @Transactional
    public List<LavadaDisponible> findAll() {
        return lavadaDisponibleDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(LavadaDisponible lavadaDisponible) {
        lavadaDisponibleDAO.save(lavadaDisponible);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(LavadaDisponible lavadaDisponible) {
        lavadaDisponibleDAO.update(lavadaDisponible);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        lavadaDisponibleDAO.delete(id);
    }

    @Override
    @Transactional
    public LavadaDisponible buscarPorAuto(LavadaDisponible carroLavar) {
        return lavadaDisponibleDAO.buscarPorAuto(carroLavar);
    }

}
