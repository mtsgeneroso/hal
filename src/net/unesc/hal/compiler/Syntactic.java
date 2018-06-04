package net.unesc.hal.compiler;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Token;
import net.unesc.hal.languages.HAL;

public class Syntactic {

    private ArrayList<String[]> lexicon;
    private ArrayList<String[]> errors;
    private ArrayList<Integer> stack;
    private ArrayList<String> stacks;
    private HAL lang;
    private ArrayList<Token> non_terminals;
    private Map<String, String> parsing;
    private Semantic sem;

    public Syntactic(ArrayList<String[]> lexicon, FiniteAutomaton fa) {
        this.lexicon = lexicon;
        this.lang = fa.getLang();
        this.non_terminals = this.lang.getNonTerminals();
        this.parsing = this.lang.getParsing();
        this.errors = new ArrayList<>();
        this.stacks = new ArrayList<>();
        this.sem = new Semantic(lexicon);
        run();
    }

    private void run() {
        stack = new ArrayList<>();
        ArrayList<String> ids = new ArrayList<>();
        int begin = 0,
            end = 0,
            varLevel = 1;
        boolean hasVar = false;
        
        // Adiciona a regra inicial
        addDerivation(getParsing(52, 1));

        // Começa a percorrer a pilha de derivações até que fique vazia
        do {

            // Armazena o primeiro token (código)
            Integer lex = new Integer(lexicon.get(0)[1]);

            // Armazena o primeiro código da derivação
            Integer stk = new Integer(stack.get(0));

            // System.out.println(stk + " : " + lex);
            // Verifica se é terminal ou não-terminal ;P
            if (stk < 52) {
                // System.out.println("Terminal");

                // Caso seja terminal e o topo do léxico e da pilha sejam iguais, ambos são removidos.
                if (stk.equals(lex)) {
                    // System.out.println("Códigos iguais");
                    /*
                    if (hasVar) {
                        if(stk.equals(4)){
                            varLevel++;
                            //System.out.println("LEVEL: " + varLevel);
                        }
                        
                        if(stk.equals(6)){
                            begin++;
                            //System.out.println("BEGIN: " + begin);
                        }
                        
                        if(stk.equals(7)){
                            end++;
                            //System.out.println("END: " + end);
                        }
                        
                    } else if (stk.equals(4)) {
                        hasVar = true;
                        varLevel++;
                        //System.out.println("LEVEL: " + varLevel);
                    }
                    */
                    if(lex.equals(5)) {
                        varLevel++;
                        System.out.println("Procedure");
                    }
                    
                    if(lex.equals(25)){
                        ids.add(lexicon.get(0)[2] + "| Nível: " + varLevel);
                    }
                    
                    lexicon.remove(0);
                    stack.remove(0);
                    printStack();
                    // System.out.println("- - -");
                } else {
                    // Se forem terminais diferentes é disparado um erro.
                    addError(new Integer(lexicon.get(0)[0]), "Token esperado '" + lang.getTerminal(stk).getName() + "' (" + stk + ") e foi lido '" + lang.getTerminal(lex).getName() + "' (" + lex + ")");
                    // System.out.println("Token esperado: " + lang.getTerminal(stk).getName() + " | lido: " + lang.getTerminal(lex).getName());
                    break;
                }

            } else {
                // Caso seja não-terminal é realizada a derivação
                // System.out.println("Não terminal");

                // Remove o não-terminal para não causar recursividade
                stack.remove(0);
                printStack();

                // Adiciona os códigos de derivação no ínicio da pilha
                addDerivation(getParsing(stk, lex));
                // System.out.println("- - -");
                continue;
            }

        } while (!stack.isEmpty());

        // System.out.println("Sintático concluído");
    }

    // Busca a derivação correspondente aos códigos
    public ArrayList getParsing(int c1, int c2) {

        //System.out.println(c1 + "|" + c2);
        String der = parsing.get(c1 + "|" + c2);
        ArrayList derivation;
        if (der != null) {
            derivation = new ArrayList(Arrays.asList(der.split("\\|")));
            // System.out.println("Número de itens na derivação: " + derivation.size());
            return derivation;
        }
        // System.out.println("Derivação vazia");
        return null;
    }

    // Adiciona a derivação na pilha
    public void addDerivation(ArrayList derivation) {

        if (derivation == null) {
            return;
        }

        Collections.reverse(derivation);

        for (Object d : derivation) {
            stack.add(0, new Integer(d.toString()));
        }

        printStack();
    }

    private void printStack() {
        String out = new String();
        String outfull = new String();
        
        out = " ";
        outfull = " ";
        

        // System.out.println("Pilha: ");
        for (Integer d : stack) {
            out += d + " | ";
            
            if(d < 52) {
                outfull += lang.getTerminal(d).getName() + " | ";
            } else {
                outfull += lang.getNonTerminal(d).getName() + " | ";
            }
        }
        stacks.add(out);
        //System.out.println(outfull);
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

    public ArrayList<String> getStacks() {
        return stacks;
    }

}
