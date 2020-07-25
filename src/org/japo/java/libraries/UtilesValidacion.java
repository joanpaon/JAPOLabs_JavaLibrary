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

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesValidacion {

    // Dato + Expresión Regular
    public static final boolean validar(String dato, String er) {
        return UtilesExpReg.enlazar(dato, er).matches();
    }

    // Texto + [Lista] > Hay Ocurrencia o No
    public static final boolean validar(String dato, String[] lista) {
        // Ordena Lista
        Arrays.sort(lista);

        // Devuelve el resultado de la busqueda
        return Arrays.binarySearch(lista, dato) >= 0;
    }
}
