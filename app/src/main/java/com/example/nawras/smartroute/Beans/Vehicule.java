package com.example.nawras.smartroute.Beans;

public class Vehicule {
    private int id;
    private int catId;
    private int motId;
    private String marque;
    private String modele;
    private String imgVoiture;

    public Vehicule(int id, int catId, int motId, String marque, String modele, String imgVoiture) {
        this.id = id;
        this.catId = catId;
        this.motId = motId;
        this.marque = marque;
        this.modele = modele;
        this.imgVoiture = imgVoiture;
    }

    public Vehicule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getMotId() {
        return motId;
    }

    public void setMotId(int motId) {
        this.motId = motId;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImgVoiture() {
        return imgVoiture;
    }

    public void setImgVoiture(String imgVoiture) {
        this.imgVoiture = imgVoiture;
    }
}
