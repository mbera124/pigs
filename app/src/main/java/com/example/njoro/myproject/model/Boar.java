package com.example.njoro.myproject.model;

import java.util.ArrayList;
import java.util.List;

public  class Boar {
    private String Name;
    private String Breed;
    private String Color;
    private String Age;
    private String Weight;
    private String ServiceRecord;
    private String MatingSow;
    private String MatingDate;
    private String RemainingDays;
    private String PigId;
    private List<Boar> boarList;

    public Boar() {
    }

    public Boar(String name, String userName, String s, String toString, String string, String s1, String toString1, String string1, String s2) {
    }

    public Boar(String name, String breed, String color, String age, String weight, String serviceRecord, String matingSow, String matingDate, String remainingDays, String pigId) {
        Name = name;
        Breed = breed;
        Color = color;
        Age = age;
        Weight = weight;
        ServiceRecord = serviceRecord;
        MatingSow = matingSow;
        MatingDate = matingDate;
        RemainingDays = remainingDays;
        PigId = pigId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getServiceRecord() {
        return ServiceRecord;
    }

    public void setServiceRecord(String serviceRecord) {
        ServiceRecord = serviceRecord;
    }

    public String getMatingSow() {
        return MatingSow;
    }

    public void setMatingSow(String matingSow) {
        MatingSow = matingSow;
    }

    public String getMatingDate() {
        return MatingDate;
    }

    public void setMatingDate(String matingDate) {
        MatingDate = matingDate;
    }

    public String getRemainingDays() {
        return RemainingDays;
    }

    public void setRemainingDays(String remainingDays) {
        RemainingDays = remainingDays;
    }

    public String getPigId() {
        return PigId;
    }

    public void setPigId(String pigId) {
        PigId = pigId;
    }

    public List<Boar> getBoarList() {
        return boarList;
    }

    public void setBoarList(List<Boar> boarList) {
        this.boarList = boarList;
    }
}
