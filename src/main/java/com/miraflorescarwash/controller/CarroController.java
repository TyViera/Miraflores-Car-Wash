/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.controller;

import org.codehaus.jackson.map.ObjectMapper;
import com.miraflorescarwash.model.Carro;
import com.miraflorescarwash.model.Cliente;
import com.miraflorescarwash.service.CarroService;
import com.miraflorescarwash.service.ClienteService;
import com.miraflorescarwash.service.ModeloService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Kevin
 */
@Controller
@RequestMapping("/Carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = {"index.html", "*"}, method = RequestMethod.GET)
    public String index(Model modelo) {
        System.out.println("6");
        modelo.addAttribute("carros", carroService.findAll());
        return "/Carro/index";
    }

    @RequestMapping(value = "/view.html", method = RequestMethod.GET)
    public String doGetViewCarro(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {
        Carro carro = carroService.findById(id);
        if (carro == null) {
            redirectAttributes.addFlashAttribute("msg", "Carro no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Carro/index.html";
        }
        model.addAttribute("carro", carro);
        return "Carro/mostrar";

    }

    @RequestMapping(value = "/add.html", method = RequestMethod.GET)
    public String doGetAddCarro(Model modelo, @RequestParam(value = "cliente", required = false) Long idCliente) {
        Cliente cliente;
        Carro carro;

        carro = new Carro();
        System.out.println("dasdas das " + idCliente);
        if (!(idCliente == null || idCliente == 0)) {
            cliente = clienteService.findById(idCliente);
            carro.setCliente(cliente);
        }

        modelo.addAttribute("carroForm", carro);
        modelo.addAttribute("modelosCarro", modeloService.findAll());
        modelo.addAttribute("clientes", clienteService.findAll());
        return "/Carro/add";
    }

    //Cliente/update.html?id=1
    @RequestMapping(value = "/update.html", method = RequestMethod.GET)
    public String doGetUpdateCarro(@RequestParam("id") long id, Model model, final RedirectAttributes redirectAttributes) {

        Carro cliente = carroService.findById(id);
        if (cliente == null) {
            redirectAttributes.addFlashAttribute("msg", "Carro no encontrado");
            redirectAttributes.addFlashAttribute("css", "danger");
            return "redirect:/Carro/index.html";
        }
        model.addAttribute("carroForm", cliente);
        model.addAttribute("modelosCarro", modeloService.findAll());
        return "Carro/add";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String doPostDelete(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
        try {
            carroService.delete(id);
            redirectAttributes.addFlashAttribute("msg", "¡Carro borrado con exito!");
            redirectAttributes.addFlashAttribute("css", "success");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("msg", "Ocurrió algún error en la solicitud, por favor intentelo otra vez.\n"
                    + "Error: " + ex.getMessage());
            redirectAttributes.addFlashAttribute("css", "danger");
        }
        return "redirect:/Carro/index.html";
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    public @ResponseBody
    String doPostAdd(Model modelo, @RequestBody String car) {
        Carro carro;
        try {
            System.out.println("recibo " + car);
            if (car.contains("{")) {
                carro = new ObjectMapper().readValue(car, Carro.class);
                try {
                    carroService.save(carro);
                    return "OK";
                } catch (Exception ex) {
                    String msj;
                    msj = ex.getCause().getMessage();
//                    System.out.println("##### " + msj);
                    if (msj.contains("placa") && msj.contains("already exists")) {
                        System.out.println("PLACA REPETIDA");
                        return "PR";
                    }
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            redirectAttributes.addFlashAttribute("msg", "¡Carro agregado correctamente!");
//            redirectAttributes.addFlashAttribute("css", "success");
//            return "redirect:/Carro/index.html";
            return "ER";
        } catch (IOException ex) {
//            redirectAttributes.addFlashAttribute("msg",
//                    "Sucedió un error, por favor intentelo otra vez.\n"
//                    + "Error: " + ex.getMessage());
//            redirectAttributes.addFlashAttribute("css", "danger");
//            return "redirect:/Carro/index.html";
            return "ER";
        }
    }

    @RequestMapping(value = "/update.html", method = RequestMethod.POST)
    public @ResponseBody
        String doPostUpdate(Model modelo, @RequestBody String car) {
        Carro carro;
//        if (result.hasErrors()) {
//            System.out.println(result.getAllErrors());
//            return "Carro/add";
//        }
        try {
            System.out.println("recibo " + car);
            if (car.contains("{")) {
                carro = new ObjectMapper().readValue(car, Carro.class);
                try {
                    carroService.update(carro);
                    return "OK";
                } catch (Exception ex) {
                    String msj;
                    msj = ex.getCause().getMessage();
                    if (msj.contains("placa") && msj.contains("already exists")) {
                        System.out.println("PLACA REPETIDA");
                        return "PR";
                    }
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CarroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//            redirectAttributes.addFlashAttribute("msg", "¡Carro agregado correctamente!");
//            redirectAttributes.addFlashAttribute("css", "success");
//            return "redirect:/Carro/index.html";
            return "ER";
        } catch (IOException ex) {
//            redirectAttributes.addFlashAttribute("msg",
//                    "Sucedió un error, por favor intentelo otra vez.\n"
//                    + "Error: " + ex.getMessage());
//            redirectAttributes.addFlashAttribute("css", "danger");
//            return "redirect:/Carro/index.html";
            return "ER";
        }
    }

}
