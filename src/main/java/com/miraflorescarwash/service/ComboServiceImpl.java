/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.service;

import com.miraflorescarwash.dao.ComboDAO;
import com.miraflorescarwash.model.Combo;
import com.miraflorescarwash.model.ComboReporte;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("comboService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ComboServiceImpl implements ComboService {

    @Autowired
    private ComboDAO comboDAO;

    @Override
    @Transactional
    public Combo findById(Long id) {
        return comboDAO.findById(id);
    }

    @Override
    @Transactional
    public List<Combo> findAll() {
        return comboDAO.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void save(Combo combo) {
        comboDAO.save(combo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(Combo combo) {
        comboDAO.update(combo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void delete(Long id) {
        comboDAO.delete(id);
    }

    @Override
    @Transactional
    public List<com.miraflorescarwash.model.ComboReporte> ComboReporte() {
        return comboDAO.ComboReporte();
    }

    @Override
    public JFreeChart generarChartReporte(List<com.miraflorescarwash.model.ComboReporte> lista) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (ComboReporte comboReporte : lista) {
            dataset.setValue(comboReporte.getNombre(), comboReporte.getCantidad());
        }
        return ChartFactory.createPieChart("Reporte de Combos",
                dataset, true, true, false);
    }

}
