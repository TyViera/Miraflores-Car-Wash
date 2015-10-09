/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.test;

import com.miraflorescarwash.model.Carro;
import com.miraflorescarwash.model.Lavada;
import com.miraflorescarwash.model.Modelo;
import com.miraflorescarwash.model.ObjetoCustodia;
import com.miraflorescarwash.service.LavadaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
public class TestLavada {

    @Autowired
    private LavadaService servicio;

    @Test
    @Transactional
    @Ignore
    public void listarLavadas() {
        int n;
        System.out.println("----- INICIO DEL TEST ------");
        n = desplegarLavadas();
        System.out.println("Existen " + n + " usuarios.");
        System.out.println("----- FIN DEL TEST ------");
    }

    private int desplegarLavadas() {
        int n;
        List<Lavada> lista;
        lista = servicio.findAll();
        n = lista.size();
        for (Lavada lava : lista) {
            System.out.println(lava);
        }
        return n;
    }

    @Test
    @Transactional
    @Ignore
    public void insertar() {
        Lavada u;
        Carro car;
        List<ObjetoCustodia> lista;
        ObjetoCustodia obj;
        int n;
        System.out.println("-----------INICIO DEL TEST INSERTAR-----------");

        n = desplegarLavadas();
        System.out.println("Al inicio habian " + n + " Lavadas");

        //Lavada{id=0, fechaLavado=Thu Oct 01 00:58:36 PET 2015, 
        //estado=PDT, carro=Carro{id=13, marca=null, placa=null, 
        //cliente=null, modelo=null},
        //objetosEnCustodia=[ObjetoCustodia{id=0, nombre=jkgjk, cantidad=1}, 
        //ObjetoCustodia{id=0, nombre=aawa, cantidad=9}]}
        car = new Carro();
        car.setId(13L);

        u = new Lavada();
        u.setCarro(car);
        u.setEstado("REA");
        u.setFechaLavado(new Date());
        u.setId(0L);

        lista = new ArrayList<>();
        obj = new ObjetoCustodia("asda", 1);
//        obj.setLavada(u);
        lista.add(obj);
        obj = new ObjetoCustodia("aassda", 9);
//        obj.setLavada(u);
        lista.add(obj);

        u.setObjetosEnCustodia(lista);

        servicio.save(u);

        n = desplegarLavadas();
        System.out.println("Ahora hay " + n + " usuarios");

        System.out.println("-----------FIN DEL TEST INSERTAR-----------");
    }

    @Test
    @Transactional
    @Ignore
    public void ListarLavada(){
        Lavada l;
        l = servicio.findById(130L);
        for (ObjetoCustodia obj : l.getObjetosEnCustodia()) {
            System.out.println(obj);
        }
    }
}
