/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.ClienteReporte;
import com.miraflorescarwash.model.CreditoDisponibleCliente;
import com.miraflorescarwash.service.ClienteService;
import com.miraflorescarwash.service.ComboService;
import com.miraflorescarwash.service.ExtraService;
import com.miraflorescarwash.service.LavadaService;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ty
 */
@Controller
@RequestMapping("/Reporte")
public class ReporteController {

    @Autowired
    private LavadaService lavadaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComboService comboService;

    @RequestMapping(value = {"/index.html", "*"}, method = RequestMethod.GET)
    public String index() {
        return "/Reporte/index";
    }

    @RequestMapping(value = "/ventas.html", method = RequestMethod.GET)
    public String doGetVentas(Model model, @RequestParam("tiempo") String tiempo) {
        List<Double> listaValores;
        List<Date> fechas;
        List<String> listaEtiquetas;
        String ti;
        SimpleDateFormat format;

        int me;
        int n;
        n = 10;
        switch (tiempo) {
            case "dia":
                me = Constantes.REPORTE_LAVADA_DIARIO;
                ti = "Día";
                fechas = ExtraService.obtenerUltimosNDias(n);
                listaEtiquetas = ExtraService.formatearFechas(fechas, "dd 'de' MMM");
                format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                break;
            case "sem":
                me = Constantes.REPORTE_LAVADA_SEMANAL;
                ti = "Semana";
                fechas = ExtraService.obtenerUltimosNSemanas(n);
                listaEtiquetas = ExtraService.formatearFechas(fechas, "dd 'de' MMM");
                format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                break;
            case "mes":
                me = Constantes.REPORTE_LAVADA_MENSUAL;
                ti = "Mes";
                fechas = ExtraService.obtenerUltimosNMeses(n);
                listaEtiquetas = ExtraService.formatearFechas(fechas, "MMM 'de' yyyy");
                format = new SimpleDateFormat("MMMM 'de' yyyy");
                break;
            default:
                me = -1;
                ti = "--";
                fechas = null;
                listaEtiquetas = null;
                format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
                break;
        }
        if (me >= 0) {
            listaValores = lavadaService.obtenerLista(me, n);
            Collections.reverse(listaValores);
            model.addAttribute("data", listaValores);
            model.addAttribute("etiq", listaEtiquetas);
            model.addAttribute("tiempoInicio", format.format(fechas.get(0)));
            model.addAttribute("tiempoFin", format.format(fechas.get(fechas.size() - 2)));
            model.addAttribute("tiempo", ti);
            return "/Reporte/ventas";
        } else {
            model.addAttribute("msg", "Datos invalidos");
            model.addAttribute("css", "danger");
            return "/Reporte/index";
        }

    }

    @RequestMapping(value = "/cliente.html", method = RequestMethod.GET)
    public String doGetClientes(Model model, @RequestParam("tipo") String tipo) {
        List<ClienteReporte> reporteCompras;
        String mensaje;
        switch (tipo) {
            case "fre":
                reporteCompras = clienteService.reporteCompras(false);
                mensaje = "Clientes frecuentes";
                model.addAttribute("clientes", reporteCompras);
                model.addAttribute("mensaje", mensaje);
                return "/Reporte/cliente";
            case "prm":
                reporteCompras = clienteService.reporteComprasEsteMes();
                mensaje = "Gastos de Clientes en el mes actual";
                model.addAttribute("clientes", reporteCompras);
                model.addAttribute("mensaje", mensaje);
                return "/Reporte/cliente";
            case "evo":
                //Formularios para saber cual
                model.addAttribute("clientes", clienteService.findAll());
                return "/Reporte/clienteEvolucion";
            case "cre":
                //Formularios para saber cual
                model.addAttribute("clientes", clienteService.findAll());
                return "/Reporte/clienteCredito";
            default:
                model.addAttribute("msg", "Datos invalidos");
                model.addAttribute("css", "danger");
                return "/Reporte/index";
        }
    }

    @RequestMapping(value = "/cliente.html", method = RequestMethod.POST)
    public String doPostClientes(Model model, @RequestParam("tipo") String tipo, @RequestParam("id") Long id) {
        List<ClienteReporte> reporteCompras;
        List<CreditoDisponibleCliente> reporteCredito;
        List<Date> fechas;
        List<String> etiquetas;
        int n;
        n = 10;
        switch (tipo) {
            case "evo":
                reporteCompras = clienteService.reporteComprasPorMesPorCliente(id);
                fechas = ExtraService.obtenerUltimosNMeses(n);
                Collections.reverse(reporteCompras);
                etiquetas = ExtraService.formatearFechas(fechas, "MMM 'de' yyyy");
                model.addAttribute("clientesReporte", reporteCompras);
                model.addAttribute("etiquetas", etiquetas);
                model.addAttribute("clientes", clienteService.findAll());
                return "/Reporte/clienteEvolucion";
            case "cre":
                reporteCredito = clienteService.verCreditoDisponible(id);
                model.addAttribute("clientes", clienteService.findAll());
                model.addAttribute("credito", reporteCredito);
                return "/Reporte/clienteCredito";
            default:
                model.addAttribute("msg", "Datos invalidos");
                model.addAttribute("css", "danger");
                return "/Reporte/index";
        }

    }

    @RequestMapping(value = "/combos.html", method = RequestMethod.GET)
    public String doGetCombos(Model model) {
        model.addAttribute("combosReporte", comboService.ComboReporte());
        return "/Reporte/combo";
    }

}
