package com.example.nawras.smartroute.Beans;



public class Modele {
    String model_name;
    String model_make_id;

    public Modele(String model_name, String model_make_id) {
        this.model_name = model_name;
        this.model_make_id = model_make_id;
    }

    public Modele() {
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public String getModel_make_id() {
        return model_make_id;
    }

    public void setModel_make_id(String model_make_id) {
        this.model_make_id = model_make_id;
    }
}
