package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util .*;
import model.data.Annee;
/**
 * Classe DAO pour les années
 */
public class AnneeDAO extends DAO<Annee>{
    /**
     * &nbsp;
     */
    public AnneeDAO() {
    }

    /**
     * Trouver tous les années
     * @return la liste des années
     */
    @Override
    public List<Annee> findAll() {
        List<Annee> annees = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Annee")) {
            while (resultSet.next()) {
                annees.add(new Annee(resultSet.getInt("annee"), resultSet.getDouble("tauxInflation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }       

        return annees;
    }

    /**
     * Trouver une année par son identifiant
     * @param annee une année
     * @return l'année
     */
    @Override
    public Annee findByID(Long annee) {
        Annee anneeRet = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Annee WHERE annee = ?")) {
            statement.setLong(1, annee);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    anneeRet = new Annee(resultSet.getInt("annee"), resultSet.getDouble("tauxInflation"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return anneeRet;
    }

    /**
     * Mettre à jour une année
     * @param annee une année
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Annee SET tauxInflation = ? WHERE annee = ?")) {
            statement.setDouble(1, annee.getTauxInflation());
            statement.setInt(2, annee.getAnnee());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;
    }

    /**
     * Supprimer une année
     * @param annee une année
     * @return le nombre de lignes supprimées
     */
    @Override
    public int delete(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Annee WHERE annee = ?")) {
            statement.setInt(1, annee.getAnnee());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Créer une année
     * @param annee une année
     * @return l'identifiant de l'année créée
     */
    @Override
    public int create(Annee annee) {
        int result = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Annee (annee, tauxInflation) VALUES (?, ?)")) {
            statement.setInt(1, annee.getAnnee());
            statement.setDouble(2, annee.getTauxInflation());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    
}