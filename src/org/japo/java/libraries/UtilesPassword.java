/* 
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
public final class UtilesPassword {

    // Longitudes - user
    public static final int LEN_MIN_USER = 6;
    public static final int LEN_MAX_USER = 12;

    // Conjuntos - user
    public static final String SET_LWR_USER = "abcdeéfghiíjklmnñoópqrstuúüvwxyz";
    public static final String SET_UPR_USER = "ABCDEÉFGHIÍJKLMNÑOÓPQRSTUÚÜVWXYZ";
    public static final String SET_DIG_USER = "0123456789";
    public static final String SET_EXT_USER = "_@#$~%&";

    // Mensajes - user
    public static final String MSG_ERR_LEN_USER = "ERROR: Longitud fuera de rango";
    public static final String MSG_ERR_LWR_USER = "ERROR: Ausencia de minúsculas";
    public static final String MSG_ERR_UPR_USER = "ERROR: Ausencia de mayúsculas";
    public static final String MSG_ERR_DIG_USER = "ERROR: Ausencia de dígitos numéricos";
    public static final String MSG_ERR_EXT_USER = "ERROR: Ausencia de caracteres extendidos";
    public static final String MSG_ERR_USER = "ERROR: Caracteres NO permitidos";
    public static final String MSG_SUC_USER = "Formato de Usuario Correcto";

    // Expresiones Regulares - user
    public static final String ER_LEN_USER = String.format(".{%d,%d}", LEN_MIN_USER, LEN_MAX_USER);
    public static final String ER_LWR_USER = String.format(".*[%s].*", SET_LWR_USER);
    public static final String ER_UPR_USER = String.format(".*[%s].*", SET_UPR_USER);
    public static final String ER_DIG_USER = String.format(".*[%s].*", SET_DIG_USER);
    public static final String ER_EXT_USER = String.format(".*[%s].*", SET_EXT_USER);
    public static final String ER_SET_USER = String.format("([%s]|[%s]|[%s]|[%s]){%d,%d}",
            SET_LWR_USER, SET_UPR_USER, SET_DIG_USER, SET_EXT_USER,
            LEN_MIN_USER, LEN_MAX_USER);

    // Longitudes - pass
    public static final int LEN_MIN_PASS = 8;
    public static final int LEN_MAX_PASS = 16;

    // Conjuntos - pass
    public static final String SET_LWR_PASS = "abcdefghijklmnopqrstuvwxyz";
    public static final String SET_UPR_PASS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String SET_DIG_PASS = "0123456789";
    public static final String SET_EXT_PASS = "_@#$~%&";

    // Mensajes - pass
    public static final String MSG_ERR_LEN_PASS = "ERROR: Longitud fuera de rango";
    public static final String MSG_ERR_LWR_PASS = "ERROR: Ausencia de minúsculas";
    public static final String MSG_ERR_UPR_PASS = "ERROR: Ausencia de mayúsculas";
    public static final String MSG_ERR_DIG_PASS = "ERROR: Ausencia de dígitos numéricos";
    public static final String MSG_ERR_EXT_PASS = "ERROR: Ausencia de caracteres extendidos";
    public static final String MSG_ERR_PASS = "ERROR: Caracteres NO permitidos";
    public static final String MSG_SUC_PASS = "Formato de Contraseña Correcto";

    // Expresiones Regulares - pass
    public static final String ER_LEN_PASS = String.format(".{%d,%d}", LEN_MIN_PASS, LEN_MAX_PASS);
    public static final String ER_LWR_PASS = String.format(".*[%s].*", SET_LWR_PASS);
    public static final String ER_UPR_PASS = String.format(".*[%s].*", SET_UPR_PASS);
    public static final String ER_DIG_PASS = String.format(".*[%s].*", SET_DIG_PASS);
    public static final String ER_EXT_PASS = String.format(".*[%s].*", SET_EXT_PASS);
    public static final String ER_SET_PASS = String.format("([%s]|[%s]|[%s]|[%s]){%d,%d}",
            SET_LWR_PASS, SET_UPR_PASS, SET_DIG_PASS, SET_EXT_PASS,
            LEN_MIN_PASS, LEN_MAX_PASS);

    // Categorias de caracteres
    public static final int CAT_LWR = 0;
    public static final int CAT_UPR = 1;
    public static final int CAT_DIG = 2;
    public static final int CAT_EXT = 3;

    // Número de Categorias
    public static final int NUM_CAT = 4;

    // Símbolos categoria - Arrays
    public static final char[] LST_PASS_LWR = SET_LWR_PASS.toCharArray();
    public static final char[] LST_PASS_UPR = SET_UPR_PASS.toCharArray();
    public static final char[] LST_PASS_DIG = SET_DIG_PASS.toCharArray();
    public static final char[] LST_PASS_PUN = SET_EXT_PASS.toCharArray();

    // Sistema aleatorio
    public static final Random RND = new Random();

    // Genera Password (min + may + num + pun) - Longitud
    public static final char[] generarPassword(int longPass) {
        return UtilesValidacion.validar(longPass + "", ER_LEN_PASS)
                ? generarPassword(new char[longPass]) : null;
    }

    // Genera Password (min + may + num + pun) - Array
    public static final char[] generarPassword(char[] pass) {
        if (UtilesValidacion.validar(pass.length + "", ER_LEN_PASS)) {
            // Diferentes Categorias - Manual
            pass[CAT_LWR] = generarCaracter(SET_LWR_PASS);
            pass[CAT_UPR] = generarCaracter(SET_UPR_PASS);
            pass[CAT_DIG] = generarCaracter(SET_DIG_PASS);
            pass[CAT_EXT] = generarCaracter(SET_EXT_PASS);

            // Bucle generador - A partir de Posicion 5
            for (int posAct = NUM_CAT; posAct < pass.length; posAct++) {
                // Generar categoria
                int catAct = RND.nextInt(NUM_CAT);

                // Analizar categoria actual 
                switch (catAct) {
                    case CAT_LWR:
                        pass[posAct] = generarCaracter(LST_PASS_LWR);
                        break;
                    case CAT_UPR:
                        pass[posAct] = generarCaracter(LST_PASS_UPR);
                        break;
                    case CAT_DIG:
                        pass[posAct] = generarCaracter(LST_PASS_DIG);
                        break;
                    case CAT_EXT:
                        pass[posAct] = generarCaracter(LST_PASS_PUN);
                }
            }

            // Desordenar Array
            UtilesArrays.desordenar(pass);
        } else {
            pass = null;
        }

        // Devuelve el array
        return pass;
    }

    // Genera un Caracter Aleatorio de un Texto
    public static final char generarCaracter(String lista) {
        return lista.charAt(RND.nextInt(lista.length()));
    }

    // Genera un Caracter Aleatorio de una Lista
    public static final char generarCaracter(char[] lista) {
        return lista[RND.nextInt(lista.length)];
    }
}
