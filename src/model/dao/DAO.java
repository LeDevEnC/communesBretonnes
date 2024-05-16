package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Classe DAO générique
 *
 * @param <T> le type d'objet
 */
public abstract class DAO<T> {
    /**
     * &nbsp;
     */
    public DAO() {
    }
    
    /**
     * Nom de la classe du pilote
     */
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";
    /**
     * URL de la base de données
     */
    private static String url = "jdbc:mysql://localhost:3306/bdsae";
    /**
     * Nom d'utilisateur de la base de données
     */
    private static String username = "root";
    /**
     * Mot de passe de la base de données
     */
    private static String password = "root";

    /**
     * Obtenir la connection
     * @return la connection
     * @throws SQLException si une erreur survient
     */
    protected Connection getConnection() throws SQLException {
        // Charger la classe du pilote
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        // Obtenir la connection
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Trouver tous les objets
     * @return la liste des objets
     */
    public abstract List<T> findAll();

    /**
     * Trouver un objet par son identifiant
     * @param id l'identifiant
     * @return l'objet
     */
    public abstract T findByID(Long id);

    /**
     * Trouver un objet par son nom
     * @param name le nom 
     * @return l'objet
     */
    public abstract T findByName(String name);

    /**
     * Mettre à jour un objet
     * @param user l'objet
     * @return le nombre de lignes mises à jour
     */
    public abstract int update(T user);

    /**
     * Supprimer un objet
     * @param user l'objet
     * @return le nombre de lignes supprimées
     */
    public abstract int delete(T user);

    /**
     * Créer un objet
     * @param user l'objet
     * @return l'identifiant de l'objet créé
     */
    public abstract int create(T user);
}