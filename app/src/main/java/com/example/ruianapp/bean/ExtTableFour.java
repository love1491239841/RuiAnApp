package com.example.ruianapp.bean;

public class ExtTableFour {
    private String type;
    private int display_order;
    private String col1;
    private String col2;
    private String col3;

    public ExtTableFour(String type, int display_order, String col1, String col2, String col3) {
        this.type = type;
        this.display_order = display_order;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public void setDisplay_order(int display_order) {
        this.display_order = display_order;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }
}
