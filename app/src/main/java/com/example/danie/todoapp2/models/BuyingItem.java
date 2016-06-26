package com.example.danie.todoapp2.models;

/**
 * Created by danie on 25/06/2016.
 */
public class BuyingItem {
    private String name;
    private String description;
    private int amount;
    private Boolean isDone;

    public BuyingItem(String name, String description, int amount, Boolean isDone) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.isDone = isDone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }
}
