/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import com.miraflorescarwash.model.Usuario;
import com.miraflorescarwash.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ty
 */
@Controller
@SessionAttributes("usuario")
public class HomeController {

    @RequestMapping(value = {"index.html", "*"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "login.html", method = RequestMethod.GET)
    public String login(Model modelo, 
            @RequestParam(value = "error", required = false)boolean error, 
            final RedirectAttributes redirectAttributes
    ) {
        if(error){
            redirectAttributes.addFlashAttribute("msg", "Usuario o Contraseña incorrectos");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/login.html";
        }
        return "login";
    }

    private boolean validarUsuario(Usuario us) {
        if (us == null) {
            return false;
        }
        if (us.getUsername()== null || us.getUsername().isEmpty()) {
            return false;
        }
        if (us.getPassword() == null || us.getPassword().isEmpty()) {
            return false;
        }
        return true;
    }

}
