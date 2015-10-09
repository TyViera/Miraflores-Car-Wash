/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miraflorescarwash.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Kevin
 */
public class ExtraService {
    
    public static List<Date> obtenerUltimosNDias(int n) {
        Calendar c;
        List<Date> lista;

        lista = new ArrayList<>();
        c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
        for (int i = 0; i <= n; i++) {
            lista.add(c.getTime());
            c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
        }
        Collections.reverse(lista);
        return lista;
    }

    public static List<Date> obtenerUltimosNSemanas(int n) {
        Calendar c;
        List<Date> lista;

        lista = new ArrayList<>();
        c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 7);
        for (int i = 0; i <= n; i++) {
            lista.add(c.getTime());
            c.set(Calendar.DATE, c.get(Calendar.DATE) - 7);
        }
        Collections.reverse(lista);
        return lista;
    }

    public static List<Date> obtenerUltimosNMeses(int n) {
        Calendar c;
        List<Date> lista;

        lista = new ArrayList<>();
        c = Calendar.getInstance();
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        for (int i = 0; i <= n; i++) {
            lista.add(c.getTime());
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
        }
        Collections.reverse(lista);
        return lista;
    }

    public static List<String> formatearFechas(List<Date> fechas, String format) {
        List<String> lista;
        SimpleDateFormat formateador;
        lista = new ArrayList<>();
        formateador = new SimpleDateFormat(format, Locale.getDefault());
        for (Date fecha : fechas) {
            lista.add(formateador.format(fecha));
        }
        return lista;
    }
    
}
