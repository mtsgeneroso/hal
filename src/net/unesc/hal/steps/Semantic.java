package net.unesc.hal.steps;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class Semantic {

    private ArrayList<String[]> errors;
    private ArrayList<String[]> ids;
    private Integer scope;
    private String currHash;
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
        this.currHash = "";
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
        String[] id = new String[5];
        id[0] = name;
        id[1] = category;
        id[2] = type;
        id[3] = level.toString();
        id[4] = ""; // Hash do scope
        ids.add(id);
    }

    public void setRegressiveTypeId(String type) {
        for (int i = ids.size() - 1; i > 0; i--) {
            if (ids.get(i)[2].isEmpty() && ids.get(i)[1].equals("Variável")) {
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

        if (cod.equals(2) || cod.equals(3) || cod.equals(4) || cod.equals(5)) {
            isRunning = true;
        }

        if (!isRunning) {
            return;
        }

        // BEGIN
        if (cod.equals(6)) {
            if (skipLevel > 0) {
                skipLevel--;
            } else {
                scope++;
            }
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
        }

        if (cod.equals(11)) {
            category = "Procedure";
            type = "Call";
        }

        if (lastCategory.equals("Procedure")) {
            category = "Procedure";
        }

        if (!ids.isEmpty() && getId(ids.size() - 1)[1].equals("Procedure") && !getId(ids.size() - 1)[2].equals("Call")) {
            category = "Parâmetro";
        }

        if (cod.equals(25)) {
            if (lastToken.equals(5)) {
                addId(source, category, type, scope);
                skipLevel++;
                scope++;

            } else {
                addId(source, category, type, scope);
            }
        }

        lastToken = cod;
        lastCategory = category;

        // END
        if (cod.equals(7)) {
            scope--;
            beforeEnd();
        }
    }

    public void beforeEnd() {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i)[2].isEmpty() && ids.get(i)[1].equals("Variável")) {
                if (isInstanced(ids.get(i))) {
                    String[] id = ids.get(i);
                    id[2] = "Inteiro";
                    ids.set(i, id);
                } else {
                    addError("A variável " + ids.get(i)[0] + " não foi instanciada");
                }
            }
        }
        setRegressiveHashingLevels();
    }

    private boolean isInstanced(String[] id) {
        for (int i = 0; i < ids.size(); i++) {
            if (!ids.get(i)[2].isEmpty()
                    && ids.get(i)[1].equals("Variável")
                    && ids.get(i)[3].equals(id[3])
                    && ids.get(i)[0].equals(id[0])) {
                return true;
            }
        }
        return false;
    }

    private void setRegressiveHashingLevels() {
        Integer currScope = 1;
        String hash = generateHash();
        for (int i = 0; i < ids.size(); i++) {
            int scope = new Integer(ids.get(i)[3]);
            if (ids.get(i)[4].isEmpty() && scope > 1) {
                if (currScope.equals(new Integer(ids.get(i)[3]))) {
                    String[] id = ids.get(i);
                    id[4] = scope > 1 ? hash : "";
                    System.out.println(id[4]);
                    ids.set(i, id);
                } else  {
                    currScope = scope;
                    hash = generateHash();
                }
            }
        }
    }

    private String generateHash() {
        return UUID.randomUUID().toString();
    }
}
