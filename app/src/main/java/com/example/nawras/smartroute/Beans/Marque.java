package com.example.nawras.smartroute.Beans;

/**
 * Created by Hamza on 08/03/2018.
 */

public class Marque {
    String make_id;
    String make_display;
    String make_is_common;
    String make_country;

    public Marque(String make_id, String make_display, String make_is_common, String make_country) {
        this.make_id = make_id;
        this.make_display = make_display;
        this.make_is_common = make_is_common;
        this.make_country = make_country;
    }

    public Marque() {
    }

    public String getMake_id() {
        return make_id;
    }

    public void setMake_id(String make_id) {
        this.make_id = make_id;
    }

    public String getMake_display() {
        return make_display;
    }

    public void setMake_display(String make_display) {
        this.make_display = make_display;
    }

    public String getMake_is_common() {
        return make_is_common;
    }

    public void setMake_is_common(String make_is_common) {
        this.make_is_common = make_is_common;
    }

    public String getMake_country() {
        return make_country;
    }

    public void setMake_country(String make_country) {
        this.make_country = make_country;
    }
}
