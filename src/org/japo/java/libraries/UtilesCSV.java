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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesCSV {

    // Separadores
    public static final String SEPARADOR_LECTURA = "\\s*,\\s*";
    public static final String SEPARADOR_ESCRITURA = ", ";

    // Archivo CSV > Lista Items
    public static final String[] importar(String fichero)
            throws Exception {
        // Lista Items (Vacio)
        String[] items;

        // Importar Items
        try (
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr)) {
            // Fichero CSV > Linea Items (1ª Linea)
            String linea = br.readLine();

            // Linea Items > Lista Items
            items = convertir(linea);
        }

        // Devolver Items
        return items;
    }

    // Lista Items > Archivo CSV
    public static final void exportar(String[] items, String fichero)
            throws Exception {
        // Lectura de un fichero de texto
        try (
                FileWriter fw = new FileWriter(fichero);
                PrintWriter pw = new PrintWriter(fw)) {

            // Lista Items > Linea Items
            String linea = convertir(items);

            // Primer item por separado
            pw.print(linea);

            // Confirmacion
            pw.flush();
        }
    }

    // Array String > Secuencia String
    public static final String convertir(String[] items, String separador) {
        // Acumulador
        StringBuilder sb = new StringBuilder();

        // Primer item por separado
        sb.append(items[0]);

        // Resto de los items
        for (int i = 1; i < items.length; i++) {
            sb.append(separador);
            sb.append(items[i]);
        }

        // Devuelve Secuencia
        return sb.toString();
    }

    // Array String > Secuencia String - Separador Predeterminado
    public static final String convertir(String[] items) {
        return convertir(items, SEPARADOR_ESCRITURA);
    }

    // Secuencia String > Array String
    public static final String[] convertir(String secuencia, String separador) {
        return secuencia.split(separador);
    }

    // Secuencia String > Array String - Separador Predeterminado
    public static final String[] convertir(String secuencia) {
        return convertir(secuencia, SEPARADOR_LECTURA);
    }
}
