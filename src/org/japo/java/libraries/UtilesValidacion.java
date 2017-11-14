/* 
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.libraries;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesValidacion {

    // Dato + Expresión Regular
    public static boolean validarDato(String dato, String expReg) {
        // Semáforo de validación
        boolean testOK = false;

        // Realizar Validación
        try {
            // Patrón de la expresión regular
            Pattern patron = Pattern.compile(expReg);

            // Detector Texto de Prueba
            Matcher detector = patron.matcher(dato);

            // Averiguar Coincidencia
            testOK = detector.matches();
        } catch (Exception e) {
            System.out.println(e);
        }

        // Devolver Semáforo
        return testOK;
    }

    // Campo de texto con DNI + Texto campo vacío
    public static boolean validarCampoDNI(
            JTextField txfActual, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Campo vacio
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Valida el Dato
        boolean validacionOK = UtilesDNI.validarDNI(textoActual);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Campo de texto con FECHA + Texto campo vacío
    public static boolean validarCampoFecha(
            JTextField txfActual, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Campo vacio
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Valida el Dato
        boolean validacionOK = UtilesFecha.validarFecha(textoActual);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Campo de texto con DATO + ExpReg + Texto campo vacío
    public static boolean validarCampoTexto(
            JTextField txfActual, String expReg, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Comprueba campo vacío
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Restaura el texto formateado
        txfActual.setText(textoActual);

        // Valida el Dato
        boolean validacionOK = validarDato(textoActual, expReg);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Campo de texto con DATO + Lista + Texto campo vacío
    public static boolean validarCampoTextoLista(
            JTextField txfActual, String[] lista, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Comprueba campo vacío
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Restaura el texto formateado
        txfActual.setText(textoActual);

        // Valida el Dato
        boolean validacionOK = validarDatoLista(textoActual, lista);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Validar URL
    public static boolean validarURL(String url) {
        // Expresión Regular
        final String ER = "^(https?://)?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";

        // Devuelve Semáforo
        return validarDato(url, ER);
    }

    // Validar email
    public static boolean validarEMail(String email) {
        // Expresión Regular
        final String ER = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*";

        // Devuelve Semáforo
        return validarDato(email, ER);
    }

    // Validar Dato < Lista Datos
    public static boolean validarDatoLista(String dato, String[] lista) {
        // Semáforo Validación
        boolean validacionOK = false;

        // Proceso Validación
        if (lista != null) {
            // Referencia Expresión Regular
            String er = "";

            // Construye Expresión Regular
            for (String item : lista) {
                er += item + "|";
            }

            // Elimina Operador Final
            er = er.substring(0, er.lastIndexOf("|"));

            // Calcula Semáforo
            validacionOK = validarDato(dato, er);
        }

        // Devuelve Semáforo
        return validacionOK;
    }
}
