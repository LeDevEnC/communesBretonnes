package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public AeroportDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Trouver tous les aéroports
     */
    public Map<String, ArrayList<Aeroport>> findAll() {
        Map<String, ArrayList<Aeroport>> aeroports = new HashMap<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT nom, adresse, leDepartement FROM Aeroport");
                ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String nom = rs.getString("nom");
                String adresse = rs.getString("adresse");
                int dep = rs.getInt("leDepartement");
                Aeroport aeroport = new Aeroport(nom, adresse);
                if (!aeroports.containsKey(String.valueOf(dep))) {
                    aeroports.put(String.valueOf(dep), new ArrayList<>());
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
    public Map<Integer, Aeroport> findByName(String name) {
        Aeroport aeroport;
        Map<Integer, Aeroport> aeroports = new HashMap<>();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT nom, adresse, leDepartement FROM Aeroport WHERE nom = ?")) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String nom = rs.getString("nom");
                    String adresse = rs.getString("adresse");
                    aeroport = new Aeroport(nom, adresse);
                    Integer dep = rs.getInt("leDepartement");
                    aeroports.put(dep, aeroport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aeroports;
    }

    /**
     * Mettre à jour un aéroport
     * 
     * @param aeroport un aéroport
     * @return le nombre de lignes modifiées
     */
    public int update(Aeroport aeroport, int id) {
        int rowsUpdated = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE Aeroport SET adresse = ?, leDepartement = ? WHERE nom = ?")) {
            statement.setString(1, aeroport.getAdresse());
            statement.setInt(2, id);
            statement.setString(3, aeroport.getNom());
            System.out.println(statement);
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }

    public int update(Aeroport aeroport) {
        throw new UnsupportedOperationException("Not supported yet.");
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
                        .prepareStatement("DELETE FROM Aeroport WHERE nom = ?")) {
            statement.setString(1, aeroport.getNom());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsDeleted;
    }

    /**
     * Supprimer un aéroport en prennant en compte la commune
     * 
     * @param aeroport un aéroport
     * @return le nombre de lignes supprimées
     */
    public int delete(Aeroport aeroport, int idDep) {
        int rowsDeleted = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM Aeroport WHERE nom = ? AND leDepartement = ?")) {
            statement.setString(1, aeroport.getNom());
            statement.setInt(2, idDep);
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
     * @param aeroport l'aéroport
     */
    @Override
    public int create(Aeroport aeroport) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Méthode de recherche non implémentée car l'objet aéroport ne dispose pas
     * d'attribut long et la table aéroport n'a pas d'attribut long
     * @param id l'id
     */
    @Override
    public Aeroport findByID(Long id) {
        throw new UnsupportedOperationException("No long values in Aeroport table");
    }

}
