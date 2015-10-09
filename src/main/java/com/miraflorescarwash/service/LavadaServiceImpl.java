/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import com.miraflorescarwash.controller.Constantes;
import com.miraflorescarwash.dao.LavadaDAO;
import com.miraflorescarwash.model.Lavada;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Month;
import org.jfree.data.xy.XYDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("lavadaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class LavadaServiceImpl implements LavadaService {

    @Autowired
    private LavadaDAO lavadaDAO;
    
    @Override
    @Transactional
    public Lavada findById(Long id) {
        return lavadaDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Lavada> findAll() {
        return lavadaDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Lavada lavada) {
        lavadaDAO.save(lavada);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Lavada lavada) {
        lavadaDAO.update(lavada);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        lavadaDAO.delete(id);
    }

    @Override
    @Transactional
    public List<Double> obtenerLista(int tipo, int cantidadRegistros) {
        return lavadaDAO.obtenerLista(tipo, cantidadRegistros);
    }
    
    @Override
    @Transactional
    public List<Lavada> verLavadasPendientes(){
        return lavadaDAO.verLavadasPendientes();
    }
    
    @Override
    @Transactional
    public void iniciarRelacionesLazy(Lavada lavada){
        lavadaDAO.iniciarRelacionesLazy(lavada);
    }
    
    @Override
    @Transactional
    public JFreeChart generarChartReporteVentas(int tipo, int cantidadRegistros){
        Object localObject;
        JFreeChart localJFreeChart;
        XYPlot localXYPlot;
        XYItemRenderer localXYItemRenderer;
        String nameX;
        XYDataset paramXYDataset;

        paramXYDataset = createDataset(tipo, cantidadRegistros);
        
        nameX = obtenerTiempo(tipo);
        
        localJFreeChart = ChartFactory.createTimeSeriesChart(
                "Reporte de Ventas por " + nameX, "Fecha", "Ventas Nuevo Soles", paramXYDataset, true, true, false
        );
        localXYPlot = (XYPlot) localJFreeChart.getPlot(); 
//        localXYPlot.setDomainPannable(true);
//        localXYPlot.setRangePannable(false);
        localXYPlot.setDomainCrosshairVisible(true);
        localXYPlot.setRangeCrosshairVisible(true);
        localXYItemRenderer = localXYPlot.getRenderer();
        if ((localXYItemRenderer instanceof XYLineAndShapeRenderer)) {
            localObject = (XYLineAndShapeRenderer) localXYItemRenderer;
            ((XYLineAndShapeRenderer) localObject).setBaseShapesVisible(false);
        }
        localObject = (DateAxis) localXYPlot.getDomainAxis();
        switch (tipo) {
            case Constantes.REPORTE_LAVADA_DIARIO:
                ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
                break;
            case Constantes.REPORTE_LAVADA_SEMANAL:
                ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
                break;
            case Constantes.REPORTE_LAVADA_MENSUAL:
                ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
                break;
            default:
                ((DateAxis) localObject).setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
                break;
        }
        
        return localJFreeChart;
    }
    
    @Transactional
    private XYDataset createDataset(int tipo, int cantidad) {
        String nombreSerie;
        TimeSeries serie1;
        List<Double> lista;
        Calendar c;
        Date fecha;
        int val;

        nombreSerie = "Ventas";

        serie1 = new TimeSeries(nombreSerie, Day.class);

        lista = this.obtenerLista(tipo, cantidad);

        if (lista != null) {
            c = Calendar.getInstance();
            fecha = c.getTime();
            switch (tipo) {
                case Constantes.REPORTE_LAVADA_DIARIO:
                    for (Double valor : lista) {
                        serie1.add(new Day(fecha), valor);

                        c.setTime(fecha);
                        val = c.get(Calendar.DATE);
                        val = val - 1;
                        c.set(Calendar.DATE, val);
                        fecha = c.getTime();
                    }
                    break;
                case Constantes.REPORTE_LAVADA_SEMANAL:
                    serie1 = new TimeSeries(nombreSerie, Week.class);
                    for (Double valor : lista) {
                        serie1.add(new Week(fecha), valor);

                        c.setTime(fecha);
                        val = c.get(Calendar.DATE);
                        val = val - 7;
                        c.set(Calendar.DATE, val);
                        fecha = c.getTime();
                    }
                    break;
                case Constantes.REPORTE_LAVADA_MENSUAL:
                    serie1 = new TimeSeries(nombreSerie, Month.class);
                    for (Double valor : lista) {
                        serie1.add(new Month(fecha), valor);
                        c.setTime(fecha);
                        val = c.get(Calendar.MONTH);
                        val = val - 1;
                        c.set(Calendar.MONTH, val);
                        fecha = c.getTime();
                    }
                    break;
                default:
                    break;
            }

        }

        TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
        localTimeSeriesCollection.addSeries(serie1);
//        localTimeSeriesCollection.addSeries(localTimeSeries2);
        return localTimeSeriesCollection;
    }

    private String obtenerTiempo(int tipo) {
        switch (tipo) {
            case Constantes.REPORTE_LAVADA_DIARIO:
                return "Dia";
            case Constantes.REPORTE_LAVADA_SEMANAL:
                return "Semana";
            case Constantes.REPORTE_LAVADA_MENSUAL:
                return "Mes";
            default:
                return "None";
        }
    }
    
}
