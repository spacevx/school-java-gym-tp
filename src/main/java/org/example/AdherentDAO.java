package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {

    public void inserer(Adherent adherent) throws SQLException {
        String sql = "INSERT INTO adherent(id, nom) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, adherent.getId());
            ps.setString(2, adherent.getNom());
            ps.executeUpdate();
        }
    }

    public List<Adherent> lister() throws SQLException {
        List<Adherent> adherents = new ArrayList<>();
        String sql = "SELECT * FROM adherent";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                adherents.add(new Adherent(rs.getInt("id"), rs.getString("nom")));
            }
        }
        return adherents;
    }

    public void modifier(Adherent adherent) throws SQLException {
        String sql = "UPDATE adherent SET nom = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, adherent.getNom());
            ps.setInt(2, adherent.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM adherent WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Adherent trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM adherent WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Adherent(rs.getInt("id"), rs.getString("nom"));
                }
            }
        }
        return null;
    }
}
