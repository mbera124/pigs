package com.example.njoro.myproject.model;

import java.util.List;

public class Expenses {
    private String Date;
    private String Type;
    private String Amount;
    private List<Expenses> expensesList;

    public Expenses() {
    }

    public Expenses(String userName, String s, String toString, String string) {
    }

    public Expenses(String date, String type, String amount) {
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

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }
}
