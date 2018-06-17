package net.unesc.hal.analysis.semantic;

import java.util.Objects;

public class Identifier {
    private String name;
    private int category;
    private int type;
    private boolean instance;

    public Identifier(String name) {
        this.name = name;
    }

    public Identifier(String name, int category) {
        this.name = name;
        this.category = category;
    }

    public Identifier(String name, int category, int type) {
        this.name = name;
        this.category = category;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isInstance() {
        return instance;
    }

    public void setInstance(boolean instance) {
        this.instance = instance;
    }
    
}
