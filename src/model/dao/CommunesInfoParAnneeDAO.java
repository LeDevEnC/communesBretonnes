package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

/**
 * Classe DAO pour les informations des communes par année
 */
public class CommunesInfoParAnneeDAO extends DAO<CommunesInfoParAnnee> {

    /**
     * Cache de Toutes les années
     */
    private Map<String, Annee> toutesLesAnnees;
    /**
     * Cache de Toutes les communes de base
     */
    private Map<String, CommuneBase> toutesLesCommunesBase;

    /**
     * &nbsp;
     * 
     */
    public CommunesInfoParAnneeDAO(Map<String, Annee> annees, Map<String, CommuneBase> communesBase) {
        super();
        if (annees == null) {
            throw new IllegalArgumentException("annees cannot be null");
        }
        if (communesBase == null) {
            throw new IllegalArgumentException("communesBase cannot be null");
        }
        this.toutesLesAnnees = annees;
        this.toutesLesCommunesBase = communesBase;
    }

    /**
     * &nbsp;
     * 
     * @param username &nbsp;
     * @param password &nbsp;
     */
    public CommunesInfoParAnneeDAO(String username, String password, Map<String, Annee> annees,
            Map<String, CommuneBase> communesBase) {
        super(username, password);
        if (annees == null) {
            throw new IllegalArgumentException("annees cannot be null");
        }
        if (communesBase == null) {
            throw new IllegalArgumentException("communesBase cannot be null");
        }
        this.toutesLesAnnees = annees;
        this.toutesLesCommunesBase = communesBase;
    }

    /**
     * Trouver tous les informations des communes par année
     * 
     * @return la liste des informations des communes par année
     */
    public Map<String, CommunesInfoParAnnee> findAll() {
        Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = new HashMap<>();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "SELECT laCommune, lAnnee, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population FROM DonneesAnnuelles")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String nomCommune = resultSet.getString("laCommune");
                    String annee = resultSet.getString("lAnnee");
                    CommuneBase communeBase = toutesLesCommunesBase.get(nomCommune);
                    Annee annees = toutesLesAnnees.get(annee);
                    int nbMaison = resultSet.getInt("nbMaison");
                    int nbAppart = resultSet.getInt("nbAppart");
                    double prixMoyen = resultSet.getDouble("prixMoyen");
                    int prixM2Moyen = resultSet.getInt("prixM2Moyen");
                    double surfaceMoyResult = resultSet.getDouble("SurfaceMoy");
                    double depensesCulturellesTotales = resultSet.getDouble("depensesCulturellesTotales");
                    double budgetTotal = resultSet.getDouble("budgetTotal");
                    int population = resultSet.getInt("population");

                    CommunesInfoParAnnee nouvelleCommune = new CommunesInfoParAnnee(communeBase, annees, nbMaison,
                            nbAppart, prixMoyen,
                            prixM2Moyen, surfaceMoyResult, depensesCulturellesTotales, budgetTotal, population);
                    String primaryKey = communeBase.getNomCommune() + " " + annees.getAnneeRepr();
                    toutesLesCommunesInfoParAnnee.put(primaryKey, nouvelleCommune);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toutesLesCommunesInfoParAnnee;
    }

    /**
     * Trouver les informations d'une commune pour une année donnée
     * 
     * @param anneeCourante l'année
     * @param communeBase   la commune
     * @return les informations de la commune pour l'année donnée
     */
    public CommunesInfoParAnnee findByIDComplete(Annee annees, CommuneBase communeBase) {
        CommunesInfoParAnnee communesInfoParAnnee = null;
        long anneeCourante = annees.getAnneeRepr();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(
                                "SELECT nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population FROM DonneesAnnuelles WHERE laCommune = ? AND lAnnee = ?")) {
            statement.setLong(1, communeBase.getIdCommune());
            statement.setLong(2, anneeCourante);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int nbMaison = resultSet.getInt("nbMaison");
                    int nbAppart = resultSet.getInt("nbAppart");
                    double prixMoyen = resultSet.getDouble("prixMoyen");
                    int prixM2Moyen = resultSet.getInt("prixM2Moyen");
                    double surfaceMoy = resultSet.getDouble("SurfaceMoy");
                    double depensesCulturellesTotales = resultSet.getDouble("depensesCulturellesTotales");
                    double budgetTotal = resultSet.getDouble("budgetTotal");
                    int population = resultSet.getInt("population");

                    communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, annees, nbMaison, nbAppart, prixMoyen,
                            prixM2Moyen, surfaceMoy, depensesCulturellesTotales, budgetTotal, population);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communesInfoParAnnee;
    }

    /**
     * Mettre à jour une entrée de la base de données
     * 
     * @param info les informations à mettre à jour
     * @return le nombre de lignes affectées
     */
    @Override
    public int update(CommunesInfoParAnnee info) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE DonneesAnnuelles SET nbMaison = ?, nbAppart = ?, prixMoyen = ?, prixM2Moyen = ?, SurfaceMoy = ?, depensesCulturellesTotales = ?, budgetTotal = ?, population = ? WHERE laCommune = ? AND lAnnee = ?");
            statement.setInt(1, info.getNbMaison());
            statement.setInt(2, info.getNbAppart());
            statement.setDouble(3, info.getPrixMoyen());
            statement.setDouble(4, info.getPrixMCarreMoyen());
            statement.setDouble(5, info.getSurfaceMoy());
            statement.setDouble(6, info.getDepCulturellesTotales());
            statement.setDouble(7, info.getBudgetTotal());
            statement.setDouble(8, info.getPopulation());
            statement.setInt(9, info.getLaCommune().getIdCommune());
            statement.setInt(10, info.getLannee().getAnneeRepr());
            int result = statement.executeUpdate();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Supprimer une entrée de la base de données
     * @param info les informations à supprimer
     * @return le nombre de lignes affectées
     */
    @Override
    public int delete(CommunesInfoParAnnee info) {
        try{
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM DonneesAnnuelles WHERE laCommune = ? AND lAnnee = ?");
            statement.setInt(1, info.getLaCommune().getIdCommune());
            statement.setInt(2, info.getLannee().getAnneeRepr());
            int result = statement.executeUpdate();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Créer une nouvelle entrée dans la base de données
     * @param info les informations à ajouter
     * @return le nombre de lignes affectées
     */
    @Override
    public int create(CommunesInfoParAnnee info) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO DonneesAnnuelles (laCommune, lAnnee, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, info.getLaCommune().getIdCommune());
            statement.setInt(2, info.getLannee().getAnneeRepr());
            statement.setInt(3, info.getNbMaison());
            statement.setInt(4, info.getNbAppart());
            statement.setDouble(5, info.getPrixMoyen());
            statement.setDouble(6, info.getPrixMCarreMoyen());
            statement.setDouble(7, info.getSurfaceMoy());
            statement.setDouble(8, info.getDepCulturellesTotales());
            statement.setDouble(9, info.getBudgetTotal());
            statement.setDouble(10, info.getPopulation());
            int result = statement.executeUpdate();
            connection.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Méthode non implémentée car il n'y a pas de clé satisfaisante pour identifier
     * des données annuelles dans notre représentation
     */
    @Override
    public CommunesInfoParAnnee findByID(Long id) {
        throw new UnsupportedOperationException(
                "Méthode non implémentée car il n'y a pas de clé satisfaisante pour identifier des données annuelles dans notre représentation");
    }

}
