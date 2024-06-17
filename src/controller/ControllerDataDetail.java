package controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.data.Aeroport;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

public class ControllerDataDetail extends Controller implements ReceiveInfo<CommunesInfoParAnnee>{

    @FXML
    private Label aeroportLabel;

    @FXML
    private Label villeVoisineLabel;

    @FXML
    private Label prixM2MoyenLabel;

    @FXML
    private Label budgetDepCulRatioLabel;

    @FXML
    private Label surfaceMoyLabel;

    @FXML 
    private PieChart scoreVilleLabel;

    private List<Aeroport> aeroports;

    private CommuneBase villeVoisine;

    private double prixM2Moyen;

    private double budgetTotal;

    private double surfaceMoy;

    private double depCulturellesTotales;

    /**
     * Stocke les informations de la commune pour une année donnée
     */
    private CommunesInfoParAnnee currentCommunesInfoParAnnee = null;


    @Override
    /**
     * Permet de recevoir les infos de la commune pour une année donnée et de la traiter
     * @param communeAnnee les informations de la commune pour une année donnée
     */
    public void receiveInfo(CommunesInfoParAnnee communeAnnee) {
        if (communeAnnee == null) {
            throw new IllegalArgumentException("info cannot be null");
        }
        this.currentCommunesInfoParAnnee = communeAnnee;
        this.onViewOpened();
    }

    @Override
    public void initialize() {
        resize();
    }

    @Override
    public void onViewOpened() {
        if (this.currentCommunesInfoParAnnee != null) {
            this.aeroports = this.currentCommunesInfoParAnnee.getLaCommune().getLeDepartement().getAeroports();
            this.villeVoisine = this.currentCommunesInfoParAnnee.getLaCommune().getLesVoisins().get(0);
            this.prixM2Moyen = this.currentCommunesInfoParAnnee.getPrixMCarreMoyen();
            this.budgetTotal = this.currentCommunesInfoParAnnee.getBudgetTotal();
            this.surfaceMoy = this.currentCommunesInfoParAnnee.getSurfaceMoy();
            this.depCulturellesTotales = this.currentCommunesInfoParAnnee.getDepCulturellesTotales();
    
            updateLabels();
        }
    }

        
    private void updateLabels() {
        aeroportLabel.setText("Aéroport : " + getPrimaryAeroportName());
        villeVoisineLabel.setText("Ville voisine : " + this.villeVoisine.getNomCommune());
        prixM2MoyenLabel.setText("Prix moyen du m² : " + colorizeValue(prixM2Moyen, "prixM2Moyen"));
        budgetDepCulRatioLabel.setText("Dépenses culturelles / Budget : " + colorizeValue(depCulturellesTotales, "depCulturellesTotales") + " / " + colorizeValue(budgetTotal, "budgetTotal"));
        surfaceMoyLabel.setText("Surface moyenne des logements : " + colorizeValue(surfaceMoy, "surfaceMoy") + " m²");
    }

    private String getPrimaryAeroportName() {
        return aeroports.isEmpty() ? "Inconnu" : aeroports.get(0).getNom();
    }

    private String colorizeValue(double value, String type) {
        double score = 0;
        switch (type) {
            case "prixM2Moyen":
                score = this.currentCommunesInfoParAnnee.calculateScorePrixM2Moyen();
                break;
            case "depCulturellesTotales":
                score = this.currentCommunesInfoParAnnee.calculateScoreDepensesCulturelles();
                break;
            case "budgetTotal":
                score = this.currentCommunesInfoParAnnee.calculateScoreBudgetTotal();
                break;
            case "surfaceMoy":
                score = this.currentCommunesInfoParAnnee.calculateScoreSurfaceMoy();
                break;
        }
        Color color = currentCommunesInfoParAnnee.getColorForScore(score);
        return String.format("%s", color.toString(), value);
    }


    @Override
    protected void resize() {
        // TODO Implémenter la vue et le redimensionnement
    }
}
