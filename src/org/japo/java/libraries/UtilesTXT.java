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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesTXT {

    // Archivo TXT > Lista Lineas
    public static final String[] importar(String fichero) throws Exception {
        // Acumulador Lineas
        List<String> listaLineas = new ArrayList<>();

        // Importar Items
        try (
                FileReader fr = new FileReader(fichero);
                BufferedReader br = new BufferedReader(fr)) {
            // Bucle Recorrido Lineas Fichero
            boolean finFicheroOK = false;
            do {
                // Fichero TXT > Linea Items (1ª Linea)
                String linea = br.readLine();

                // Análisis Linea
                if (linea == null) {
                    finFicheroOK = true;
                } else {
                    listaLineas.add(linea);
                }
            } while (!finFicheroOK);
        }

        // Devolver Items
        return listaLineas.toArray(new String[listaLineas.size()]);
    }
}
