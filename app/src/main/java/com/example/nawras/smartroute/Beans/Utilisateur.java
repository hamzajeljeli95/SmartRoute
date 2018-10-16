package com.example.nawras.smartroute.Beans;

import java.sql.Timestamp;

public class Utilisateur {
    private int id;
    private int vehId;
    private String nom;
    private String prenom;
    private Timestamp dateNaissance;
    private String genre;
    private String address;
    private Long telephone;
    private String email;
    private String mdp;
    private String img;
    private String profile;

    public Utilisateur(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(int id, int vehId) {
        this.id = id;
        this.vehId = vehId;
    }

    public Utilisateur(int id, int vehId, String nom, String prenom, Timestamp dateNaissance, String genre, String address, Long telephone, String email, String mdp, String img, String profile) {
        this.id = id;
        this.vehId = vehId;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.mdp = mdp;
        this.img = img;
        this.profile = profile;
    }

    public Utilisateur() {
        this.id = id;
    }

    public Utilisateur(int id) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehId() {
        return vehId;
    }

    public void setVehId(int vehId) {
        this.vehId = vehId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Timestamp getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Timestamp dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
