/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miraflorescarwash.test;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Kevin
 */
public class TestSMS {

    @Test
//    @Ignore
    public void enviaSMS() {
        System.out.println("Inicio del test SMS");
        //Se inicia el objeto HTTP
        HttpClient client = new HttpClient();
        client.setStrictMode(true);
        //Se fija el tiempo máximo de espera de la respuesta del servidor
        client.setTimeout(60000);
        //Se fija el tiempo máximo de espera para conectar con el servidor
        client.setConnectionTimeout(5000);
        PostMethod post = null;
        //Se fija la URL sobre la que enviar la petición POST

        //Como ejemplo se supone www.pasarela_push_altiria.com/post/sms
//        post = new PostMethod("http://www.pasarela_push_altiria.com/post/sms");
        post = new PostMethod("http://www.enviosmsconjava.com/post/sms");

        //Se fija la codificación de caracteres en la cabecera de la petición
        post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");

        //Se crea la lista de parámetros a enviar en la petición POST
        NameValuePair[] parametersList = new NameValuePair[3];
//        parametersList[0] = new NameValuePair("dest", "+51951972371");
        parametersList[0] = new NameValuePair("dest", "951972371");
        parametersList[1] = new NameValuePair("dest", "51951972371");
//        parametersList[1] = new NameValuePair("dest", "34600111333");
        parametersList[2] = new NameValuePair("msg", "Mensaje de prueba");

        //Se rellena el cuerpo de la petición POST con los parámetros
        post.setRequestBody(parametersList);
        int httpstatus = 0;
        String response = null;

        try {
        //Se envía la petición
            httpstatus = client.executeMethod(post);
        //Se consigue la respuesta
            response = post.getResponseBodyAsString();
        } catch (Exception e) {
        //Habrá que prever la captura de excepciones
            e.printStackTrace();
            return;
        } finally {
        //En cualquier caso se cierra la conexión
            System.out.println("cerrar con");
            post.releaseConnection();
        }

        //Habrá que prever posibles errores en la respuesta del servidor
        if (httpstatus != 200) {
            System.out.println("no 200");
            return;
        } else {
        //Se procesa la respuesta capturada en la cadena ‘‘response’’
            System.out.println("Respuesta: ");
            System.out.println(response);
        }

        System.out.println("Final del test SMS");
    }
}
