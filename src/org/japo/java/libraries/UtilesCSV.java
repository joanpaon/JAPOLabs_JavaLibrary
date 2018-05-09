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
    public static final String[] importarItemsCSV(String fichero) throws Exception {
        // Lista Items (Vacio)
        String[] items;

        // Importar Items
        try (BufferedReader entrada = new BufferedReader(new FileReader(fichero))) {
            // Fichero CSV > Linea Items
            String linea = entrada.readLine();

            // Linea Items > Lista Items
            items = linea.split(SEPARADOR_LECTURA);
        }

        // Devolver Items
        return items;
    }

    // Lista Items > Archivo CSV
    public static final void exportarItemsCSV(String[] items, String fichero) throws Exception {
        // Lectura de un fichero de texto
        try (PrintWriter salida = new PrintWriter(new FileWriter(fichero))) {

            // Primer item por separado
            salida.print(items[0]);

            // Resto de los items
            for (int i = 1; i < items.length; i++) {
                salida.print(SEPARADOR_ESCRITURA + items[i]);
            }
        }
    }
}
