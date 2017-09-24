package net.unesc.hal.lexico.controllers;

public class FiniteAutomaton {

    private String langName;
    
    public FiniteAutomaton() {
    }
    
    public FiniteAutomaton(String langName) {
        this.langName = langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

}
