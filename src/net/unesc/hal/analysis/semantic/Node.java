package net.unesc.hal.analysis.semantic;

import java.util.ArrayList;

public class Node {
    private ArrayList<Identifier> identifiers;
    private ArrayList<Node> children;
    private Node parent;
    private String name;

    public Node() {
        this.parent = null;
        this.children = new ArrayList<>();
        this.identifiers = new ArrayList<>();
    }
    
    public Node(String name){
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>();
        this.identifiers = new ArrayList<>();
    }
    
    public ArrayList<Identifier> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<Identifier> identifiers) {
        this.identifiers = identifiers;
    }
    
    public void addIdentifier(Identifier id){
        this.identifiers.add(id);
    }
    
    public Identifier getIdentifier(int value){
        return this.identifiers.get(value);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
    
    public void addChild(Node child){
        this.children.add(child);
    }
    
    public Node getChild(int index){
        return this.children.get(index);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isProcedure() {
        return false;
    }
    
    
}
