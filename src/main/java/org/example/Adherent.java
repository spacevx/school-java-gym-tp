package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private int id;
    private String nom;
    private Abonnement abonnement;
    private List<Reservation> reservations;

    public Adherent(int id, String nom, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.abonnement = abonnement;
        this.reservations = new ArrayList<>();
    }

    public Adherent(int id, String nom) {
        this(id, nom, null);
    }

    public void reserver(Seance s) {
        reservations.add(new Reservation(s));
    }

    public double depensesTotales() {
        double total = 0;
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE) {
                total += r.coutPrestation();
            }
        }
        return total;
    }

    public List<Reservation> reservationsFutures() {
        List<Reservation> futures = new ArrayList<>();
        LocalDateTime maintenant = LocalDateTime.now();
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE
                    && r.getSeance().getDateHeure().isAfter(maintenant)) {
                futures.add(r);
            }
        }
        return futures;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Abonnement getAbonnement() { return abonnement; }
    public List<Reservation> getReservations() { return reservations; }

    @Override
    public String toString() {
        return String.format("Adherent{id=%d, nom='%s', abonnement=%s}", id, nom, abonnement);
    }
}
