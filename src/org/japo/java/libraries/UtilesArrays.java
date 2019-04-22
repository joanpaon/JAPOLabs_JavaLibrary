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
public final class UtilesArrays {

    // Sistema Números Aleatórios
    public static final Random RND = new Random();

    // Array int >> Total
    public static final int acumular(int[] listaDatos) {
        // Acumulador
        int total = 0;

        // Proceso
        for (int dato : listaDatos) {
            total += dato;
        }

        // Devolución Valor
        return total;
    }

    // Array double >> Total
    public static final double acumular(double[] listaDatos) {
        // Acumulador
        double total = 0;

        // Proceso
        for (double dato : listaDatos) {
            total += dato;
        }

        // Devolución Valor
        return total;
    }

    // Número aleatorio >> Array Char
    public static final char[] generar(int numPos, char chrMin, char chrMax) {
        // Array Vacío
        char[] listaDatos = new char[numPos];

        // Generación de datos
        for (int i = 0; i < numPos; i++) {
            listaDatos[i] = (char) (RND.nextInt(chrMax - chrMin + 1) + chrMin);
        }

        // Devolución Array
        return listaDatos;
    }

    // Número aleatorio >> Array Entero
    public static final int[] generar(int numPos, int numMin, int numMax) {
        // Array Vacío
        int[] listaDatos = new int[numPos];

        // Generación de datos
        for (int i = 0; i < numPos; i++) {
            listaDatos[i] = RND.nextInt(numMax - numMin + 1) + numMin;
        }

        // Devolución Array
        return listaDatos;
    }

    // Número aleatorio >> Array Real
    public static final double[] generar(int numPos, double numMin, double numMax) {
        // Array Vacío
        double[] listaDatos = new double[numPos];

        // Generación de datos
        for (int i = 0; i < numPos; i++) {
            listaDatos[i] = RND.nextDouble() * (numMax - numMin) + numMin;
        }

        // Devolución Array
        return listaDatos;
    }

    // Inicializar Array - char
    public static final void inicializar(char[] listaDatos, char dato) {
        for (int i = 0; i < listaDatos.length; i++) {
            listaDatos[i] = dato;
        }
    }

    // Inicializar Array - int
    public static final void inicializar(int[] listaDatos, int dato) {
        for (int i = 0; i < listaDatos.length; i++) {
            listaDatos[i] = dato;
        }
    }

    // Inicializar Array - double
    public static final void inicializar(double[] listaDatos, double dato) {
        for (int i = 0; i < listaDatos.length; i++) {
            listaDatos[i] = dato;
        }
    }

    // Inicializar Array - boolean
    public static final void inicializar(boolean[] listaDatos, boolean dato) {
        for (int i = 0; i < listaDatos.length; i++) {
            listaDatos[i] = dato;
        }
    }

    // Búsqueda Simple - Enteros
    public static final int buscar(int[] listaDatos, int clave) {
        // Marcador de posición
        int posicion = 0;

        // Semáforo de Proceso de Búsqueda
        boolean finBusquedaOK = false;

        // Proceso de búsqueda
        do {
            if (posicion >= listaDatos.length) {
                finBusquedaOK = true;
                posicion = -1;
            } else if (listaDatos[posicion] == clave) {
                finBusquedaOK = true;
            } else {
                posicion++;
            }
        } while (!finBusquedaOK);

        // Posición de la clave en el array
        return posicion;
    }

    // Búsqueda Simple - Reales
    public static final int buscar(double[] listaDatos, double clave) {
        // Marcador de posición
        int posicion = 0;

        // Semáforo de Proceso de Búsqueda
        boolean finBusquedaOK = false;

        // Proceso de búsqueda
        do {
            if (posicion >= listaDatos.length) {
                finBusquedaOK = true;
                posicion = -1;
            } else if (listaDatos[posicion] == clave) {
                finBusquedaOK = true;
            } else {
                posicion++;
            }
        } while (!finBusquedaOK);

        // Posición de la clave en el array
        return posicion;
    }

    // Búsqueda Simple - String
    public static final int buscar(String[] listaDatos, String clave) {
        // Marcador de posición
        int posicion = 0;

        // Semáforo de Proceso de Búsqueda
        boolean finBusquedaOK = false;

        // Proceso de búsqueda
        do {
            if (posicion >= listaDatos.length) {
                finBusquedaOK = true;
                posicion = -1;
            } else if (listaDatos[posicion].equals(clave)) {
                finBusquedaOK = true;
            } else {
                posicion++;
            }
        } while (!finBusquedaOK);

        // Posición de la clave en el array
        return posicion;
    }

    // Ordenación - Burbuja
    public static final void ordenarBurbuja(int[] lista) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = 0; j < lista.length - i - 1; j++) {
                // Actual > Siguiente
                if (lista[j] > lista[j + 1]) {
                    // Intercambiar
                    int aux = lista[j + 1];
                    lista[j + 1] = lista[j];
                    lista[j] = aux;
                }
            }
        }
    }

    // Ordenación - Inserción
    public static final void ordenarInsercion(int[] lista) {
        for (int i = 1; i <= lista.length - 1; i++) {
            // Elemento Actual
            int aux = lista[i];

            // Hacer Hueco a Izquierda
            int j = i - 1;
            while (j >= 0 && aux < lista[j]) {
                lista[j + 1] = lista[j];
                j = j - 1;
            }

            // Insertar Elemento Actual en Hueco
            lista[j + 1] = aux;
        }
    }

    // Ordenación - Selección
    public static final void ordenarSeleccion(int[] lista) {
        for (int i = 0; i < lista.length - 1; i++) {
            // Posición de Mínimo
            int pos = i;

            // Seleccionar Posición de Minimo a Derecha
            for (int j = i + 1; j < lista.length; j++) {
                if (lista[j] < lista[pos]) {
                    pos = j;
                }
            }

            // Intercambiar
            int aux = lista[i];
            lista[i] = lista[pos];
            lista[pos] = aux;
        }
    }

    // Desordenar Lista - caracter
    public static final char[] desordenar(char[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int posRnd = RND.nextInt(lista.length);

            char aux = lista[posRnd];
            lista[posRnd] = lista[i];
            lista[i] = aux;
        }
        
        return lista;
    }

    // Desordenar Lista - entero
    public static final int[] desordenar(int[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int posRnd = RND.nextInt(lista.length);

            int aux = lista[posRnd];
            lista[posRnd] = lista[i];
            lista[i] = aux;
        }
        
        return lista;
    }

    // Desordenar - real
    public static final double[] desordenar(double[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int posRnd = RND.nextInt(lista.length);

            double aux = lista[posRnd];
            lista[posRnd] = lista[i];
            lista[i] = aux;
        }
        
        return lista;
    }

    // Desordenar - boolean
    public static final boolean[] desordenar(boolean[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int posRnd = RND.nextInt(lista.length);

            boolean aux = lista[posRnd];
            lista[posRnd] = lista[i];
            lista[i] = aux;
        }
        
        return lista;
    }

    // Desordenar - String
    public static final String[] desordenar(String[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int posRnd = RND.nextInt(lista.length);

            String aux = lista[posRnd];
            lista[posRnd] = lista[i];
            lista[i] = aux;
        }
        
        return lista;
    }

    // int[] > Posicion (Primera) Valor Máximo
    public static final int obtenerMaximo(int[] lista) {
        // Primero es maximo
        int posicion = 0;

        // Recorrer a partir del segundo
        for (int actual = 1; actual < lista.length; actual++) {
            posicion = lista[actual] > lista[posicion] ? actual : posicion;
}

        // Devolver posicion
        return posicion;
    }

    // int[] > Posición (Primera) Valor Mínimo
    public static final int obtenerMinimo(int[] lista) {
        // Primero es maximo
        int posicion = 0;

        // Recorrer a partir del segundo
        for (int actual = 1; actual < lista.length; actual++) {
            posicion = lista[actual] < lista[posicion] ? actual : posicion;
        }

        // Devolver posicion
        return posicion;
    }
    
    public static final int contarOcurrencias(int[] lista, int clave) {
        // Contador ocurrencias
        int contador = 0;

        // Recorrer
        for (int item : lista) {
            contador = item == clave ? contador + 1 : contador;
        }

        // Devolver contador
        return contador;
    }
}
