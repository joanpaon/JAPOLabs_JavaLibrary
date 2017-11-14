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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesFecha {

    // Nombres de los dias de la semana
    public static final String[] NOMBRE_DIA = {
        "lunes", "martes", "miércoles", "jueves",
        "viernes", "sábado", "domingo"};

    // Nombres de los meses del año
    public static final String[] NOMBRE_MES = {
        "enero", "febrero", "marzo", "abril", "mayo",
        "junio", "julio", "agosto", "septiembre",
        "octubre", "noviembre", "diciembre"};

    // Nombres de las estaciones
    public static final String[] NOMBRE_ESTACION = {
        "primavera", "verano", "otoño", "invierno"};

    // Día de cualquier mes hasta el día 28
    public static final String ER_DIA28 = "(0?[1-9]|1[0-9]|2[0-8])";

    // Mes del año - Entre 1 ó 01 y 12
    public static final String ER_MES = "(0?[1-9]|1[0-2])";

    // Año - Entre 0 y 9999
    public static final String ER_ANY = "([0-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3})";

    // Separador de campos de fecha: "/" o "-"
    public static final String ER_SEP_FECHA = "[/-]";

    // Fecha de cualquier Mes desde el dia 1 al día 28
    public static final String ER_FECHA_DIA28
            = "(" + ER_DIA28 + ER_SEP_FECHA + ER_MES + ER_SEP_FECHA + ER_ANY + ")";

    // Años divisibles por 400 (Hasta 4 digitos)
    public static final String ER_ANYS_MOD400
            = "(" + "0?[48]00|[13579][26]00|[2468][048]00" + ")";

    // Años NO divisibles por 100 pero SI divisibles por 4 (Hasta 4 dígitos)
    public static final String ER_ANYS_MOD4_NO100
            = "(" + "[0-9]{0,2}" + "((0?|[2468])[48]|[13579][26]|[2468]0)" + ")";    // Desde 4 hasta 96

    // Años Bisiestos (Hasta 4 digitos)
    public static final String ER_ANYS_BISIESTOS
            = "(" + ER_ANYS_MOD400 + "|" + ER_ANYS_MOD4_NO100 + ")";

    // Fecha del Dia 29 de Febreros BISIESTOS
    public static final String ER_FECHA_DIA29_BISIESTO
            = "(" + "29" + ER_SEP_FECHA + "(2|02)" + ER_SEP_FECHA + ER_ANYS_BISIESTOS + ")";

    // Meses que tienen 29/30 dias (Todos menos Febrero)
    public static final String ER_MESES_30DIAS = "(0?[13456789]|1[012])";

    // Fecha del Día 29 de cualquier Mes SIN Febrero
    public static final String ER_FECHA_DIA29_NORMAL
            = "(" + "29" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // Fecha del Dia 29 de cualquier Mes CON Febrero
    public static final String ER_FECHA_DIA29
            = "(" + ER_FECHA_DIA29_BISIESTO + "|" + ER_FECHA_DIA29_NORMAL + ")";

    // Fecha del Dia 30 de cualquier Mes
    public static final String ER_FECHA_DIA30
            = "(" + "30" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";
    // Meses que tienen 31 dias
    public static final String ER_MESES_31DIAS = "(0?[13578]|1[02])";

    // Fecha del Día 31 de cualquier Mes
    public static final String ER_FECHA_DIA31
            = "(" + "31" + ER_SEP_FECHA + ER_MESES_31DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // Fecha
    public static final String ER_FECHA
            = "(" + ER_FECHA_DIA28 + "|" + ER_FECHA_DIA29 + "|" + ER_FECHA_DIA30 + "|" + ER_FECHA_DIA31 + ")";

    // Validación Fecha - Campos Separados
    public static boolean validarFecha(int dia, int mes, int any) {
        // Calcula cuantos dias tiene el mes de la fecha
        int numDias = calcularDiasMes(mes, any);
        
        // Devuelve el resultado de la validación
        return dia >= 1 && dia <= numDias;
    }

    // Obtener el número de dias del mes del año
    public static int calcularDiasMes(int mes, int any) {
        // Número de dias del mes
        int numDias;

        // Análisis
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDias = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                numDias = 30;
                break;
            case 2:
                numDias = comprobarBisiesto(any) ? 29 : 28;
                break;
            default:
                numDias = 0;
        }
        
        // Devolución resultado
        return numDias;
    }

    // Comprobar si el año es bisiesto
    public static boolean comprobarBisiesto(int any) {
        return any % 400 == 0 || any % 100 != 0 && any % 4 == 0;
    }

    // Validación Fecha - Campos Separados
    public static boolean validarFechaExpresion(int dia, int mes, int any) {
        return dia >= 1 && dia <= 31
                && (mes == 1 || mes == 3 || mes == 5
                || mes == 7 || mes == 8 || mes == 10
                || mes == 12)
                || dia >= 1 && dia <= 30
                && (mes == 4 || mes == 6 || mes == 9
                || mes == 11)
                || dia >= 1 && dia <= 29 && mes == 2
                && (any % 400 == 0
                || any % 100 != 0 && any % 4 == 0)
                || dia >= 1 && dia <= 28 && mes == 2;
    }

    // Validación Fecha - dd/mm/aaaa
    public static boolean validarFecha(String fecha) {
        // Extrae los componentes de la fecha
        int dia = obtenerDiaFecha(fecha);
        int mes = obtenerMesFecha(fecha);
        int any = obtenerAnyFecha(fecha);

        // Valida la fecha
        return validarFecha(dia, mes, any);
    }

    // Día (Número) > Día (Nombre)
    public static String obtenerNombreDia(int dia) {
        // Referencia del nombre del Día
        String nombre;

        // Valida el número de Día
        if (dia >= 1 && dia <= 12) {
            nombre = NOMBRE_DIA[dia - 1];
        } else {
            nombre = "indefinido";
        }

        // Devuelve el nombre del Día
        return nombre;
    }

    // Mes (Número) > Mes (Nombre)
    public static String obtenerNombreMes(int mes) {
        // Referencia del nombre del mes
        String nombre;

        // Valida el número de mes
        if (mes >= 1 && mes <= 12) {
            nombre = NOMBRE_MES[mes - 1];
        } else {
            nombre = "indefinido";
        }

        // Devuelve el nombre del mes
        return nombre;
    }

    // Fecha (String) > dia (int)
    public static int obtenerDiaFecha(String fecha) {
        // Dia de la fecha
        int dia;

        try {
            // Posición del primer separador
            int posSepIni = fecha.indexOf("/");

            // Extrae el dia de la fecha
            String dato = fecha.substring(0, posSepIni);

            // Convierte el dia a número
            dia = Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            dia = -1;
        }

        // Devuelve el dia
        return dia;
    }

    // Fecha (String) > mes (int)
    public static int obtenerMesFecha(String fecha) {
        // Mes de la fecha
        int mes;

        try {
            // Posición del primer separador
            int posSepIni = fecha.indexOf("/");

            // Posición del segundo separador
            int posSepFin = fecha.lastIndexOf("/");

            // Extrae el mes de la fecha
            String dato = fecha.substring(posSepIni + 1, posSepFin);

            // Convierte el mes a número
            mes = Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            mes = -1;
        }

        // Devuelve el mes
        return mes;
    }

    // Fecha (String) > año (int)
    public static int obtenerAnyFecha(String fecha) {
        // Año de la fecha
        int any;

        try {
            // Posición del segundo separador
            int posSepFin = fecha.lastIndexOf("/");

            // Extrae el año de la fecha
            String dato = fecha.substring(posSepFin);

            // Convierte el año a número
            any = Integer.parseInt(dato);
        } catch (NumberFormatException e) {
            any = -1;
        }

        // Devuelve el año
        return any;
    }

    // Número del Dia del Mes de Hoy
    public static int obtenerDiaHoy() {
        return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    }

    // Número del Mes de Hoy
    public static int obtenerMesHoy() {
        return new GregorianCalendar().get(Calendar.MONTH) + 1;
    }

    // Número del Año de Hoy
    public static int obtenerAnyHoy() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    // Fecha de Hoy - dd/mm/aaaa
    public static String obtenerFechaHoy() {
        // Obtiene Fecha Hoy
        Date fechaHoy = new Date();

        // Formateador de Fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Formatea y devuelve la Fecha
        return sdf.format(fechaHoy);
    }
}
