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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
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
    public List<ClienteReporte> reporteComprasEsteMes() {
        return clienteDAO.reporteComprasEsteMes();
    }

    @Override
    @Transactional
    public List<ClienteReporte> reporteComprasPorMesPorCliente(Long idCliente) {
        return clienteDAO.reporteComprasPorMesPorCliente(idCliente);
    }

    @Override
    @Transactional
    public List<CreditoDisponibleCliente> verCreditoDisponible(Long idCliente) {
        return clienteDAO.verCreditoDisponible(idCliente);
    }

    @Override
    public JFreeChart generarChartReporte(List<ClienteReporte> lista) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ClienteReporte clienteReporte : lista) {
            dataset.setValue(clienteReporte.getTotal(), "H", clienteReporte.getId());
        }
        return ChartFactory.createBarChart("Clientes Frecuentes",
                "Clientes", "Dinero Recaudado", dataset,
                PlotOrientation.VERTICAL, false, true, false);
    }

    @Override
    public JFreeChart generarChartReporteMes(List<ClienteReporte> lista) {
        TimeSeriesCollection localTimeSeriesCollection;
        TimeSeries serie1;
        Date fecha;
        Calendar c;
        Object localObject;
        JFreeChart localJFreeChart;
        XYPlot localXYPlot;
        XYItemRenderer localXYItemRenderer;
        int val;

        serie1 = new TimeSeries("Ventas", Month.class);
        c = Calendar.getInstance();
        fecha = c.getTime();
        for (ClienteReporte clienteReporte : lista) {
            serie1.add(new Month(fecha), clienteReporte.getTotal());
            c.setTime(fecha);
            val = c.get(Calendar.MONTH);
            val = val - 1;
            c.set(Calendar.MONTH, val);
            fecha = c.getTime();
        }
        
        localTimeSeriesCollection = new TimeSeriesCollection();
        localTimeSeriesCollection.addSeries(serie1);
        
        localJFreeChart = ChartFactory.createTimeSeriesChart("Evolución de cliente",
                "Tiempo", "Dinero Recaudado", localTimeSeriesCollection, false, true, false);
        localXYPlot = (XYPlot) localJFreeChart.getPlot();
        localXYPlot.setDomainCrosshairVisible(true);
        localXYPlot.setRangeCrosshairVisible(true);
        localXYItemRenderer = localXYPlot.getRenderer();
        if ((localXYItemRenderer instanceof XYLineAndShapeRenderer)) {
            localObject = (XYLineAndShapeRenderer) localXYItemRenderer;
            ((XYLineAndShapeRenderer) localObject).setBaseShapesVisible(false);
        }
        localObject = (DateAxis) localXYPlot.getDomainAxis();
        ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        return localJFreeChart;
    }

}
