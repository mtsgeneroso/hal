package net.unesc.hal.lexico.models;

import java.util.ArrayList;
import net.unesc.hal.lexico.controllers.FiniteAutomaton;
import net.unesc.hal.lexico.data.Source;
import net.unesc.hal.lexico.data.Character;

public class Lexicon {
    
    private final Source src;
    private ArrayList<String[]> tokens;
    private ArrayList<String[]> errors;

    public Lexicon(Source src, FiniteAutomaton fa) {
        this.src = src;
        checkLexicon();
    }

    public ArrayList<String[]> getTokens() {
        return tokens;
    }

    public ArrayList<String[]> getErrors() {
        return errors;
    }
    
    private void checkLexicon(){
        ArrayList<Character> chars = src.getChars();
        
        for(Character c : chars){
            // TODO: Enviar partes da string até dar match (fim) no automato
            // fa.validate(str);
            System.out.println(c.toString());
        }
    }
    
}
