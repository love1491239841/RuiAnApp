package com.example.ruianapp.bean;

public class FuelGas {
    private String name;
    private String radio;
    private String bz;

    public FuelGas(String name, String radio, String bz) {
        this.name = name;
        this.radio = radio;
        this.bz = bz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
