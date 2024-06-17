package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.data.CommuneBase;
import model.data.Departement;
import model.data.Gare;

public class CommuneBaseDAO extends DAO<CommuneBase> {

    Map<String, Departement> tousDepartements;
    Map<String, ArrayList<Gare>> toutesGares;
    Map<String, CommuneBase> toutesCommunes;

    /**
     * &nbsp;
     */

    public CommuneBaseDAO(String username, String password, Map<String, Departement> departements,
            Map<String, ArrayList<Gare>> gares) {
        super(username, password);
        if (departements == null) {
            throw new IllegalArgumentException("departements cannot be null");
        }
        if (gares == null) {
            throw new IllegalArgumentException("gares cannot be null");
        }

        this.tousDepartements = departements;
        this.toutesGares = gares;
        this.toutesCommunes = new HashMap<>();
    }

    public CommuneBaseDAO(Map<String, Departement> departements, Map<String, ArrayList<Gare>> gares) {
        super();
        if (departements == null) {
            throw new IllegalArgumentException("departements cannot be null");
        }
        if (gares == null) {
            throw new IllegalArgumentException("gares cannot be null");
        }

        this.tousDepartements = departements;
        this.toutesGares = gares;
        this.toutesCommunes = new HashMap<>();
    }

    /**
     * Trouver toutes les communes
     * 
     * @return la liste des communes
     */
    public Map<String, CommuneBase> findAll() {
        Map<String, CommuneBase> communes = new HashMap<>();

        // First pass: create all CommuneBase objects without setting their neighbors
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT idCommune, nomCommune, leDepartement FROM Commune")) {
            while (resultSet.next()) {
                Long idCommune = resultSet.getLong("idCommune");
                String nomCommune = resultSet.getString("nomCommune");
                Long departement = resultSet.getLong("leDepartement");
                Departement leDepartement = tousDepartements.get(String.valueOf(departement));
                ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                ArrayList<Gare> lesGares = this.toutesGares.get(String.valueOf(idCommune.intValue()));
                CommuneBase commune = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins,
                        lesGares);
                communes.put(String.valueOf(idCommune), commune);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Second pass: set the neighbors for each CommuneBase
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT commune, communeVoisine FROM Voisinage")) {
            while (resultSet.next()) {
                Long communeId = resultSet.getLong("commune");
                Long voisinId = resultSet.getLong("communeVoisine");

                CommuneBase commune = communes.get(String.valueOf(communeId));
                CommuneBase voisin = communes.get(String.valueOf(voisinId));

                if (commune != null && voisin != null) {
                    commune.addNeighbor(voisin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return communes;
    }

    /**
     * Trouver une commune par son ID
     * 
     * @param idCommune     l'ID de la commune
     * @param loadNeighbors si les voisins doivent être chargés
     * @return la commune
     */
    public CommuneBase findByID(Long idCommune, boolean loadNeighbors) {
        CommuneBase communeRet = null;

        // First pass: create all CommuneBase objects without setting their neighbors
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT nomCommune, leDepartement FROM Commune WHERE idCommune = ?")) {
            statement.setLong(1, idCommune);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nomCommune = resultSet.getString("nomCommune");
                    Long idDepartement = resultSet.getLong("leDepartement");
                    Departement leDepartement = tousDepartements.get(String.valueOf(idDepartement));
                    ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                    List<Gare> lesGares = new GareDAO().findByCommuneID(idCommune.intValue());

                    communeRet = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins, lesGares);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Second pass: set the neighbors for each CommuneBase
        if (loadNeighbors && communeRet != null) {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection
                            .prepareStatement("SELECT communeVoisine FROM Voisinage WHERE commune = ?")) {
                statement.setLong(1, idCommune);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Long communeVoisine = resultSet.getLong("communeVoisine");
                        CommuneBase voisin = findByID(communeVoisine, false);
                        communeRet.addNeighbor(voisin);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return communeRet;
    }

    /**
     * Trouver une commune par son nom
     * 
     * @param name le nom de la commune
     * @return la commune
     */
    public CommuneBase findByName(String name, boolean loadNeighbors) {
        CommuneBase communeRet = null;

        // First pass: create the CommuneBase object without setting its neighbors
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT idCommune, nomCommune FROM Commune WHERE nomCommune = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long idCommune = resultSet.getLong("idCommune");
                    String nomCommune = resultSet.getString("nomCommune");
                    Departement leDepartement = this.tousDepartements
                            .get(String.valueOf(resultSet.getLong("leDepartement")));
                    ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                    List<Gare> lesGares = new GareDAO().findByCommuneID(idCommune.intValue());

                    communeRet = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins, lesGares);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Second pass: set the neighbors for the CommuneBase
        if (loadNeighbors && communeRet != null) {
            try (Connection connection = getConnection();
                    PreparedStatement statement = connection
                            .prepareStatement("SELECT communeVoisine FROM Voisinage WHERE commune = ?")) {
                statement.setLong(1, communeRet.getIdCommune());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Long communeVoisine = resultSet.getLong("communeVoisine");
                        CommuneBase voisin = findByID(communeVoisine, false);
                        communeRet.addNeighbor(voisin);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return communeRet;
    }

    /**
     * Mettre à jour une commune
     * 
     * @param commune la commune à mettre à jour
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(CommuneBase commune) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("UPDATE Commune SET nomCommune = ?, leDepartement = ? WHERE idCommune = ?")) {
            statement.setString(1, commune.getNomCommune());
            statement.setLong(2, commune.getLeDepartement().getIdDep());
            statement.setLong(3, commune.getIdCommune());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Supprimer une commune
     * 
     * @param commune la commune à supprimer
     * @return le nombre de lignes supprimées
     */
    @Override
    public int delete(CommuneBase commune) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Commune WHERE idCommune = ?")) {
            statement.setLong(1, commune.getIdCommune());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Créer une commune
     * 
     * @param commune la commune à créer
     * @return le nombre de lignes créées
     */
    @Override
    public int create(CommuneBase commune) {
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (?, ?, ?)")) {
            statement.setLong(1, commune.getIdCommune());
            statement.setString(2, commune.getNomCommune());
            statement.setLong(3, commune.getLeDepartement().getIdDep());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public CommuneBase findByID(Long idCommune) {
        return findByID(idCommune, true);
    }
}
