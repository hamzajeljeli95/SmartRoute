package com.example.nawras.smartroute.Beans;

public class Motorisation {
    private int id;
    private String libMot;

    public Motorisation(int id, String libMot) {
        this.id = id;
        this.libMot = libMot;
    }

    public Motorisation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibMot() {
        return libMot;
    }

    public void setLibMot(String libMot) {
        this.libMot = libMot;
    }
}
