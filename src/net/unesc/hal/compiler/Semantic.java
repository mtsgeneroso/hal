package net.unesc.hal.compiler;

import java.util.ArrayList;

public class Semantic {

    private ArrayList<String[]> errors;
    private ArrayList<String[]> ids;
    private Integer scope;
    private boolean hasVar;
    private boolean isRunning;
    private int skipLevel;
    private Integer lastToken;
    private String lastCategory;
    private String category;
    private String type;

    public Semantic() {
        this.errors = new ArrayList<>();
        this.ids = new ArrayList<>();
        this.scope = 1;
        this.hasVar = false;
        this.isRunning = false;
        this.skipLevel = 1;
        this.category = "";
        this.type = "";
        this.lastCategory = "";
    }

    public ArrayList<String[]> getErrors() {
        return this.errors;
    }

    public void addError(String msg) {
        String[] adition = new String[1];
        adition[0] = msg;
        errors.add(adition);
    }

    public ArrayList<String[]> getIds() {
        return ids;
    }

    private String[] getId(int index) {
        return ids.get(index);
    }

    public void setIds(ArrayList<String[]> ids) {
        this.ids = ids;
    }

    private void addId(String name, String category, String type, Integer level) {
        String[] id = new String[4];
        id[0] = name;
        id[1] = category;
        id[2] = type;
        id[3] = level.toString();
        ids.add(id);
    }
    
    public void setRegressiveTypeId(String type) {
        for(int i = ids.size() - 1; i > 0; i--){
            if(ids.get(i)[2].isEmpty() && ids.get(i)[1].equals("Variável")){
                String[] id = ids.get(i);
                id[2] = type;
                ids.set(i, id);
            } else {
                return;
            }
        }
    }
    
    public void check(String[] item) {

        Integer cod = new Integer(item[1]);
        String token = item[2];
        String source = item[3];

        if (cod.equals(2) || cod.equals(3) || cod.equals(4)) {
            isRunning = true;
        }

        if (!isRunning) {
            return;
        }

        // BEGIN
        if (cod.equals(6)) {
            System.out.print(item[0] + " : " + skipLevel);
            if (skipLevel > 0) {
                skipLevel--;
            } else {
                scope++;
            }
            System.out.print(" -> " + scope + "\n");
        }

        if (cod.equals(2)) {
            category = "Rótulo";
            type = "String";
        }

        if (cod.equals(3)) {
            category = "Constante";
            type = "Inteiro";
        }

        if (cod.equals(4)) {
            category = "Variável";
            type = "";
        }
        
        // Integer
        if (cod.equals(8)) {
            setRegressiveTypeId("Inteiro");
        }

        if (cod.equals(5)) {
            category = "Procedure";
            type = "Procedure";
            skipLevel++;
            scope++;
        }

        if (lastCategory.equals("Procedure")) {
            category = "Procedure";
        }

        if (!ids.isEmpty() && getId(ids.size() - 1)[1].equals("Procedure")) {
            category = "Parâmetro";
        }

        if (cod.equals(25)) {
            addId(source, category, type, scope);
        }

        lastToken = cod;
        lastCategory = category;

        // END
        if (cod.equals(7)) {
            scope--;
        }
    }

}
