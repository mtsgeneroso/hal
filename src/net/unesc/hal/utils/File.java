package net.unesc.hal.utils;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFileChooser;

/**
 *
 * @author mtsgeneroso
 */
public class File {

    public static void write(String str, Component comp) throws FileNotFoundException, IOException {
        FileOutputStream arq = null;
        PrintStream ps = null;
        JFileChooser c = new JFileChooser();
        String name =  "codigo.lms";
        String dir = "";
        
        int rVal = c.showSaveDialog(comp);
        
        if (rVal == JFileChooser.APPROVE_OPTION) {
            name = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
        }
        try {
            
            java.io.File f = new java.io.File(dir + "/" + name);

            System.out.println(f.getAbsolutePath());

            arq = new FileOutputStream(f);

            try {
                ps = new PrintStream(arq);
                ps.println(str);
            } finally {
                if (ps != null) {
                    ps.close();
                }
            }
        } finally {
            if (arq != null) {
                arq.close();
            }
        }

    }

    public static StringBuilder read(Component comp) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream arq = null;
        String dir = "";
        String name = "";

        JFileChooser c = new JFileChooser();
        
        int rVal = c.showOpenDialog(comp);
        if (rVal == JFileChooser.APPROVE_OPTION) {
            name = c.getSelectedFile().getName();
            dir = c.getCurrentDirectory().toString();
        }

        try {

            java.io.File f = new java.io.File(dir + "/" + name);
            arq = new FileInputStream(f);

            int caracterlido = arq.read();

            while (caracterlido != -1) {
                result.append((char) caracterlido);
                caracterlido = arq.read();
            }
        } finally {
            if (arq != null) {
                arq.close();
            }
        }

        return result;
    }

}
