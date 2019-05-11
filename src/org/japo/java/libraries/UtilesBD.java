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

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesBD {

    // Propiedades BBDD
    private static final String FICHERO_PRP = "db.properties";

    // Valores Predeterminados Conexión BBDD
    private static final String DEF_PROT = "jdbc:mysql";
    private static final String DEF_HOST = "localhost";
    private static final String DEF_PORT = "3306";
    private static final String DEF_DBAM = "test";
    private static final String DEF_USER = "admin";
    private static final String DEF_PASS = "12345";

    // Propiedades Conexión BBDD
    private static final String PRP_PROT = "db_prot";
    private static final String PRP_HOST = "db_host";
    private static final String PRP_PORT = "db_port";
    private static final String PRP_DBNM = "db_name";
    private static final String PRP_USER = "db_user";
    private static final String PRP_PASS = "db_pass";

    // Formato Conexión
    private static final String FORMATO_CON = "%s://%s:%s/%s?user=%s&password=%s";

    // Cadena Conexión  Predeterminada
    private static final String DEF_CADENA_CON = String.format(
            FORMATO_CON, DEF_PROT, DEF_HOST, DEF_PORT, DEF_DBAM, DEF_USER, DEF_PASS);

    // Obtiene Conexión con BD - Predeterminada
    public static final Connection conectar() throws SQLException {
        // Referencia a la Conexión
        Connection con;

        if (new File(FICHERO_PRP).exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(FICHERO_PRP);

            // Obtener Conexión
            con = UtilesBD.conectar(prp);
        } else {
            // Aviso 
            System.out.println("ERROR: Fichero Propiedades BD NO existe");

            // Obtener Conexión
            con = UtilesBD.conectar(DEF_CADENA_CON);
        }

        // Devolver Conexión
        return con;
    }

    // Obtiene Conexión con BD - Cadena Conexión
    public static final Connection conectar(String cadena) throws SQLException {
        return DriverManager.getConnection(cadena);
    }

    // Obtiene Conexión con BD - Parámetros
    public static final Connection conectar(
            String prot, String host, String port, String db,
            String user, String pass) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = String.format(
                FORMATO_CON, prot, host, port, db, user, pass);

        // Realizar la conexión
        return UtilesBD.conectar(cadenaConexion);
    }

    // Obtiene Conexión con BD - Propiedades
    public static final Connection conectar(Properties prp) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = String.format(
                FORMATO_CON,
                prp.getProperty(PRP_PROT, DEF_PROT),
                prp.getProperty(PRP_HOST, DEF_HOST),
                prp.getProperty(PRP_PORT, DEF_PORT),
                prp.getProperty(PRP_DBNM, DEF_DBAM),
                prp.getProperty(PRP_USER, DEF_USER),
                prp.getProperty(PRP_PASS, DEF_PASS));

        // Realizar la conexión
        return DriverManager.getConnection(cadenaConexion);
    }

    // Obtiene Conexión con BD - Propiedades
    public static final Connection conectar(File f) throws SQLException {
        // Referencia a la Conexión
        Connection con = null;

        if (f != null && f.exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(f.getName());

            // Obtener Conexión
            con = UtilesBD.conectar(prp);
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

    // ResultSet > Número Registros
    public static final int obtenerNumeroRegistros(ResultSet rs) {
        // Variable para almacenar el resultado
        int numFilas;

        try {
            // Número de la fila a la que apunta el cursor
            int filaAct = rs.getRow();

            // Va al final del ResultSet
            rs.last();

            // Obtiene el número de filas
            numFilas = rs.getRow();

            if (filaAct != 0) {
                // Coloca el cursor en la posición previa
                rs.absolute(filaAct);
            } else {
                // Coloca el cursor al principio del ResultSet
                rs.first();
            }
        } catch (SQLException e) {
            numFilas = 0;
        }

        // Devuelve el número de filas calculadas
        return numFilas;
    }

    // ResultSet > Número Registro Actual
    public static final int obtenerPosicionActual(ResultSet rs) {
        int filaActual;

        try {
            filaActual = rs.getRow();
        } catch (SQLException | NullPointerException e) {
            filaActual = 0;
        }

        return filaActual;
    }

    // Cierre JDBC Connection
    public static final void cerrar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Cierre JDBC Statement
    public static final void cerrar(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Cierre JDBC ResultSet
    public static final void cerrar(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
