package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import model.data.Annee;

/**
 * Classe DAO pour les années
 */
public class AnneeDAO extends DAO<Annee> {
    /**
     * &nbsp;
     */
    public AnneeDAO() {
        super();
    }

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public AnneeDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Trouver tous les années
     * 
     * @return la liste des années
     */
    public Map<String, Annee> findAll() {
        Map<String, Annee> annees = new HashMap<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT annee, tauxInflation FROM Annee")) {
            while (resultSet.next()) {
                int anneeInt = resultSet.getInt("annee");
                double tauxInflation = resultSet.getDouble("tauxInflation");
                Annee annee = new Annee(anneeInt, tauxInflation);
                annees.put(String.valueOf(anneeInt), annee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return annees;
    }

    /**
     * Trouver une année par son identifiant
     * 
     * @param annee une année
     * @return l'année
     */
    @Override
    public Annee findByID(Long annee) {
        Annee anneeRet = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT annee, tauxInflation FROM Annee WHERE annee = ?")) {
            statement.setLong(1, annee);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int anneeInt = resultSet.getInt("annee");
                    double tauxInflation = resultSet.getDouble("tauxInflation");
                    anneeRet = new Annee(anneeInt, tauxInflation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anneeRet;
    }

    /**
     * Mettre à jour un taux d'inflation d'une année
     * 
     * @param annee une année
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE Annee SET tauxInflation = ? WHERE annee = ?")) {
            statement.setDouble(1, annee.getTauxInflation());
            statement.setInt(2, annee.getAnneeRepr());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

    /**
     * Supprimer une année
     * 
     * @param annee une année
     * @return le nombre de lignes supprimées
     */
    @Override
    public int delete(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Annee WHERE annee = ?")) {
            statement.setInt(1, annee.getAnneeRepr());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Créer une année
     * 
     * @param annee une année
     * @return l'identifiant de l'année créée
     */
    @Override
    public int create(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO Annee (annee, tauxInflation) VALUES (?, ?)")) {
            statement.setInt(1, annee.getAnneeRepr());
            statement.setDouble(2, annee.getTauxInflation());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}