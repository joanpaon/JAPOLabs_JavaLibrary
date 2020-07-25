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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesExpReg {

    // ER > Pattern
    public static final Pattern compilar(String er) {
        return compilar(er, 0);
    }

    // ER + Modificadores > Pattern
    public static final Pattern compilar(String er, int modificadores) {
        Pattern p = null;
        try {
            p = Pattern.compile(er, modificadores);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    // ER + Texto > Matcher
    public static final Matcher enlazar(String texto, String er) {
        Matcher m = null;
        try {
            m = compilar(er).matcher(texto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return m;
    }

    // Texto + Expresión Regular > Coincidencia
    public static final boolean validarCoincidencia(String dato, String er) {
        return UtilesExpReg.enlazar(dato, er).matches();
    }

    // Texto + Expresión Regular > Existencia
    public static final boolean validarExistencia(String dato, String er) {
        return UtilesExpReg.enlazar(dato, er).lookingAt();
    }

    // Texto + Expresión Regular > Lista Coincidencias
    public static final List<String> obtenerCoincidencias(String dato, String er) {
        List<String> lista = new ArrayList<>();

        Matcher m = enlazar(dato, er);

        while (m.find()) {
            String s = m.group(1);
            lista.add(s);
//            System.out.println(m.group());
//            System.out.println(m.group(1));
        }

        return lista;
    }

    // Texto + Expresión Regular > Número Coincidencias
    public static final int contarCoincidencias(String dato, String er) {
        return obtenerCoincidencias(dato, er).size();
    }
}
