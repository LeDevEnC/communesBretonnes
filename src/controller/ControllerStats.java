package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.GraphGenerator;
import model.data.CommunesInfoParAnnee;

public class ControllerStats extends Controller {

    @FXML
    private ComboBox<String> firstVarChoice;

    @FXML
    private ComboBox<String> secondVarChoice;

    @FXML
    private Button crossFactors;

    @FXML
    private Pane graphDisplayPane;
    @FXML
    private ComboBox<String> graphChoice;

    @FXML
    private TextFlow dataTextFlow;

    @FXML
    private TextFlow analyseTextFlow;

    public void initialize() {

        List<String> items = Arrays.asList("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison",
                "nbAppart", "depCulturellesTotales", "budgetTotal");

        this.firstVarChoice.getItems().addAll(items);
        this.secondVarChoice.getItems().addAll(items);

        this.graphChoice.getItems().addAll("Barre", "Ligne", "Nuage de points");

        crossFactors.setOnAction(event -> handleCrossFactors());
    }

    private void handleCrossFactors() {
        String firstVar = firstVarChoice.getValue();
        String secondVar = secondVarChoice.getValue();

        if (firstVar != null && secondVar != null) {
            Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                    .getToutesLesCommunesInfoParAnnee();
            ArrayList<Double> firstVarValues = new ArrayList<>();
            ArrayList<Double> secondVarValues = new ArrayList<>();

            for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
                double firstVarValue = getVariableValue(communesInfoParAnnee, firstVar);
                double secondVarValue = getVariableValue(communesInfoParAnnee, secondVar);
                if (firstVarValue != -1 && secondVarValue != -1) {
                    firstVarValues.add(firstVarValue);
                    secondVarValues.add(secondVarValue);
                }
            }

            graphDisplayPane.getChildren().clear();
            if (graphChoice.getValue() == null) {
                System.out.println("Veuillez sélectionner un type de graphique.");
                return;
            } else if (graphChoice.getValue().equals("Barre")) {
                BarChart<String, Number> barChart = GraphGenerator.generateBarChart(firstVarValues, firstVar,
                        secondVar);
                graphDisplayPane.getChildren().add(barChart);
            } else if (graphChoice.getValue().equals("Ligne")) {
                LineChart<Number, Number> lineChart = GraphGenerator.generateLineChart(firstVarValues, secondVarValues,
                        firstVar, secondVar);
                graphDisplayPane.getChildren().add(lineChart);
            } else if (graphChoice.getValue().equals("Nuage de points")) {
                ScatterChart<Number, Number> scatterChart = GraphGenerator.generateScatterChart(firstVarValues,
                        secondVarValues, firstVar, secondVar);
                graphDisplayPane.getChildren().add(scatterChart);
            }

            // Display statistics
            this.displayStatistics(this.dataTextFlow, firstVarValues, secondVarValues, firstVar, secondVar);
            this.displayAnalysis(this.analyseTextFlow, firstVarValues, secondVarValues);
        } else {
            System.out.println("Veuillez sélectionner des valeurs dans les deux ComboBox.");
        }
    }

    /**
     * Display statistics (mean and variance) in a given Pane.
     * 
     * @param pane   the Pane to display the statistics
     * @param values the list of values to calculate statistics for
     */
    public void displayStatistics(TextFlow textFlow, List<Double> xValues, List<Double> yValues, String xVar,
            String yVar) { // TODO : ajouter utilité des variables xVar et yVar
        double meanX = GraphGenerator.calculateMean(xValues);
        double varianceX = GraphGenerator.calculateVariance(xValues, meanX);
        double meanY = GraphGenerator.calculateMean(yValues);
        double varianceY = GraphGenerator.calculateVariance(yValues, meanY);

        Text meanXLabel = new Text("Moyenne de : " + String.format("%.1f", meanX) + "\n");
        Text varianceXLabel = new Text("Variance de : " + String.format("%.1f", varianceX) + "\n");
        Text meanYLabel = new Text("Moyenne de : " + String.format("%.1f", meanY) + "\n");
        Text varianceYLabel = new Text("Variance de : " + String.format("%.1f", varianceY) + "\n");

        textFlow.getChildren().clear(); // Clear any existing text
        textFlow.getChildren().addAll(meanXLabel, varianceXLabel, meanYLabel, varianceYLabel);
    }

    public void displayAnalysis(TextFlow textFlow, List<Double> xValues, List<Double> yValues) {
        SimpleRegression regression = new SimpleRegression();
        int numPoints = xValues.size();

        double[] xValuesArray = xValues.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yValuesArray = yValues.stream().mapToDouble(Double::doubleValue).toArray();

        for (int i = 0; i < numPoints; i++) {
            regression.addData(xValuesArray[i], yValuesArray[i]);
        }

        double slope = regression.getSlope();
        double intercept = regression.getIntercept();
        double rValue = regression.getRSquare();
        double stdErr = regression.getSlopeStdErr();

        Text regrText = new Text("Coefficient de corrélation (R^2): " + String.format("%.4f", rValue) + "\n");
        Text slopeText = new Text("Modèle linéaire: y = " + String.format("%.4f", slope) + "x + "
                + String.format("%.4f", intercept) + "\n");
        Text slopeCoefText = new Text("Coefficient directeur: " + String.format("%.4f", slope) + "\n");
        Text stdErrText = new Text("Coefficient de variation: " + String.format("%.4f", stdErr) + "\n");

        textFlow.getChildren().clear();
        textFlow.getChildren().addAll(regrText, slopeText, slopeCoefText, stdErrText);
    }

    private double getVariableValue(CommunesInfoParAnnee communeInfo, String variable) {
        double value;
        switch (variable) {
            case "prixMCarreMoyen":
                value = communeInfo.getPrixMCarreMoyen();
                break;
            case "surfaceMoy":
                value = communeInfo.getSurfaceMoy();
                break;
            case "prixMoyen":
                value = communeInfo.getPrixMoyen();
                break;
            case "population":
                value = communeInfo.getPopulation();
                break;
            case "nbMaison":
                value = communeInfo.getNbMaison();
                break;
            case "nbAppart":
                value = communeInfo.getNbAppart();
                break;
            case "depCulturellesTotales":
                value = communeInfo.getDepCulturellesTotales();
                break;
            case "budgetTotal":
                value = communeInfo.getBudgetTotal();
                break;
            default:
                value = 0;
                break;
        }
        return value;
    }

    @Override
    protected void resize() {
        // TODO : faire la responsive
    }

    @Override
    public void onViewOpened() {
        // Inutilisé
    }
}
