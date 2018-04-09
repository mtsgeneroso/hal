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

    public ArrayList<Token> terminals;
    public ArrayList<Token> non_terminals;
    public ArrayList<Token> separators;

    public HAL() {
        buildTokens();
    }

    @Override
    public Token getTerminal(String str) {

        for (int i = 0; i < terminals.size(); i++) {
            if (str.equals(terminals.get(i).getName())) {
                return terminals.get(i);
            }
        }

        return null;
    }

    @Override
    public Token getTerminal(Integer cod) {

        for (int i = 1; i <= terminals.size(); i++) {
            if (cod.equals(terminals.get(i).getCode())) {
                return terminals.get(i);
            }
        }
        return null;
    }

    public boolean isSeparator(Character c) {
        for (int i = 0; i < separators.size(); i++) {
            if (c.toString().equals(separators.get(i).getName())) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<Token> getNonTerminals(){
        return non_terminals;
    }

    private void buildTokens() {
        terminals = new ArrayList<>();
        non_terminals = new ArrayList<>();
        separators = new ArrayList<>();

        terminals.add(new Token(1, "program"));
        terminals.add(new Token(2, "label"));
        terminals.add(new Token(3, "const"));
        terminals.add(new Token(4, "var"));
        terminals.add(new Token(5, "procedure"));
        terminals.add(new Token(6, "begin"));
        terminals.add(new Token(7, "end"));
        terminals.add(new Token(8, "integer"));
        terminals.add(new Token(9, "array"));
        terminals.add(new Token(10, "of"));
        terminals.add(new Token(11, "call"));
        terminals.add(new Token(12, "goto"));
        terminals.add(new Token(13, "if"));
        terminals.add(new Token(14, "then"));
        terminals.add(new Token(15, "else"));
        terminals.add(new Token(16, "while"));
        terminals.add(new Token(17, "do"));
        terminals.add(new Token(18, "repeat"));
        terminals.add(new Token(19, "until"));
        terminals.add(new Token(20, "readln"));
        terminals.add(new Token(21, "writeln"));
        terminals.add(new Token(22, "or"));
        terminals.add(new Token(23, "and"));
        terminals.add(new Token(24, "not"));
        terminals.add(new Token(27, "for"));
        terminals.add(new Token(28, "to"));
        terminals.add(new Token(29, "case"));
        terminals.add(new Token(30, "+"));
        separators.add(this.getTerminal(30));
        terminals.add(new Token(31, "-"));
        separators.add(this.getTerminal(31));
        terminals.add(new Token(32, "*"));
        separators.add(this.getTerminal(32));
        terminals.add(new Token(33, "/"));
        separators.add(this.getTerminal(33));
        terminals.add(new Token(34, "["));
        separators.add(this.getTerminal(34));
        terminals.add(new Token(35, "]"));
        separators.add(this.getTerminal(35));
        terminals.add(new Token(36, "("));
        separators.add(this.getTerminal(36));
        terminals.add(new Token(37, ")"));
        separators.add(this.getTerminal(37));
        terminals.add(new Token(38, ":="));
        terminals.add(new Token(39, ":"));
        terminals.add(new Token(40, "="));
        separators.add(this.getTerminal(40));
        terminals.add(new Token(41, ">"));
        separators.add(this.getTerminal(41));
        terminals.add(new Token(42, ">="));
        terminals.add(new Token(43, "<"));
        separators.add(this.getTerminal(43));
        terminals.add(new Token(44, "<="));
        terminals.add(new Token(45, "<>"));
        terminals.add(new Token(46, ","));
        separators.add(this.getTerminal(46));
        terminals.add(new Token(47, ";"));
        terminals.add(new Token(49, "."));
        terminals.add(new Token(50, ".."));

        non_terminals.add(new Token(52, "PROGRAMA"));
        non_terminals.add(new Token(53, "BLOCO"));
        non_terminals.add(new Token(54, "DCLROT"));
        non_terminals.add(new Token(55, "LID"));
        non_terminals.add(new Token(56, "REPIDENT"));
        non_terminals.add(new Token(57, "DCLCONST"));
        non_terminals.add(new Token(58, "LDCONST"));
        non_terminals.add(new Token(59, "DCLVAR"));
        non_terminals.add(new Token(60, "LDVAR"));
        non_terminals.add(new Token(61, "TIPO"));
        non_terminals.add(new Token(62, "DCLPROC"));
        non_terminals.add(new Token(63, "DEFPAR"));
        non_terminals.add(new Token(64, "CORPO"));
        non_terminals.add(new Token(65, "REPCOMANDO"));
        non_terminals.add(new Token(66, "COMANDO"));
        non_terminals.add(new Token(67, "RCOMID"));
        non_terminals.add(new Token(68, "RVAR"));
        non_terminals.add(new Token(69, "PARAMETROS"));
        non_terminals.add(new Token(70, "REPPAR"));
        non_terminals.add(new Token(71, "ELSEPARTE"));
        non_terminals.add(new Token(72, "VARIAVEL"));
        non_terminals.add(new Token(73, "VARIAVEL1"));
        non_terminals.add(new Token(74, "REPVARIAVEL"));
        non_terminals.add(new Token(75, "ITEMSAIDA"));
        non_terminals.add(new Token(76, "REPITEM"));
        non_terminals.add(new Token(77, "EXPRESSAO"));
        non_terminals.add(new Token(78, "REPEXPSIMP"));
        non_terminals.add(new Token(79, "EXPSIMP"));
        non_terminals.add(new Token(80, "REPEXP"));
        non_terminals.add(new Token(81, "TERMO"));
        non_terminals.add(new Token(82, "REPTERMO"));
        non_terminals.add(new Token(83, "FATOR"));
        non_terminals.add(new Token(84, "CONDCASE"));
        non_terminals.add(new Token(85, "CONTCASE"));
        non_terminals.add(new Token(86, "RPINTEIRO"));
        non_terminals.add(new Token(87, "SEMEFEITO"));

    }

}
