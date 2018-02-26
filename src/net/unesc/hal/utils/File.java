package net.unesc.hal.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 *
 * @author mtsgeneroso
 */
public class File {
    
    public static void write(String path, String str) throws FileNotFoundException, IOException {
        FileOutputStream arq = null;
        PrintStream ps = null;
            try {
                // cria o arquivo
                java.io.File f = new java.io.File(path);

                System.out.println(f.getAbsolutePath());

                arq = new FileOutputStream(f);

                // escreve no arquivo
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
    
    public static StringBuilder read(String caminhoDoArquivo) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        FileInputStream arq = null;
        try {

            java.io.File f = new java.io.File(caminhoDoArquivo);
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
