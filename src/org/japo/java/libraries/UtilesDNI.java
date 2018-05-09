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

    // Limites DNI - Extranjero
    public static final int EXT_MIN = 1000000;
    public static final int EXT_MAX = 29999999;

    // NUMERO DIGITOS DNI
    public static final int NUM_DIG_DNI = 8;

    // Expresión Regular FORMATO NÚMERO DE DNI ESPAÑOL SIN VALIDACION
    public static final String ER_NUM_DNI_ESP = "\\d{" + NUM_DIG_DNI + "}";

    // Expresión Regular FORMATO DNI ESPAÑOL SIN VALIDACION
    public static final String ER_DNI_ESP = ER_NUM_DNI_ESP + "[" + SECUENCIA + "]";

    // Expresión Regular FORMATO NUMERO DE DNI EXTRANJERO SIN VALIDACION
    public static final String ER_NUM_DNI_EXT = "[XYZ]\\d{" + (NUM_DIG_DNI - 1) + "}";

    // Expresión Regular FORMATO DNI EXTRANJERO SIN VALIDACION
    public static final String ER_DNI_EXT = ER_NUM_DNI_EXT + "[" + SECUENCIA + "]";

    // Expresión Regular FORMATO NUMERO DE DNI (ESPAÑOL + EXTRANJERO) SIN VALIDACION
    public static final String ER_NUM_DNI = "[XYZ\\d]\\d{" + (NUM_DIG_DNI - 1) + "}";

    // Expresión Regular FORMATO DNI (ESPAÑOL + EXTRANJERO) SIN VALIDACION
    public static final String ER_DNI = ER_NUM_DNI + "[" + SECUENCIA + "]";

    // Calcula letra a partir del número de DNI
    public static final char calcularLetraDNI(int dni) {
        return SECUENCIA.charAt(dni % SECUENCIA.length());
    }

    // Extraer número del DNI
    public static final int extraerNumeroDNI(String dni) throws Exception {
        // Almacen del DNI extraido
        int numero;

        // Validar Formato DNI
        if (!UtilesValidacion.validarDato(dni, ER_DNI)) {
            throw new Exception("ERROR: Formato de DNI incorrecto");
        }

        // Extraer Prefijo Numérico
        String prefijo = normalizarNumeroDNI(dni.substring(0, NUM_DIG_DNI));

        // Convierte el texto a entero
        numero = Integer.parseInt(prefijo);

        // Devuelve el DNI obtenido
        return numero;
    }

    // Número DNI Extranjeros >> Número DNI Normalizado
    public static final String normalizarNumeroDNI(String numDNI) throws Exception {
        // Número DNI Normalizado
        String numDNINorm;

        // Proceso Normalización
        switch (numDNI.charAt(0)) {
            case 'X':
                numDNINorm = '0' + numDNI.substring(1);
                break;
            case 'Y':
                numDNINorm = '1' + numDNI.substring(1);
                break;
            case 'Z':
                numDNINorm = '2' + numDNI.substring(1);
                break;
            default:
                numDNINorm = numDNI;
        }

        // Devuelve Resultado
        return numDNINorm;
    }

    // Extraer letra del DNI
    public static char extraerLetraDNI(String dni) throws Exception {

        // Validar Formato DNI
        if (!UtilesValidacion.validarDato(dni, ER_DNI)) {
            throw new Exception("Error: Formato erróneo de DNI");
        }

        // Devuelve Letra
        return dni.charAt(NUM_DIG_DNI);
    }

    // Genera un DNI aleatorio
    public static final String generarDNI() {
        // Generar Número
        int num = new Random().nextInt(NUM_MAX - NUM_MIN + 1) + NUM_MIN;

        // Generar Control
        char ctr = calcularLetraDNI(num);

        // Devolver DNI
        return "" + num + ctr;
    }
}
