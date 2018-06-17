package net.unesc.hal.analysis.lexicon;

public class Token {
    private Integer code;
    private String name;

    public Token(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    
    
}
