/*
 * Copyright 2020 JAPO Labs - japolabs@gmail.com.
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesScrapping {

    // URL Página Web > Texto
    public static final String obtenerTextoHTML(String urlObjetivo) {
        String contenido = "";
        try {
            URL url = new URL(urlObjetivo);
            try (
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr)) {

                // Acumulador de texto
                StringBuilder sb = new StringBuilder();

                // Línea actual
                String linea;
                do {
                    // URL > linea texto
                    linea = br.readLine();

                    // Comprueba Fin de Stream
                    if (linea != null) {
                        // Acumula linea actual
                        sb.append(linea);

                        // Separador
//                        sb.append(' ');
                        sb.append('\n');
                    }
                } while (linea != null);

                // Guarda el contenido
                contenido = sb.toString();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return contenido;
    }

}
