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

    // Número del DNI
    public static int obtenerNumero(String dni) throws Exception {
        // Transformación DNI extranjero
        String dato = procesarDigitoInicial(dni);
        
        // Devuelve el número
        return Integer.parseInt(dato.substring(0, 8));
    }

    // Control del DNI
    public static char obtenerControl(String dni) throws Exception {
        // Devuelve Letra
        return dni.charAt(8);
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

    // Procesa el DNI/número de DNI - Residentes Extranjeros
    public static final String procesarDigitoInicial(String dato) {
        // Auxiliar
        String aux = dato;
        
        // Análisis
        try {
            // Transformar Letra Residentes Extranjeros
            switch (aux.toUpperCase().charAt(0)) {
                case 'X':
                    aux = '0' + aux.substring(1);
                    break;
                case 'Y':
                    aux = '1' + aux.substring(1);
                    break;
                case 'Z':
                    aux = '2' + aux.substring(1);
            }
        } catch (Exception e) {
            aux = dato;
        }
        
        // Devuelve el número
        return aux;
    }
}
