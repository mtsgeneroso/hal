package net.unesc.hal.analysis.semantic;

import java.util.ArrayList;

public class Procedure extends Node{

    private String name;
    private ArrayList<Identifier> parameters;
    
    public Procedure(String name) {
        super(name);
    }

    @Override
    public boolean isProcedure() {
        return true;
    }
    
}
