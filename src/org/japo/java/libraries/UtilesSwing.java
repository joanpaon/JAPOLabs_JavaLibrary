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

import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesSwing {

    // Perfiles LnF
    public static final String LNF_WINDOWS = "Windows";
    public static final String LNF_WINDOWS_CLASSIC = "Windows Classic";
    public static final String LNF_MOTIF = "CDE/Motif";
    public static final String LNF_METAL = "Metal";
    public static final String LNF_NIMBUS = "Nimbus";

    // Cerrar programa
    public static void terminarPrograma(JFrame f) {
        // Oculta la ventana
        f.setVisible(false);

        // Devuelve los recursos
        f.dispose();

        // Cierra el programa
        System.exit(0);
    }

    // Establecer LnF
    public static boolean establecerLnF(String lnf) {
        // Semáforo
        boolean procesoOK = false;

        // Instala LnF
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lnf.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Actualiza semáforo
            procesoOK = true;
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("ERROR: Instalación del LnF");
        }

        // Devuelve semáforo
        return procesoOK;
    }

    // Escalar/Asignar Image > Etiqueta
    public static boolean asignarImagenEscalada(JLabel lblImagen, Image imgOriginal) {
        // Semáforo
        boolean procesoOK = false;

        // Procesado Imagen
        try {
            // Obtiene la imagen escalada
            Image imgEscalada = imgOriginal.getScaledInstance(
                    lblImagen.getSize().width,
                    lblImagen.getSize().height,
                    Image.SCALE_FAST);

            // Image (Final) > Icon
            Icon i = new ImageIcon(imgEscalada);

            // Icon > Etiqueta Imagen
            lblImagen.setIcon(i);

            // Actualiza semáforo
            procesoOK = true;
        } catch (Exception e) {
            System.out.println("ERROR: Reescalar/Asignar imagen a etiqueta");
        }

        // Devuelve semáforo
        return procesoOK;
    }

    // Obtiene el texto copiado al portapapeles
    public static String obtenerTextoPortapapeles() {
        // Referencia al texto del portapapeles
        String result = "";

        try {
            // Acceso al portapapeles
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            // Contenido del Portapapeles
            // El parámetro de getContents no se usa
            Transferable contents = clipboard.getContents(null);

            // Extrae texto del portapapeles
            result = (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
            System.out.println("ERROR: Lectura del portapapeles");
        }

        // Texto extraido
        return result;
    }

    // Coloca texto en el portapapeles
    public static boolean ponerTextoPortapapeles(String texto, ClipboardOwner propietario) {
        // Semáforo
        boolean procesoOK = false;

        try {
            // Entidad que implementa la capacidad de transmitir texto
            StringSelection transmisor = new StringSelection(texto);

            // Acceso al portapapeles
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            // Transmisión de texto
            clipboard.setContents(transmisor, propietario);

            // Actualiza semáforo
            procesoOK = true;
        } catch (HeadlessException e) {
            System.out.println("ERROR: Escritura en el portapapeles");
        }

        // Devuelve semáforo
        return procesoOK;
    }

    // Cambiar valor sin disparar Eventos de Ajuste
    public static void ajustarValorDeslizador(JSlider sldActual, int valor) {
        // Captura los escuchadores del deslizador
        ChangeListener[] lista = sldActual.getChangeListeners();

        // Desactiva los escuchadores del deslizador
        for (ChangeListener clAct : lista) {
            sldActual.removeChangeListener(clAct);
        }

        // Establece el valor al deslizador
        sldActual.setValue(valor);

        // Asocia los escuchadores al deslizador
        for (ChangeListener clAct : lista) {
            sldActual.addChangeListener(clAct);
        }
    }

    // Cambiar valor sin disparar Eventos de Ajuste
    public static void ajustarValorCambiador(JSpinner spnActual, int valor) {
        // Captura los escuchadores del cambiador
        ChangeListener[] lista = spnActual.getChangeListeners();

        /**
         * Desactiva el primer ChangeListener para que no se propaguen eventos.
         * Si se desconectan todos los listeners el JSpinner no se actualiza con
         * el valor cambiado por set value.
         */
        spnActual.removeChangeListener(lista[0]);

        // Cambia el valor del spinner
        spnActual.setValue(valor);

        // Reconecta el primer ChangeListener
        spnActual.addChangeListener(lista[0]);
    }

    // Tipografias disponibles en el sistema
    public static String[] obtenerTipografiasSistema() {
        return GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();
    }

    public static void seleccionarElementoCombo(JComboBox<String> cbbActual, String item) {
        // Captura los escuchadores del combo
        ActionListener[] lista = cbbActual.getActionListeners();

        // Desactiva los escuchadores del deslizador
        for (ActionListener al : lista) {
            cbbActual.removeActionListener(al);
        }

        // Selecciona el elemento
        cbbActual.setSelectedItem(item);

        // Asocia los escuchadores al combo
        for (ActionListener al : lista) {
            cbbActual.addActionListener(al);
        }
    }

    // Asignar Favicon Ventana
    public static void establecerFavicon(JFrame ventana, String rutaFavicon) {
        try {
            // Ruta Favicon > URL Favicon
            URL urlICN = ClassLoader.getSystemResource(rutaFavicon);

            // URL Favicon > Ventana Favicon
            ventana.setIconImage(new ImageIcon(urlICN).getImage());
        } catch (Exception e) {
            System.out.println("ERROR: Instalación del icono de la ventana");
        }
    }
}
