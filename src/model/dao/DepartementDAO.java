package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.data.Aeroport;
import model.data.DepPossibles;
import model.data.Departement;

/**
 * Classe DAO pour les départements
 */
public class DepartementDAO extends DAO<Departement> {

    /**
     * Cache de Tous les aéroports
     */
    private Map<String, ArrayList<Aeroport>> tousAeroport;

    /**
     * &nbsp;
     */

    public DepartementDAO(Map<String, ArrayList<Aeroport>> aeroports) {
        super();
        if (aeroports == null) {
            throw new IllegalArgumentException("aeroports cannot be null");
        }
        this.tousAeroport = aeroports;
    }

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public DepartementDAO(String username, String password, Map<String, ArrayList<Aeroport>> aeroports) {
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
    public Map<String, Departement> findAll() {
        Map<String, Departement> departements = new HashMap<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT idDep, nomDep, investissementCulturel2019 FROM Departement")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idDepartement = resultSet.getInt("idDep");
                    String nomDep = resultSet.getString("nomDep");
                    nomDep = nomDep.replace("-", "_"); // Ile et Vilaine et Cotes d'Armor
                    nomDep = nomDep.replace("'", "_"); // Cotes d'Armor
                    try {

                        DepPossibles nomDepEnum = DepPossibles.valueOf(nomDep);
                        Long investissementCulturel2019 = resultSet.getLong("investissementCulturel2019");
                        ArrayList<Aeroport> aeroportsOfDept = tousAeroport.get(String.valueOf(idDepartement));
                        Departement departement = new Departement(idDepartement, nomDepEnum, investissementCulturel2019,
                                aeroportsOfDept);
                        departements.put(String.valueOf(idDepartement), departement);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Nom de département non reconnu : " + nomDep);
                    }
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
        ArrayList<Aeroport> aeroportsOfDept = tousAeroport.get(String.valueOf(idDep));
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "SELECT nomDep, investissementCulturel2019 FROM Departement WHERE idDep = ?")) {
            statement.setLong(1, idDep);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nomDep = resultSet.getString("nomDep");
                    nomDep = nomDep.replace("-", "_"); // Ile et Vilaine et Cotes d'Armor
                    nomDep = nomDep.replace("'", "_"); // Cotes d'Armor
                    try {
                        DepPossibles nomDepEnum = DepPossibles.valueOf(nomDep);
                        Long investissementCulturel2019 = resultSet.getLong("investissementCulturel2019");
                        departement = new Departement(Math.toIntExact(idDep), nomDepEnum, investissementCulturel2019,
                                aeroportsOfDept);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Nom de département non reconnu : " + nomDep);
                    }
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
                        Statement.RETURN_GENERATED_KEYS)) {
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
