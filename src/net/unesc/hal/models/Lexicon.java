package net.unesc.hal.models;

import java.util.ArrayList;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Source;
import net.unesc.hal.data.Character;
import net.unesc.hal.data.Token;
import net.unesc.hal.languages.HAL;

public class Lexicon {

    private final Source src;
    private HAL lang;
    private FiniteAutomaton fa;
    private ArrayList<String[]> tokens;
    private ArrayList<String[]> errors;

    public Lexicon(Source src, FiniteAutomaton fa) {
        this.src = src;
        this.fa = fa;
        this.lang = fa.getLang();
        this.tokens = new ArrayList<>();
        this.errors = new ArrayList<>();
        run();
    }

    public ArrayList<String[]> getTokens() {
        return tokens;
    }

    public ArrayList<String[]> getErrors() {
        return errors;
    }

    private void addError(Integer line, String msg) {
        String[] adition = new String[3];
        adition[0] = line.toString();
        adition[1] = msg;
        errors.add(adition);
    }

    private void addToken(Integer line, Token token) {
        String[] adition = new String[3];
        adition[0] = line.toString();
        adition[1] = token.getCode().toString();
        adition[2] = token.getName();
        tokens.add(adition);
    }

    private String parseBuffer(ArrayList<Character> buffer) {
        String out = "";

        for (int i = 0; i < buffer.size(); i++) {
            out += buffer.get(i).getCharacter();
        }
        return out;
    }

    private void run() {
        int cur_line = 1;
        int car = 0;
        boolean comment = false;

        ArrayList<Character> chars = src.getChars();

        Character cur_char;

        ArrayList<Character> buffer = new ArrayList<>();

        while (car < chars.size()) {
            cur_char = chars.get(car);

            // Keywords
            while (cur_char.isLetter() && !comment) {
                buffer.add(cur_char);
                if (car < chars.size()) {
                    cur_char = chars.get(++car);
                }
            }

            // Identificador
            if (cur_char.isNum() && !comment) {
                while (cur_char.isNum()) {
                    buffer.add(cur_char);

                    if (car < chars.size()) {
                        cur_char = chars.get(++car);
                    }

                }
                if (!buffer.isEmpty()) {
                    //System.out.println(cur_line + " : " + parseBuffer(buffer));

                    addToken(cur_line, lang.IDENTIFIER);
                    buffer.clear();
                }

            }
            if (!buffer.isEmpty() && !comment) {
                //System.out.println(cur_line + " : " + parseBuffer(buffer));
                Token token = lang.getToken(parseBuffer(buffer));
                if (token != null) {
                    addToken(cur_line, token);
                } else {
                    if (parseBuffer(buffer).length() <= 30) {
                        addToken(cur_line, lang.IDENTIFIER);
                    } else {
                        this.addError(cur_line, "Limite excedido");
                        break;
                    }
                }
                buffer.clear();
            }

            // Integer
            while (cur_char.isNum() && !comment) {
                buffer.add(cur_char);
                if (car < chars.size()) {
                    cur_char = chars.get(++car);
                }
            }

            if (!buffer.isEmpty() && !comment) {
                addToken(cur_line, lang.INTEGER);
                buffer.clear();
            }

            // Operadores Aritiméticos, Sinais Relacionais, Simbolos Especiais
            if (cur_char.isSymbol()) {
                buffer.add(cur_char);

                if (car < chars.size()) {
                    cur_char = chars.get(++car);
                    if (lang.getToken(parseBuffer(buffer) + cur_char.getCharacter()) != null && !comment) {
                        buffer.add(cur_char);
                        cur_char = chars.get(++car);
                    }

                    // Comentários
                    if (new String(parseBuffer(buffer) + cur_char.getCharacter()).equals(lang.COMMENT_BLOCK_BEGIN) && !comment) {
                        comment = true;
                        cur_char = chars.get(++car);
                        buffer.clear();
                    }

                    if (new String(parseBuffer(buffer) + cur_char.getCharacter()).equals(lang.COMMENT_BLOCK_END)) {
                        comment = false;
                        cur_char = chars.get(++car);
                        buffer.clear();
                    }
                }

                if (comment) {
                    cur_char = chars.get(++car);
                    buffer.clear();
                }
            }

            if (comment) {
                while (!cur_char.isSymbol() && !cur_char.isEndFile() && !cur_char.isEndLine() && !cur_char.isSpace()) {
                    //System.out.println(cur_line + " : " + cur_char.getCharacter());
                    if (car < chars.size()) {
                        cur_char = chars.get(++car);
                    }
                }
            }

            if (!buffer.isEmpty() && !comment) {
                //System.out.println(cur_line + " : " + parseBuffer(buffer));
                Token token = lang.getToken(parseBuffer(buffer));
                if (token != null) {
                    addToken(cur_line, token);
                } else {
                    //System.out.println("Symbolo inexistente: " + parseBuffer(buffer));
                }
                buffer.clear();
            }

            // Controle do pointeiro
            if (cur_char.isEndLine()) {
                if (car < chars.size()) {
                    cur_char = chars.get(++car);
                }
                cur_line++;
            }

            // Identifica o final do arquivo
            if (cur_char.isEndFile()) {
                if (comment) {
                    this.addError(cur_line, "Comentário sem fechamento");
                    break;
                }
                addToken(cur_line, lang.EOF);
                break;
            }

            while (cur_char.isSpace()) {
                if (car < chars.size()) {
                    cur_char = chars.get(++car);
                }
            }
        }

    }

}
