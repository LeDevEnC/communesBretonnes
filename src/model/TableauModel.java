package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.data.CommunesInfoParAnnee;

/**
 * Classe TableauModel
 * Utiliser pour stocker une ligne de tableau
 * Une fois instancié, les valeurs des attributs ne peuvent pas être modifiées
 */
public class TableauModel {
    /**
     * Stocke la variable d'où provient les données de la ligne
     */
    private CommunesInfoParAnnee laCommune;

    /**
     * Score de la commune en pourcentage
     */
    private SimpleStringProperty score;

    /**
     * Nom de la commune
     */
    private SimpleStringProperty ville;

    /**
     * Code INSEE de la commune
     */
    private SimpleIntegerProperty codeInsee;

    /**
     * Département de la commune
     */
    private SimpleStringProperty departement;

    /**
     * Année de la donnée
     */
    private SimpleIntegerProperty annee;

    /**
     * Valeur de la dépense culturelle de la commune sur l'année
     */
    private SimpleDoubleProperty culturel;

    /**
     * Nombre de voisins de la commune
     */
    private SimpleIntegerProperty nbVoisins;

    /**
     * Constructeur de la classe TableauModel, initialise les valeurs des attributs
     * de la classe
     * 
     * @param communesInfoParAnnee L'objet contenant les informations de la commune
     *                             pour l'année
     */
    public TableauModel(CommunesInfoParAnnee communesInfoParAnnee) {
        if (communesInfoParAnnee == null) {
            throw new IllegalArgumentException("La commune ne peut pas être null");
        }
        this.laCommune = communesInfoParAnnee;

        this.score = new SimpleStringProperty(communesInfoParAnnee.scoreCompute() + "%");
        this.ville = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getNomCommune());
        this.codeInsee = new SimpleIntegerProperty(communesInfoParAnnee.getLaCommune().getIdCommune());
        this.departement = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getLeDepartement().getNomDep());
        this.annee = new SimpleIntegerProperty(communesInfoParAnnee.getLannee().getAnneeRepr());
        this.culturel = new SimpleDoubleProperty(communesInfoParAnnee.getDepCulturellesTotales());
        this.nbVoisins = new SimpleIntegerProperty(communesInfoParAnnee.getLaCommune().getLesVoisins().size());
    }

    /**
     * Getters, permet de récupérer le score de la commune
     * 
     * @return le score de la commune
     */
    public String getScore() {
        return score.get();
    }

    /**
     * Getters, permet de récupérer le nom de la commune
     * 
     * @return le nom de la commune
     */
    public String getVille() {
        return ville.get();
    }

    /**
     * Getters, permet de récupérer le code INSEE de la commune
     * 
     * @return le code INSEE de la commune
     */
    public int getCodeInsee() {
        return codeInsee.get();
    }

    /**
     * Getters, permet de récupérer le département de la commune
     * 
     * @return le département de la commune
     */
    public String getDepartement() {
        return departement.get();
    }

    /**
     * Getters, permet de récupérer l'année de la donnée
     * 
     * @return l'année de la donnée
     */
    public int getAnnee() {
        return annee.get();
    }

    /**
     * Getters, permet de récupérer la valeur de la dépense culturelle de la commune
     * sur l'année
     * 
     * @return la valeur de la dépense culturelle de la commune sur l'année
     */
    public double getCulturel() {
        return culturel.get();
    }

    /**
     * Getters, permet de récupérer le nombre de voisins de la commune
     * 
     * @return le nombre de voisins de la commune
     */
    public int getNbVoisins() {
        return nbVoisins.get();
    }

    /**
     * Getters, permet de récupérer la commune
     * 
     * @return la commune
     */
    public CommunesInfoParAnnee getLaCommune() {
        return laCommune;
    }
}
