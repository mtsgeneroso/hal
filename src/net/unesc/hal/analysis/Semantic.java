package net.unesc.hal.analysis;

import java.util.ArrayList;
import java.util.UUID;
import net.unesc.hal.analysis.semantic.Identifier;
import net.unesc.hal.analysis.semantic.Node;
import net.unesc.hal.analysis.semantic.Procedure;

public class Semantic {

    public static final int CATEGORY_VARIABLE = 1;
    public static final int CATEGORY_CONSTANT = 2;
    public static final int CATEGORY_LABEL = 3;
    public static final int CATEGORY_PARAMETER = 4;
    public static final int CATEGORY_PROCEDURE = 5;

    public static final int TYPE_INTEGER = 1;
    public static final int TYPE_LITERAL = 2;

    private ArrayList<String[]> errors;

    private int currType;
    private int currCategory;

    private Node tree;
    private Node currNode;

    public Semantic() {
        this.errors = new ArrayList<>();
        this.tree = new Node();
        this.currNode = tree;
        this.currType = 0;
        this.currCategory = -1;
    }

    public ArrayList<String[]> getErrors() {
        return this.errors;
    }

    public void addError(String msg) {
        String[] adition = new String[1];
        adition[0] = msg;
        errors.add(adition);
    }

    public void check(String[] item) {
        
        if(!errors.isEmpty()){
            return;
        }
        
        String line = item[0];
        Integer cod = new Integer(item[1]);
        String token = item[2];
        String source = item[3];

        //1, "program"
        //2, "label"
        //3, "const"
        //4, "var"
        //5, "procedure"
        //6, "begin"
        //7, "end"
        //8, "integer"
        //9, "array"
        //10, "of"
        //11, "call"
        //12, "goto"
        //13, "if"
        //14, "then"
        //15, "else"
        //16, "while"
        //17, "do"
        //18, "repeat"
        //19, "until"
        //20, "readln"
        //21, "writeln"
        //22, "or"
        //23, "and"
        //24, "not"
        //27, "for"
        //28, "to"
        //29, "case"
        switch (cod) {
            case 2:
                currCategory = CATEGORY_LABEL;
                break;
            case 3:
                currCategory = CATEGORY_CONSTANT;
                break;
            case 4:
                currCategory = CATEGORY_VARIABLE;
                break;
            case 5:
                currCategory = CATEGORY_PROCEDURE;
                break;
            case 6:
                currCategory = 0;
                break;
            case 7:
                if (currNode.getParent() != null) {
                    currNode = currNode.getParent();
                }
                break;
            case 25:
                handleIdentifier(line, source);
                break;
        }

    }

    private void handleIdentifier(String line, String source) {
        switch (currCategory) {
            case -1:
                // Nome do programa
                return;
            case CATEGORY_PROCEDURE:
                if (addProcedure(source) == -1) {
                    addError("Linha ->" + line + ": A procedure " + source + " já foi declarada");
                    return;
                }
                currCategory = CATEGORY_PARAMETER;
                break;
            case CATEGORY_PARAMETER:
                if (addIdentifier(source, CATEGORY_PARAMETER, TYPE_INTEGER) == -1) {
                    addError("Linha ->" + line + ": O parametro " + source + " já foi declarado");
                }
                break;
            case CATEGORY_CONSTANT:
                if (addIdentifier(source, currCategory, TYPE_INTEGER) == -1) {
                    addError("Linha ->" + line + ": O identificador " + source + " já foi declarado");
                }
                break;
            case CATEGORY_LABEL:
                if (addIdentifier(source, currCategory, TYPE_LITERAL) == -1) {
                    addError("Linha ->" + line + ": O identificador " + source + " já foi declarado");
                }
                break;
            case CATEGORY_VARIABLE:
                if (addIdentifier(source, currCategory, TYPE_INTEGER) == -1) {
                    addError("Linha ->" + line + ": O identificador " + source + " já foi declarado");
                }
                break;
            default:
                if (!anyMatchDeclaration(source, currNode)) {
                    addError("Linha ->" + line + ": O identificador " + source + " não foi declarado");
                }
        }
    }

    private int addIdentifier(String name, int category, int type) {
        if (anyMatchIdentifier(name)) {
            return -1;
        }

        currNode.addIdentifier(new Identifier(name, category, type));
        return 0;
    }

    private int addProcedure(String name) {

        if (anyMatchProcedure(name)) {
            return -1;
        }

        addNode(new Procedure(name));
        return 0;
    }

    private boolean anyMatchDeclaration(String name, Node n) {

        for (Node p : n.getChildren()) {
            if (p.isProcedure() && p.getName().toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }

        for (Identifier i : n.getIdentifiers()) {
            if (i.getName().toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }

        if (n.getParent() != null) {
            return anyMatchDeclaration(name, n.getParent());
        }

        return false;
    }

    private boolean anyMatchIdentifier(String name) {
        if (currNode.getIdentifiers().isEmpty()) {
            return false;
        }

        for (Identifier i : currNode.getIdentifiers()) {
            if (i.getName().toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private boolean anyMatchProcedure(String name) {

        if (currNode.getChildren().isEmpty()) {
            return false;
        }

        for (Node n : currNode.getChildren()) {
            if (n.isProcedure() && n.getName().toUpperCase().equals(name.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private void addNode(Node n) {
        Node node = n;
        currNode.addChild(node);
        node.setParent(currNode);
        currNode = node;
    }

    public ArrayList<String[]> getIds() {

        ArrayList<String[]> ids = new ArrayList<>();

        for (Identifier i : currNode.getIdentifiers()) {
            String[] id = new String[4];
            id[0] = i.getName();
            id[1] = parseCategory(i.getCategory());
            id[2] = parseType(i.getType());
            id[3] = "1";
            ids.add(id);
        }
        if (!currNode.getChildren().isEmpty()) {
            ids = makeSymbolTable(ids, 1, currNode);
        }
        return ids;
    }

    private ArrayList<String[]> makeSymbolTable(ArrayList<String[]> ids, int level, Node currNode) {
        level++;
        for (Node n : currNode.getChildren()) {
            if (n.isProcedure()) {
                String[] id = new String[4];
                id[0] = n.getName();
                id[1] = parseCategory(CATEGORY_PROCEDURE);
                id[2] = "";
                id[3] = new Integer(level - 1).toString();
                ids.add(id);
            }
            for (Identifier i : n.getIdentifiers()) {
                String[] id = new String[4];
                id[0] = i.getName();
                id[1] = parseCategory(i.getCategory());
                id[2] = parseType(i.getType());
                id[3] = new Integer(level).toString();
                ids.add(id);
            }
            if (!n.getChildren().isEmpty()) {
                ids = makeSymbolTable(ids, level, n);
            }
        }

        return ids;
    }

    private String parseCategory(int category) {
        switch (category) {
            case CATEGORY_CONSTANT:
                return "Constante";
            case CATEGORY_LABEL:
                return "Label";
            case CATEGORY_PARAMETER:
                return "Parâmetro";
            case CATEGORY_PROCEDURE:
                return "Procedure";
            case CATEGORY_VARIABLE:
                return "Variável";
        }
        return "";
    }

    private String parseType(int type) {
        switch (type) {
            case TYPE_INTEGER:
                return "Inteiro";
            case TYPE_LITERAL:
                return "Literal";
        }
        return "";
    }
}
