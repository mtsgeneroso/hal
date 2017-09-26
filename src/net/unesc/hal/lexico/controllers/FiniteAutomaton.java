package net.unesc.hal.lexico.controllers;

import java.util.ArrayList;
import net.unesc.hal.lexico.data.Character;
import net.unesc.hal.lexico.exceptions.EditorException;
import net.unesc.hal.lexico.languages.HAL;

public class FiniteAutomaton {

    private String langName;
    
    // TODO: Change instance to generic call.
    private HAL lang = new HAL();
    private ArrayList<Character> chars = null;
    
    public FiniteAutomaton() {
    }
    
    public FiniteAutomaton(String langName) {
        this.langName = langName;
    }

    public void setLangName(String langName) throws EditorException{
        this.langName = langName;
    }
    
    public ArrayList<String> checkLetter(ArrayList<Character> chars, Integer row, Integer col){
        String partial = "";
        ArrayList<String> out = new ArrayList<>();
            partial += chars.get(0).getCharacter();
            int i = 1;
            while(chars.get(i).getCharacter().matches("[a-zA-Z]")){
                partial += chars.get(i).getCharacter();
                i++;
            }
            Integer tokenCod = lang.getToken(partial);
            if(tokenCod != -1){
                ArrayList<String> newToken = new ArrayList<>();
                newToken.add(tokenCod.toString());
                newToken.add(partial);
                newToken.add(row.toString());
                return newToken;
            }
        return out;
    }
    
    public ArrayList<ArrayList<String>> checkLang(ArrayList<Character> chars) {
        this.chars = chars;
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        
        Integer row = 1;
        Integer col = 1;
        String partial = "";
        
        if(chars.get(0).getCharacter().matches("[a-zA-Z]")){
            res.add(checkLetter(chars, row, col));
        }
        
        if(chars.get(0).getCharacter().matches("[0-9]")){
            System.out.println("Number");
        }
               
        return res;
    }

}
