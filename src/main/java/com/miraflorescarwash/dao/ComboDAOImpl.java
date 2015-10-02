/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Combo;
import com.miraflorescarwash.model.ComboReporte;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("comboDAO")
public class ComboDAOImpl extends AbstractDao<Long, Combo> implements ComboDAO {

    @Override
    public Combo findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<Combo> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<Combo>) criteria.list();
    }

    @Override
    public void save(Combo combo) {
        super.save(combo);
    }

    @Override
    public void update(Combo combo) {
        super.update(combo);
    }

    @Override
    public void delete(Long id) {
        Combo c;
        c = this.findById(id);
        super.delete(c);
    }

    @Override
    public List<ComboReporte> ComboReporte() {
        List lista;
        List<ComboReporte> out;
        Object a[];
        lista = super.getSession()
                .createSQLQuery("SELECT * FROM public.ventas_combos() ORDER BY cantidad DESC;")
                .list();
        out = new ArrayList<>();
        for (Object l : lista) {
            a = (Object[]) l;
            out.add(new ComboReporte(
                    Long.valueOf(a[0].toString()),
                    a[1].toString(),
                    Double.valueOf(a[2].toString())
            ));
        }
        return out;
    }
    
}
