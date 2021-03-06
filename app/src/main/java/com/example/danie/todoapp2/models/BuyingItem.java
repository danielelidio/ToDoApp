package com.example.danie.todoapp2.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by danie on 25/06/2016.
 */
public class BuyingItem {
    private String name;
    private String description;
    private float amount;
    private Greatness greatness;

    public BuyingItem() {
        this.setGreatness(Greatness.KILOGRAM);
    }

    public BuyingItem(String name, String description, int amount) {
        this(name, description, amount, Greatness.KILOGRAM);
    }

    public BuyingItem(String name, String description, int amount, Greatness greatness) {
        this.setName(name);
        this.setDescription(description);
        this.setAmount(amount);
        this.setGreatness(greatness);
    }

    public static BuyingItem fromJSON(JSONObject json) throws JSONException {
        BuyingItem buyingItem = new BuyingItem();
        if (json.has("name"))
            buyingItem.setName(json.getString("name"));
        if (json.has("description"))
            buyingItem.setDescription(json.getString("description"));
        if (json.has("amount"))
            buyingItem.setAmount(new Float(json.getString("amount")));
        if (json.has("greatness"))
            buyingItem.setGreatness(Greatness.getFromString(json.getString("greatness")));

        return buyingItem;
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
        if (this.getGreatness() == Greatness.KILOGRAM && this.getAmount() < 1.0f) {
            return Greatness.getShortValue(Greatness.GRAM, this.getAmount() * 1000 > 1);
        } else {
            return Greatness.getShortValue(this.getGreatness(), this.getAmount() > 1);
        }
    }

    public String getAmountWithGreatness() {
        String str = "";
        if (this.getGreatness() == Greatness.KILOGRAM || this.getGreatness() == Greatness.LITERS) {
            float amount = this.getAmount();
            if (this.getGreatness() == Greatness.KILOGRAM) {
                if (amount < 1.0f) {
                    amount *= 1000;
                    str += String.format("%.0f", amount);
                } else {
                    str += String.format("%.3f", amount);
                }
            } else {
                str += String.format("%.2f", amount);
            }
        } else {
            str += String.format("%.0f", this.getAmount());
        }

        return str + " " + this.getShortGreatness();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name", this.getName());
        json.put("amount", this.getAmount());
        json.put("greatness", this.getGreatness().toString());

        return json;
    }
}
