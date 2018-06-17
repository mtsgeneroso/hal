package net.unesc.hal.languages;

import net.unesc.hal.analysis.lexicon.Token;

public interface Language {
    public Token getTerminal(String str);
    public Token getTerminal(Integer cod);
}
