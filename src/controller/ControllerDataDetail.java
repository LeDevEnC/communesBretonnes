package controller;

import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.data.Aeroport;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;

public class ControllerDataDetail extends Controller implements ReceiveInfo<CommunesInfoParAnnee> {

    /**
     * StackPane contenant la vue.
     */
    @FXML
    private StackPane window;

    /**
     * Conteneur pour les textFlow contenant les infos.
     */
    @FXML
    private VBox textFlowVBox;

    /**
     * Conteneur pour les labels associés aux aéroports.
     */
    @FXML
    private TextFlow aeroportLabel;

    /**
     * Valeur textuelle représentant le ou les aéroports associés.
     */
    @FXML
    private Text aeroportValue;

    /**
     * Conteneur pour les labels associés aux villes voisines.
     */
    @FXML
    private TextFlow villeVoisineLabel;

    /**
     * Valeur textuelle représentant la ville voisine.
     */
    @FXML
    private Text villeVoisineValue;

    /**
     * Conteneur pour les labels associés au prix moyen du mètre carré.
     */
    @FXML
    private TextFlow prixM2MoyenLabel;

    /**
     * Valeur textuelle représentant le prix moyen du mètre carré.
     */
    @FXML
    private Text prixM2MoyenValue;

    /**
     * Conteneur pour les labels associés au ratio du budget de dépenses
     * culturelles.
     */
    @FXML
    private TextFlow budgetDepCulRatioLabel;

    /**
     * Valeur textuelle représentant les dépenses culturelles totales.
     */
    @FXML
    private Text depCulturellesValue;

    /**
     * Valeur textuelle représentant le budget total.
     */
    @FXML
    private Text budgetTotalValue;

    /**
     * Conteneur pour les labels associés à la surface moyenne.
     */
    @FXML
    private TextFlow surfaceMoyLabel;

    /**
     * Valeur textuelle représentant la surface moyenne.
     */
    @FXML
    private Text surfaceMoyValue;

    /**
     * PieChart représentant le score global.
     */
    private PieChart pieChartScore;

    /**
     * StackPane contenant le PieChart.
     */
    @FXML
    private StackPane pieChartStackPane;

    /**
     * Hyperlink pour ouvrir le lien vers Google Maps.
     */
    @FXML
    private Hyperlink googleMapsHyperlink;

    @FXML
    private Label titreDetails;

    /**
     * Liste des aéroports associés.
     */
    private List<Aeroport> aeroports;

    /**
     * Référence à la commune voisine.
     */
    private CommuneBase villeVoisine;

    /**
     * Prix moyen du mètre carré.
     */
    private double prixM2Moyen;

    /**
     * Budget total disponible.
     */
    private double budgetTotal;

    /**
     * Surface moyenne en mètres carrés.
     */
    private double surfaceMoy;

    /**
     * Dépenses culturelles totales.
     */
    private double depCulturellesTotales;

    /**
     * Informations courantes.
     */
    private CommunesInfoParAnnee currentCommunesInfoParAnnee = null;

    /**
     * Ouvre le lien de la ville vers Google Maps.
     */
    @FXML
    private void openInGoogleMaps() {
        super.openWebLink("https://www.google.com/maps/search/"
                + this.currentCommunesInfoParAnnee.getLaCommune().getNomCommune() + ",France");
    }

    /**
     * Réceptionne l'objet CommunesInfoParAnnee et met à jour les labels.
     * 
     * @param communeAnnee Objet CommunesInfoParAnnee à traiter.
     */
    @Override
    public void receiveInfo(CommunesInfoParAnnee communeAnnee) {
        if (communeAnnee == null) {
            throw new IllegalArgumentException("info cannot be null");
        }
        this.currentCommunesInfoParAnnee = communeAnnee;
        this.onViewOpened();
    }

    /**
     * Appelé la première fois que la vue est affichée.
     */
    @Override
    public void initialize() {
        resize();
    }

    /**
     * Appelé chaque fois que la vue est affichée.
     */
    @Override
    public void onViewOpened() {
        if (this.currentCommunesInfoParAnnee != null) {
            this.aeroports = this.currentCommunesInfoParAnnee.getLaCommune().getLeDepartement().getAeroports();
            this.villeVoisine = this.currentCommunesInfoParAnnee.getLaCommune().getLesVoisins().get(0);
            this.prixM2Moyen = this.currentCommunesInfoParAnnee.getPrixMCarreMoyen();
            this.budgetTotal = this.currentCommunesInfoParAnnee.getBudgetTotal();
            this.surfaceMoy = this.currentCommunesInfoParAnnee.getSurfaceMoy();
            this.depCulturellesTotales = this.currentCommunesInfoParAnnee.getDepCulturellesTotales();

            this.titreDetails.setText("Détails de " + this.currentCommunesInfoParAnnee.getLaCommune().getNomCommune()
                    + " en " + this.currentCommunesInfoParAnnee.getLannee().getAnneeRepr());
            
            resetPieChartStackPane();
            updateLabels();
            initPieChart();
        }
    }

    /**
     * Réinitialise le PieChart.
     */
    private void resetPieChartStackPane() {
        this.pieChartStackPane.getChildren().clear();

        PieChart pieChart = new PieChart();
        this.pieChartScore = pieChart;
        this.pieChartStackPane.getChildren().add(pieChart);
    }

    /**
     * Initialise le PieChart avec les données courantes.
     */
    private void initPieChart() {
        int scoreGlobal = this.currentCommunesInfoParAnnee.scoreCompute();
        String nomCommune = this.currentCommunesInfoParAnnee.getLaCommune().getNomCommune();
        int annee = this.currentCommunesInfoParAnnee.getLannee().getAnneeRepr();

        // Ajouter deux segments au PieChart : un pour le score, un pour le reste
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(scoreGlobal + " % en " + annee, scoreGlobal),
                new PieChart.Data("", (100 - scoreGlobal)));
        this.pieChartScore.setData(pieChartData);
        this.pieChartScore.setTitle("% d'attractivité de " + nomCommune);
        this.pieChartScore.setStartAngle(90);
        this.pieChartScore.getData().get(0).getNode().setStyle("-fx-pie-color: #80caff;");
        this.pieChartScore.getData().get(1).getNode().setStyle("-fx-pie-color: #85e0a3;");
        this.pieChartScore.setLegendVisible(false);
    }

    /**
     * Met à jour les labels avec les valeurs courantes.
     */
    private void updateLabels() {
        aeroportValue.setText(getPrimaryAeroportName());
        aeroportValue.setFont(Font.font("System", FontWeight.BOLD, 12)); // Set the font to bold
    
        villeVoisineValue.setText(this.villeVoisine.getNomCommune());
        villeVoisineValue.setFont(Font.font("System", FontWeight.BOLD, 12)); // Set the font to bold
    
        setColorizedValue(prixM2MoyenValue, prixM2Moyen, "prixM2Moyen");
        setColorizedValue(depCulturellesValue, depCulturellesTotales, "depCulturellesTotales");
        setColorizedValue(budgetTotalValue, budgetTotal, "budgetTotal");
        setColorizedValue(surfaceMoyValue, surfaceMoy, "surfaceMoy");
        resize();
    }

    /**
     * Retourne le nom de l'aéroport principal.
     * 
     * @return Nom de l'aéroport principal.
     */
    private String getPrimaryAeroportName() {
        String nomAeroport = "Inconnu";
        if (!aeroports.isEmpty()) {
            nomAeroport = aeroports.get(0).getNom();
        }
        return nomAeroport;
    }

    /**
     * Met à jour la couleur et la valeur d'un Text en fonction de la valeur
     * passée en paramètre.
     * 
     * @param textNode Text à modifier.
     * @param value    Valeur à afficher.
     * @param type     Type de valeur à afficher.
     */
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
        textNode.setFont(Font.font("System", FontWeight.BOLD, 12)); // Set the font to bold
    }

    /**
     * Calcule le score en fonction du type de valeur.
     * 
     * @param type Type de valeur à calculer.
     * @return Score calculé.
     */
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

    /**
     * Redimensionne les éléments de la vue.
     */
    @Override
    protected void resize() {
        DoubleBinding fontSize = Bindings.createDoubleBinding(
                () -> this.textFlowVBox.getWidth() / 25.0,
                this.textFlowVBox.widthProperty());

        for (Node node : this.aeroportLabel.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }

        for (Node node : this.villeVoisineLabel.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }

        for (Node node : this.prixM2MoyenLabel.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }

        for (Node node : this.budgetDepCulRatioLabel.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }

        for (Node node : this.surfaceMoyLabel.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }

        googleMapsHyperlink.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
    }
}
