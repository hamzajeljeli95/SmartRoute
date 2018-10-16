package com.example.nawras.smartroute.Beans;

import java.sql.Timestamp;

public class Message {
    private int id;
    private int idUtilEnvoyant;
    private int idUtilEnvoyee;
    private Timestamp dateHeure;
    private String msg;

    public Message(int id, int idUtilEnvoyant, int idUtilEnvoyee, Timestamp dateHeure, String msg) {
        this.id = id;
        this.idUtilEnvoyant = idUtilEnvoyant;
        this.idUtilEnvoyee = idUtilEnvoyee;
        this.dateHeure = dateHeure;
        this.msg = msg;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilEnvoyant() {
        return idUtilEnvoyant;
    }

    public void setIdUtilEnvoyant(int idUtilEnvoyant) {
        this.idUtilEnvoyant = idUtilEnvoyant;
    }

    public int getIdUtilEnvoyee() {
        return idUtilEnvoyee;
    }

    public void setIdUtilEnvoyee(int idUtilEnvoyee) {
        this.idUtilEnvoyee = idUtilEnvoyee;
    }

    public Timestamp getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
