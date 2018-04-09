package net.unesc.hal.models;

import java.util.ArrayList;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Token;
import net.unesc.hal.languages.HAL;

public class Syntactic {
    
    private ArrayList<String[]> stack;
    private HAL lang;
    private ArrayList<Token> non_terminals;

    public Syntactic(ArrayList<String[]> lexicon, FiniteAutomaton fa) {
        this.stack = lexicon;
        this.lang = fa.getLang();
        this.non_terminals = this.lang.getNonTerminals();
        run();
    }
    
    private void run(){
        
    }
}