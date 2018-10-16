package com.example.nawras.smartroute.Beans;


public class Categorie {
    private int id;
    private String libCat;

    public Categorie(int id, String libCat) {
        this.id = id;
        this.libCat = libCat;
    }

    public Categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibCat() {
        return libCat;
    }

    public void setLibCat(String libCat) {
        this.libCat = libCat;
    }
}
