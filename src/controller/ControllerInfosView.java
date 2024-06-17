package controller;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.data.Aeroport;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

public class ControllerInfosView extends Controller {
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

    private CommunesInfoParAnnee lineClicked;

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    @Override
    protected void resize() {
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }

    @Override
    public void onViewOpened() {
        this.lineClicked = getModel().getLineClicked();

        this.aeroports = this.lineClicked.getLaCommune().getLeDepartement().getAeroports();
        this.villeVoisine = this.lineClicked.getLaCommune().getLesVoisins().get(0);
        this.prixM2Moyen = this.lineClicked.getPrixMCarreMoyen();
        this.budgetTotal = this.lineClicked.getBudgetTotal();
        this.surfaceMoy = this.lineClicked.getSurfaceMoy();
        this.depCulturellesTotales = this.lineClicked.getDepCulturellesTotales();

        updateLabels();

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
                score = this.lineClicked.calculateScorePrixM2Moyen();
                break;
            case "depCulturellesTotales":
                score = this.lineClicked.calculateScoreDepensesCulturelles();
                break;
            case "budgetTotal":
                score = this.lineClicked.calculateScoreBudgetTotal();
                break;
            case "surfaceMoy":
                score = this.lineClicked.calculateScoreSurfaceMoy();
                break;
        }
        Color color = lineClicked.getColorForScore(score);
        return String.format("%s", color.toString(), value);
    }



}
