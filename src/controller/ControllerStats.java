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

/**
 * Permet de gérer la vue de stats.fxml
 */
public class ControllerStats extends Controller {

    /**
     * ComboBox permettant à l'utilisateur de choisir la première variable pour
     * l'analyse croisée.
     */
    @FXML
    private ComboBox<String> firstVarChoice;

    /**
     * ComboBox permettant à l'utilisateur de choisir la seconde variable pour
     * l'analyse croisée.
     */
    @FXML
    private ComboBox<String> secondVarChoice;

    /** Bouton pour déclencher l'analyse croisée des facteurs sélectionnés. */
    @FXML
    private Button crossFactors;

    /** Panneau destiné à afficher le graphique résultant de l'analyse croisée. */
    @FXML
    private Pane graphDisplayPane;

    /**
     * ComboBox permettant à l'utilisateur de choisir le type de graphique pour
     * l'affichage des données.
     */
    @FXML
    private ComboBox<String> graphChoice;

    /**
     * TextFlow pour l'affichage des données brutes ou des résultats de l'analyse.
     */
    @FXML
    private TextFlow dataTextFlow;

    /**
     * TextFlow pour l'affichage de l'analyse textuelle des données ou des
     * conclusions tirées.
     */
    @FXML
    private TextFlow analyseTextFlow;

    /** Fenêtre principale de l'interface utilisateur pour ce contrôleur. */
    @FXML
    private StackPane window;

    /**
     * Est appelée lorsque la vue est ouverte pour la première fois
     * Initialise les éléments de la vue
     */
    public void initialize() {
        List<String> items = Arrays.asList("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison",
                "nbAppart", "depCulturellesTotales", "budgetTotal");

        this.firstVarChoice.getItems().addAll(items);
        this.secondVarChoice.getItems().addAll(items);

        this.graphChoice.getItems().addAll("Barre", "Ligne", "Nuage de points");
    }

    /**
     * Est appelée lorsque l'utilisateur appuie sur la touche Entrée
     * 
     * @param event L'événement déclenché par l'appui sur la touche
     */
    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleCrossFactors();
        }
    }

    /**
     * Est appelée lorsque l'utilisateur appuie sur le bouton pour lancer l'analyse
     */
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
     * Affiche les statistiques des variables
     * 
     * @param textFlow Le TextFlow où afficher les statistiques
     * @param xValues  Les valeurs de la première variable
     * @param yValues  Les valeurs de la deuxième variable
     * @param xVar     Le nom de la première variable
     * @param yVar     Le nom de la deuxième variable
     */
    public void displayStatistics(TextFlow textFlow, List<Double> xValues, List<Double> yValues, String xVar,
            String yVar) { 
        double meanX = GraphGenerator.calculateMean(xValues);
        double varianceX = GraphGenerator.calculateVariance(xValues, meanX);
        double meanY = GraphGenerator.calculateMean(yValues);
        double varianceY = GraphGenerator.calculateVariance(yValues, meanY);

        Text meanXLabel = new Text("Moyenne de X : " + String.format("%.1f", meanX) + "\n");
        Text varianceXLabel = new Text("Variance de X : " + String.format("%.1f", varianceX) + "\n");
        Text meanYLabel = new Text("Moyenne de Y : " + String.format("%.1f", meanY) + "\n");
        Text varianceYLabel = new Text("Variance de Y : " + String.format("%.1f", varianceY) + "\n");

        textFlow.getChildren().clear(); // Réinitialise le contenu du TextFlow
        textFlow.getChildren().addAll(meanXLabel, varianceXLabel, meanYLabel, varianceYLabel);
    }

    /**
     * Affiche l'analyse des données
     * 
     * @param textFlow Le TextFlow où afficher l'analyse
     * @param xValues  Les valeurs de la première variable
     * @param yValues  Les valeurs de la deuxième variable
     */
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

    /**
     * Récupère la valeur d'une variable pour une commune
     * 
     * @param communeInfo L'objet contenant les informations de la commune
     * @param variable    La variable à récupérer
     * @return La valeur de la variable
     */
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

    /**
     * Redimensionne les éléments de la vue en fonction de la taille de la fenêtre
     */
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

    /**
     * Est appelée à chaque fois que la vue est ouverte
     */
    @Override
    public void onViewOpened() {
        resize();
    }
}
