package net.unesc.hal.lexico.data;

public class Character {

    public static String EOL = "EOL";
    public static String EOF = "EOF";
    private String character;
    private boolean num;
    private boolean letter;
    private boolean space;
    private boolean endFile;
    private boolean endLine;

    public Character(String character) {
        this.character = character;
        setProperties();
    }
    
    private void setProperties(){
        space = character.equals(" ");
        endFile = character.equals(EOF);
        endLine = character.equals(EOL);
        num = character.matches("[0-9]");
        letter = character.matches("[a-zA-Z]");
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
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

    @Override
    public String toString() {
        return "Character{" + "character=" + character + ", num=" + num + ", letter=" + letter + ", space=" + space + ", endFile=" + endFile + ", endLine=" + endLine + '}';
    }
    
}
