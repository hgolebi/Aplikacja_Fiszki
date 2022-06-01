package com.pap.fishes.papfishes;

public class Fish {
    private int id;
    private String term;
    private String definition;
    private String category;
    private boolean repeat;

    public Fish(int f_id, String ter, String def, String cat){
        id = f_id;
        term = ter;
        definition = def;
        category = cat;
        repeat = false;
    }

    public void print(){
        String id_string = String.valueOf(id);
        System.out.println(id_string + " " + term + " " + definition + " " + category);
    }

    public int getId() {
        return id;
    }
    public String getTerm() {
        return term;
    }
    public String getDefinition() {
        return definition;
    }
    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTerm(String term) {
        this.term = term;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isRepeat() {
        return repeat;
    }
    public void changeRepeat(){
        if (repeat == true)
            repeat = false;
        else
            repeat = true;
    }
}
