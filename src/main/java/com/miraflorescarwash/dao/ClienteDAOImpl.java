/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.dao;

import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.model.ClienteReporte;
import com.miraflorescarwash.model.CreditoDisponibleCliente;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

@Repository("clienteDAO")
public class ClienteDAOImpl extends AbstractDao<Long, Cliente> implements ClienteDAO {

    @Override
    public Cliente findById(Long id) {
        return super.getByKey(id);
    }

    @Override
    public List<Cliente> findAll() {
        Criteria criteria = super.createEntityCriteria().addOrder(Order.asc("id"));
        return (List<Cliente>) criteria.list();
    }

    @Override
    public void save(Cliente cliente) {
        super.save(cliente);
    }

    @Override
    public void update(Cliente cliente) {
        super.update(cliente);
    }

    @Override
    public void delete(Long id) {
        Cliente c;
        c = this.findById(id);
        super.delete(c);
    }

    @Override
    public List<ClienteReporte> reporteCompras(boolean ordenAscendente) {
        List lista;
        String consulta;

        consulta = "SELECT * FROM public.ventas_clientes() ORDER BY cantidad ";
        if (ordenAscendente) {
            consulta += " ASC ";
        } else {
            consulta += " DESC ";
        }
        consulta += " LIMIT 10;";
        SQLQuery query = super.getSession().createSQLQuery(consulta);
        lista = query.list();
        return this.obtenerListaReportes(lista);
    }

    @Override
    public List<ClienteReporte> reporteComprasEsteMes() {
        List lista;
        String consulta;

        consulta = "SELECT * FROM public.ventas_clientes_mes()";
        SQLQuery query = super.getSession().createSQLQuery(consulta);
        lista = query.list();
        return this.obtenerListaReportes(lista);
    }

    @Override
    public List<ClienteReporte> reporteComprasPorMesPorCliente(Long idCliente) {
        List lista;
        String consulta;

        consulta = "SELECT * FROM public.ventas_cliente_por_mes(?)";
        SQLQuery query = super.getSession().createSQLQuery(consulta);
        query.setParameter(0, idCliente);
        lista = query.list();
        return this.obtenerListaReportes(lista);
    }

    @Override
    public List<CreditoDisponibleCliente> verCreditoDisponible(Long idCliente) {
        List lista;
        List<CreditoDisponibleCliente> out;
        String consulta;
        Object a[];

        consulta = "SELECT * FROM public.credito_cliente(?)";
        SQLQuery query = super.getSession().createSQLQuery(consulta);
        query.setParameter(0, idCliente);
        lista = query.list();

        out = new ArrayList<>();
        if (lista != null) {
            for (Object l : lista) {
                a = (Object[]) l;
                out.add(new CreditoDisponibleCliente(
                        Long.valueOf(a[0].toString()),
                        a[1].toString(),
                        a[2].toString(),
                        a[3].toString(),
                        Integer.valueOf(a[4].toString()),
                        a[5].toString()
                ));
            }
        }
        return out;
    }

    @Override
    public void iniciarRelacionesLazy(Cliente cliente) {
        Cliente aux;

        aux = super.getByKey(cliente.getId());

        Hibernate.initialize(aux.getCarros());
        System.out.println(aux.getCarros());
        cliente.setCarros(aux.getCarros());

        Hibernate.initialize(aux.getClientecombopormodelos());
        System.out.println(aux.getCarros());
        cliente.setClientecombopormodelos(aux.getClientecombopormodelos());

        Hibernate.initialize(aux.getLavadadisponibles());
        System.out.println(aux.getCarros());
        cliente.setLavadadisponibles(aux.getLavadadisponibles());

    }

    public List<ClienteReporte> obtenerListaReportes(List lista) {
        List<ClienteReporte> out;
        Object a[];
        out = new ArrayList<>();
        if (lista != null) {
            for (Object l : lista) {
                a = (Object[]) l;
                out.add(new ClienteReporte(
                        Long.valueOf(a[0].toString()),
                        a[1].toString(),
                        a[2].toString(),
                        Double.valueOf(a[3].toString())
                ));
            }
        }
        return out;
    }

}
