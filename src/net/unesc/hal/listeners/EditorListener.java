package net.unesc.hal.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unesc.hal.data.Source;
import net.unesc.hal.exceptions.EditorException;
import net.unesc.hal.models.Lexicon;
import net.unesc.hal.models.Syntactic;
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
            case Editor.SAVE:
                File.write(src.getCode(), ed);
                break;
            case Editor.OPEN:
                ed.setSource(File.read(ed).toString());
                break;
            case Editor.CLOSE:
                ed.dispose();
                break;
            case Editor.NEW:
                ed.setSource("");
                break;
            case Editor.LEXICON:
                ed.clearTokens();
                ed.clearErrors();
                Lexicon lex = new Lexicon(src, ed.getFiniteAutomaton());
                ArrayList<String[]> tokens = lex.getTokens();
                ed.setTokens(tokens);
                ed.setErrors(lex.getErrors());
                if(lex.getErrors().isEmpty()) {
                    Syntactic syn = new Syntactic(tokens, ed.getFiniteAutomaton());
                }
                break;
            default:
                throw new EditorException("Comando inexistente: " + cmd);
        }

    }
}
