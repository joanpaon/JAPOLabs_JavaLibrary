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

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesApp {

    // Valores por Defecto
    public static final String DEF_PAQUETE_PRP = "properties";
    public static final String DEF_FICHERO_PRP = "app.properties";
    public static final String DEF_FICHERO_XML = "app.xml";
    public static final String DEF_RECURSO_PRP = "config/app.properties";
    public static final String DEF_PUERTO_BLOQUEO = "54321";

    // Fichero (Por defecto) > Propiedades    
    public static final Properties importarPropiedades() {
        return importarPropiedades(DEF_FICHERO_PRP);
    }

    // Fichero XML (Por defecto) > Propiedades    
    public static final Properties importarPropiedadesXML() {
        return importarPropiedadesXML(DEF_FICHERO_XML);
    }

    // Fichero Propiedades > Objeto Propiedades
    public static final Properties importarPropiedades(String fichero) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades 
        try (FileReader fr = new FileReader(fichero)) {
            prp.load(fr);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devolver Propiedades
        return prp;
    }

    // Recurso Propiedades > Objeto Propiedades
    public static final Properties importarPropiedadesRecurso(String recurso) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades 
        try (InputStream is = ClassLoader.getSystemResourceAsStream(recurso)) {
            prp.load(is);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al recurso de propiedades " + recurso);
        }

        // Devolver Propiedades
        return prp;
    }

    // Recurso Propiedades ( Predefinido ) > Objeto Propiedades
    public static final Properties importarPropiedadesRecurso() {
        return importarPropiedadesRecurso(DEF_RECURSO_PRP);
    }

    // Fichero Propiedades XML > Objeto Propiedades
    public static final Properties importarPropiedadesXML(String fichero) {
        // Objeto de Propiedades Vacio
        Properties prp = new Properties();

        // Cargar Fichero de Propiedades 
        try (FileInputStream fisXml = new FileInputStream(fichero)) {
            // Carga las propiedades
            prp.loadFromXML(fisXml);
        } catch (Exception e) {
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devolver Propiedades
        return prp;
    }

    // Propiedades > Fichero (Por defecto)
    public static final boolean exportarPropiedades(Properties prp) {
        return exportarPropiedades(prp, DEF_FICHERO_PRP);
    }

    // Propiedades > Fichero XML (Por defecto)
    public static final boolean exportarPropiedadesXML(Properties prp) {
        return exportarPropiedadesXML(prp, DEF_FICHERO_PRP);
    }

    // Propiedades > Fichero
    public static final boolean exportarPropiedades(Properties prp, String fichero) {
        // Semáforo Estado
        boolean procesoOK = false;

        // Proceso de salvaguarda de propiedades
        try (FileWriter fw = new FileWriter(fichero)) {
            // Guarda las propiedades
            prp.store(fw, null);

            // Proceso OK
            procesoOK = true;
        } catch (Exception e) {
            // Mensaje de error
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devuelve Estado
        return procesoOK;
    }

    // Propiedades > Fichero XML
    public static final boolean exportarPropiedadesXML(Properties prp, String fichero) {
        // Semáforo Estado
        boolean procesoOK = false;

        // Proceso de salvaguarda de propiedades
        try (FileOutputStream fosXml = new FileOutputStream(fichero)) {
            // Guarda las propiedades
            prp.storeToXML(fosXml, null);

            // Proceso OK
            procesoOK = true;
        } catch (Exception e) {
            // Mensaje de error
            System.out.println("ERROR: Acceso al fichero " + fichero);
        }

        // Devuelve Estado
        return procesoOK;
    }

    // Activa Instancia Única
    public static final boolean activarInstancia(Properties prp) {
        // Semaforo Estado
        boolean instanciaOK = false;

        try {
            // Obtener dato
            String dato = prp.getProperty("puerto_bloqueo", DEF_PUERTO_BLOQUEO);

            // Puerto de bloqueo
            int puerto = Integer.parseInt(dato);

            // Abre un ServerSocket al puerto de bloqueo
            ServerSocket ss = new ServerSocket(puerto);

            // Marca Semáforo
            instanciaOK = true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR: Activación ejecución única");
        }

        // Devuelve Estado
        return instanciaOK;
    }

    // Activa Instancia Única
    public static final boolean activarInstancia(String txtPuerto) {
        // Semaforo Estado
        boolean instanciaOK = false;

        try {
            // Conversión numérica
            int puerto = Integer.parseInt(txtPuerto);

            // Abre un ServerSocket al puerto de bloqueo
            ServerSocket ss = new ServerSocket(puerto);

            // Marca Semáforo
            instanciaOK = true;
        } catch (IOException | NumberFormatException e) {
            System.out.println("ERROR: Activación ejecución única");
        }

        // Devuelve Estado
        return instanciaOK;
    }

    // Objeto > Serialización Binaria
    public static final void serializarBin(Object objeto, String archivo)
            throws Exception {
        try (
                FileOutputStream fos = new FileOutputStream(archivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // Escribe el objeto
            oos.writeObject(objeto);

            // Vacia Buffers
            oos.flush();
        }
    }

    // Objeto > Deserialización Binaria
    public static final Object deserializarBin(String archivo)
            throws Exception {

        // Referencia Objeto
        Object objeto = null;

        try (
                FileInputStream fis = new FileInputStream(archivo);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            objeto = ois.readObject();
        }

        return objeto;
    }

    // Objeto > Serialización XML
    public static final void serializarXML(Object objeto, String archivo)
            throws Exception {
        try (
                FileOutputStream fos = new FileOutputStream(archivo);
                XMLEncoder salida = new XMLEncoder(fos)) {
            // Escribe el objeto
            salida.writeObject(objeto);

            // Vacia Buffers
            salida.flush();
        }
    }

    // Objeto > Deserialización XML
    public static final Object deserializarXML(String archivo)
            throws Exception {

        // Referencia Objeto
        Object objeto = null;

        try (
                FileInputStream fis = new FileInputStream(archivo);
                XMLDecoder entrada = new XMLDecoder(fis)) {
            objeto = entrada.readObject();
        }

        return objeto;
    }
}
