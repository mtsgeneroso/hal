package net.unesc.hal.lexico.models;

import java.util.ArrayList;
import net.unesc.hal.lexico.data.Source;

public class Lexicon {
    
    private final Source src;
    private ArrayList<String[]> tokens;
    private ArrayList<String[]> errors;

    public Lexicon(Source src) {
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
        
    }
    
}
