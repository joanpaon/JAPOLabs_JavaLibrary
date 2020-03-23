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

import java.util.StringJoiner;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesTXT {

    // Longitud predeterminada Título formateado
    public static final int LONGITUD = 21;

    // Archivo TXT > Array Lineas Texto
    public static final String[] importar(String fichero) throws Exception {
        return UtilesFicheros.leerArrayFichero(fichero);
    }

    // Titulo > Titulo + SPC + puntos + ':' + SPC [longitud]
    public static final String formatearTitulo(String titulo, int longitud) {
        // Titulo > Mayúscula Inicial
        titulo = capitalizarTexto(titulo);

        // Genera Linea Puntos
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            buffer.append('.');
        }

        // Recorta Linea Puntos
        String puntos = buffer.toString().substring(titulo.length() + 2);

        // Genera y devuelve título formateado
        return String.format("%s %s: ", titulo, puntos);
    }

    // Titulo > Titulo + SPC + puntos + ':' + SPC [longitud]
    public static final String formatearTitulo(String titulo) {
        return formatearTitulo(titulo, LONGITUD);
    }

    // String > Mayuscula Inicial (capitalize)
    public static final String capitalizarTexto(String titulo) {
        return titulo.substring(0, 1).toUpperCase() + titulo.substring(1).toLowerCase();
    }

    // Array String > Enumeración String
    public static final String enumerar(String[] items) {
        // Buffer de Texto
        StringJoiner buffer = new StringJoiner(",");

        for (String item : items) {
            buffer.add(item);
        }

        return buffer.toString();
    }

}
