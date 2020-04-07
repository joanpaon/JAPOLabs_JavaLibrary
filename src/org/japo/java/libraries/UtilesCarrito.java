/*
 * Copyright 2020 José A. Pacheco Ondoño - joanpaon@gmail.com.
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

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesCarrito {

    // separador de Línea
    public static final String LS = System.getProperty("line.separator");

    // Opciones Menús
    public static final String OPC_MENU_PPAL = "IiCcPpXx";
    public static final String OPC_MENU_ITEM = "AaBbCcMmXx";
    public static final String OPC_MENU_CONT = "LlFfOoXx";
    public static final String OPC_MENU_FILT = "DdIiNnPpCcXx";
    public static final String OPC_MENU_ORDE = "IiNnPpCcXx";
    public static final String OPC_MENU_PERS = "IiEeXx";

    // Texto Menú Principal
    public static final String TXT_MENU_PPAL
            = "MENÚ PRINCIPAL" + LS
            + "==============" + LS
            + "[ I ] Gestión Items" + LS
            + "[ C ] Gestión Contenido" + LS
            + "[ P ] Gestión Persistencia" + LS
            + "---" + LS
            + "[ X ] Salir" + LS
            + "---" + LS
            + "Introducir opción: ";

    // Texto Menú Items
    public static final String TXT_MENU_ITEM
            = "MENÚ ITEMS" + LS
            + "==========" + LS
            + "[ A ] Agregar item" + LS
            + "[ B ] Borrar Item" + LS
            + "[ C ] Consultar Item" + LS
            + "[ M ] Modificar Item" + LS
            + "---" + LS
            + "[ X ] Menú Anterior" + LS
            + "---" + LS
            + "Introducir opción: ";

    // Texto Menú Contenido
    public static final String TXT_MENU_CONT
            = "MENÚ CONTENIDO" + LS
            + "==============" + LS
            + "[ L ] Listar" + LS
            + "[ F ] Filtrar" + LS
            + "[ O ] Ordenar" + LS
            + "---" + LS
            + "[ X ] Menú Anterior" + LS
            + "---" + LS
            + "Introducir opción: ";

    // Texto Menú Filtrado
    public static final String TXT_MENU_FILT
            = "MENÚ FILTRADO" + LS
            + "=============" + LS
            + "[ D ] Desactivar" + LS
            + "---" + LS
            + "[ I ] Por ID" + LS
            + "[ N ] Por NOMBRE" + LS
            + "[ P ] Por PRECIO" + LS
            + "[ C ] Por COLOR" + LS
            + "---" + LS
            + "[ X ] Menú Anterior" + LS
            + "---" + LS
            + "Introducir opción: ";

    // Texto Menú Ordenación
    public static final String TXT_MENU_ORDE
            = "MENÚ ORDENACIÓN" + LS
            + "===============" + LS
            + "[ I ] Por ID" + LS
            + "[ N ] Por NOMBRE" + LS
            + "[ P ] Por PRECIO" + LS
            + "[ C ] Por COLOR" + LS
            + "---" + LS
            + "[ X ] Menú Anterior" + LS
            + "---" + LS
            + "Introducir opción: ";

    // Texto Menú Persistencia
    public static final String TXT_MENU_PERS
            = "MENÚ PERSISTENCIA" + LS
            + "=================" + LS
            + "[ I ] Importar Datos" + LS
            + "[ E ] Exportar Datos" + LS
            + "---" + LS
            + "[ X ] Menú Anterior" + LS
            + "---" + LS
            + "Introducir opción: ";

    public static final String TXT_MENU_ERROR = "ERROR: Opción no válida";
}
