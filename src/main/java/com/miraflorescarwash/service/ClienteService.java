/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.model.ClienteReporte;
import com.miraflorescarwash.model.ComboReporte;
import com.miraflorescarwash.model.CreditoDisponibleCliente;
import java.util.List;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author Kevin
 */
public interface ClienteService {

    public Cliente findById(Long id);

    public List<Cliente> findAll();

    public void iniciarRelacionesLazy(Cliente cliente);
    
    public void save(Cliente cliente);

    public void update(Cliente cliente);

    public void delete(Long id);

    public List<ClienteReporte> reporteCompras(boolean ordenAscendente);

    public List<ClienteReporte> reporteComprasEsteMes();
    
    public List<ClienteReporte> reporteComprasPorMesPorCliente(Long idCliente);
    
    public List<CreditoDisponibleCliente> verCreditoDisponible(Long idCliente);
    
    public JFreeChart generarChartReporte(List<ClienteReporte> lista);
    
    public JFreeChart generarChartReporteMes(List<ClienteReporte> lista);
}
