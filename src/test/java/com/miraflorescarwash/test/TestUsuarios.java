/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.test;

import com.miraflorescarwash.model.Usuario;
import com.miraflorescarwash.service.UsuarioService;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kevin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
        classes = com.miraflorescarwash.config.SpringDBConfig.class,
        loader = AnnotationConfigWebContextLoader.class
)
public class TestUsuarios {

    @Autowired
    private UsuarioService servicio;

    @Test
    @Transactional
    @Ignore
    public void listarUsuarios() {
        int n;
        System.out.println("----- INICIO DEL TEST ------");
        n = desplegarUsuarios();
        System.out.println("Existen " + n + " usuarios.");
        System.out.println("----- FIN DEL TEST ------");
    }

    @Test
    @Transactional
    @Ignore
    public void insertar() {
        Usuario u;
        int n;
        System.out.println("-----------INICIO DEL TEST INSERTAR-----------");

        n = desplegarUsuarios();
        System.out.println("Al inicio habian " + n + " usuarios");

        u = new Usuario();
        u.setPassword("1234");
        u.setNombreCompleto("Pedro Infante");
        u.setUsername("pepe");

        servicio.save(u);

        n = desplegarUsuarios();
        System.out.println("Ahora hay " + n + " usuarios");
        
        System.out.println("-----------FIN DEL TEST INSERTAR-----------");
    }

    private int desplegarUsuarios() {
        int n;
        List<Usuario> lista;
        lista = servicio.findAll();
        n = lista.size();
        for (Usuario usuario : lista) {
            System.out.println(usuario);
        }
        return n;
    }

}
