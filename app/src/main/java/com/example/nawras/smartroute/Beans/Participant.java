package com.example.nawras.smartroute.Beans;


public class Participant {
    private int utilId;
    private int covId;
    private Byte estAccepte;

    public Participant(int utilId, int covId, Byte estAccepte) {
        this.utilId = utilId;
        this.covId = covId;
        this.estAccepte = estAccepte;
    }

    public Participant() {
    }

    public int getUtilId() {
        return utilId;
    }

    public void setUtilId(int utilId) {
        this.utilId = utilId;
    }

    public int getCovId() {
        return covId;
    }

    public void setCovId(int covId) {
        this.covId = covId;
    }

    public Byte getEstAccepte() {
        return estAccepte;
    }

    public void setEstAccepte(Byte estAccepte) {
        this.estAccepte = estAccepte;
    }
}
