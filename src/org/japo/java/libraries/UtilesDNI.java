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

import java.util.Random;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesDNI {

    // Secuencia letras DNI
    public static final String SECUENCIA = "TRWAGMYFPDXBNJZSQVHLCKE";

    // Limites DNI
    public static final int NUM_MIN = 10000000;
    public static final int NUM_MAX = 99999999;

    // Expresión Regular FORMATO DNI ESPAÑOL SIN VALIDACION
    public static final String ER_DNI_ESP = "[0-9]{8}[" + SECUENCIA + "]";

    // Expresión Regular FORMATO DNI EXTRANJERO SIN VALIDACION
    public static final String ER_DNI_EXT = "[XYZ][0-9]{7}[" + SECUENCIA + "]";

    // Expresión Regular FORMATO DNI (ESPAÑOL + EXTRANJERO) SIN VALIDACION
    public static final String ER_DNI = ER_DNI_ESP + "|" + ER_DNI_EXT;

    // Calcula letra a partir del número de DNI
    public static char calcularControl(int dni) {
        return SECUENCIA.charAt(dni % SECUENCIA.length());
    }

    // Extraer número del DNI
    public static int extraerNumero(String dni) throws Exception {
        // Almacen del DNI extraido
        int numero;

        // Validar Formato DNI
        if (!UtilesValidacion.validarDato(dni, ER_DNI)) {
            throw new Exception("ERROR: Formato erróneo de DNI");
        }

        // Extraer Prefijo Numérico
        String prefijo = "";
        switch (dni.charAt(0)) {
            case 'X':
                prefijo += '0' + dni.substring(1, dni.length() - 1);
                break;
            case 'Y':
                prefijo += '1' + dni.substring(1, dni.length() - 1);
                break;
            case 'Z':
                prefijo += '2' + dni.substring(1, dni.length() - 1);
                break;
            default:
                prefijo += dni.substring(0, dni.length() - 1);
        }

        // Convierte el texto a entero
        numero = Integer.parseInt(prefijo);

        // Devuelve el DNI obtenido
        return numero;
    }

    // Extraer letra del DNI
    public static char extraerControl(String dni) throws Exception {
        // Validar Formato DNI
        if (!UtilesValidacion.validarDato(dni, ER_DNI)) {
            throw new Exception("ERROR: Formato erróneo de DNI");
        }

        // Devuelve Letra
        return dni.charAt(dni.length() - 1);
    }

    // Genera un DNI aleatorio
    public static final String generarDNI() {
        // Generar Número
        int num = new Random().nextInt(NUM_MAX - NUM_MIN + 1) + NUM_MIN;

        // Generar Control
        char ctr = calcularControl(num);

        // Devolver DNI
        return "" + num + ctr;
    }
}
