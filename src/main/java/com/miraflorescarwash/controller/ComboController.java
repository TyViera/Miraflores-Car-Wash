/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.Combo;
import com.miraflorescarwash.model.ComboPorModelo;
import com.miraflorescarwash.model.Modelo;
import com.miraflorescarwash.service.ComboPorModeloService;
import com.miraflorescarwash.service.ComboService;
import com.miraflorescarwash.service.ModeloService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/Combo")
public class ComboController {

    @Autowired
    private ComboPorModeloService comboPorModeloService;

    @Autowired
    private ComboService comboService;

    @Autowired
    private ModeloService modeloService;

    //Combo/index.html
    @RequestMapping(value = {"/index.html", "*"}, method = RequestMethod.GET)
    public String index(Model modelo) {
        modelo.addAttribute("combos", comboPorModeloService.findAll());
        return "/Combo/index";
    }

    //Combo/view.html?id=1
    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String doGetViewCombo(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        ComboPorModelo combo = comboPorModeloService.findById(id);
        if (combo == null) {
            redirectAttributes.addFlashAttribute("msg", "Combo no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Combo/index.html";
        }
        model.addAttribute("combo", combo);
        return "Combo/mostrar";

    }

    //Combo/add.html
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String doGetAddCombo(Model modelo) {
        modelo.addAttribute("comboForm", new ComboPorModelo());
        modelo.addAttribute("combos", comboService.findAll());
        modelo.addAttribute("modelos", modeloService.findAll());
        return "Combo/add";
    }

    //Combo/update.html?id=1
    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String doGetUpdateCombo(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        ComboPorModelo combo = comboPorModeloService.findById(id);
        if (combo == null) {
            redirectAttributes.addFlashAttribute("msg", "Combo no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Combo/index.html";
        }
        model.addAttribute("comboForm", combo);
        model.addAttribute("combos", comboService.findAll());
        model.addAttribute("modelos", modeloService.findAll());
        return "Combo/add";

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String doPostDelete(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
        try {
            comboPorModeloService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "¡Combo borrado con exito!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msg", "Ocurrió algún error en la solicitud, por favor intentelo otra vez.\n"
                    + "Error: " + ex.getMessage());
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Combo/index.html";

    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public String doPostAdd(Model modelo, @RequestBody String json, BindingResult result, final RedirectAttributes redirectAttributes) {
        ComboPorModelo combo;
        if (result.hasErrors()) {
            return "Combo/add";
        }
        System.out.println("PETICION CON JSON " + json);
        try {
            if (json.contains("{")) {
                combo = new ObjectMapper().readValue(json, ComboPorModelo.class);
                comboPorModeloService.save(combo);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            redirectAttributes.addFlashAttribute("msg", "Combo agregado correctamente!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (IOException ex) {
            Logger.getLogger(ComboController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttributes.addFlashAttribute("msg", "Error!");
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Combo/index.html";
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.POST)
    public String doPostUpdate(Model modelo, @RequestBody String json, BindingResult result, final RedirectAttributes redirectAttributes) {
        ComboPorModelo combo;
        if (result.hasErrors()) {
            return "Combo/add";
        }
        try {
            System.out.println("Recibimos " + json);
            if (json.contains("{")) {
                combo = new ObjectMapper().readValue(json, ComboPorModelo.class);
                System.out.println("Actualizaremos " + combo);
                comboPorModeloService.update(combo);
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            redirectAttributes.addFlashAttribute("msg", "Combo modificado correctamente!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (IOException ex) {
            Logger.getLogger(ComboController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttributes.addFlashAttribute("msg", "Error!");
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Combo/index.html";
    }

    @RequestMapping(value = "/addmodelo.html", method = RequestMethod.POST)
    public @ResponseBody
    String doPostAddModeloSingle(Model model, @RequestBody String json) {
        Modelo modelo;
        Map<String, Object> mapa;
        try {
            modelo = new ObjectMapper().readValue(json, Modelo.class);
            modeloService.save(modelo);
            mapa = new HashMap();
            mapa.put("modelos", modeloService.findAll());
            model.mergeAttributes(mapa);
            return modelo.getId() + "";
        } catch (IOException ex) {
            Logger.getLogger(ComboController.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }

    }

    @RequestMapping(value = "/addcombo.html", method = RequestMethod.POST)
    public @ResponseBody
    String doPostAddComboSingle(Model model, @RequestBody String json) {
        Combo combo;
        Map<String, Object> mapa;
        try {
            combo = new ObjectMapper().readValue(json, Combo.class);
            comboService.save(combo);
            mapa = new HashMap();
            mapa.put("combos", comboService.findAll());
            model.mergeAttributes(mapa);
            return combo.getId() + "";
        } catch (IOException ex) {
            Logger.getLogger(ComboController.class.getName()).log(Level.SEVERE, null, ex);
            return "0";
        }

    }

}
