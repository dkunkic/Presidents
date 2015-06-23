package com.example.dijana.presidentproject.models;

import java.io.Serializable;

/**
 * Created by Dijana on 15.6.2015..
 */
public class President implements Serializable {

    private int president_id;
    private String name;
    private String email;
    private String photo_url;
    private String years;

    public int getPresidentId() {
        return president_id;
    }

    public void setPresidentId(int presidentId) {
        this.president_id = presidentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
