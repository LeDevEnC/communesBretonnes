package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.data.CommuneBase;

public class VoisinageDAO extends DAO<CommuneBase> {
    /**
     * Constructeur de la classe VoisinageDAO
     * Initialise la connexion à la base de données
     */
    public VoisinageDAO() {
        super();
    }

    /**
     * Constructeur de la classe VoisinageDAO
     * Initialise la connexion à la base de données
     * 
     * @param username Nom d'utilisateur pour la connexion à la base de données
     * @param password Mot de passe pour la connexion à la base de données
     */
    public VoisinageDAO(String username, String password) {
        super(username, password);
    }

    /**
     * Crée une relation de voisinage entre deux communes
     * 
     * @param commune         Identifiant de la commune
     * @param communeVoisine  Identifiant de la commune voisine
     * @return                Nombre de lignes affectées par la requête
     */
    public int create(int commune, int communeVoisine) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("INSERT INTO Voisinage (commune, communeVoisine) VALUES (?, ?)")) {
            statement.setInt(1, commune);
            statement.setDouble(2, communeVoisine);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Supprime une relation de voisinage entre deux communes
     * 
     * @param commune         Identifiant de la commune
     * @param communeVoisine  Identifiant de la commune voisine
     * @return                Nombre de lignes affectées par la requête
     */
    public int delete(int commune, int communeVoisine) {
        int result = 0;
        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("DELETE FROM Voisinage WHERE commune = ? AND communeVoisine = ?")) {
            statement.setInt(1, commune);
            statement.setInt(2, communeVoisine);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Recherche une commune par son identifiant
     *
     * @param user Commune à mettre à jour
     */
    @Override
    public CommuneBase findByID(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

    /**
     * Recherche une commune par son nom
     * 
     * @param user Commune à mettre à jour
     */
    @Override
    public int create(CommuneBase user) {
        throw new UnsupportedOperationException(
                "An other methode create with 2 parameters is implemented (int commune, int communeVoisine)");
    }

    /**
     * Met à jour une commune
     * @param user Commune à mettre à jour
     */
    @Override
    public int update(CommuneBase user) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    /**
     * Supprime une commune
     * @param user Commune à supprimer
     */
    @Override
    public int delete(CommuneBase user) {
        throw new UnsupportedOperationException(
                "An other methode delete with 2 parameters is implemented (int commune, int communeVoisine)");
    }

}
