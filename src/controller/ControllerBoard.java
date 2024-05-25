package controller;

import java.util.Map;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import model.data.CommunesInfoParAnnee;

public class ControllerBoard extends Controller {
    /**
     * Score global de l'attractivité de la Bretagne
     */
    private int scoreGlobal;

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
        // TODO : Appel resize() lorsque resize sera implémenté

    }

    /**
     * Méthode appelée lors de l'ouverture de la vue
     */
    public void onViewOpened() {
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

    protected void resize() {
        throw new UnsupportedOperationException("Not supported yet.");
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

    private void normalizeScores(int[] scores, int[] nb) {
        for (int i = 0; i < 4; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }



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



    private void addDataToChart(int[] scores) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>("Finistère : " + scores[0] + " %", scores[0]));
        series.getData().add(new XYChart.Data<>("Côtes d'Armor : " + scores[1] + " %", scores[1]));
        series.getData().add(new XYChart.Data<>("Morbihan : " + scores[2] + " %", scores[2]));
        series.getData().add(new XYChart.Data<>("Ille-et-Vilaine : " + scores[3] + " %", scores[3]));

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
    
    private void setupLineChart() {
        NumberAxis xAxis = (NumberAxis) this.lineChartBretagneAtt.getXAxis();
        NumberAxis yAxis = (NumberAxis) this.lineChartBretagneAtt.getYAxis();
        this.lineChartBretagneAtt.setTitle("Score d'Attractivité de la Bretagne en Fonction des Années");

        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(2018);
        xAxis.setUpperBound(2022);
        xAxis.setTickUnit(1);

        xAxis.setLabel("Année");
        yAxis.setLabel("Score d'Attractivité");
    }

    private void normalizeScoresLineChart(int[] scores, int[] nb) {
        for (int i = 0; i < 3; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }
    private void addDataToLineChart(int[] scores) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.getData().add(new XYChart.Data<>(2019, scores[0]));
        series.getData().add(new XYChart.Data<>(2020, scores[1]));
        series.getData().add(new XYChart.Data<>(2021, scores[2]));

        series.setName("Score d'Attractivité");
        Platform.runLater(()
                -> {

            Set<Node> nodes = this.lineChartBretagneAtt.lookupAll(".series" + 0);
            for (Node n : nodes) {
                n.setStyle("-fx-background-color: green;");
            }

            series.getNode().lookup(".chart-series-line").setStyle("-fx-stroke: #2eb82e;");
        });
        this.lineChartBretagneAtt.getData().add(series);
    }

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
}
