package controller;

import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.data.CommunesInfoParAnnee;

public class ControllerBoard extends Controller {
    /**
     * Score global de l'attractivité de la Bretagne
     */
    private int scoreGlobal;

    /**
     * Texte contenant le titre "Conseil"
     */
    @FXML
    private Text conseilText;

    /**
     * TextFlow contenant le texte du conseil
     */
    @FXML
    private TextFlow conseilTextFlow;
    /**
     * GridPane contenant les éléments de la vue
     */
    @FXML
    private GridPane gridPane;

    /**
     * PieChart représentant le score global de l'attractivité de la Bretagne
     */
    @FXML
    private PieChart pieChartBretagneAtt;

    /**
     * BarChart représentant le score moyen de l'attractivité de la Bretagne par
     * département en 2021
     */
    @FXML
    private BarChart<String, Number> barChartBretagneAtt;

    @FXML
    private LineChart<Number, Number> lineChartBretagneAtt;

    private boolean[] graphCharged = new boolean[3];

    /**
     * Méthode appelée lors de la première initialisation de la vue
     */
    @Override
    public void initialize() {
        // TODO : Implémenter le conseilText
        setConseilText(new String[]{"Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "Aenean gravida justo vitae consequat venenatis.", "Aenean euismod nibh sit amet ullamcorper varius."});
        resize();
    }

    /**
     * Méthode appelée lors de l'ouverture de la vue
     */
    public void onViewOpened() {
        resize();
        this.pieChartBretagneAtt.setVisible(true);
        if (!graphCharged[0]) {
            initPieChart();
        }
        if (!graphCharged[1]) {
            initBarChartDep();
        }
        if (!graphCharged[2]) {
            initLineChart();
        }

    }

    /**
     * Change la taille des éléments de la vue en fonction de la taille de la
     * fenêtre
     * Change la taille du titre de chaque graphes,
     */
    protected void resize() {
        // Créer un binding pour la taille de la police en fonction de la largeur de
        // gridPane
        DoubleBinding fontSize = Bindings.createDoubleBinding(
                () -> this.gridPane.getWidth() / 65.0,
                this.gridPane.widthProperty());

        // Change la taille des différents éléments
        this.gridPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            final String TITRE_STYLE = "-fx-font-size: " + fontSize.get() + "px;";
            final String TITRE_LOOKUP = ".chart-title";
            pieChartBretagneAtt.lookup(TITRE_LOOKUP).setStyle(TITRE_STYLE);
            barChartBretagneAtt.lookup(TITRE_LOOKUP).setStyle(TITRE_STYLE);
            lineChartBretagneAtt.lookup(TITRE_LOOKUP).setStyle(TITRE_STYLE);
        });
    }

    /**
     * Initialiser le PieChart
     */
    private void initPieChart() {

        Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                .getToutesLesCommunesInfoParAnnee();


        int scoreGlobalTotal = 0;
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            scoreGlobalTotal += communesInfoParAnnee.scoreCompute();
        }

        this.scoreGlobal = scoreGlobalTotal / toutesLesCommunesInfoParAnnee.size();

        // Ajouter deux segments au PieChart : un pour le score, un pour le reste
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bretagne : " + this.scoreGlobal + " %", this.scoreGlobal),
                new PieChart.Data("", (100 - this.scoreGlobal)));
        this.pieChartBretagneAtt.setData(pieChartData);
        this.pieChartBretagneAtt.setTitle("% d'attractivité de la Bretagne");
        this.pieChartBretagneAtt.setStartAngle(90);

        this.pieChartBretagneAtt.getData().get(0).getNode().setStyle("-fx-pie-color: #80caff;");
        this.pieChartBretagneAtt.getData().get(1).getNode().setStyle("-fx-pie-color: #85e0a3;");

        this.pieChartBretagneAtt.setLegendVisible(false);
    }

    private void initBarChartDep() {
        Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                .getToutesLesCommunesInfoParAnnee();
        int[] scores = new int[4];
        int[] nb = new int[4];

        computeScoresAndNb(toutesLesCommunesInfoParAnnee, scores, nb);

        normalizeScores(scores, nb);

        setupBarChart();

        addDataToChart(scores);
        graphCharged[1] = true;
    }


    /**
     * Permet de calculer les scores moyens par département
     * 
     * @param toutesLesCommunesInfoParAnnee Les communes
     * @param scores                        Les scores totaux par département
     * @param nb                            Le nombre de communes par département
     */
    private void computeScoresAndNb(Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee, int[] scores,
            int[] nb) {
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            int idDep = communesInfoParAnnee.getLaCommune().getLeDepartement().getIdDep();
            int annee = communesInfoParAnnee.getLannee().getAnneeRepr();

            if (annee == 2021) {
                switch (idDep) {
                    case 29:
                        scores[0] += communesInfoParAnnee.scoreCompute();
                        nb[0]++;
                        break;
                    case 22:
                        scores[1] += communesInfoParAnnee.scoreCompute();
                        nb[1]++;
                        break;
                    case 56:
                        scores[2] += communesInfoParAnnee.scoreCompute();
                        nb[2]++;
                        break;
                    case 35:
                        scores[3] += communesInfoParAnnee.scoreCompute();
                        nb[3]++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Permet de calculer le score moyen de toute les communes par année
     * 
     * @param toutesLesCommunesInfoParAnnee Les communes
     * @param scores                        Les scores totaux par année
     * @param nb                            Le nombre d'années
     */
    private void computeScoresAndNbByYear(Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee, int[] scores,
            int[] nb) {
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            int annee = communesInfoParAnnee.getLannee().getAnneeRepr();

            switch (annee) {
                case 2019:
                    scores[0] += communesInfoParAnnee.scoreCompute();
                    nb[0]++;
                    break;
                case 2020:
                    scores[1] += communesInfoParAnnee.scoreCompute();
                    nb[1]++;
                    break;
                case 2021:
                    scores[2] += communesInfoParAnnee.scoreCompute();
                    nb[2]++;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Permet de normaliser les scores
     * 
     * @param scores Les scores totaux
     * @param nb     Le nombre de communes
     */
    private void normalizeScores(int[] scores, int[] nb) {
        for (int i = 0; i < 4; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }

    /**
     * Initialiser le BarChart
     */
    private void setupBarChart() {
        this.barChartBretagneAtt.setTitle("Scores Moyens par Département en 2021");
        this.barChartBretagneAtt.getXAxis().setLabel("Département");
        this.barChartBretagneAtt.setCategoryGap(30);
        this.barChartBretagneAtt.setBarGap(0);
        this.barChartBretagneAtt.setLegendVisible(false);
        double maxYValue = 100.0;
        ((NumberAxis) barChartBretagneAtt.getYAxis()).setAutoRanging(false);
        ((NumberAxis) barChartBretagneAtt.getYAxis()).setUpperBound(maxYValue);
    }

    /**
     * Ajouter les données au BarChart
     * 
     * @param scores Les scores par département
     */
    private void addDataToChart(int[] scores) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("Finistère\n" + scores[0] + " %", scores[0]));
        series.getData().add(new XYChart.Data<>("Côtes d'Armor\n" + scores[1] + " %", scores[1]));
        series.getData().add(new XYChart.Data<>("Morbihan\n" + scores[2] + " %", scores[2]));
        series.getData().add(new XYChart.Data<>("Ille-et-Vilaine\n" + scores[3] + " %", scores[3]));
        this.barChartBretagneAtt.getData().add(series);
        applySingleColorToSeries(series, "#2eb82e");
        graphCharged[0] = true;
    }

    /**
     * Appliquer une couleur unique à une série
     *
     * @param series la série
     * @param color  la couleur
     */
    private void applySingleColorToSeries(XYChart.Series<String, Number> series, String color) {
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node bar = data.getNode();
            bar.setStyle("-fx-bar-fill: " + color + ";");
        }
    }

    /**
     * Initialiser le LineChart en termes de paramètres
     */
    private void setupLineChart() {
        NumberAxis xAxis = (NumberAxis) this.lineChartBretagneAtt.getXAxis();
        NumberAxis yAxis = (NumberAxis) this.lineChartBretagneAtt.getYAxis();
        this.lineChartBretagneAtt.setTitle("Score d'Attractivité moyen par Années");

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(2018);
        xAxis.setUpperBound(2022);
        xAxis.setTickUnit(1);

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(100);
        yAxis.setTickUnit(10);

        xAxis.setLabel("Année");
        yAxis.setLabel("Score d'Attractivité");

        this.lineChartBretagneAtt.setLegendVisible(false);

    }

    /**
     * Permet de normaliser les scores pour le lineChart
     * 
     * @param scores Les scores totaux
     * @param nb     Le nombre de communes
     */
    private void normalizeScoresLineChart(int[] scores, int[] nb) {
        for (int i = 0; i < 3; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }

    /**
     * Ajouter les données au LineChart
     * 
     * @param scores Les scores par année
     */
    private void addDataToLineChart(int[] scores) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(2019, scores[0]));
        series.getData().add(new XYChart.Data<>(2020, scores[1]));
        series.getData().add(new XYChart.Data<>(2021, scores[2]));

        series.setName("Score d'Attractivité");
        Platform.runLater(() -> {

            Set<Node> nodes = this.lineChartBretagneAtt.lookupAll(".series" + 0);
            for (Node n : nodes) {
                n.setStyle("-fx-background-color: green;");
            }

            series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #2eb82e;");
        });
        this.lineChartBretagneAtt.getData().add(series);
    }

    /**
     * Initialiser le LineChart en termes de valeurs réélles
     */
    private void initLineChart() {
        Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                .getToutesLesCommunesInfoParAnnee();
        int[] scores = new int[3];
        int[] nb = new int[3];

        computeScoresAndNbByYear(toutesLesCommunesInfoParAnnee, scores, nb);

        normalizeScoresLineChart(scores, nb);

        setupLineChart();

        addDataToLineChart(scores);
        graphCharged[2] = true;
    }

    /**
     * Permet de changer les conseils affichés
     * @param conseils Les conseils à afficher
     */
    private void setConseilText(String[] conseils) {
        this.conseilTextFlow.getChildren().clear();
        Font font = new Font(20);
        for (int i = 0; i < conseils.length; i++) {
            Text text = new Text("➤ " + conseils[i] + "\n");
            text.setFont(font);
            this.conseilTextFlow.getChildren().add(text);
        }
    }
}
