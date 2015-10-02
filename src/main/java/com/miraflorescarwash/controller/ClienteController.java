/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ty
 */
@Controller
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    //Cliente/index.html
    @RequestMapping(value = {"/index.html", "*"}, method = RequestMethod.GET)
    public String index(Model modelo) {
        modelo.addAttribute("clientes", clienteService.findAll());
        return "/Cliente/index";
    }

    //Cliente/view.html?id=1
    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String doGetViewCliente(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            redirectAttributes.addFlashAttribute("msg", "Cliente no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Cliente/index.html";
        }
        clienteService.iniciarRelacionesLazy(cliente);
        model.addAttribute("cliente", cliente);
        return "Cliente/mostrar";

    }

    //Cliente/add.html
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String doGetAddCliente(Model modelo) {
        modelo.addAttribute("clienteForm", new Cliente());
        return "Cliente/add";
    }

    //Cliente/update.html?id=1
    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String doGetUpdateCliente(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            redirectAttributes.addFlashAttribute("msg", "Cliente no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Cliente/index.html";
        }
        model.addAttribute("clienteForm", cliente);
        return "Cliente/add";

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String doPostDelete(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
        try {
            clienteService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "¡Cliente borrado con exito!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msg", "Ocurrió algún error en la solicitud, por favor intentelo otra vez.\n"
                    + "Error: " + ex.getMessage());
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Cliente/index.html";

    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public String doPostAdd(Model modelo, @ModelAttribute("clienteForm") @Validated Cliente cliente, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Cliente/add";
        }
        try {
            clienteService.save(cliente);
        } catch (Exception ex) {
            String msj;
            msj = ex.getCause().getMessage();
            if (msj.contains("dni") && msj.contains("already exists")) {
                modelo.addAttribute("err", "El Dni ingresado ya existe");
//                modelo.addAttribute("clienteForm", new Cliente());
                return "Cliente/add";
            }
        }
        redirectAttributes.addFlashAttribute("msg", "Cliente agregado correctamente!");
        redirectAttributes.addFlashAttribute("css", "success");
        return "redirect:/Cliente/index.html";
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.POST)
    public String doPostUpdate(Model modelo, @ModelAttribute("clienteForm") @Validated Cliente cliente, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Cliente/add";
        }
        clienteService.update(cliente);
        redirectAttributes.addFlashAttribute("msg", "Cliente modificado correctamente!");
        redirectAttributes.addFlashAttribute("css", "success");
        return "redirect:/Cliente/index.html";
    }

}
