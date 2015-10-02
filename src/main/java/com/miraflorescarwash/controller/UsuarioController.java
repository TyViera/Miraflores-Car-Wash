/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.Usuario;
import com.miraflorescarwash.model.UsuarioRol;
import com.miraflorescarwash.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/Usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Usuario/index.html
    @RequestMapping(value = {"/index.html", "*"}, method = RequestMethod.GET)
    public String index(Model modelo) {
        modelo.addAttribute("usuarios", usuarioService.findAll());
        return "/Usuario/index";
    }

    //Usuario/view.html?id=1
    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String doGetViewUsuario(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("msg", "Usuario no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Usuario/index.html";
        }
        model.addAttribute("usuario", usuario);
        return "Usuario/mostrar";

    }

    //Usuario/add.html
    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String doGetAddUsuario(Model modelo) {
        modelo.addAttribute("usuarioForm", new Usuario());
        return "Usuario/add";
    }

    //Usuario/update.html?id=1
    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String doGetUpdateUsuario(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            redirectAttributes.addFlashAttribute("msg", "Usuario no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Usuario/index.html";
        }
        model.addAttribute("usuarioForm", usuario);
        return "Usuario/add";

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String doPostDelete(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
        try {
            usuarioService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "¡Usuario borrado con exito!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msg", "Ocurrió algún error en la solicitud, por favor intentelo otra vez.\n"
                    + "Error: " + ex.getMessage());
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Usuario/index.html";

    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public String doPostAdd(Model modelo, @ModelAttribute("usuarioForm") @Validated Usuario usuario, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Usuario/add";
        }
        usuarioService.save(usuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario agregado correctamente!");
        redirectAttributes.addFlashAttribute("css", "success");
        return "redirect:/Usuario/index.html";
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.POST)
    public String doPostUpdate(Model modelo, @ModelAttribute("usuarioForm") @Validated Usuario usuario, BindingResult result, final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Usuario/add";
        }
        usuarioService.update(usuario);
        redirectAttributes.addFlashAttribute("msg", "Usuario modificado correctamente!");
        redirectAttributes.addFlashAttribute("css", "success");
        return "redirect:/Usuario/index.html";
    }

}
