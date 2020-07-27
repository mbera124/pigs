package com.example.njoro.myproject.model;

import java.util.List;

public class Pigs {
    private String ImageURL;
    private String Contact;
    private String Price;
    private List<Pigs> pigsList;
    public Pigs() {
    }

    public Pigs(String imageURL, String contact, String price) {
        ImageURL = imageURL;
        Contact = contact;
        Price = price;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public List<Pigs> getPigsList() {
        return pigsList;
    }

    public void setPigsList(List<Pigs> pigsList) {
        this.pigsList = pigsList;
    }
}
