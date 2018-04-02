package net.unesc.hal.languages;

import java.util.ArrayList;
import net.unesc.hal.data.Token;

public class HAL implements Language {

    public final String COMMENT_BLOCK_BEGIN = "(*";
    public final String COMMENT_BLOCK_END = "*)";
    public final String LITERAL_BEGIN = "'";
    public final String LITERAL_END = "'";
    public final int LITERAL_LIMIT = 255;

    public final Token IDENTIFIER = new Token(25, "identificador");
    public final Token INTEGER = new Token(26, "inteiro");
    public final Token EOF = new Token(51, "$");
    public final Token LITERAL = new Token(48, "literal");

    public ArrayList<Token> tokens;
    public ArrayList<Token> separators;


    public HAL() {
        buildTokens();
    }

    @Override
    public Token getToken(String str) {

        for (int i = 0; i < tokens.size(); i++) {
            if (str.equals(tokens.get(i).getName())) {
                return tokens.get(i);
            }
        }

        return null;
    }

    @Override
    public Token getToken(Integer cod) {

        for (int i = 1; i <= tokens.size(); i++) {
            if (cod.equals(tokens.get(i).getCode())) {
                return tokens.get(i);
            }
        }
        return null;
    }
    
    public boolean isSeparator(Character c){
        for (int i = 0; i < separators.size(); i++) {
            if (c.toString().equals(tokens.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    private void buildTokens() {
        tokens = new ArrayList<>();
        separators = new ArrayList<>();
        
        tokens.add(new Token(1, "program"));
        tokens.add(new Token(2, "label"));
        tokens.add(new Token(3, "const"));
        tokens.add(new Token(4, "var"));
        tokens.add(new Token(5, "procedure"));
        tokens.add(new Token(6, "begin"));
        tokens.add(new Token(7, "end"));
        tokens.add(new Token(8, "integer"));
        tokens.add(new Token(9, "array"));
        tokens.add(new Token(10, "of"));
        tokens.add(new Token(11, "call"));
        tokens.add(new Token(12, "goto"));
        tokens.add(new Token(13, "if"));
        tokens.add(new Token(14, "then"));
        tokens.add(new Token(15, "else"));
        tokens.add(new Token(16, "while"));
        tokens.add(new Token(17, "do"));
        tokens.add(new Token(18, "repeat"));
        tokens.add(new Token(19, "until"));
        tokens.add(new Token(20, "readln"));
        tokens.add(new Token(21, "writeln"));
        tokens.add(new Token(22, "or"));
        tokens.add(new Token(23, "and"));
        tokens.add(new Token(24, "not"));
        tokens.add(new Token(27, "for"));
        tokens.add(new Token(28, "to"));
        tokens.add(new Token(29, "case"));
        tokens.add(new Token(30, "+"));
        separators.add(this.getToken(30));
        tokens.add(new Token(31, "-"));
        separators.add(this.getToken(31));
        tokens.add(new Token(32, "*"));
        separators.add(this.getToken(32));
        tokens.add(new Token(33, "/"));
        separators.add(this.getToken(33));
        tokens.add(new Token(34, "["));
        separators.add(this.getToken(34));
        tokens.add(new Token(35, "]"));
        separators.add(this.getToken(35));
        tokens.add(new Token(36, "("));
        separators.add(this.getToken(36));
        tokens.add(new Token(37, ")"));
        separators.add(this.getToken(37));
        tokens.add(new Token(38, ":="));
        tokens.add(new Token(39, ":"));
        tokens.add(new Token(40, "="));
        separators.add(this.getToken(40));
        tokens.add(new Token(41, ">"));
        separators.add(this.getToken(41));
        tokens.add(new Token(42, ">="));
        tokens.add(new Token(43, "<"));
        separators.add(this.getToken(43));
        tokens.add(new Token(44, "<="));
        tokens.add(new Token(45, "<>"));
        tokens.add(new Token(46, ","));
        separators.add(this.getToken(46));
        tokens.add(new Token(47, ";"));
        tokens.add(new Token(49, "."));
        tokens.add(new Token(50, ".."));
        
        
    }

}
