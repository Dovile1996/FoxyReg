package com.example.dovile.foxy;

public class Vartotojas {
    private String elpastas;
    private String vardas;
    private String slaptazodis;

    public Vartotojas(String elpastas, String vardas, String slaptazodis) {
        this.elpastas = elpastas;
        this.vardas = vardas;
        this.slaptazodis = slaptazodis;
    }

    public String getElpastas() {
        return elpastas;
    }

    public void setElpastas(String elpastas) {
        this.elpastas = elpastas;
    }

    public String getVardas() {
        return vardas;
    }

    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getSlaptazodis() {
        return slaptazodis;
    }

    public void setSlaptazodis(String slaptazodis) {
        this.slaptazodis = slaptazodis;
    }
}
