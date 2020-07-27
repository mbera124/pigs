package com.example.njoro.myproject.model;

import java.util.List;

public class Sales {
    private String Date;
    private String Type;
    private String Amount;
    private List<Sales> salesList;

    public Sales() {
    }

    public Sales(String userName, String s, String toString, String string) {
    }

    public Sales(String date, String type, String amount) {
        Date = date;
        Type = type;
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }
}
