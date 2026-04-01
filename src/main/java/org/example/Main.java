package org.example;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        Prestation sauna    = new Prestation("SAUNA",    "Accès sauna",      5.0);
        Prestation coach    = new Prestation("COACH",    "Séance coaching",  25.0);
        Prestation serviette = new Prestation("SERVIETTE", "Location serviette", 2.0);

        Seance s1 = new Seance(1, "Yoga",       LocalDateTime.now().plusDays(3),  15);
        Seance s2 = new Seance(2, "CrossFit",   LocalDateTime.now().plusDays(7),  10);
        Seance s3 = new Seance(3, "Natation",   LocalDateTime.now().minusDays(1),  20);

        Abonnement basic   = new AbonnementBasic("REF-B01", LocalDate.now(), 3, 20.0);
        Abonnement premium = new AbonnementPremium("REF-P01", LocalDate.now(), 6, 45.0, 4);

        Adherent alice = new Adherent(1, "Alice", basic);
        Adherent bob   = new Adherent(2, "Bob",   premium);

        alice.reserver(s1);
        alice.getReservations().get(0).ajouterPrestation(serviette);
        alice.reserver(s3);
        alice.getReservations().get(1).ajouterPrestation(sauna);
        alice.getReservations().get(1).annuler();   // annulation

        bob.reserver(s1);
        bob.getReservations().get(0).ajouterPrestation(coach);
        bob.getReservations().get(0).ajouterPrestation(sauna);

        bob.reserver(s2);
        bob.getReservations().get(1).ajouterPrestation(coach);

        SalleDeSport salle = new SalleDeSport();
        salle.ajouterAdherent(alice);
        salle.ajouterAdherent(bob);
        salle.ajouterSeance(s1);
        salle.ajouterSeance(s2);
        salle.ajouterSeance(s3);

        System.out.println("Adhérents");
        System.out.println(alice);
        System.out.println(bob);

        System.out.println("\nRéservations futures d'Alice");
        List<Reservation> futuresAlice = alice.reservationsFutures();
        if (futuresAlice.isEmpty()) {
            System.out.println("Aucune réservation future.");
        } else {
            futuresAlice.forEach(System.out::println);
        }

        System.out.println("\nAdhérents avec accès sauna");
        salle.adherentsAvecSauna().forEach(a -> System.out.println(a.getNom()));

        System.out.printf("%nChiffre d'affaires prestations : %.2f€ ===%n",
                salle.chiffreAffairesPrestations());

        System.out.println("\nRecherche adhérent id=2");
        System.out.println(salle.trouverAdherent(2).getNom());

        System.out.println("\nRecherche adhérent id=99 (exception attendue)");
        try {
            salle.trouverAdherent(99);
        } catch (IllegalArgumentException e) {
            System.out.println("Exception : " + e.getMessage());
        }

        /* TP DAO */

        AdherentDAO dao = new AdherentDAO();

        dao.inserer(new Adherent(1, "Alice"));
        dao.inserer(new Adherent(2, "Bob"));

        System.out.println("Liste des adhérents");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }

        System.out.println("\nModif de Bob");
        dao.modifier(new Adherent(2, "Robert"));

        System.out.println("\nListe après modif");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }

        System.out.println("\nSuppr de Alice");
        dao.supprimer(1);

        System.out.println("\nListe finale");
        for (Adherent a : dao.lister()) {
            System.out.println(a.getId() + " - " + a.getNom());
        }
    }
}
