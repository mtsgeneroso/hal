package net.unesc.hal.languages;

import net.unesc.hal.data.Token;

public interface Language {
    public Token getToken(String str);
    public Token getToken(Integer cod);
}
