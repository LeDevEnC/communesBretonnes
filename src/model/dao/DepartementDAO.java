package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import model.data.Aeroport;
import model.data.Departement;

/**
 * Classe DAO pour les départements
 */
public class DepartementDAO extends DAO<Departement> {

    private HashMap<String, ArrayList<Aeroport>> tousAeroport;

    /**
     * &nbsp;
     */

    public DepartementDAO(HashMap<String, ArrayList<Aeroport>> aeroports) {
        super();
        if (aeroports == null) {
            throw new IllegalArgumentException("aeroports cannot be null");
        }
        this.tousAeroport = aeroports;
    }

    public DepartementDAO(String username, String password , HashMap<String, ArrayList<Aeroport>> aeroports) {
        super(username, password);
        if (aeroports == null) {
            throw new IllegalArgumentException("aeroports cannot be null");
        }
        this.tousAeroport = aeroports;
    }
    /**
     * Trouver tous les départements
     * 
     * @return la liste des départements
     */
    public HashMap<String, Departement> findAll() {
        HashMap<String, Departement> departements = new HashMap<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Departement")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long idDep = resultSet.getLong("idDep");
                    ArrayList<Aeroport> aeroportsOfDept = tousAeroport.get(String.valueOf(idDep));
                    int idDepartement = resultSet.getInt("idDep");
                    Departement departement = new Departement(
                            idDepartement, resultSet.getString("nomDep"),
                            resultSet.getLong("investissementCulturel2019"), aeroportsOfDept);
                    departements.put(String.valueOf(idDepartement),departement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departements;
    }

    /**
     * Trouver un département par son identifiant
     * 
     * @param idDep un identifiant de département
     * @return le département
     */
    @Override
    public Departement findByID(Long idDep) {
        Departement departement = null;
        ArrayList<Aeroport> aeroportsOfDept = new ArrayList<Aeroport>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM Aeroport WHERE leDepartement = ?")) {
            statement.setLong(1, idDep);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    aeroportsOfDept = tousAeroport.get(String.valueOf(idDep));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM Departement WHERE idDep = ?")) {
            statement.setLong(1, idDep);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    departement = new Departement(resultSet.getInt("idDep"), resultSet.getString("nomDep"),
                            resultSet.getLong("investissementCulturel2019"), aeroportsOfDept);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departement;
    }

    /**
     * Mettre à jour un département
     * 
     * @param departement un département
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(Departement departement) {
        int rowsUpdated = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Departement SET nomDep = ?, investissementCulturel2019 = ? WHERE idDep = ?")) {
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
     * 
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
     * 
     * @param departement un département
     * @return l'identifiant du département créé
     */
    @Override
    public int create(Departement departement) {
        int id = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Departement (idDep, nomDep, investissementCulturel2019) VALUES (?, ?, ?)",
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

}
