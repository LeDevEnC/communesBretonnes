package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.data.CommunesInfoParAnnee;

public class TableauModel {
    private CommunesInfoParAnnee laCommune;

    private SimpleStringProperty score;
    private SimpleStringProperty ville;
    private SimpleIntegerProperty codePostal;
    private SimpleStringProperty departement;
    private SimpleIntegerProperty annee;
    private SimpleDoubleProperty culturel;
    private SimpleIntegerProperty nbVoisins;

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

    public String getScore() {
        return score.get();
    }

    public String getVille() {
        return ville.get();
    }

    public int getCodePostal() {
        return codePostal.get();
    }

    public String getDepartement() {
        return departement.get();
    }

    public int getAnnee() {
        return annee.get();
    }

    public double getCulturel() {
        return culturel.get();
    }

    public int getNbVoisins() {
        return nbVoisins.get();
    }

    public CommunesInfoParAnnee getLaCommune() {
        return laCommune;
    }
}
