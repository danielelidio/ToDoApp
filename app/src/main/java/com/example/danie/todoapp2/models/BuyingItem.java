package com.example.danie.todoapp2.models;

/**
 * Created by danie on 25/06/2016.
 */
public class BuyingItem {
    private String name;
    private String description;
    private float amount;
    private Greatness greatness;

    public BuyingItem(String name, String description, int amount) {
        this(name, description, amount, Greatness.KILOGRAM);
    }

    public BuyingItem(String name, String description, int amount, Greatness greatness) {
        this.setName(name);
        this.setDescription(description);
        this.setAmount(amount);
        this.setGreatness(greatness);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
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

    public Greatness getGreatness() {
        return greatness;
    }

    public void setGreatness(Greatness greatness) {
        this.greatness = greatness;
    }

    public String getShortGreatness() {
        return Greatness.getShortValue(this.getGreatness(), this.getAmount() > 1);
    }

    public String getAmountWithGreatness() {
        String str = "";
        if (this.getGreatness() == Greatness.KILOGRAM || this.getGreatness() == Greatness.LITERS) {
            str += String.format("%.2f", this.getAmount());
        } else {
            str += String.format("%.0f", this.getAmount());
        }

        return str + " " + this.getShortGreatness();
    }
}
