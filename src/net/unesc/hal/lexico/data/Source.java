package net.unesc.hal.lexico.data;

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
    
    
    
}
