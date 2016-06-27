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
        if (greatness.compareTo("KILOGRAM") == 0)
            return Greatness.KILOGRAM;
        else if (greatness.compareTo("LITERS") == 0)
            return Greatness.LITERS;
        else if (greatness.compareTo("POT") == 0)
            return Greatness.POT;
        else if (greatness.compareTo("PACKAGE") == 0)
            return Greatness.PACKAGE;
        else
            return null;
    }

    public static String getValue(Greatness greatness, boolean pural) {
        if (greatness == Greatness.KILOGRAM) {
            return (pural) ? "Kilos" : "Kilo";
        } else if (greatness == Greatness.PACKAGE) {
            return (pural) ? "Pacotes" : "Pacote";
        } else if (greatness == Greatness.POT) {
            return (pural) ? "Latas" : "Lata";
        } else if (greatness == Greatness.LITERS) {
            return (pural) ? "Litros" : "Litro";
        } else {
            return "";
        }
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
