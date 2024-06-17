package controller;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.data.Aeroport;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

public class ControllerDataDetail extends Controller implements ReceiveInfo<CommunesInfoParAnnee>{

    @FXML
    private TextFlow aeroportLabel;
    @FXML
    private Text aeroportValue;

    @FXML
    private TextFlow villeVoisineLabel;
    @FXML
    private Text villeVoisineValue;

    @FXML
    private TextFlow prixM2MoyenLabel;
    @FXML
    private Text prixM2MoyenValue;

    @FXML
    private TextFlow budgetDepCulRatioLabel;
    @FXML
    private Text depCulturellesValue;
    @FXML
    private Text budgetTotalValue;

    @FXML
    private TextFlow surfaceMoyLabel;
    @FXML
    private Text surfaceMoyValue;

    private List<Aeroport> aeroports;
    private CommuneBase villeVoisine;
    private double prixM2Moyen;
    private double budgetTotal;
    private double surfaceMoy;
    private double depCulturellesTotales;

    private CommunesInfoParAnnee currentCommunesInfoParAnnee = null;

    @Override
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
        aeroportValue.setText(getPrimaryAeroportName());
        villeVoisineValue.setText(this.villeVoisine.getNomCommune());
        setColorizedValue(prixM2MoyenValue, prixM2Moyen, "prixM2Moyen");
        setColorizedValue(depCulturellesValue, depCulturellesTotales, "depCulturellesTotales");
        setColorizedValue(budgetTotalValue, budgetTotal, "budgetTotal");
        setColorizedValue(surfaceMoyValue, surfaceMoy, "surfaceMoy");
    }

    private String getPrimaryAeroportName() {
        return aeroports.isEmpty() ? "Inconnu" : aeroports.get(0).getNom();
    }

    private void setColorizedValue(Text textNode, double value, String type) {
        if (value == -1.0) {
            textNode.setText("Inconnu");
            textNode.setFill(Color.RED);
        } else {
            double score = calculateScore(type);
            Color color = currentCommunesInfoParAnnee.getColorForScore(score);
            textNode.setText(String.format("%.2f", value));
            textNode.setFill(color);
        }
    }

    private double calculateScore(String type) {
        switch (type) {
            case "prixM2Moyen":
                return this.currentCommunesInfoParAnnee.calculateScorePrixM2Moyen();
            case "depCulturellesTotales":
                return this.currentCommunesInfoParAnnee.calculateScoreDepensesCulturelles();
            case "budgetTotal":
                return this.currentCommunesInfoParAnnee.calculateScoreBudgetTotal();
            case "surfaceMoy":
                return this.currentCommunesInfoParAnnee.calculateScoreSurfaceMoy();
            default:
                return 0;
        }
    }

    @Override
    protected void resize() {
        // TODO Implémenter la vue et le redimensionnement
    }
}
