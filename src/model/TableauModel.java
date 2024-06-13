package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.data.CommunesInfoParAnnee;

/**
 * Classe permettant de gérer les données du tableau
 */
public class TableauModel {
    /**
     * Commune info par année
     */
    private CommunesInfoParAnnee laCommune;
    /**
     * Score
     */
    private SimpleStringProperty score;
    /**
     * Ville
     */
    private SimpleStringProperty ville;
    /**
     * Code postal
     */
    private SimpleIntegerProperty codePostal;
    /**
     * Département
     */
    private SimpleStringProperty departement;
    /**
     * Année
     */
    private SimpleIntegerProperty annee;
    /**
     * Culturel
     */
    private SimpleDoubleProperty culturel;
    /**
     * Nombre de voisins
     */
    private SimpleIntegerProperty nbVoisins;

    /**
     * Constructeur
     * 
     * @param communesInfoParAnnee Commune info par année
     */
    public TableauModel(CommunesInfoParAnnee communesInfoParAnnee) {
        this.laCommune = communesInfoParAnnee;

        this.score = new SimpleStringProperty(communesInfoParAnnee.scoreCompute()+"%");
        this.ville = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getNomCommune());
        this.codePostal = new SimpleIntegerProperty(communesInfoParAnnee.getLaCommune().getIdCommune());
        this.departement = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getLeDepartement().getNomDep());
        this.annee = new SimpleIntegerProperty(communesInfoParAnnee.getLannee().getAnneeRepr());
        this.culturel = new SimpleDoubleProperty(communesInfoParAnnee.getDepCulturellesTotales());
        this.nbVoisins = new SimpleIntegerProperty(communesInfoParAnnee.getLaCommune().getLesVoisins().size());
    }

    /**
     * Getter
     * 
     * @return Score
     */
    public String getScore() {
        return score.get();
    }

    /**
     * Getter
     * 
     * @return Ville
     */
    public String getVille() {
        return ville.get();
    }

    /**
     * Getter
     * 
     * @return Code postal
     */
    public int getCodePostal() {
        return codePostal.get();
    }

    /**
     * Getter
     * 
     * @return Département
     */
    public String getDepartement() {
        return departement.get();
    }

    /**
     * Getter
     * 
     * @return Année
     */
    public int getAnnee() {
        return annee.get();
    }

    /**
     * Getter
     * 
     * @return Culturel
     */
    public double getCulturel() {
        return culturel.get();
    }

    /**
     * Getter
     * 
     * @return Nombre de voisins
     */
    public int getNbVoisins() {
        return nbVoisins.get();
    }

    /**
     * Getter
     * 
     * @return Commune
     */
    public CommunesInfoParAnnee getLaCommune() {
        return laCommune;
    }
}
