package net.unesc.hal.models;

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
    private ArrayList<Integer> stack;
    private HAL lang;
    private ArrayList<Token> non_terminals;
    private Map<String, String> parsing;

    public Syntactic(ArrayList<String[]> lexicon, FiniteAutomaton fa) {
        this.lexicon = lexicon;
        this.lang = fa.getLang();
        this.non_terminals = this.lang.getNonTerminals();
        this.parsing = this.lang.getParsing();
        run();
    }

    private void run() {
        stack = new ArrayList<>();

        // Adiciona a regra inicial
        addDerivation(getParsing(52, 1));

        // Começa a percorrer a pilha de derivações até que fique vazia
        do {
            
            // Armazena o primeiro token (código)
            Integer lex = new Integer(lexicon.get(0)[1]);
            
            // Armazena o primeiro código da derivação
            Integer stk = new Integer(stack.get(0));
            
            System.out.println(stk + " : " + lex);
            
            // Verifica se é terminal ou não-terminal ;P
            if (stk < 52) {
                System.out.println("Terminal");
                
                // Caso seja terminal e o topo do léxico e da pilha sejam iguais, ambos são removidos.
                if (stk.equals(lex)) {
                    System.out.println("Códigos iguais");
                    lexicon.remove(0);
                    stack.remove(0);
                    System.out.println("- - -");
                } else {
                    // Se forem terminais diferentes é disparado um erro.
                    System.out.println("Código com erro: " + lang.getTerminal(stk).getName() + " : " + lang.getTerminal(lex).getName());
                    break;
                }

            } else {
                // Caso seja não-terminal é realizada a derivação
                System.out.println("Não terminal");
                
                // Remove o não-terminal para não causar recursividade
                stack.remove(0);
                
                // Adiciona os códigos de derivação no ínicio da pilha
                addDerivation(getParsing(stk, lex));
                System.out.println("- - -");
                continue;
            }

        } while (!stack.isEmpty());
        
        System.out.println("Sintático concluído");

    }

    // Busca a derivação correspondente aos códigos
    public ArrayList getParsing(int c1, int c2) {
        
        System.out.println(c1 + "|" + c2);
        String der = parsing.get(c1 + "|" + c2);
        ArrayList derivation;
        if(der != null) {
            derivation = new ArrayList(Arrays.asList(der.split("\\|")));
            System.out.println("Número de itens na derivação: " + derivation.size());
            return derivation;
        }
        System.out.println("Derivação vazia");
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

        System.out.println("Pilha: ");
        for (Integer d : stack) {
            System.out.print(d + " | ");
        }
        System.out.println("");

    }

}
