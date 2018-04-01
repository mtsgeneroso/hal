package net.unesc.hal.data;

public class Char {

    public static int EOL = 1;
    public static int EOF = 2;
    private Character character;
    private boolean num;
    private boolean letter;
    private boolean space;
    private boolean symbol;
    private boolean endFile;
    private boolean endLine;

    public Char(Character character) {
        this.character = character;
        setProperties();
    }
    
    public Char() {
    }
    
    private void setProperties(){
        space = Character.isWhitespace(character);
        endFile = character.equals(EOF);
        endLine = character.equals(EOL);
        num = Character.isDigit(character);
        letter = Character.isAlphabetic(character);
        symbol = character.toString().matches("(\\+|\\-|\\*|\\/|\\[|\\]|\\(|\\)|\\:|\\=|\\>|\\<|\\,|\\;|\\.|\\')");
        
        
    }

    public Character getChar() {
        return character;
    }

    public void setChar(Character character) {
        this.character = character;
    }

    public boolean isNum() {
        return num;
    }

    public boolean isLetter() {
        return letter;
    }

    public boolean isEndFile() {
        return endFile;
    }

    public boolean isEndLine() {
        return endLine;
    }

    public boolean isSpace() {
        return space;
    }
    
    public void setEndLine(boolean value){
        this.endLine = value;
    }
    
    public void setEndFile(boolean value){
        this.endFile = value;
    }

    @Override
    public String toString() {
        return character.toString();
    }

    public boolean isSymbol() {
        return symbol;
    }

    public void setSymbol(boolean symbol) {
        this.symbol = symbol;
    }
    
}
