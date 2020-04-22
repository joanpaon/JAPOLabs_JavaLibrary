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
    private static final String FICHERO_PRP = "app.properties";

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

    // Obtiene Conexión con BD - Properties
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

    // Obtiene Conexión con BD - Predeterminada
    public static final Connection conectar() throws SQLException {
        // Referencia a la Conexión
        Connection con;

        if (new File(FICHERO_PRP).exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(FICHERO_PRP);

            // Obtener Conexión
            con = conectar(prp);
        } else {
            // Aviso
            System.out.println("ERROR: Fichero Propiedades BD NO existe");

            // Obtener Conexión
            con = UtilesBD.conectar(DEF_CADENA_CON);
        }

        // Devolver Conexión
        return con;
    }

    // Obtiene Conexión con BD - Predeterminada
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

    // Obtiene Conexión con BD - Cadena Conexión
//    public static final Connection abrir(String cadena) throws SQLException {
//        return DriverManager.getConnection(cadena);
//    }
    // Obtiene Conexión con BD - Parámetros
//    public static final Connection abrir(
//            String prot, String host, String port, String db,
//            String user, String pass) throws SQLException {
//        // Definir cadena de conexión
//        String cadenaConexion = String.format(
//                FORMATO_CON, prot, host, port, db, user, pass);
//
//        // Realizar la conexión
//        return UtilesBD.conectar(cadenaConexion);
//    }
    // Obtiene Conexión con BD - Propiedades
    public static final Connection abrir(Properties prp) throws SQLException {
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
    public static final Connection abrir(File f) throws SQLException {
        // Referencia a la Conexión
        Connection con = null;

        if (f != null && f.exists()) {
            // Cargar Propiedades
            Properties prp = UtilesApp.importarPropiedades(f.getName());

            // Obtener Conexión
            con = UtilesBD.abrir(prp);
        }

        // Realizar la conexión
        return con;
    }

    // Conexión + Access + Concurrency > Statement
    public static final Statement vincular(Connection conn, int acceso, int concurrencia) throws SQLException {
        // ---- CAMBIOS DE DATOS ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        // ---- MODOS DE CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        Statement stmt = conn.createStatement(acceso, concurrencia);

        // > Statement
        return stmt;
    }

    // Conexión + Access + Concurrency > Statement
    public static final Statement vincular(Connection conn, Properties prp) throws SQLException {
        // ---- CAMBIOS DE DATOS ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        // ---- MODOS DE CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        // Tipo de Acceso
        int tipoAcceso = ResultSet.TYPE_FORWARD_ONLY;
        if (prp.getProperty("access_type").equals("TYPE_SCROLL_INSENSITIVE")) {
            tipoAcceso = ResultSet.TYPE_SCROLL_INSENSITIVE;
        } else if (prp.getProperty("access_type").equals("TYPE_SCROLL_SENSITIVE")) {
            tipoAcceso = ResultSet.TYPE_SCROLL_SENSITIVE;
        }

        // Concurrencia
        int concurrencia = ResultSet.CONCUR_READ_ONLY;
        if (prp.getProperty("concurrency").equals("CONCUR_UPDATABLE")) {
            concurrencia = ResultSet.CONCUR_UPDATABLE;
        }

        // Connection + Access Type + Concurrency > Statement
        Statement stmt = conn.createStatement(tipoAcceso, concurrencia);

        // > Statement
        return stmt;
    }

    // Statement + SQL > ResultSet
    public static final ResultSet obtener(Statement stmt, String sql) throws SQLException {
        // Statement + Select SQL
        ResultSet rs = stmt.executeQuery(sql);

        // > ResultSet
        return rs;
    }

    // Statement + Properties > ResultSet
    public static final ResultSet obtener(Statement stmt, Properties prp) throws SQLException {
        // Statement + Select SQL
        ResultSet rs = stmt.executeQuery(prp.getProperty("default_query"));

        // > ResultSet
        return rs;
    }

    // Properties > ResultSet ( Easy )
    public static final ResultSet obtener(Properties prp) throws SQLException {
        // Establecer Conexión
        Connection conn = UtilesBD.conectar(prp);

        // Establecer Vinculación
        Statement stmt = vincular(conn, prp);

        // Obtener Conjunto de Datos
        ResultSet rs = obtener(stmt, prp);

        // > ResultSet
        return rs;
    }

    // SQL Date > String (dd/MM/yyyy)
    public static final String convertirSQLDate2String(java.sql.Date sqlDate) {
        return new SimpleDateFormat("dd/MM/yyyy").format(sqlDate);
    }

    // SQL Date + Pattern > String
    public static final String convertirSQLDate2String(java.sql.Date sqlDate, String pattern) {
        return new SimpleDateFormat(pattern).format(sqlDate);
    }

    // ResultSet > Número Registros
    public static final int obtenerNumeroRegistros(ResultSet rs) {
        // Almacén resultado
        int numFilas;

        try {
            // Comprobar ResultSet
            if (rs != null && !rs.isClosed()) {
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
            } else {
                numFilas = 0;
            }
        } catch (SQLException ex) {
            numFilas = 0;
        }

        // Número de filas calculadas
        return numFilas;
    }

    // ResultSet > Número Registro Actual
    public static final int obtenerPosicionActual(ResultSet rs) {
        // Almacén resultado
        int filaActual;

        try {
            // Comprobar ResultSet
            if (rs != null && !rs.isClosed()) {
                filaActual = rs.getRow();
            } else {
                filaActual = 0;
            }
        } catch (SQLException ex) {
            filaActual = 0;
        }

        // Número Registro Actual
        return filaActual;
    }

    // Cierre JDBC Connection
    public static final void cerrar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
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
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
