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

import model.data.Gare;

/**
 * Classe DAO pour les gares
 */
public class GareDAO extends DAO<Gare> {
    /**
     * &nbsp;
     */
    public GareDAO() {
        super();
    }

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public GareDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Trouver toutes les gares
     * 
     * @return la liste des gares
     */
    public Map<String, ArrayList<Gare>> findAll() {
        Map<String, ArrayList<Gare>> gares = new HashMap<>();
        try (Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement
                        .executeQuery("SELECT codeGare, nomGare, estFret, estVoyageur FROM Gare")) {
            while (resultSet.next()) {
                int codeGare = resultSet.getInt("codeGare");
                Gare gare = new Gare(codeGare, resultSet.getString("nomGare"),
                        resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                if (gares.containsKey(String.valueOf(codeGare))) {
                    gares.get(String.valueOf(codeGare)).add(gare);
                } else {
                    ArrayList<Gare> gareList = new ArrayList<>();
                    gareList.add(gare);
                    gares.put(String.valueOf(codeGare), gareList);
                }
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
                PreparedStatement statement = connection
                        .prepareStatement("SELECT nomGare, estFret, estVoyageur FROM Gare WHERE codeGare = ?")) {
            statement.setLong(1, codeGare);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gareRet = new Gare(Math.toIntExact(codeGare), resultSet.getString("nomGare"),
                            resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gareRet;
    }

    /**
     * Trouver une gare et sa commune par son identifiant
     * 
     * @param codeGare un code de gare
     * @return la gare
     */
    public Map<Integer, Gare> findAll(Long codeGare) {
        Map<Integer, Gare> gareMap = new HashMap<>();
        Gare gareRet = null;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT nomGare, estFret, estVoyageur, laCommune FROM Gare WHERE codeGare = ?")) {
            statement.setLong(1, codeGare);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gareRet = new Gare(Math.toIntExact(codeGare), resultSet.getString("nomGare"),
                            resultSet.getBoolean("estFret"), resultSet.getBoolean("estVoyageur"));
                    int laCommune = resultSet.getInt("laCommune");
                    gareMap.put(laCommune, gareRet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gareMap;
    }

    /**
     * Trouver les gares d'une commune
     * 
     * @param idCommune un identifiant de commune
     * @return la liste des gares
     */
    public List<Gare> findByCommuneID(int idCommune) {
        ArrayList<Gare> gares = new ArrayList<>();
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT codeGare, nomGare, estFret, estVoyageur FROM Gare WHERE laCommune = ?")) {
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
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT codeGare, nomGare, estFret, estVoyageur FROM Gare WHERE nomGare = ?")) {
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
     * Mettre à jour une gare avec sa commune
     * 
     * @param gare la gare
     */
    public int update(Gare gare, int idCommune) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE Gare SET nomGare = ?, estFret = ?, estVoyageur = ? , estFret = ?, laCommune = ? WHERE codeGare = ?")) {
            statement.setString(1, gare.getNomGare());
            statement.setBoolean(2, gare.getEstFret());
            statement.setBoolean(3, gare.getEstVoyageur());
            statement.setBoolean(4, gare.getEstFret());
            statement.setInt(5, idCommune);
            statement.setInt(6, gare.getCodeGare());
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
     * Supprimer une gare en prennant en compte sa commune
     * 
     * @param gare la gare
     */
    public int delete(Gare gare, int idCommune) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM Gare WHERE codeGare = ? AND laCommune = ?")) {
            statement.setInt(1, gare.getCodeGare());
            statement.setInt(2, idCommune);
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
     * @param idCommune la commune de la gare
     */
    public int create(Gare gare, int idCommune) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, gare.getCodeGare());
            statement.setString(2, gare.getNomGare());
            statement.setBoolean(3, gare.getEstFret());
            statement.setBoolean(4, gare.getEstVoyageur());
            statement.setInt(5, idCommune);
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
                        "INSERT INTO Gare (codeGare, nomGare, estFret, estVoyageur, laCommune) VALUES (?, ?, ?, ?, ?)")) {
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
