package net.unesc.hal.languages;

import java.util.ArrayList;
import net.unesc.hal.data.Token;

public class HAL implements Language {
    
    public final String COMMENT_BLOCK_BEGIN = "|+";
    public final String COMMENT_BLOCK_END = "+|";
    public final String COMMENT_INLINE = "||";
    public final String STRING_BEGIN = "@";
    public final String STRING_END = "@";
    public final String CHAR_BEGIN = "\'";
    public final String CHAR_END =  "\'";
    public final String LITERAL_BEGIN = "&";
    public final String LITERAL_END = "&";
    public final String DECIMAL_MARK = ",";
    
    public final Token VAR_NAME = new Token(7, "nomevariavel");
    public final Token VAR_INTEGER = new Token(5, "numerointeiro");
    public final Token VAR_FLOAT = new Token(6, "numerofloat");
    public final Token VAR_CHAR = new Token(8, "nomedochar");
    public final Token VAR_STRING = new Token(9, "nomedastring");  
    
    public final Token EOF = new Token(44, "$");    
    public final Token EMPTY = new Token(15, "î"); 
    
    public ArrayList<Token> tokens;

    public HAL() {
        buildTokens();
    }

    @Override
    public Token getToken(String str){
        
        for(int i = 1; i <= tokens.size(); i++){
            if(str.equals(tokens.get(i).getName()))
                return tokens.get(i);
        } 
        
        return null;
    }

    @Override
    public Token getToken(Integer cod) {
            
        for(int i = 1; i <= tokens.size(); i++){
            if(cod.equals(tokens.get(i).getCode()))
                return tokens.get(i);
        } 
        
        return null;
    }
    
    private void buildTokens(){
        tokens = new ArrayList<>();
        tokens.add(new Token(1,"while"));
        tokens.add(new Token(2,"void"));
        tokens.add(new Token(3,"string"));
        tokens.add(new Token(4,"return"));
        tokens.add(new Token(10,"main"));
        tokens.add(new Token(11,"literal"));
        tokens.add(new Token(12,"integer"));
        tokens.add(new Token(13,"inicio"));
        tokens.add(new Token(14,"if"));
        tokens.add(new Token(16,"for"));
        tokens.add(new Token(17,"float"));
        tokens.add(new Token(18,"fim"));
        tokens.add(new Token(19,"else"));
        tokens.add(new Token(20,"do"));
        tokens.add(new Token(21,"cout"));
        tokens.add(new Token(22,"cin"));
        tokens.add(new Token(23,"char"));
        tokens.add(new Token(24,"callfuncao"));
        tokens.add(new Token(25,">>"));
        tokens.add(new Token(26,">="));
        tokens.add(new Token(27,">"));
        tokens.add(new Token(28,"=="));
        tokens.add(new Token(29,"="));
        tokens.add(new Token(30,"<="));
        tokens.add(new Token(31,"<<"));
        tokens.add(new Token(32,"<"));
        tokens.add(new Token(33,"++"));
        tokens.add(new Token(34,"+"));
        tokens.add(new Token(35,"}"));
        tokens.add(new Token(36,"{"));
        tokens.add(new Token(37,";"));
        tokens.add(new Token(38,":"));
        tokens.add(new Token(39,"/"));
        tokens.add(new Token(40,","));
        tokens.add(new Token(41,"*"));
        tokens.add(new Token(42,")"));
        tokens.add(new Token(43,"("));
        tokens.add(new Token(45,"!="));
        tokens.add(new Token(46,"--"));
        tokens.add(new Token(47,"-"));
    }
    
}
