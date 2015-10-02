/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Carro;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("carroDAO")
public class CarroDAOImpl extends AbstractDao<Long, Carro> implements CarroDAO {

    @Override
    public Carro findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<Carro> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<Carro>) criteria.list();
    }

    @Override
    public Carro findByPlaca(String placa) {
        Criteria criteria = super.createEntityCriteria()
                .addOrder(Order.asc("id"))
                .add(Restrictions.eq("placa", placa));
        return (Carro) criteria.uniqueResult();
    }

    @Override
    public void save(Carro carro) {
        super.save(carro);
    }

    @Override
    public void update(Carro carro) {
        super.update(carro);
    }

    @Override
    public void delete(Long id) {
        Carro c;
        c = this.findById(id);
        super.delete(c);
    }
    
}
