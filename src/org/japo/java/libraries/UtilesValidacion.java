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

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesValidacion {

    // Dato + Expresión Regular
    public static final boolean validar(String dato, String er) {
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

    // Texto + [Lista]
    public static final boolean validar(String dato, String[] lista) {
        Arrays.sort(lista);
        return Arrays.binarySearch(lista, dato) >= 0;
    }
}
