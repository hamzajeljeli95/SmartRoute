package com.example.nawras.smartroute.Beans;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Covoiturage {
    private int id;
    private String villeDep;
    private String villeArr;
    private String pointDep;
    private String pointArr;
    private Timestamp dateHeureDep;
    private Integer nbrPlaces;
    private Double prix;
    private String conditionCov;
    private int idUtilisateur;

    public Covoiturage(int id, String villeDep, String villeArr, String pointDep, String pointArr, Timestamp dateHeureDep, Integer nbrPlaces, Double prix, String conditionCov, int idUtilisateur) {
        this.id = id;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.pointDep = pointDep;
        this.pointArr = pointArr;
        this.dateHeureDep = dateHeureDep;
        this.nbrPlaces = nbrPlaces;
        this.prix = prix;
        this.conditionCov = conditionCov;
        this.idUtilisateur = idUtilisateur;
    }

    public Covoiturage() {
    }

    public Covoiturage(String villeDep, String villeArr, String dateHeureDep) {
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        try{
            dateHeureDep = dateHeureDep+" 00:00:00";
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.FRENCH);
            Date date = (Date) formatter.parse(dateHeureDep);
            this.dateHeureDep = new Timestamp(date.getTime());
        }
        catch (Exception e)
        {
            this.dateHeureDep = null;
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVilleDep() {
        return villeDep;
    }

    public void setVilleDep(String villeDep) {
        this.villeDep = villeDep;
    }

    public String getVilleArr() {
        return villeArr;
    }

    public void setVilleArr(String villeArr) {
        this.villeArr = villeArr;
    }

    public String getPointDep() {
        return pointDep;
    }

    public void setPointDep(String pointDep) {
        this.pointDep = pointDep;
    }

    public String getPointArr() {
        return pointArr;
    }

    public void setPointArr(String pointArr) {
        this.pointArr = pointArr;
    }

    public Timestamp getDateHeureDep() {
        return dateHeureDep;
    }

    public void setDateHeureDep(Timestamp dateHeureDep) {
        this.dateHeureDep = dateHeureDep;
    }

    public Integer getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(Integer nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getConditionCov() {
        return conditionCov;
    }

    public void setConditionCov(String conditionCov) {
        this.conditionCov = conditionCov;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public String toString() {
        return "Covoiturage{" +
                "id=" + id +
                ", villeDep='" + villeDep + '\'' +
                ", villeArr='" + villeArr + '\'' +
                ", pointDep='" + pointDep + '\'' +
                ", pointArr='" + pointArr + '\'' +
                ", nbrPlaces=" + nbrPlaces +
                ", prix=" + prix +
                ", conditionCov='" + conditionCov + '\'' +
                ", idUtilisateur=" + idUtilisateur +
                '}';
    }
}
