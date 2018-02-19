package net.unesc.hal.models;

import java.util.ArrayList;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Source;
import net.unesc.hal.data.Character;
import net.unesc.hal.data.Token;
import net.unesc.hal.languages.HAL;

public class Lexicon {
    
    private final Source src;
    private HAL lang;
    private FiniteAutomaton fa;
    private ArrayList<String[]> tokens;
    private ArrayList<String[]> errors;

    public Lexicon(Source src, FiniteAutomaton fa) {
        this.src = src;
        this.fa = fa;
        this.lang = fa.getLang();
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
        run();
    }

    public ArrayList<String[]> getTokens() {
        return tokens;
    }

    public ArrayList<String[]> getErrors() {
        return errors;
    }
    
    private void addError(Integer line, Token token){
        String[] adition = new String[3];
        adition[0] = line.toString();
        adition[1] = token.getName();
        adition[2] = token.getCode().toString();
        errors.add(adition);
    }
    
    private void addToken(Integer line, Token token){
        String[] adition = new String[3];
        adition[0] = line.toString();
        adition[1] = token.getCode().toString();
        adition[2] = token.getName();
        tokens.add(adition);
    }
    
    private void run(){
        Integer cur_line = 1;
        boolean inBlockComment = false;
        boolean inLineComment = false;
        
        ArrayList<Character> chars = src.getChars();
        for(int pointer = 0; pointer < chars.size(); pointer++){
            Character cur_char = chars.get(pointer);
            if(cur_char.getCharacter().matches(""))
                System.out.println("Match");
            if(cur_char.isEndLine()) {
                cur_line++;
            }
            if(cur_char.isEndFile()) {
                addToken(cur_line, lang.EOF);
            }
            //System.out.println(cur_char.getCharacter());
        }
        
    }
    
    
    public void checkLetter(ArrayList<Character> chars, Integer line, Integer col){
        String partial = "";
            partial += chars.get(0).getCharacter();
            int i = 1;
            while(chars.get(i).getCharacter().matches("[a-zA-Z]")){
                partial += chars.get(i).getCharacter();
                i++;
            }
            Token tkn = lang.getToken(partial);
            if(tkn != null)
                addToken(line, tkn);
    }
    
    public ArrayList<ArrayList<String>> checkLang(ArrayList<Character> chars) {
        chars = chars;
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        
        Integer row = 1;
        Integer col = 1;
        String partial = "";
        
        if(chars.get(0).getCharacter().matches("[a-zA-Z]")){
            //res.add(checkLetter(chars, row, col));
        }
        
        if(chars.get(0).getCharacter().matches("[0-9]")){
            System.out.println("Number");
        }
               
        return res;
    }
    
}
