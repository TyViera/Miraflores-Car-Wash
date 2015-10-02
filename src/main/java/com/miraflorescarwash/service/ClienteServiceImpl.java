/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.ClienteDAO;
import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.model.ClienteReporte;
import com.miraflorescarwash.model.CreditoDisponibleCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("clienteService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    @Transactional
    public Cliente findById(Long id) {
        return clienteDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Cliente> findAll() {
        return clienteDAO.findAll();
    }

    @Override
    @Transactional
    public void iniciarRelacionesLazy(Cliente cliente) {
        clienteDAO.iniciarRelacionesLazy(cliente);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        clienteDAO.delete(id);
    }

    @Override
    @Transactional
    public List<ClienteReporte> reporteCompras(boolean ordenAscendente) {
        return clienteDAO.reporteCompras(ordenAscendente);
    }

    @Override
    @Transactional
    public List<ClienteReporte> reporteComprasEsteMes(){
        return clienteDAO.reporteComprasEsteMes();
    }
    
    @Override
    @Transactional
    public List<ClienteReporte> reporteComprasPorMesPorCliente(Long idCliente){
        return clienteDAO.reporteComprasPorMesPorCliente(idCliente);
    }
    
    @Override
    @Transactional
    public List<CreditoDisponibleCliente> verCreditoDisponible(Long idCliente){
        return clienteDAO.verCreditoDisponible(idCliente);
    }
}
