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
public class UtilesSorteos {

    // Referencias
    public static final int LONGITUD_BOMBO_PRIMITIVA = 49;
    public static final int LONGITUD_APUESTA_PRIMITIVA = 6;

    // Sistema de Números Aleatorios
    public static final Random RND = new Random();

    // Genera Bombo Loteria Primitiva | Inicializado 1 - 49
    public static final int[] generarBomboPrimitiva() {
        // Definir bombo
        int[] bombo = new int[LONGITUD_BOMBO_PRIMITIVA];

        // Llenar bombo - Primer Número: 1
        for (int i = 0; i < bombo.length; i++) {
            bombo[i] = i + 1;
        }

        // Devolver Bombo
        return bombo;
    }

    // Genera Apuesta Loteria Primitiva
    public static final int[] generarApuestaPrimitiva() {
        // Definir Apuesta
        int[] apuesta = new int[LONGITUD_APUESTA_PRIMITIVA];

        // Definir bombo
        int[] bombo = generarBomboPrimitiva();

        // Generar apuesta
        for (int i = 0; i < apuesta.length; i++) {
            // Extrae la posición actual
            int posicion = RND.nextInt(LONGITUD_BOMBO_PRIMITIVA - i);

            // Extrae la bola de la posición actual
            apuesta[i] = bombo[posicion];

            // Rellena el hueco
            bombo[posicion] = bombo[LONGITUD_BOMBO_PRIMITIVA - i - 1];
        }

        // Devolver Apuesta
        return apuesta;
    }
}
