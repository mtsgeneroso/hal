/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unesc.hal.compiler;

import java.util.ArrayList;

/**
 *
 * @author mtsgeneroso
 */
public class Semantic {
    
    private ArrayList<String[]> lexicon;
    private ArrayList<String> errors;
    private ArrayList<String[]> log;
    
    public Semantic(ArrayList<String[]> lexicon){
        setLexicon(lexicon);
    }

    public ArrayList<String[]> getLexicon() {
        return lexicon;
    }

    public void setLexicon(ArrayList<String[]> lexicon) {
        this.lexicon = lexicon;
        checkIdentifiers();
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public ArrayList<String[]> getLog() {
        return log;
    }

    public void setLog(ArrayList<String[]> log) {
        this.log = log;
    }
    
    private void checkIdentifiers(){
        
        int level = 0;
        
        for(int i = 0; i < lexicon.size(); i++){
            
            if(lexicon.get(i)[1].equals("25")) {
                System.out.println("TOKEN: " + lexicon.get(i)[3]);
            }
            
            if(lexicon.get(i)[1].equals("4") || lexicon.get(i)[1].equals("3")) {
                System.out.println("VAR ou CONST: " + ++level);
            }
            if(lexicon.get(i)[1].equals("7")) {
                System.out.println("END: " + --level);
            }
        }
    
    }
    
    
}
