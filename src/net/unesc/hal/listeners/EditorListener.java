package net.unesc.hal.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.unesc.hal.analysis.lexicon.Source;
import net.unesc.hal.exceptions.EditorException;
import net.unesc.hal.analysis.Lexicon;
import net.unesc.hal.analysis.Semantic;
import net.unesc.hal.analysis.Syntactic;
import net.unesc.hal.utils.File;
import net.unesc.hal.views.Editor;

public class EditorListener implements ActionListener {

    private Editor ed;

    public EditorListener(Editor ed) {
        this.ed = ed;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        
        try {
            handle(cmd);
        } catch (EditorException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Arquivo inexistente!");
        }
    }

    private void handle(String cmd) throws EditorException, IOException {
        Source src = ed.getSource();
        
        switch (cmd) {
            case Editor.ABOUT:
                JOptionPane.showMessageDialog(null, ""
                        + "UNESC - Universidade do Extremo Sul Catarinense"
                        + "\n\nEquipe: "
                        + "\n- Mateus Generoso e Aminatha Miguel"
                        + "\n\nDisciplina:"
                        + "\n- Compiladores 2018/1"
                        + "\n\nProfessor:"
                        + "\n- Gilberto Vieira", "Sobre", JOptionPane.INFORMATION_MESSAGE);
                break;
            case Editor.SAVE:
                ed.setPath(File.write(src.getCode(), ed, ed.getPath()));
                break;
            case Editor.OPEN:
                ed.setSource(File.read(ed, ""));
                break;
            case Editor.CLOSE:
                ed.dispose();
                break;
            case Editor.NEW:
                ed.setSource("");
                break;
            case Editor.RUN:
                ed.clear();
                Lexicon lex = new Lexicon(src, ed.getFiniteAutomaton());
                ArrayList<String[]> tokens = lex.getTokens();
                ed.setTokens(tokens);
                ed.setErrors(lex.getErrors());
                if(lex.getErrors().isEmpty()) {
                    Semantic sem = new Semantic();
                    Syntactic syn = new Syntactic(tokens, ed.getFiniteAutomaton(), sem);
                    ed.setStacks(syn.getStacks());
                    ed.setErrors(syn.getErrors());
                    if(syn.getErrors().isEmpty()) {
                        ed.setIdentifiers(sem.getIds());
                        ed.setErrors(sem.getErrors());
                    }
                }
                break;
            default:
                throw new EditorException("Comando inexistente: " + cmd);
        }

    }
}
