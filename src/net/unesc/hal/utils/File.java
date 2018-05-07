package net.unesc.hal.utils;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.JFileChooser;
import net.unesc.hal.views.Editor;

/**
 *
 * @author mtsgeneroso
 */
public class File {

    public static String write(String str, Editor comp, String path) throws FileNotFoundException, IOException {
        FileOutputStream arq = null;
        PrintStream ps = null;
        JFileChooser c = new JFileChooser();
        String name = "codigo.lms";
        String dir = "";

        if (path.isEmpty()) {
            int rVal = c.showSaveDialog(comp);

            if (rVal == JFileChooser.APPROVE_OPTION) {
                name = c.getSelectedFile().getName();
                dir = c.getCurrentDirectory().toString();
            }
            path = dir + "/" + name;
        }
        try {

            java.io.File f = new java.io.File(path);

            System.out.println(f.getAbsolutePath());

            arq = new FileOutputStream(f);

            try {
                ps = new PrintStream(arq);
                ps.println(str);

                return path;

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

    public static String read(Editor comp, String path) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream arq = null;
        String dir = "";
        String name = "";

        JFileChooser c = new JFileChooser();

        if (path.isEmpty()) {

            int rVal = c.showOpenDialog(comp);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                name = c.getSelectedFile().getName();
                dir = c.getCurrentDirectory().toString();
            }
            
            path = dir + "/" + name;
        }

        try {

            java.io.File f = new java.io.File(path);
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
        comp.setPath(path);
        return result.toString();
    }

}
