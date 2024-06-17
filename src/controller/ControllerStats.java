package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.math4.legacy.stat.regression.SimpleRegression;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

    @FXML
    private StackPane window;

    public void initialize() {
        List<String> items = Arrays.asList("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison",
                "nbAppart", "depCulturellesTotales", "budgetTotal");

        this.firstVarChoice.getItems().addAll(items);
        this.secondVarChoice.getItems().addAll(items);

        this.graphChoice.getItems().addAll("Barre", "Ligne", "Nuage de points");
    }

    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleCrossFactors();
        }
    }

    @FXML
    private void handleCrossFactors() {
        String firstVar = firstVarChoice.getValue();
        String secondVar = secondVarChoice.getValue();
        String graphValue = graphChoice.getValue();

        if (firstVar != null && secondVar != null && graphValue != null) {
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

            Chart elem = null;

            if (graphValue.equals("Barre")) {
                elem = GraphGenerator.generateBarChart(firstVarValues, firstVar, secondVar);
            } else if (graphValue.equals("Ligne")) {
                elem = GraphGenerator.generateLineChart(firstVarValues, secondVarValues, firstVar, secondVar);
            } else if (graphValue.equals("Nuage de points")) {
                elem = GraphGenerator.generateScatterChart(firstVarValues, secondVarValues, firstVar, secondVar);
            }

            if (elem != null) {
                elem.prefWidthProperty().bind(graphDisplayPane.widthProperty());
                elem.prefHeightProperty().bind(graphDisplayPane.heightProperty());
                graphDisplayPane.getChildren().add(elem);
            }

            // Display statistics
            this.displayStatistics(this.dataTextFlow, firstVarValues, secondVarValues, firstVar, secondVar);
            this.displayAnalysis(this.analyseTextFlow, firstVarValues, secondVarValues);
            this.resize();
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

        textFlow.getChildren().clear(); // Réinitialise le contenu du TextFlow
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
        DoubleBinding scale = super.getScale(window);

        DoubleBinding fontSize = scale.multiply(30);

        final String buttonLabelStyle = "-fx-font-size: %.2f;";

        for (Node node : this.dataTextFlow.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                text.styleProperty().bind(Bindings.format(buttonLabelStyle, fontSize));
            }
        }

        for (Node node : this.analyseTextFlow.getChildren()) {
            if (node instanceof Text) {
                Text text = (Text) node;
                text.styleProperty().bind(Bindings.format(buttonLabelStyle, fontSize));
            }
        }
    }

    @Override
    public void onViewOpened() {
        resize();
    }
}
