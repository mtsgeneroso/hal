package net.unesc.hal.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.unesc.hal.data.Source;
import net.unesc.hal.exceptions.EditorException;
import net.unesc.hal.models.Lexicon;
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
            speak(cmd);
        } catch (EditorException ex) {
            ex.printStackTrace();
        }
    }

    private void speak(String cmd) throws EditorException {
        switch (cmd) {
            case Editor.SAVE:
                // Save file
                break;
            case Editor.OPEN:
                // Open file
                break;
            case Editor.CLOSE:
                ed.dispose();
                break;
            case Editor.NEW:
                // New file
                break;
            case Editor.LEXICON:
                
                Source src = ed.getSource();
                Lexicon lex = new Lexicon(src, ed.getFiniteAutomaton());
                ed.setTokens(lex.getTokens());
                ed.setErrors(lex.getErrors());
                
                break;
            default:
                System.out.println(cmd);
                throw new EditorException("Command of editor menu is undefined!");
        }

    }
}
