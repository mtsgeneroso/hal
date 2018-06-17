package net.unesc.hal.analysis.lexicon;

import java.util.ArrayList;

public class Source {

    private String code;
    private Integer line_count;

    public Source(String code) {
        this.code = code != null ? code : "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code != null ? code : "";
    }

    @Override
    public String toString() {
        return "Source{" + "code=" + code + '}';
    }

    public ArrayList<Char> getChars() {

        ArrayList<Char> chars = new ArrayList<>();

        String[] rows = code.split("\n");
        line_count = rows.length;
        for (int i = 0; i < rows.length; i++) {

            String[] cols = rows[i].split("");
            
            for (int j = 0; j < cols.length; j++) {
                if(!cols[j].isEmpty() && !cols[j].matches("\\t")) {
                    chars.add(new Char(cols[j].charAt(0)));
                } else {
                    chars.add(new Char((" ").charAt(0)));
                }
            }

            Char endLine = new Char();
            endLine.setEndLine(true);
            chars.add(endLine);
        }

        Char endFile = new Char();
        endFile.setEndFile(true);
        chars.add(endFile);
        return chars;
    }

    public Integer getLineCount() {
        return line_count;
    }
}
