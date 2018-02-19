package net.unesc.hal.test;

import net.unesc.hal.controllers.FiniteAutomaton;
import net.unesc.hal.data.Source;
import net.unesc.hal.languages.HAL;
import net.unesc.hal.models.Lexicon;
import org.junit.Test;

public class LexiconTest {
    
    @Test
    public void withCorrectSource(){
        String src = "void main {\n" +
                            "    #var1: int;\n" +
                            "    #var2, #var3, #var4: float;\n" +
                            "    \n|+ Comentario" +
                            "    \nComentario +|" +
                            "    \n||Comentario2" +
                            "    int #var5 ( int ; float ) {\n" +
                            "        #var6: int;\n" +
                            "        float #var7 {\n" +
                            "            inicio\n" +
                            "                cout << #var7;\n" +
                            "            fim\n" +
                            "            return (#var7)\n" +
                            "        };\n" +
                            "        inicio\n" +
                            "            cout << #var6 ;\n" +
                            "        fim\n" +
                            "        return (#var6)\n" +
                            "    };\n" +
                            "    inicio\n" +
                            "        cin  >> #var1;\n" +
                            "    fim\n" +
                            "}";
        
        Lexicon lex = new Lexicon(new Source(src), new FiniteAutomaton(new HAL()));
    }
    
    @Test
    public void withWrongSource(){}
}
