package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.data.Gare;

/**
 * Classe DAO pour les gares
 */
public class GareDAO extends DAO<Gare> {
    /**
     * &nbsp;
     */
    public GareDAO() {
    }

    /**
     * Trouver toutes les gares
     * 
     * @return la liste des gares
     */
    @Override
    public ArrayList<Gare> findAll() {
        ArrayList<Gare> gares = new ArrayList<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Gare")) {
            while (resultSet.next()) {
                gares.add(new Gare(resultSet.getInt("codeGare"), resultSet.getString("nomGare"),
                        resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gares;
    }

    /**
     * Trouver une gare par son identifiant
     * 
     * @param codeGare un code de gare
     * @return la gare
     */
    @Override
    public Gare findByID(Long codeGare) {
        Gare gareRet = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Gare WHERE codeGare = ?")) {
            statement.setLong(1, codeGare);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gareRet = new Gare(resultSet.getInt("codeGare"), resultSet.getString("nomGare"),
                            resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gareRet;
    }

    /**
     * Trouver les gares d'une commune
     * 
     * @param idCommune un identifiant de commune
     * @return la liste des gares
     */
    public ArrayList<Gare> findByCommuneID(int idCommune) {
        ArrayList<Gare> gares = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Gare WHERE laCommune = ?")) {
            statement.setInt(1, idCommune);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Gare gare = new Gare(resultSet.getInt("codeGare"), resultSet.getString("nomGare"),
                            resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                    gares.add(gare);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gares;
    }

    /**
     * Trouver une gare par son nom
     * 
     * @param nomGare un nom de gare
     * @return la gare
     */
    public Gare findByName(String nomGare) {
        Gare gareRet = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Gare WHERE nomGare = ?")) {
            statement.setString(1, nomGare);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gareRet = new Gare(resultSet.getInt("codeGare"), resultSet.getString("nomGare"),
                            resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gareRet;
    }

    /**
     * Mettre à jour une gare
     * 
     * @param gare la gare
     */
    @Override
    public int update(Gare gare) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Gare SET nomGare = ?, estFret = ?, estVoyageur = ? , estFret = ? WHERE codeGare = ?")) {
            statement.setString(1, gare.getNomGare());
            statement.setBoolean(2, gare.getEstFret());
            statement.setBoolean(3, gare.getEstVoyageur());
            statement.setBoolean(4, gare.getEstFret());
            statement.setInt(5, gare.getCodeGare());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Supprimer une gare
     * 
     * @param gare la gare
     */
    @Override
    public int delete(Gare gare) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Gare WHERE codeGare = ?")) {
            statement.setInt(1, gare.getCodeGare());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Créer une gare
     * 
     * @param gare la gare
     */
    @Override
    public int create(Gare gare) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur) VALUES (?, ?, ?, ?)")) {
            statement.setInt(1, gare.getCodeGare());
            statement.setString(2, gare.getNomGare());
            statement.setBoolean(3, gare.getEstFret());
            statement.setBoolean(4, gare.getEstVoyageur());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
