package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import model.data.CommunesInfoParAnnee;

public class TableauModel {
    private CommunesInfoParAnnee laCommune;

    private SimpleStringProperty score;
    private SimpleStringProperty ville;
    private SimpleStringProperty codePostal;
    private SimpleStringProperty departement;
    private SimpleIntegerProperty annee;
    private SimpleStringProperty culturel;
    private SimpleIntegerProperty nbVoisins;

    public TableauModel(CommunesInfoParAnnee communesInfoParAnnee) {
        this.laCommune = communesInfoParAnnee;

        this.score = new SimpleStringProperty(0d+"%");
        this.ville = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getNomCommune());
        this.codePostal = new SimpleStringProperty(String.valueOf(communesInfoParAnnee.getLaCommune().getIdCommune()));
        this.departement = new SimpleStringProperty(communesInfoParAnnee.getLaCommune().getLeDepartement().getNomDep());
        this.annee = new SimpleIntegerProperty(communesInfoParAnnee.getLannee().getAnnee());
        this.culturel = new SimpleStringProperty(String.valueOf(communesInfoParAnnee.getDepCulturellesTotales()));
        this.nbVoisins = new SimpleIntegerProperty(communesInfoParAnnee.getLaCommune().getLesVoisins().size());
    }

    public String getScore() {
        return score.get();
    }

    public String getVille() {
        return ville.get();
    }

    public String getCodePostal() {
        return codePostal.get();
    }

    public String getDepartement() {
        return departement.get();
    }

    public int getAnnee() {
        return annee.get();
    }

    public String getCulturel() {
        return culturel.get();
    }

    public int getNbVoisins() {
        return nbVoisins.get();
    }

    public CommunesInfoParAnnee getLaCommune() {
        return laCommune;
    }
}
