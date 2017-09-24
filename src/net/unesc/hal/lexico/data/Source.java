package net.unesc.hal.lexico.data;

import java.util.ArrayList;
import net.unesc.hal.lexico.data.Character;

public class Source {
    
    private String code;

    public Source(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Source{" + "code=" + code + '}';
    }

    public ArrayList<Character> getChars() {
        ArrayList<Character> chars = new ArrayList<>();
        
        String[] rows = code.split("\n");
        
        for(int i = 0; i < rows.length; i++){
            
            String[] cols = rows[i].split("");
            
            for(int j = 0; j < cols.length; j++){                
                chars.add(new Character(cols[j]));
            }
            
            chars.add(new Character(Character.EOL));
        }
        chars.add(new Character(Character.EOF));
        return chars;
    }
    
    
    
}
