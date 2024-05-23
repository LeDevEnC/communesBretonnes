package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

public class CommunesInfoParAnneeDAO extends DAO<CommunesInfoParAnnee> {

    private HashMap<String, Annee> toutesLesAnnees;
    private HashMap<String, CommuneBase> toutesLesCommunesBase;

    /**
     * &nbsp;
     */
    public CommunesInfoParAnneeDAO(HashMap<String, Annee> annees, HashMap<String, CommuneBase> communesBase) {
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

    public CommunesInfoParAnneeDAO(String username, String password, HashMap<String, Annee> annees,
            HashMap<String, CommuneBase> communesBase) {
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

    public HashMap<String, CommunesInfoParAnnee> findAll() {
        HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = new HashMap<>();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM DonneesAnnuelles")) {
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
                    double SurfaceMoy = resultSet.getDouble("SurfaceMoy");
                    double depensesCulturellesTotales = resultSet.getDouble("depensesCulturellesTotales");
                    double budgetTotal = resultSet.getDouble("budgetTotal");
                    int population = resultSet.getInt("population");

                    CommunesInfoParAnnee nouvelleCommune = new CommunesInfoParAnnee(communeBase, annees, nbMaison,
                            nbAppart, prixMoyen,
                            prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population);
                    String primaryKey = communeBase.getNomCommune() + " " + annees.getAnnee();
                    toutesLesCommunesInfoParAnnee.put(primaryKey, nouvelleCommune);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toutesLesCommunesInfoParAnnee;
    }

    // public HashMap<String, CommunesInfoParAnnee> findAll() {
    // HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = new
    // HashMap<>();
    // for (Annee annee : toutesLesAnnees.values()) {
    // for (CommuneBase communeBase : toutesLesCommunesBase.values()) {
    // CommunesInfoParAnnee communesInfoParAnnee = findByIDComplete(annee,
    // communeBase);
    // if (communesInfoParAnnee != null) {
    // toutesLesCommunesInfoParAnnee.put(communesInfoParAnnee.getLaCommune().getNomCommune(),
    // communesInfoParAnnee);
    // }
    // }
    // }
    // return toutesLesCommunesInfoParAnnee;
    // }

    /**
     * Trouver les informations d'une commune pour une année donnée
     * 
     * @param anneeCourante l'année
     * @param communeBase   la commune
     * @return les informations de la commune pour l'année donnée
     */
    public CommunesInfoParAnnee findByIDComplete(Annee annees, CommuneBase communeBase) {
        CommunesInfoParAnnee communesInfoParAnnee = null;
        long anneeCourante = annees.getAnnee();

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM DonneesAnnuelles WHERE laCommune = ? AND lAnnee = ?")) {
            statement.setLong(1, communeBase.getIdCommune());
            statement.setLong(2, anneeCourante);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, annees, resultSet.getInt("nbMaison"),
                            resultSet.getInt("nbAppart"), resultSet.getDouble("prixMoyen"),
                            resultSet.getInt("prixM2Moyen"), resultSet.getDouble("SurfaceMoy"),
                            resultSet.getDouble("depensesCulturellesTotales"), resultSet.getDouble("budgetTotal"),
                            resultSet.getInt("population"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communesInfoParAnnee;
    }

    @Override
    public int update(CommunesInfoParAnnee user) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(CommunesInfoParAnnee user) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int create(CommunesInfoParAnnee user) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
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
