package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Seance {
    private int id;
    private String nom;
    private LocalDateTime dateHeure;
    private int capaciteMax;

    public Seance() {
        this.id = 0;
        this.nom = "";
        this.dateHeure = LocalDateTime.now();
        this.capaciteMax = 0;
        this.capaciteMax = 0;
    }

    public Seance(int id, String nom,  LocalDateTime dateHeure, int capaciteMax) {
        this.id = id;
        this.nom = nom;
        this.dateHeure = dateHeure;
        this.capaciteMax = capaciteMax;
    }

    public int getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public LocalDateTime getDateHeure() {
        return this.dateHeure;
    }

    public int getCapaciteMax() {
        return this.capaciteMax;
    }

    public String toString() {
        return String.format("La séance %s (id: %d) à %s posséde une capacité de %d personnes",
                this.nom,
                this.id,
                this.dateHeure.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.capaciteMax
        );
    }
}
