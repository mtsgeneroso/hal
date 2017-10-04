package net.unesc.hal.languages;

import java.util.HashMap;
import java.util.Map;

public class HAL implements Language {
    
    public final Integer COMMENT_BLOCK_BEGIN = 48;
    public final Integer COMMENT_BLOCK_END = 49;
    public final Integer COMMENT_INLINE = 50;
    public final Integer STRING_BEGIN = 51;
    public final Integer STRING_END = 52;
    public final Integer CHAR_BEGIN = 53;
    public final Integer CHAR_END = 54;
    public final Integer LITERAL_BEGIN = 55;
    public final Integer LITERAL_END = 56;
    
    public Map<Integer, String> tokens;

    public HAL() {
        buildTokens();
    }

    @Override
    public int getToken(String str){
        if(!tokens.containsValue(str)) return -1;
            
        for(int i = 1; i <= tokens.size(); i++){
            if(str.equals(tokens.get(i)))
                return i;
        } 
        
        return -1;
    }

    @Override
    public String getToken(Integer cod) {
        if(tokens.containsKey(cod))
            return tokens.get(cod);
        return null;
    }
    
    private void buildTokens(){
        tokens = new HashMap<Integer, String>();
        tokens.put(1,"while");
        tokens.put(2,"void");
        tokens.put(3,"string");
        tokens.put(4,"return");
        tokens.put(5,"numerointeiro");
        tokens.put(6,"numerofloat");
        tokens.put(7,"nomevariavel");
        tokens.put(8,"nomedochar");
        tokens.put(9,"nomedastring");
        tokens.put(10,"main");
        tokens.put(11,"literal");
        tokens.put(12,"integer");
        tokens.put(13,"inicio");
        tokens.put(14,"if");
        tokens.put(15,"î");
        tokens.put(16,"for");
        tokens.put(17,"float");
        tokens.put(18,"fim");
        tokens.put(19,"else");
        tokens.put(20,"do");
        tokens.put(21,"cout");
        tokens.put(22,"cin");
        tokens.put(23,"char");
        tokens.put(24,"callfuncao");
        tokens.put(25,">>");
        tokens.put(26,">=");
        tokens.put(27,">");
        tokens.put(28,"==");
        tokens.put(29,"=");
        tokens.put(30,"<=");
        tokens.put(31,"<<");
        tokens.put(32,"<");
        tokens.put(33,"++");
        tokens.put(34,"+");
        tokens.put(35,"}");
        tokens.put(36,"{");
        tokens.put(37,";");
        tokens.put(38,":");
        tokens.put(39,"/");
        tokens.put(40,",");
        tokens.put(41,"*");
        tokens.put(42,")");
        tokens.put(43,"(");
        tokens.put(44,"$");
        tokens.put(45,"!=");
        tokens.put(46,"--");
        tokens.put(47,"-");
        tokens.put(48,"|+");
        tokens.put(49,"+|");
        tokens.put(50,"||");
        tokens.put(51,"@");
        tokens.put(52,"@");
        tokens.put(53,"\'");
        tokens.put(54,"\'");
        tokens.put(55,"&");
        tokens.put(56,"&");
    }
    
}
