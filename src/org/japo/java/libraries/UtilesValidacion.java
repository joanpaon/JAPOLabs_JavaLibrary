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
import java.util.regex.PatternSyntaxException;
import javax.swing.JTextField;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesValidacion {

    // Dato + Expresión Regular
    public static final boolean validarDato(String dato, String er) {
        // Semáforo de validación
        boolean testOK = false;

        // Proceso de validación
        try {
            // Compila la expresión regular
            Pattern patron = Pattern.compile(er);

            // Genera el motor de búsqueda
            Matcher detector = patron.matcher(dato);

            // Averiguar Coincidencia
            testOK = detector.matches();
        } catch (PatternSyntaxException e) {
            System.out.println(e);
        }

        // Devolver Semáforo
        return testOK;
    }

    // Texto + Expresión Regular
    public static final Matcher buscarPatron(String texto, String er) {
        // Compila la expresión regular
        Pattern patron = Pattern.compile(er);

        // Genera el motor de búsqueda
        Matcher detector = patron.matcher(texto);

        // Realiza la comprobación
        detector.find();

        // Retorno del resultado
        return detector;
    }

    // Campo de texto con DNI + Texto campo vacío
    public static final boolean validarCampoDNI(
            JTextField txfActual, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Campo vacio
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Valida el Dato
        boolean validacionOK = validarDNI(textoActual);

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
    public static final boolean validarCampoFecha(
            JTextField txfActual, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Campo vacio
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Valida el Dato
        boolean validacionOK = validarFecha(textoActual);

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
    public static final boolean validarCampoTexto(
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
    public static final boolean validarCampoTextoLista(
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
    public static final boolean validarURL(String url) {
        // Expresión Regular
        final String ER = "^(https?://)?(([\\w!~*'().&=+$%-]+: )?[\\w!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([\\w!~*'()-]+\\.)*([\\w^-][\\w-]{0,61})?[\\w]\\.[a-z]{2,6})(:[0-9]{1,4})?((/*)|(/+[\\w!~*'().;?:@&=+$,%#-]+)+/*)$";

        // Devuelve Semáforo
        return validarDato(url, ER);
    }

    // Validar email
    public static final boolean validarEMail(String email) {
        // Expresión Regular
        final String ER = "[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*";

        // Devuelve Semáforo
        return validarDato(email, ER);
    }

    // Validar Dato < Lista Datos
    public static final boolean validarDatoLista(String dato, String[] lista) {
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

    // Validación Fecha - Campos Separados - Expresión Regular
    public static final boolean validarFechaExpReg(int dia, int mes, int any) {
        // Construye la fecha a partir de sus componentes
        String fecha = String.format("%02d"
                + UtilesFecha.ER_SEP_FECHA.charAt(1) + "%02d"
                + UtilesFecha.ER_SEP_FECHA.charAt(1) + "%d", dia, mes, any);

        // Devuelve la validación de la fecha
        return UtilesValidacion.validarDato(fecha, UtilesFecha.ER_FECHA);
    }

    // Validación Fecha - Campos Separados - No dependencias
    public static final boolean validarFechaNoDep(int dia, int mes, int any) {
        return dia >= 1 && dia <= 31
                && (mes == 1 || mes == 3 || mes == 5
                || mes == 7 || mes == 8 || mes == 10
                || mes == 12)
                || dia >= 1 && dia <= 30
                && (mes == 4 || mes == 6 || mes == 9
                || mes == 11)
                || dia >= 1 && dia <= 29 && mes == 2
                && (any % 400 == 0
                || any % 100 != 0 && any % 4 == 0)
                || dia >= 1 && dia <= 28 && mes == 2;
    }

    // Validación Fecha - Campos Separados
    public static final boolean validarFecha(int dia, int mes, int any) {
        // Calcula cuantos dias tiene el mes de la fecha
        int numDias = UtilesFecha.obtenerDiasMes(mes, any);

        // Devuelve el resultado de la validación
        return dia >= 1 && dia <= numDias;
    }

    // Comprobar si el año es bisiesto
    public static final boolean validarBisiesto(int any) {
        return any % 400 == 0 || any % 100 != 0 && any % 4 == 0;
    }

    // Validación Fecha - Texto sin gesglosar - Expresión Regular
    public static final boolean validarFechaExpReg(String fecha) {
        return UtilesValidacion.validarDato(fecha, UtilesFecha.ER_FECHA);
    }

    // Validación Fecha - Texto sin gesglosar
    public static final boolean validarFecha(String fecha) {
        // Extrae los componentes de la fecha
        int dia = UtilesFecha.obtenerDiaFecha(fecha);
        int mes = UtilesFecha.obtenerMesFecha(fecha);
        int any = UtilesFecha.obtenerAnyFecha(fecha);

        // Valida la fecha
        return validarFecha(dia, mes, any);
    }

    // Valida DNI - Formato texto
    public static final boolean validarDNI(String dni) {
        // Semáforo de validación
        boolean dniOK = false;

        // Validar DNI
        try {
            // Extraer número de DNI
            int num = UtilesDNI.extraerNumeroDNI(dni);

            // Extraer letra de DNI
            char ctr = UtilesDNI.extraerLetraDNI(dni);

            // Análisis Concordancia
            dniOK = validarDNI(num, ctr);
        } catch (Exception e) {
            System.out.println("ERROR: Formato erróneo de DNI");
        }

        // Resultado del análisis
        return dniOK;
    }

    // Valida DNI - Desglosado
    public static final boolean validarDNI(int num, char ctr) {
        return UtilesDNI.calcularLetraDNI(num) == ctr;
    }

    // Valida la parte del NUMERO del DNI (sin el control)
    public static final boolean validarNumeroDNI(String dato) {
        return validarDato(dato, UtilesDNI.ER_DNI);
    }
}
