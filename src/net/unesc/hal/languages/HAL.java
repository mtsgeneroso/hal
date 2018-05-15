package net.unesc.hal.languages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    public ArrayList<Token> terminals, non_terminals, separators, derivations;
    public Map<String, String> parsing;

    public HAL() {
        buildTokens();
    }

    @Override
    public Token getTerminal(String str) {

        for (int i = 0; i < terminals.size(); i++) {
            if (str.toLowerCase().equals(terminals.get(i).getName())) {
                return terminals.get(i);
            }
        }

        return null;
    }

    @Override
    public Token getTerminal(Integer cod) {
        
        switch(cod) {
            case 26:
                return INTEGER;
            case 25:
                return IDENTIFIER;
            case 51:
                return EOF;
            case 48:
                return LITERAL;
        }
        
        for (int i = 0; i < terminals.size(); i++) {
            if (cod.equals(terminals.get(i).getCode())) {
                return terminals.get(i);
            }
        }
        return null;
    }

    public Token getNonTerminal(String str) {

        for (int i = 0; i < non_terminals.size(); i++) {
            if (str.equals(non_terminals.get(i).getName())) {
                return non_terminals.get(i);
            }
        }

        return null;
    }

    public Token getNonTerminal(Integer cod) {

        for (int i = 0; i < non_terminals.size(); i++) {
            if (cod.equals(non_terminals.get(i).getCode())) {
                return non_terminals.get(i);
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

    public ArrayList<Token> getNonTerminals() {
        return non_terminals;
    }

    public Map<String, String> getParsing() {
        return parsing;
    }

    private void buildTokens() {
        terminals = new ArrayList<>();
        non_terminals = new ArrayList<>();
        separators = new ArrayList<>();
        derivations = new ArrayList<>();
        parsing = new HashMap<>();

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
        
        parsing.put("52|1","1|25|47|53|49");
        parsing.put("53|2","54|57|59|62|64");
        parsing.put("53|3","54|57|59|62|64");
        parsing.put("53|4","54|57|59|62|64");
        parsing.put("53|5","54|57|59|62|64");
        parsing.put("53|6","54|57|59|62|64");
        parsing.put("54|2","2|55|47");
        parsing.put("54|3", null);
        parsing.put("54|4", null);
        parsing.put("54|5", null);
        parsing.put("54|6", null);
        parsing.put("55|25","25|56");
        parsing.put("56|39", null);
        parsing.put("56|46","46|25|56");
        parsing.put("56|47", null);
        parsing.put("57|3","3|25|40|26|47|58");
        parsing.put("57|4", null);
        parsing.put("57|5", null);
        parsing.put("57|6", null);
        parsing.put("58|4", null);
        parsing.put("58|5", null);
        parsing.put("58|6", null);
        parsing.put("58|25","25|40|26|47|58");
        parsing.put("59|4","4|55|39|61|47|60");
        parsing.put("59|5", null);
        parsing.put("59|6", null);
        parsing.put("60|5", null);
        parsing.put("60|6", null);
        parsing.put("60|25","55|39|61|47|60");
        parsing.put("61|8","8");
        parsing.put("61|9","9|34|26|50|26|35|10|8");
        parsing.put("62|5","5|25|63|47|53|47|62");
        parsing.put("62|6", null);
        parsing.put("63|36","36|55|39|8|37");
        parsing.put("63|39", null);
        parsing.put("63|47", null);
        parsing.put("64|6","6|66|65|7");
        parsing.put("65|7", null);
        parsing.put("65|47","47|66|65");
        parsing.put("66|6","64");
        parsing.put("66|7", null);
        parsing.put("66|11","11|25|69");
        parsing.put("66|12","52|25");
        parsing.put("66|13","13|77|14|66|71");
        parsing.put("66|15", null);
        parsing.put("66|16","16|77|17|66");
        parsing.put("66|18","18|66|19|77");
        parsing.put("66|19", null);
        parsing.put("66|20","20|36|72|74|37");
        parsing.put("66|21","21|36|75|76|37");
        parsing.put("66|25","25|67");
        parsing.put("66|27","27|25|38|77|28|77|17|66");
        parsing.put("66|29","29|77|10|84|7");
        parsing.put("66|47", null);
        parsing.put("67|34","68|38|77");
        parsing.put("67|38","68|38|77");
        parsing.put("67|39","39|66");
        parsing.put("68|34","34|77|35");
        parsing.put("68|38", null);
        parsing.put("69|7", null);
        parsing.put("69|15", null);
        parsing.put("69|19", null);
        parsing.put("69|36","36|77|70|37");
        parsing.put("69|47", null);
        parsing.put("70|37", null);
        parsing.put("70|46","46|77|70");
        parsing.put("71|7", null);
        parsing.put("71|15","15|66");
        parsing.put("71|19", null);
        parsing.put("71|47", null);
        parsing.put("72|25","25|73");
        parsing.put("73|7", null);
        parsing.put("73|10", null);
        parsing.put("73|14", null);
        parsing.put("73|15", null);
        parsing.put("73|17", null);
        parsing.put("73|19", null);
        parsing.put("73|22", null);
        parsing.put("73|23", null);
        parsing.put("73|28", null);
        parsing.put("73|30", null);
        parsing.put("73|31", null);
        parsing.put("73|32", null);
        parsing.put("73|33", null);
        parsing.put("73|34","34|77|35");
        parsing.put("73|35", null);
        parsing.put("73|37", null);
        parsing.put("73|40", null);
        parsing.put("73|41", null);
        parsing.put("73|42", null);
        parsing.put("73|43", null);
        parsing.put("73|44", null);
        parsing.put("73|45", null);
        parsing.put("73|46", null);
        parsing.put("73|47", null);
        parsing.put("74|37", null);
        parsing.put("74|46","46|72|74");
        parsing.put("75|24","77");
        parsing.put("75|25","77");
        parsing.put("75|26","77");
        parsing.put("75|30","77");
        parsing.put("75|31","77");
        parsing.put("75|36","77");
        parsing.put("75|48","48");
        parsing.put("76|37", null);
        parsing.put("76|46","46|75|76");
        parsing.put("77|24","79|78");
        parsing.put("77|25","79|78");
        parsing.put("77|26","79|78");
        parsing.put("77|30","79|78");
        parsing.put("77|31","79|78");
        parsing.put("77|36","79|78");
        parsing.put("78|7", null);
        parsing.put("78|10", null);
        parsing.put("78|14", null);
        parsing.put("78|15", null);
        parsing.put("78|17", null);
        parsing.put("78|19", null);
        parsing.put("78|28", null);
        parsing.put("78|35", null);
        parsing.put("78|37", null);
        parsing.put("78|40","40|79");
        parsing.put("78|41","41|79");
        parsing.put("78|42","42|79");
        parsing.put("78|43","43|79");
        parsing.put("78|44","44|79");
        parsing.put("78|45","45|79");
        parsing.put("78|46", null);
        parsing.put("78|47", null);
        parsing.put("79|24","81|80");
        parsing.put("79|25","81|80");
        parsing.put("79|26","81|80");
        parsing.put("79|30","30|81|80");
        parsing.put("79|31","31|81|80");
        parsing.put("79|36","81|80");
        parsing.put("80|7", null);
        parsing.put("80|10", null);
        parsing.put("80|14", null);
        parsing.put("80|15", null);
        parsing.put("80|17", null);
        parsing.put("80|19", null);
        parsing.put("80|22","22|81|80");
        parsing.put("80|28", null);
        parsing.put("80|30","30|81|80");
        parsing.put("80|31","31|81|80");
        parsing.put("80|35", null);
        parsing.put("80|37", null);
        parsing.put("80|40", null);
        parsing.put("80|41", null);
        parsing.put("80|42", null);
        parsing.put("80|43", null);
        parsing.put("80|44", null);
        parsing.put("80|45", null);
        parsing.put("80|46", null);
        parsing.put("80|47", null);
        parsing.put("81|24","83|82");
        parsing.put("81|25","83|82");
        parsing.put("81|26","83|82");
        parsing.put("81|36","83|82");
        parsing.put("82|7", null);
        parsing.put("82|10", null);
        parsing.put("82|14", null);
        parsing.put("82|15", null);
        parsing.put("82|17", null);
        parsing.put("82|19", null);
        parsing.put("82|22", null);
        parsing.put("82|23","23|83|82");
        parsing.put("82|28", null);
        parsing.put("82|30", null);
        parsing.put("82|31", null);
        parsing.put("82|32","32|83|82");
        parsing.put("82|33","33|83|82");
        parsing.put("82|35", null);
        parsing.put("82|37", null);
        parsing.put("82|40", null);
        parsing.put("82|41", null);
        parsing.put("82|42", null);
        parsing.put("82|43", null);
        parsing.put("82|44", null);
        parsing.put("82|45", null);
        parsing.put("82|46", null);
        parsing.put("82|47", null);
        parsing.put("83|24","24|83");
        parsing.put("83|25","72");
        parsing.put("83|26","26");
        parsing.put("83|36","36|77|37");
        parsing.put("84|26","26|86|39|66|85");
        parsing.put("85|7", null);
        parsing.put("85|47","47|84");
        parsing.put("86|39", null);
        parsing.put("86|46","46|26|86");

    }

}
