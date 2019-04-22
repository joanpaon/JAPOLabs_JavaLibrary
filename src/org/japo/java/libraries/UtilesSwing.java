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

import java.awt.Color;
import java.awt.Font;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeListener;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesSwing {

    // Perfiles LnF
    public static final String LNF_WINDOWS_PROFILE = "Windows";
    public static final String LNF_WINDOWS_CLASSIC_PROFILE = "Windows Classic";
    public static final String LNF_MOTIF_PROFILE = "CDE/Motif";
    public static final String LNF_GTK_PROFILE = "GTK+";    // Sólo en LINUX
    public static final String LNF_METAL_PROFILE = "Metal";
    public static final String LNF_NIMBUS_PROFILE = "Nimbus";

    // Perfiles LnF - Extra
    public static final String LNF_SYSTEM_PROFILE = "System";
    public static final String LNF_CROSS_PLATFORM_PROFILE = "Cross Platform";

    // Nombres de Clases LnF
    public static final String LNF_WINDOWS_CLASSNAME = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    public static final String LNF_WINDOWS_CLASSIC_CLASSNAME = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    public static final String LNF_MOTIF_CLASSNAME = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String LNF_GTK_CLASSNAME = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";  // LINUX
    public static final String LNF_METAL_CLASSNAME = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String LNF_NIMBUS_CLASSNAME = "javax.swing.plaf.nimbus.NimbusLookAndFeel";

    // Fuentes Lucida (JRE)
    public static final String FONT_LUCIDA_SANS_NAME = "Lucida Sans";
    public static final String FONT_LUCIDA_TYPEWRITER_NAME = "Lucida Sans Typewriter";
    public static final String FONT_LUCIDA_BRIGHT_NAME = "Lucida Bright";

    // Fuentes Lógicas
    public static final String FONT_LOGICAL_SERIF_NAME = Font.SERIF;
    public static final String FONT_LOGICAL_SANS_NAME = Font.SANS_SERIF;
    public static final String FONT_LOGICAL_MONO_NAME = Font.MONOSPACED;
    public static final String FONT_LOGICAL_DIALOG_NAME = Font.DIALOG;
    public static final String FONT_LOGICAL_INPUT_NAME = Font.DIALOG_INPUT;

    // Fuente Predeterminada
    public static final String DEF_FONT_FAMILY = Font.SANS_SERIF;
    public static final int DEF_FONT_STYLE = Font.PLAIN;
    public static final int DEF_FONT_SIZE = 12;
    public static final Font DEF_FONT = new Font(DEF_FONT_FAMILY, DEF_FONT_STYLE, DEF_FONT_SIZE);

    // Cerrar Programa Swing
    public static final void terminarPrograma(JFrame f) {
        // Oculta la ventana
        f.setVisible(false);

        // Devuelve los recursos
        f.dispose();

        // Cierra el programa
        System.exit(0);
    }

    // Lista de Nombres de los LnF Instalados
    public static final String[] obtenerNombresLnFInstalados() {
        // Lista Info LnF Instalados
        UIManager.LookAndFeelInfo[] lnfInfo = UIManager.getInstalledLookAndFeels();

        // Lista Nombres LnF Instalados
        String[] lnfName = new String[lnfInfo.length];

        // Extrae Nombres LnF Instalados
        for (int i = 0; i < lnfInfo.length; i++) {
            lnfName[i] = lnfInfo[i].getName();
        }

        // Devuelve Nombres LnF Instalados
        return lnfName;
    }

    // Lista de Nombres de las Clases de los LnF Instalados
    public static final String[] obtenerNombresClasesLnFInstalados() {
        // Lista Info LnF Instalados
        UIManager.LookAndFeelInfo[] lnfInfo = UIManager.getInstalledLookAndFeels();

        // Lista Nombres de las clases LnF Instalados
        String[] lnfClassName = new String[lnfInfo.length];

        // Extrae Nombres de las clases LnF Instalados
        for (int i = 0; i < lnfInfo.length; i++) {
            lnfClassName[i] = lnfInfo[i].getClassName();
        }

        // Devuelve Nombres de las clases LnF Instalados
        return lnfClassName;
    }

    // Establecer LnF - Nombre de Clase
    public static final void establecerLnFClassName(String lnfClassName) {
        try {
            UIManager.setLookAndFeel(lnfClassName);
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("ERROR: Instalación del LnF - Clase");
        }
    }

    // Establecer LnF - Nombre de Perfil
    public static final void establecerLnFProfile(String profile) {
        if (profile.equalsIgnoreCase(LNF_SYSTEM_PROFILE)) {
            establecerLnFSistema();
        } else if (profile.equalsIgnoreCase(LNF_CROSS_PLATFORM_PROFILE)) {
            establecerLnFCrossPlatform();
        } else {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (profile.equalsIgnoreCase(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException
                    | InstantiationException | UnsupportedLookAndFeelException e) {
                System.out.println("ERROR: Instalación del LnF - Perfil");
            }
        }
    }

    // Obtener Nombre LnF Sistema
    public static final String obtenerNombreLnFSistema() {
        return UIManager.getSystemLookAndFeelClassName();
    }

    // Establecer LnF Sistema
    public static final void establecerLnFSistema() {
        try {
            UIManager.setLookAndFeel(obtenerNombreLnFSistema());
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("ERROR: Instalación del LnF del Sistema");
        }
    }

    // Obtener Nombre LnF Sistema
    public static final String obtenerNombreLnFCrossPlatform() {
        return UIManager.getCrossPlatformLookAndFeelClassName();
    }

    // Establecer LnF Cross-Platform
    public static final void establecerLnFCrossPlatform() {
        try {
            UIManager.setLookAndFeel(obtenerNombreLnFCrossPlatform());
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | UnsupportedLookAndFeelException e) {
            System.out.println("ERROR: Instalación del LnF Cross Platform");
        }
    }

    // Adaptar Image >> Etiqueta
    public static final void adaptarImagenEtiqueta(JLabel lblAct, Image imgIni) {
        try {
            // Imagen Original >> Imagen Escalada 
            Image imgFin = imgIni.getScaledInstance(
                    lblAct.getSize().width,
                    lblAct.getSize().height,
                    Image.SCALE_FAST);

            // Icon >> Etiqueta Imagen
            lblAct.setIcon(new ImageIcon(imgFin));
        } catch (Exception e) {
            System.out.println("ERROR: Reescalar/Asignar imagen a etiqueta");
        }
    }

    // Escalar Image > Etiqueta
    public static void escalarImagenEtiqueta(JLabel lblAct, Image imgIni, int ancAct, int altAct) {
        try {
            // Imagen Original >> Imagen Escalada 
            Image imgFin = imgIni.getScaledInstance(ancAct, altAct, Image.SCALE_FAST);

            // Icon > Etiqueta Imagen
            lblAct.setIcon(new ImageIcon(imgFin));
        } catch (Exception e) {
            System.out.println("ERROR: No se ha podido adaptar imagen a etiqueta");
        }
    }

    // Portapapeles >> Texto
    public static final String importarTextoPortapapeles() {
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

    // Texto >> Portapapeles
    public static final boolean exportarTextoPortapapeles(String texto, ClipboardOwner propietario) {
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
    public static final void establecerValorDeslizador(JSlider sldActual, int valor) {
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
    public static final void establecerValorCambiador(JSpinner spnActual, int valor) {
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
    public static final String[] obtenerTipografiasSistema() {
        return GraphicsEnvironment.
                getLocalGraphicsEnvironment().
                getAvailableFontFamilyNames();
    }

    // Selecciona Elemento Combo por Programa sin activar Listeners
    public static final void establecerElementoCombo(JComboBox<String> cbbActual, String item) {
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
    public static final void establecerFavicon(JFrame ventana, String recurso) {
        try {
            // Ruta Favicon > URL Favicon
            URL urlICN = ClassLoader.getSystemResource(recurso);

            // URL Favicon > Ventana Favicon
            ventana.setIconImage(new ImageIcon(urlICN).getImage());
        } catch (Exception e) {
            System.out.println("ERROR: Favicon no instalado");
        }
    }

    // Importar Fuente TTF - Fichero
    public static final Font importarFuenteFichero(String fichero) {
        // Referencia a la fuente
        Font f;

        // Cargar Fuente
        try (InputStream is = new FileInputStream(fichero)) {
            f = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            f = null;
        }

        // Devuelve fuente
        return f;
    }

    // Importar Fuente TTF - Recurso
    public static final Font importarFuenteRecurso(String recurso) {
        // Referencia a la fuente
        Font f;

        // Cargar Fuente
        try (InputStream is = ClassLoader.getSystemResourceAsStream(recurso)) {
            f = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            f = null;
        }

        // Devuelve fuente
        return f;
    }

    // Fuente Original ( null ) > Fuente Derivada ( Estilo )
    public static final Font derivarFuente(Font fuente, int estilo) {
        return fuente == null || !(fuente instanceof Font) ? null
                : fuente.deriveFont(estilo);
    }

    // Fuente Original ( null ) > Fuente Derivada ( Talla )
    public static final Font derivarFuente(Font fuente, float talla) {
        return fuente == null || !(fuente instanceof Font) ? null
                : fuente.deriveFont(talla);
    }

    // Fuente Original ( null ) > Fuente Derivada ( Estilo + Talla )
    public static final Font derivarFuente(Font fuente, int estilo, float talla) {
        return fuente == null || !(fuente instanceof Font) ? null
                : fuente.deriveFont(estilo, talla);
    }

    // Fuente ( Fichero | Sistema | Lógica ) + Estilo + Talla > Fuente
    public static final Font generarFuenteFichero(String fichero,
            int estilo, float talla, String fuenteSistema, String fuenteLogica) {
        // Fichero > Fuente
        Font fuente = importarFuenteFichero(fichero);

        // Comprobar Fuente
        if (fuente != null) {
            fuente = fuente.deriveFont(estilo, talla);
        } else if (validarFuenteSistema(fuenteSistema)) {
            fuente = new Font(fuenteSistema, estilo, (int) talla);
        } else {
            fuente = new Font(fuenteLogica, estilo, (int) talla);
        }

        // Devolver Fuente
        return fuente;
    }

    // Fuente ( Fichero | Sistema | Lógica ) + Estilo + Talla > Fuente
    public static final Font generarFuenteFichero(String fichero,
            String fuenteSistema, String fuenteLogica) {
        return generarFuenteFichero(fichero, DEF_FONT_STYLE, DEF_FONT_STYLE,
                fuenteSistema, fuenteLogica);
    }

    // Fuente ( Recurso | Sistema | Lógica ) + Estilo + Talla > Fuente
    public static final Font generarFuenteRecurso(String recurso,
            int estilo, float talla, String fuenteSistema, String fuenteLogica) {
        // Fichero > Fuente
        Font fuente = importarFuenteRecurso(recurso);

        // Comprobar Fuente
        if (fuente != null) {
            fuente = fuente.deriveFont(estilo, talla);
        } else if (validarFuenteSistema(fuenteSistema)) {
            fuente = new Font(fuenteSistema, estilo, (int) talla);
        } else {
            fuente = new Font(fuenteLogica, estilo, (int) talla);
        }

        // Devolver Fuente
        return fuente;
    }

    // Fuente ( Recurso | Sistema | Lógica ) + Estilo + Talla > Fuente
    public static final Font generarFuenteRecurso(String recurso,
            String fuenteSistema, String fuenteLogica) {
        return generarFuenteRecurso(recurso, DEF_FONT_STYLE, DEF_FONT_STYLE,
                fuenteSistema, fuenteLogica);
    }

    // Campo de texto con DATO + ExpReg + Texto campo vacío
    public static final boolean validarCampo(
            JTextField txfActual, String expReg, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String textoActual = txfActual.getText().trim();

        // Comprueba campo vacío
        textoActual = textoActual.equals("") ? textoCampoVacio : textoActual;

        // Restaura el texto formateado
        txfActual.setText(textoActual);

        // Valida el Dato
        boolean validacionOK = UtilesValidacion.validar(textoActual, expReg);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Campo de texto con DATO + Lista + Texto campo vacío
    public static final boolean validarCampo(
            JTextField txfActual, String[] lista, String textoCampoVacio) {
        // Texto del campo - No espaciadores
        String texto = txfActual.getText().trim();

        // Comprueba campo vacío
        texto = texto.equals("") ? textoCampoVacio : texto;

        // Restaura el texto formateado
        txfActual.setText(texto);

        // Valida el Dato
        boolean validacionOK = UtilesValidacion.validar(texto, lista);

        // Señala la validación
        if (validacionOK) {
            // Señalar Contenido Correcto
            txfActual.setForeground(Color.BLACK);
        } else {
            // Señalar Contenido Erróneo
            txfActual.setForeground(Color.RED);
        }

        // Resultado de la validación
        return validacionOK;
    }

    // Campo de texto con DNI + Texto campo vacío
    public static final boolean validarCampoDNI(
            JTextField txfActual, String textoCampoVacio) {
        return validarCampo(txfActual, UtilesDNI.ER_DNI, textoCampoVacio);
    }

    // Campo de texto con FECHA + Texto campo vacío
    public static final boolean validarCampoFecha(
            JTextField txfActual, String textoCampoVacio) {
        return validarCampo(txfActual, UtilesFecha.ER_FECHA, textoCampoVacio);
    }

    public static final boolean validarFuenteSistema(String fuente) {
        return UtilesArrays.buscar(obtenerTipografiasSistema(), fuente) != -1;
    }
    
    public static final Image importarImagenRecurso(String recurso) {
        // Referencia Imagen
        Image img;
        
        try {
            // URL del Recurso
            URL urlPpal = ClassLoader.getSystemResource(recurso);
            
            // Imagen de la URL
            img = new ImageIcon(urlPpal).getImage();
            
        } catch (Exception e) {
            img = new ImageIcon().getImage();
        }
        
        // Devuelve la imagen
        return img;
    }
}
