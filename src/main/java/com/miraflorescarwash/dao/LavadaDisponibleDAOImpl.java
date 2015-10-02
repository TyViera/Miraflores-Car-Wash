/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.LavadaDisponible;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("lavadaDisponibleDAO")
public class LavadaDisponibleDAOImpl extends AbstractDao<Long, LavadaDisponible> implements LavadaDisponibleDAO {

    @Override
    public LavadaDisponible findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<LavadaDisponible> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<LavadaDisponible>) criteria.list();
    }

    @Override
    public void save(LavadaDisponible lavadaDisponible) {
        super.save(lavadaDisponible);
    }

    @Override
    public void update(LavadaDisponible lavadaDisponible) {
        super.update(lavadaDisponible);
    }

    @Override
    public void delete(Long id) {
        LavadaDisponible c;
        c = this.findById(id);
        super.delete(c);
    }

    @Override
    public LavadaDisponible buscarPorAuto(LavadaDisponible carroLavar) {
        String consulta;
        Query query;
        try {
            consulta = "SELECT p "
                    + " FROM LavadaDisponible p "
                    + " WHERE p.cliente.id = :idCliente AND p.modelo.id = :idModelo";
            query = super.getSession().createQuery(consulta);
            query.setParameter("idCliente", carroLavar.getCliente().getId());
            query.setParameter("idModelo", carroLavar.getModelo().getId());
            return (LavadaDisponible) query.uniqueResult();
        } catch (NullPointerException ex) {
            return null;
        }
    }

}
