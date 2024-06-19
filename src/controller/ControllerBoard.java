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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.Calculator;
import model.data.CommunesInfoParAnnee;

/**
 * Permet de gérer la vue de board.fxml
 */
public class ControllerBoard extends Controller {

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

    /**
     * LineChart représentant l'évolution du score moyen de l'attractivité de la
     * Bretagne par année
     */
    @FXML
    private LineChart<Number, Number> lineChartBretagneAtt;

    /**
     * Tableau de booléens indiquant si les graphes ont été chargés
     */
    private boolean[] graphCharged = new boolean[3];

    /**
     * Méthode appelée lors de la première initialisation de la vue
     */
    @Override
    public void initialize() {
        resize();
    }

    /**
     * Méthode appelée lors de l'ouverture de la vue
     */
    public void onViewOpened() {
        setConseilText(super.getModel().generateConseil());
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
        resize();
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

        // Change la taille de la police du conseil
        this.conseilText.styleProperty()
                .bind(Bindings.concat("-fx-font-size: ", fontSize.add(8).asString(), "px;", "-fx-font-weight: bold;"));

        for (Node node : this.conseilTextFlow.getChildren()) {
            if (node instanceof Text) {
                ((Text) node).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), "px;"));
            }
        }
    }

    /**
     * Initialiser le PieChart
     */
    private void initPieChart() {

        Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                .getToutesLesCommunesInfoParAnnee();
        int scoreGlobal;
        int scoreGlobalTotal = 0;
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            scoreGlobalTotal += communesInfoParAnnee.scoreCompute();
        }
        if (toutesLesCommunesInfoParAnnee.size() == 0) {
            scoreGlobal = 0;
        } else {
            scoreGlobal = scoreGlobalTotal / toutesLesCommunesInfoParAnnee.size();
        }
        // Ajouter deux segments au PieChart : un pour le score, un pour le reste
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bretagne : " + scoreGlobal + " %", scoreGlobal),
                new PieChart.Data("", (100 - scoreGlobal)));
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

        Calculator.computeScoresAndNb(toutesLesCommunesInfoParAnnee, scores, nb);

        Calculator.normalizeScores(scores, nb);

        setupBarChart();

        addDataToChart(scores);
        graphCharged[1] = true;
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
        this.lineChartBretagneAtt.setTitle("Score d'Attractivité moyen par Année");

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

        Calculator.computeScoresAndNbByYear(toutesLesCommunesInfoParAnnee, scores, nb);

        Calculator.normalizeScoresLineChart(scores, nb);

        setupLineChart();

        addDataToLineChart(scores);
        graphCharged[2] = true;
    }

    /**
     * Permet de changer les conseils affichés
     * 
     * @param conseils Les conseils à afficher
     */
    private void setConseilText(String[] conseils) {
        this.conseilTextFlow.getChildren().clear();
        for (int i = 0; i < conseils.length; i++) {
            Text text = new Text("➤ " + conseils[i] + "\n");
            this.conseilTextFlow.getChildren().add(text);
        }
    }
}
