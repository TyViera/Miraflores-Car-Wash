/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.ClienteComboPorModelo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("clienteComboPorModelo")
public class ClienteComboPorModeloDAOImpl extends AbstractDao<Long, ClienteComboPorModelo> implements ClienteComboPorModeloDAO {

    @Override
    public ClienteComboPorModelo findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<ClienteComboPorModelo> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<ClienteComboPorModelo>) criteria.list();
    }

    @Override
    public void save(ClienteComboPorModelo clienteComboPorModelo) {
        super.save(clienteComboPorModelo);
    }

    @Override
    public void update(ClienteComboPorModelo clienteComboPorModelo) {
        super.update(clienteComboPorModelo);
    }

    @Override
    public void delete(Long id) {
        ClienteComboPorModelo c;
        c = this.findById(id);
        super.delete(c);
    }
    
}
