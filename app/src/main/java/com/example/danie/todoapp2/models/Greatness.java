package com.example.danie.todoapp2.models;

/**
 * Created by danie on 27/06/2016.
 */
public enum Greatness {
    PACKAGE,
    POT,
    KILOGRAM,
    LITERS;

    public static Greatness getFromString(String greatness) {
        if (greatness == "KILOGRAM")
            return Greatness.KILOGRAM;
        else if (greatness == "LITERS")
            return Greatness.LITERS;
        else if (greatness == "POT")
            return Greatness.POT;
        else if (greatness == "PACKAGE")
            return Greatness.PACKAGE;
        else
            return null;
    }

    public static String getShortValue(Greatness greatness, boolean pural) {
        if (greatness == Greatness.KILOGRAM) {
            return (pural) ? "Kg" : "Kg";
        } else if (greatness == Greatness.PACKAGE) {
            return (pural) ? "Pct's" : "Pct";
        } else if (greatness == Greatness.POT) {
            return (pural) ? "Latas" : "Lata";
        } else if (greatness == Greatness.LITERS) {
            return (pural) ? "Lt's" : "Lt";
        } else {
            return "";
        }
    }
}
