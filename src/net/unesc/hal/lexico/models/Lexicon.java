package net.unesc.hal.lexico.models;

import java.util.ArrayList;
import net.unesc.hal.lexico.controllers.FiniteAutomaton;
import net.unesc.hal.lexico.data.Source;
import net.unesc.hal.lexico.data.Character;

public class Lexicon {
    
    private final Source src;
    private FiniteAutomaton fa;
    private ArrayList<ArrayList<String>> tokens;
    private ArrayList<ArrayList<String>> errors;

    public Lexicon(Source src, FiniteAutomaton fa) {
        this.src = src;
        this.fa = fa;
        buildLexicon();
    }

    public ArrayList<ArrayList<String>> getTokens() {
        return tokens;
    }

    public ArrayList<ArrayList<String>> getErrors() {
        return errors;
    }
    
    private void buildLexicon(){
        ArrayList<Character> chars = src.getChars();
        ArrayList<ArrayList<String>> res = fa.checkLang(chars);
        
        for(ArrayList<String> line : res){
            System.out.println(line.get(0) + " : " + line.get(1) + " : " + line.get(2));
        }
    }
    
}
