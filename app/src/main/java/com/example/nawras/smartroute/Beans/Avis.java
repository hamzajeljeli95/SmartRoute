package com.example.nawras.smartroute.Beans;

public class Avis {
    private int id;
    private int idUtilRedigant;
    private int idUtilAvisDonne;
    private String avis;

    public Avis(int id, int idUtilRedigant, int idUtilAvisDonne, String avis) {
        this.id = id;
        this.idUtilRedigant = idUtilRedigant;
        this.idUtilAvisDonne = idUtilAvisDonne;
        this.avis = avis;
    }

    public Avis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilRedigant() {
        return idUtilRedigant;
    }

    public void setIdUtilRedigant(int idUtilRedigant) {
        this.idUtilRedigant = idUtilRedigant;
    }

    public int getIdUtilAvisDonne() {
        return idUtilAvisDonne;
    }

    public void setIdUtilAvisDonne(int idUtilAvisDonne) {
        this.idUtilAvisDonne = idUtilAvisDonne;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }
}
