package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.Aeroport;

/**
 * Classe DAO pour les aéroports
 */
public class AeroportDAO extends DAO<Aeroport> {

    /**
     * &nbsp;
     */
    public AeroportDAO() {
        super();
    }

    public AeroportDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Trouver tous les aéroports
     */
    public HashMap<String, ArrayList<Aeroport>> findAll() {
        HashMap<String, ArrayList<Aeroport>> aeroports = new HashMap<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Aeroport");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                int dep = rs.getInt("leDepartement");
                Aeroport aeroport = new Aeroport(nom, adresse);
                if (!aeroports.containsKey(String.valueOf(dep))) {
                    aeroports.put(String.valueOf(dep), new ArrayList<Aeroport>());
                }
                aeroports.get(String.valueOf(dep)).add(aeroport);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aeroports;
    }

    /**
     * Trouver un aéroport par son nom
     * 
     * @param name le nom de l'aéroport
     * @return l'aéroport
     */
    public Aeroport findByName(String name) {
        Aeroport aeroport = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Aeroport WHERE nom = ?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String adresse = rs.getString("adresse");
                    aeroport = new Aeroport(nom, adresse);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeroport;
    }

    /**
     * Mettre à jour un aéroport
     * 
     * @param aeroport un aéroport
     * @return le nombre de lignes modifiées
     */
    @Override
    public int update(Aeroport aeroport) { // JE SAIS PAS QUOI UPDATE PUTAIN DE MERDE NIQUEZ VOS MERES
        int rowsUpdated = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELTE * FROM * FROM XOR")) {
            statement.setString(1, aeroport.getNom());
            statement.setString(2, aeroport.getAdresse());

            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    /**
     * Supprimer un aéroport
     * 
     * @param aeroport un aéroport
     * @return le nombre de lignes supprimées
     */
    @Override
    public int delete(Aeroport aeroport) {
        int rowsDeleted = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM Aeroport WHERE nom = ? AND adresse = ?")) {
            statement.setString(1, aeroport.getNom());
            statement.setString(2, aeroport.getAdresse());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    /**
     * Créer un aéroport
     * 
     * @param aeroport      un aéroport
     * @param leDepartement un département
     * @return le nombre de lignes créées
     */
    public int create(Aeroport aeroport, int leDepartement) {
        int rowsCreated = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO Aeroport (nom, adresse, leDepartement) VALUES (?, ?, ?)")) {
            statement.setString(1, aeroport.getNom());
            statement.setString(2, aeroport.getAdresse());
            statement.setInt(3, leDepartement);
            rowsCreated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsCreated;
    }

    /**
     * Méthode de création non implémentée car l'objet aéroport ne dispose pas
     * d'attribut département et la table aéroport a un attribut département
     */
    @Override
    public int create(Aeroport aeroport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode de recherche non implémentée car l'objet aéroport ne dispose pas
     * d'attribut long et la table aéroport n'a pas d'attribut long
     */
    @Override
    public Aeroport findByID(Long id) {
        throw new UnsupportedOperationException("No long values in Aeroport table");
    }

}
