/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Modelo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("modeloDAO")
public class ModeloDAOImpl extends AbstractDao<Long, Modelo>implements ModeloDAO {

    @Override
    public Modelo findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<Modelo> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<Modelo>) criteria.list();
    }

    @Override
    public void save(Modelo modelo) {
        super.save(modelo);
    }

    @Override
    public void update(Modelo modelo) {
        super.update(modelo);
    }

    @Override
    public void delete(Long id) {
        Modelo c;
        c = this.findById(id);
        super.delete(c);
    }
    
}
