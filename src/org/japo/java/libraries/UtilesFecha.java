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

    // ExpReg - Día del mes hasta 28 - [1..28] / [01..28]
    public static final String ER_DIA28 = "(0?[1-9]|1[0-9]|2[0-8])";

    // ExpReg - Mes del año - [1..12] / [01..12]
    public static final String ER_MES = "(0?[1-9]|1[0-2])";

    // ExpReg - Año - [0..9999]
    public static final String ER_ANY = "([0-9]|[1-9][0-9]|[1-9][0-9]{2}|[1-9][0-9]{3})";

    // ExpReg - Separador de campos de fecha: "/" o "-"
    public static final String ER_SEP_FECHA = "[/-]";

    // ExpReg - Fecha válida entre el 1 y el 28 de cualquier mes
    public static final String ER_FECHA_DIA28
            = "(" + ER_DIA28 + ER_SEP_FECHA + ER_MES + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Años SI divisibles por 400 (Hasta 4 digitos)
    public static final String ER_ANYS_MOD400
            = "(" + "0?[48]00|[13579][26]00|[2468][048]00" + ")";

    // ExpReg - Años NO divisibles por 100 pero SI divisibles por 4 (Hasta 4 dígitos)
    public static final String ER_ANYS_MOD4_NO100
            = "(" + "[0-9]{0,2}" + "((0?|[2468])[48]|[13579][26]|[2468]0)" + ")";    // Desde 4 hasta 96

    // ExpReg - Años Bisiestos (Hasta 4 digitos)
    public static final String ER_ANYS_BISIESTOS
            = "(" + ER_ANYS_MOD400 + "|" + ER_ANYS_MOD4_NO100 + ")";

    // ExpReg - Fecha válida para 29 de Febreros BISIESTOS
    public static final String ER_FECHA_DIA29_BISIESTO
            = "(" + "29" + ER_SEP_FECHA + "(2|02)" + ER_SEP_FECHA + ER_ANYS_BISIESTOS + ")";

    // ExpReg - Meses que tienen 30 dias (Todos menos Febrero)
    public static final String ER_MESES_30DIAS = "(0?[13456789]|1[012])";

    // ExpReg - Fecha válida para el 29 de cualquier Mes EXCEPTO Febrero
    public static final String ER_FECHA_DIA29_NORMAL
            = "(" + "29" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Fecha válida para el 29 de cualquier Mes INCLUIDO Febrero
    public static final String ER_FECHA_DIA29
            = "(" + ER_FECHA_DIA29_BISIESTO + "|" + ER_FECHA_DIA29_NORMAL + ")";

    // ExpReg - Fecha válida para el 30 de cualquier Mes
    public static final String ER_FECHA_DIA30
            = "(" + "30" + ER_SEP_FECHA + ER_MESES_30DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Meses que tienen 31 dias
    public static final String ER_MESES_31DIAS = "(0?[13578]|1[02])";

    // ExpReg - Fecha válida para el 31 de cualquier Mes
    public static final String ER_FECHA_DIA31
            = "(" + "31" + ER_SEP_FECHA + ER_MESES_31DIAS + ER_SEP_FECHA + ER_ANY + ")";

    // ExpReg - Fecha válida (Cualquiera)
    public static final String ER_FECHA
            = "(" + ER_FECHA_DIA28 + "|" + ER_FECHA_DIA29 + "|" + ER_FECHA_DIA30 + "|" + ER_FECHA_DIA31 + ")";

    // Obtener el número de dias del mes del año
    public static final int obtenerDiasMes(int mes, int any) {
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
                numDias = UtilesValidacion.validarBisiesto(any) ? 29 : 28;
                break;
            default:
                numDias = 0;
        }

        // Devolución resultado
        return numDias;
    }

    // Día (Número) > Día (Nombre)
    public static final String obtenerNombreDia(int dia) {
        return dia >= 1 && dia <= NOMBRE_DIA.length ? NOMBRE_DIA[dia - 1] : "indefinido";
    }

    // Mes (Número) > Mes (Nombre)
    public static final String obtenerNombreMes(int mes) {
        return mes >= 1 && mes <= NOMBRE_MES.length ? NOMBRE_MES[mes - 1] : "indefinido";
    }

    // Fecha (String) > dia (int)
    public static final int obtenerDiaFecha(String fecha) {
        // Dia de la fecha
        int dia;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(ER_SEP_FECHA);

            // Convierte el dia a número
            dia = Integer.parseInt(campo[0]);
        } catch (NumberFormatException e) {
            dia = -1;
        }

        // Devuelve el dia
        return dia;
    }

    // Fecha (String) > mes (int)
    public static final int obtenerMesFecha(String fecha) {
        // Mes de la fecha
        int mes;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(ER_SEP_FECHA);

            // Convierte el mes a número
            mes = Integer.parseInt(campo[1]);
        } catch (NumberFormatException e) {
            mes = -1;
        }

        // Devuelve el mes
        return mes;
    }

    // Fecha (String) > año (int)
    public static final int obtenerAnyFecha(String fecha) {
        // Año de la fecha
        int any;

        try {
            // Desglosa los campos de la fecha
            String[] campo = fecha.split(ER_SEP_FECHA);

            // Convierte el dia a número
            any = Integer.parseInt(campo[2]);
        } catch (NumberFormatException e) {
            any = -1;
        }

        // Devuelve el año
        return any;
    }

    // Número del Dia del Mes de Hoy
    public static final int obtenerDiaHoy() {
        return new GregorianCalendar().get(Calendar.DAY_OF_MONTH);
    }

    // Número del Mes de Hoy
    public static final int obtenerMesHoy() {
        return new GregorianCalendar().get(Calendar.MONTH) + 1;
    }

    // Número del Año de Hoy
    public static final int obtenerAnyHoy() {
        return new GregorianCalendar().get(Calendar.YEAR);
    }

    // Fecha de Hoy - dd/mm/aaaa
    public static final String obtenerFechaHoy() {
        // Obtiene Fecha Hoy
        Date fechaHoy = new Date();

        // Formateador de Fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Formatea y devuelve la Fecha
        return sdf.format(fechaHoy);
    }
}
