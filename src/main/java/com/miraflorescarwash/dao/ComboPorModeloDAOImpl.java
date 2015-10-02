/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.ComboPorModelo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("comboPorModeloDAO")
public class ComboPorModeloDAOImpl extends AbstractDao<Long, ComboPorModelo> implements ComboPorModeloDAO {

    @Override
    public ComboPorModelo findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<ComboPorModelo> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<ComboPorModelo>) criteria.list();
    }

    @Override
    public void save(ComboPorModelo comboPorModelo) {
        super.save(comboPorModelo);
    }

    @Override
    public void update(ComboPorModelo comboPorModelo) {
        super.update(comboPorModelo);
    }

    @Override
    public void delete(Long id) {
        ComboPorModelo c;
        c = this.findById(id);
        super.delete(c);
    }
    
}
