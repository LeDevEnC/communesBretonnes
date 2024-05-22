package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.Departement;
import model.data.Gare;

public class CommunesInfoParAnneeDAO extends DAO<CommunesInfoParAnneeDAO> {
    /**
     * &nbsp;
     */
    public CommunesInfoParAnneeDAO() {
        super();
    }

    @Override
    public List<CommunesInfoParAnneeDAO> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    /**
     * Trouver les informations d'une commune pour une année donnée
     * 
     * @param annee       l'année
     * @param communeBase la commune
     * @return les informations de la commune pour l'année donnée
     */
    public CommunesInfoParAnnee findByIDComplete(Long annee, CommuneBase communeBase) {
        CommunesInfoParAnnee communesInfoParAnnee = null;
        Annee anneeS = new Annee(0, 0);
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Annee WHERE annee = ?")) {
            statement.setInt(1, annee.intValue());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    anneeS = new Annee(annee.intValue(), rs.getDouble("tauxInflation"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM DonneesAnnuelles WHERE laCommune = ? AND lAnnee = ?")) {
            statement.setLong(1, communeBase.getIdCommune());
            statement.setLong(2, annee);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, anneeS, resultSet.getInt("nbMaison"),
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

    public ArrayList<CommunesInfoParAnnee> findAllByYear(Long annee) {
        ArrayList<CommunesInfoParAnnee> communesInfoParAnneeList = new ArrayList<>();

        Annee anneeS = new Annee(0, 0);
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Annee WHERE annee = ?")) {
            statement.setInt(1, annee.intValue());
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    anneeS = new Annee(annee.intValue(), rs.getDouble("tauxInflation"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection connection = getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("SELECT * FROM DonneesAnnuelles WHERE lAnnee = ?")) {
            statement.setLong(1, annee);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CommuneBaseDAO communeBaseDAO = new CommuneBaseDAO();
                    Long idCommune = resultSet.getLong("laCommune");
                    CommuneBase communeBase = communeBaseDAO.findByID(idCommune);
                
                    int nbMaison = resultSet.getInt("nbMaison");
                    int nbAppart = resultSet.getInt("nbAppart");
                    double prixMoyen = resultSet.getDouble("prixMoyen");
                    int prixM2Moyen = resultSet.getInt("prixM2Moyen");
                    double SurfaceMoy = resultSet.getDouble("SurfaceMoy");
                    double depensesCulturellesTotales = resultSet.getDouble("depensesCulturellesTotales");
                    double budgetTotal = resultSet.getDouble("budgetTotal");
                    int population = resultSet.getInt("population");
                
                    if (communeBase == null || anneeS == null || nbMaison < 0 || nbAppart < 0 || prixMoyen < 0 || prixM2Moyen < 0 || SurfaceMoy < 0 || depensesCulturellesTotales < 0 || budgetTotal < 0 || population < 0) {
                        continue; // Skip this iteration if any value is null or negative
                    }
                
                    CommunesInfoParAnnee communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, anneeS, nbMaison, nbAppart, prixMoyen, prixM2Moyen, SurfaceMoy, depensesCulturellesTotales, budgetTotal, population);
                    communesInfoParAnneeList.add(communesInfoParAnnee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return communesInfoParAnneeList;
    }

    @Override
    public int update(CommunesInfoParAnneeDAO user) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(CommunesInfoParAnneeDAO user) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public int create(CommunesInfoParAnneeDAO user) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    /**
     * Méthode non implémentée car il n'y a pas de clé satisfaisante pour identifier
     * des données annuelles dans notre représentation
     */
    @Override
    public CommunesInfoParAnneeDAO findByID(Long id) {
        throw new UnsupportedOperationException(
                "Méthode non implémentée car il n'y a pas de clé satisfaisante pour identifier des données annuelles dans notre représentation");
    }

}
