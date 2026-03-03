package org.example;

public class Prestation {
    private String code;
    private String libelle;
    private double prix;

    public Prestation() {
        this.code = "";
        this.libelle = "";
        this.prix = 0.0;
    }

    public Prestation(String code, String libelle, double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getCode() {
        return this.code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public double getPrix() {
        return this.prix;
    }

    public String toString() {
        String message = "La prestation avec le code %s et le libelle %s coute %d";
        return String.format(message,  this.code, this.libelle, this.prix);
    }
}
