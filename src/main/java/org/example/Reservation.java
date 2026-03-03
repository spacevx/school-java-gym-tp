package org.example;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
    private Seance seance;
    private List<Prestation> prestations;
    private StatutReservation statut;

    public Reservation() {
        this.seance = new Seance();
        this.prestations = new ArrayList<Prestation>();
    }

    public Reservation(Seance seance) {
        this.seance = seance;
        this.statut = StatutReservation.CONFIRMEE;
        this.prestations = new ArrayList<Prestation>();
    }

    public void ajouterPrestation(Prestation prestation) {
        this.prestations.add(prestation);
    }

    public double coutPrestation() {
        double cout = 0;
        for (Prestation p : this.prestations) {
            cout += p.getPrix();
        }
        return cout;
    }

    public Seance getSeance() { return seance; }
    public StatutReservation getStatut() { return statut; }

    public void annuler() {
        this.prestations.clear();
        this.statut = StatutReservation.ANNULEE;
    }

    public String toString() {
        return String.format("Reservation [séance: %s, statut: %s, prestations: %s, coût: %.2f€]",
                this.seance.getNom(),
                this.statut,
                this.prestations,
                this.coutPrestation()
        );
    }
}
