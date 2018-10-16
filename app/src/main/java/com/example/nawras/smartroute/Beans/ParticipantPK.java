package com.example.nawras.smartroute.Beans;

import java.io.Serializable;
import java.util.Objects;

public class ParticipantPK implements Serializable {
    private int utilId;
    private int covId;

    public ParticipantPK(int utilId, int covId) {
        this.utilId = utilId;
        this.covId = covId;
    }

    public ParticipantPK() {
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
}
