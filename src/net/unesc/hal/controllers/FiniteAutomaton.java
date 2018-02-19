package net.unesc.hal.controllers;

import net.unesc.hal.languages.HAL;

public class FiniteAutomaton {

    private HAL lang;
    
    public FiniteAutomaton() {
    }
    
    public FiniteAutomaton(HAL lang) {
        this.lang = lang;
    }

    public void setLang(HAL lang){
        this.lang = lang;
    }

    public HAL getLang() {
        return lang;
    }
}
