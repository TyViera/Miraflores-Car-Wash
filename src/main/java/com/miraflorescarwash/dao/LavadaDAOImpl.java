/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.controller.Constantes;
import com.miraflorescarwash.model.Lavada;
import com.miraflorescarwash.model.ObjetoCustodia;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("lavadaDAO")
public class LavadaDAOImpl extends AbstractDao<Long, Lavada> implements LavadaDAO {

    @Override
    public Lavada findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<Lavada> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.desc("fechaLavado"));
        return (List<Lavada>) criteria.list();
    }

    @Override
    public void save(Lavada lavada) {
        for (ObjetoCustodia obj : lavada.getObjetosEnCustodia()) {
            obj.setLavada(lavada);
        }
        super.save(lavada);
    }

    @Override
    public void update(Lavada lavada) {
        super.update(lavada);
    }

    @Override
    public void delete(Long id) {
        Lavada c;
        c = this.findById(id);
        super.delete(c);
    }

    @Override
    public List<Double> obtenerLista(int tipo, int cantidadRegistros) {
        List<Object> lista;
        List<Double> out;
        SQLQuery sql;
        Object[] a;

        out = new ArrayList<>();
        switch (tipo) {
            case Constantes.REPORTE_LAVADA_DIARIO:
                sql = super.getSession()
                        .createSQLQuery("SELECT * FROM public.ventas_pordia (:cantidad);");
                sql.setParameter("cantidad", cantidadRegistros);
                lista = sql.list();
//                lista = super.execProdecure("", cantidadRegistros);
                break;
            case Constantes.REPORTE_LAVADA_SEMANAL:
                sql = super.getSession()
                        .createSQLQuery("SELECT * FROM public.ventas_porsemana (:cantidad);");
                sql.setParameter("cantidad", cantidadRegistros);
                lista = sql.list();
//                lista = super.execProdecure("ventas_porsemana", cantidadRegistros);
                break;
            case Constantes.REPORTE_LAVADA_MENSUAL:
                sql = super.getSession()
                        .createSQLQuery("SELECT * FROM public.ventas_pormes (:cantidad);");
                sql.setParameter("cantidad", cantidadRegistros);
                lista = sql.list();
//                lista = super.execProdecure("ventas_pormes", cantidadRegistros);
                break;
            default:
                lista = null;
                break;
        }
        if (lista != null) {
            for (Object val : lista) {
                out.add(Double.valueOf(val.toString()));
            }
        }
        return out;
    }

    @Override
    public List<Lavada> verLavadasPendientes() {
        Criteria criteria = super.createEntityCriteria()
                .add(Restrictions.eq("estado", Constantes.LAVADA_PENDIENTE))
                .addOrder(Order.desc("fechaLavado"));
        return (List<Lavada>) criteria.list();
    }

    @Override
    public void iniciarRelacionesLazy(Lavada lavada) {
        Lavada aux;

        aux = super.getByKey(lavada.getId());

        Hibernate.initialize(aux.getObjetosEnCustodia());
        System.out.println(aux.getObjetosEnCustodia());
        lavada.setObjetosEnCustodia(aux.getObjetosEnCustodia());

    }
}
