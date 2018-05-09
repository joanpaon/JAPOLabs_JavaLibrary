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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesBD {

    // Propiedades BBDD
    private static final String FICHERO_PRP = "db.properties";

    // Valores Predeterminados Conexión BBDD
    private static final String DEF_PROT = "jdbc:mysql";
    private static final String DEF_HOST = "localhost";
    private static final String DEF_PORT = "3306";
    private static final String DEF_DBAM = "agenda";
    private static final String DEF_USER = "usuario";
    private static final String DEF_PASS = "usuario";

    // Propiedades Conexión BBDD
    private static final String PRP_PROT = "protocol";
    private static final String PRP_HOST = "host";
    private static final String PRP_PORT = "port";
    private static final String PRP_DBAM = "db";
    private static final String PRP_USER = "user";
    private static final String PRP_PASS = "password";

    // Formato Conexión
    private static final String FORMATO_CON = "%s://%s:%s/%s?user=%s&password=%s";

    // Cadena Conexión  Predeterminada
    private static final String DEF_CADENA_CON = String.format(
            FORMATO_CON, DEF_PROT, DEF_HOST, DEF_PORT, DEF_DBAM, DEF_USER, DEF_PASS);

    // Obtiene Conexión con BD - Predeterminada
    public static final Connection obtenerConexion() throws SQLException {
        // Referencia a la Conexión
        Connection con;

        if (new File(FICHERO_PRP).exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(FICHERO_PRP);

            // Obtener Conexión
            con = obtenerConexion(prp);
        } else {
            // Aviso 
            System.out.println("ERROR: Fichero Propiedades BD NO existe");

            // Obtener Conexión
            con = obtenerConexion(DEF_CADENA_CON);
        }

        // Devolver Conexión
        return con;
    }

    // Obtiene Conexión con BD - Cadena Conexión
    public static final Connection obtenerConexion(String cadena) throws SQLException {
        return DriverManager.getConnection(cadena);
    }

    // Obtiene Conexión con BD - Parámetros
    public static final Connection obtenerConexion(
            String prot, String host, String port, String db,
            String user, String pass) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = String.format(
                FORMATO_CON, prot, host, port, db, user, pass);

        // Realizar la conexión
        return obtenerConexion(cadenaConexion);
    }

    // Obtiene Conexión con BD - Propiedades
    public static final Connection obtenerConexion(Properties prp) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = String.format(
                FORMATO_CON,
                prp.getProperty(PRP_PROT, DEF_PROT),
                prp.getProperty(PRP_HOST, DEF_HOST),
                prp.getProperty(PRP_PORT, DEF_PORT),
                prp.getProperty(PRP_DBAM, DEF_DBAM),
                prp.getProperty(PRP_USER, DEF_USER),
                prp.getProperty(PRP_PASS, DEF_PASS));

        // Realizar la conexión
        return DriverManager.getConnection(cadenaConexion);
    }

    // Obtiene Conexión con BD - Propiedades
    public static final Connection obtenerConexion(File f) throws SQLException {
        // Referencia a la Conexión
        Connection con = null;

        if (f != null && f.exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(f.getName());

            // Obtener Conexión
            con = obtenerConexion(prp);
        }

        // Realizar la conexión
        return con;
    }

    // SQL Date >> String (dd/MM/yyyy)
    public static final String convertirSQLDate2String(java.sql.Date sqlDate) {
        // Obtiene milisegundos de fecha
        long ms = sqlDate.getTime();

        // Genera un objeto java.util.Date
        java.util.Date utilDate = new java.util.Date(ms);

        // Define un formateador de fecha
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Formatea la fecha
        return sdf.format(utilDate);
    }
}
