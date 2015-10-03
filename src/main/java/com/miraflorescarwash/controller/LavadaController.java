/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.Carro;
import com.miraflorescarwash.model.Lavada;
import com.miraflorescarwash.model.LavadaDisponible;
import com.miraflorescarwash.service.CarroService;
import com.miraflorescarwash.service.LavadaDisponibleService;
import com.miraflorescarwash.service.LavadaService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ty
 */
@Controller
@RequestMapping("/Lavada")
public class LavadaController {

    @Autowired
    private LavadaService lavadaService;

    @Autowired
    private LavadaDisponibleService lavadaDisponibleService;

    @Autowired
    private CarroService carroService;

    @RequestMapping(value = {"/index.html", "*"}, method = RequestMethod.GET)
    public String doGet(Model model) {
        model.addAttribute("lavadas", lavadaService.findAll());
        return "/Lavada/index";
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String doGetAdd(Model model) {
        model.addAttribute("lavadaForm", new Lavada());
        return "/Lavada/add";
    }

    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String doGetView(Model model, @RequestParam(value = "id")Long id, final RedirectAttributes redirectAttributes) {
        Lavada l;
        l = lavadaService.findById(id);
        if (l == null) {
            redirectAttributes.addFlashAttribute("msg", "Lavada no encontrada");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Lavada/index.html";
        }
        lavadaService.iniciarRelacionesLazy(l);
        model.addAttribute("lavada", l);
        return "/Lavada/mostrar";
    }
    
    @RequestMapping(value = "/pendientes.html", method = RequestMethod.GET) 
    public String doGetPendientes(Model model){
        model.addAttribute("lavadas", lavadaService.verLavadasPendientes());
        return "/Lavada/pendiente";
    }
    
    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public @ResponseBody
    String doPostAdd(@RequestBody String json) {
        Lavada lavada;
        LavadaDisponible aux, lavDispo;
        Carro carro;
        int creditoLavadas;

//        if (result.hasErrors()) {
//            return "/Lavada/add";
//            return "ER";
//        }
        try {
            System.out.println("Recibo " + json);
            if (json.contains("{")) {
                lavada = new ObjectMapper().readValue(json, Lavada.class);

                System.out.println("Se convierte en: \n" + lavada);
                
                aux = new LavadaDisponible();
                carro = carroService.findById(lavada.getCarro().getId());

                aux.setCliente(carro.getCliente());
                aux.setModelo(carro.getModelo());

                lavDispo = lavadaDisponibleService.buscarPorAuto(aux);
                if (lavDispo != null) {
                    creditoLavadas = lavDispo.getNumeroLavadas();
                    if (creditoLavadas > 0) {
                        lavadaService.save(lavada);
                        return "OK";
                    }
                }
                return "SC";
            }
            return "redirect:/Lavada/add.html";
        } catch (IOException ex) {
            return "ER";
        }
    }

    @RequestMapping(value = "/buscarAuto.html", method = RequestMethod.POST)
    public @ResponseBody
    String doPostBuscarCarroPorPlaca(@RequestBody String placa) {
        System.out.println("Recibo " + placa);
        if (placa != null && !placa.isEmpty()) {
            Carro car = carroService.findByPlaca(placa);
            if (car != null) {
                System.out.println("Envio '" + car.getId() + "'");
                return car.getId() + "";
            } else {
                return "0";
            }

        } else {
            return "";
        }
    }

    @RequestMapping(value = "/objeto.html", method = RequestMethod.POST)
    public @ResponseBody String doPostObj(@RequestBody String json){
        return "";
    }
    
    @RequestMapping(value = "/marcarRealizada.html", method = RequestMethod.POST)
    public @ResponseBody String doPostMarcarRealizada(@RequestBody String json){
        Lavada aux, lavada;
        try {
            aux = new ObjectMapper().readValue(json, Lavada.class);
            lavada = lavadaService.findById(aux.getId());
            if(lavada != null){
                lavada.setEstado(Constantes.LAVADA_REALIZADA);
                lavadaService.update(lavada);
                return "OK";
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return "ER";
    }
    
}
