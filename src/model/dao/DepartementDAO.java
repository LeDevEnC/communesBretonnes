package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.data.Departement;
/**
 * Classe DAO pour les départements
 */
public class DepartementDAO extends DAO<Departement> {
    /**
     * &nbsp;
     */
    public DepartementDAO() {
    }

    /**
     * Trouver tous les départements
     * @return la liste des départements
     */
    @Override
    public ArrayList<Departement> findAll() {
        ArrayList<Departement> departements = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Departement");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idDep = resultSet.getInt("idDep");
                String nomDep = resultSet.getString("nomDep");
                long invesCulturel = resultSet.getLong("investissementCulturel2019");
                Departement x = new Departement(idDep, nomDep, invesCulturel);
                departements.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departements;
    }

    /**
     * Trouver un département par son identifiant
     * @param idDep un identifiant de département
     * @return le département
     */
    @Override
    public Departement findByID(Long idDep) {
        Departement departement = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Departement WHERE idDep = ?")) {
            statement.setLong(1, idDep);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    departement = new Departement(resultSet.getInt("idDep"), resultSet.getString("nomDep"), resultSet.getLong("investissementCulturel2019"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }

    /**
     * Mettre à jour un département
     * @param departement un département
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(Departement departement) {
        int rowsUpdated = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Departement SET nomDep = ?, investissementCulturel2019 = ? WHERE idDep = ?")) {
            statement.setString(1, departement.getNomDep());
            statement.setLong(2, departement.getInvestCulturel2019());
            statement.setLong(3, departement.getIdDep());
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsUpdated;
    }

    /**
     * Supprimer un département
     * @param departement un département
     * @return le nombre de lignes supprimées
     */
    @Override
    public int delete(Departement departement) {
        int rowsDeleted = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Departement WHERE idDep = ?")) {
            statement.setLong(1, departement.getIdDep());
            rowsDeleted = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowsDeleted;
    }

    /**
     * Créer un département
     * @param departement un département
     * @return l'identifiant du département créé
     */
    @Override
    public int create(Departement departement) {
        int id = 0;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, departement.getIdDep());
            statement.setString(2, departement.getNomDep());
            statement.setLong(3, departement.getInvestCulturel2019());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    id = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    /**
     * Trouver un département par son nom
     * @param name le nom du département
     * @return le département
     */
    
    @Override
    public Departement findByName(String name) {
        Departement departement = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Departement WHERE nomDep = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    departement = new Departement(resultSet.getInt("idDep"), resultSet.getString("nomDep"), resultSet.getLong("investissementCulturel2019"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }


}
