package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util .*;
import model.data.CommuneBase;
import model.data.Departement;
import model.data.Gare;

public class CommuneBaseDAO extends DAO<CommuneBase>{
    /**
     * &nbsp;
     */
    public CommuneBaseDAO() {
        super();
    }

    public CommuneBaseDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Trouver toutes les communes
     * @return la liste des communes
     */
    @Override
    public ArrayList<CommuneBase> findAll() {
        ArrayList<CommuneBase> communes = new ArrayList<>();
        HashMap<Long, CommuneBase> communeMap = new HashMap<>();
    
        // First pass: create all CommuneBase objects without setting their neighbors
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Commune")) {
            while (resultSet.next()) {
                Long idCommune = resultSet.getLong("idCommune");
                String nomCommune = resultSet.getString("nomCommune");
                Departement leDepartement = new DepartementDAO().findByID(resultSet.getLong("leDepartement"));
                ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                ArrayList<Gare> lesGares = new GareDAO().findByCommuneID(idCommune.intValue());
    
                CommuneBase commune = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins, lesGares);
                communes.add(commune);
                communeMap.put(idCommune, commune);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Second pass: set the neighbors for each CommuneBase
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Voisinage")) {
            while (resultSet.next()) {
                Long communeId = resultSet.getLong("commune");
                Long voisinId = resultSet.getLong("communeVoisine");
    
                CommuneBase commune = communeMap.get(communeId);
                CommuneBase voisin = communeMap.get(voisinId);
    
                if (commune != null && voisin != null) {
                    commune.addNeighbor(voisin);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return communes;
    }

    public CommuneBase findByID(Long idCommune, boolean loadNeighbors) {
        CommuneBase communeRet = null;
    
        // First pass: create all CommuneBase objects without setting their neighbors
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Commune WHERE idCommune = ?")) {
            statement.setLong(1, idCommune);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nomCommune = resultSet.getString("nomCommune");
                    Departement leDepartement = new DepartementDAO().findByID(resultSet.getLong("leDepartement"));
                    ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                    ArrayList<Gare> lesGares = new GareDAO().findByCommuneID(idCommune.intValue());
    
                    communeRet = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins, lesGares);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Second pass: set the neighbors for each CommuneBase
        if (loadNeighbors) {
            try(Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Voisinage WHERE commune = ?")) {
                statement.setLong(1, idCommune);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        CommuneBase voisin = findByID(resultSet.getLong("communeVoisine"), false);
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
     * @param name le nom de la commune
     * @return la commune
     */
    public CommuneBase findByName(String name, boolean loadNeighbors) {
        CommuneBase communeRet = null;
    
        // First pass: create the CommuneBase object without setting its neighbors
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Commune WHERE nomCommune = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Long idCommune = resultSet.getLong("idCommune");
                    String nomCommune = resultSet.getString("nomCommune");
                    Departement leDepartement = new DepartementDAO().findByID(resultSet.getLong("leDepartement"));
                    ArrayList<CommuneBase> lesVoisins = new ArrayList<>();
                    ArrayList<Gare> lesGares = new GareDAO().findByCommuneID(idCommune.intValue());
    
                    communeRet = new CommuneBase(idCommune.intValue(), nomCommune, leDepartement, lesVoisins, lesGares);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Second pass: set the neighbors for the CommuneBase
        if (loadNeighbors && communeRet != null) {
            try(Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Voisinage WHERE commune = ?")) {
                statement.setLong(1, communeRet.getIdCommune());
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        CommuneBase voisin = findByID(resultSet.getLong("communeVoisine"), false);
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
     * @param commune la commune à mettre à jour
     * @return le nombre de lignes mises à jour
     */
    @Override
    public int update(CommuneBase commune) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE Commune SET nomCommune = ?, leDepartement = ? WHERE idCommune = ?")) {
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
     * @param commune la commune à créer
     * @return le nombre de lignes créées
     */
    @Override
    public int create(CommuneBase commune) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Commune (idCommune, nomCommune, leDepartement) VALUES (?, ?, ?)")) {
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
