package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextFlow;
import model.GraphGenerator;
import model.data.CommunesInfoParAnnee;
import java.util.ArrayList;
import java.util.Map;

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
    private TextFlow dataTextFlow;  // Add this line

    private String firstVar;
    private String secondVar;
    private ArrayList<Double> firstVarValues;
    private ArrayList<Double> secondVarValues;

    public void initialize() {
        this.firstVarChoice.getItems().addAll("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison",
                "nbAppart", "depCulturellesTotales", "budgetTotal");
        this.secondVarChoice.getItems().addAll("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison",
                "nbAppart", "depCulturellesTotales", "budgetTotal");

        this.graphChoice.getItems().addAll("Barre", "Ligne", "Nuage de points");

        crossFactors.setOnAction(event -> handleCrossFactors());
    }

    private void handleCrossFactors() {
        firstVar = firstVarChoice.getValue();
        secondVar = secondVarChoice.getValue();

        if (firstVar != null && secondVar != null) {
            Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel()
                    .getToutesLesCommunesInfoParAnnee();

            firstVarValues = new ArrayList<>();
            secondVarValues = new ArrayList<>();

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
                BarChart<String, Number> barChart = GraphGenerator.generateBarChart(firstVarValues, firstVar, secondVar);
                graphDisplayPane.getChildren().add(barChart);
            } else if (graphChoice.getValue().equals("Ligne")) {
                LineChart<Number, Number> lineChart = GraphGenerator.generateLineChart(firstVarValues, secondVarValues, firstVar, secondVar);
                graphDisplayPane.getChildren().add(lineChart);
            } else if (graphChoice.getValue().equals("Nuage de points")) {
                ScatterChart<Number, Number> scatterChart = GraphGenerator.generateScatterChart(firstVarValues, secondVarValues, firstVar, secondVar);
                graphDisplayPane.getChildren().add(scatterChart);
            }

            // Display statistics
            GraphGenerator.displayStatistics(dataTextFlow, firstVarValues, secondVarValues, firstVar, secondVar);
        } else {
            System.out.println("Veuillez sélectionner des valeurs dans les deux ComboBox.");
        }
    }

    private double getVariableValue(CommunesInfoParAnnee communeInfo, String variable) {
        switch (variable) {
            case "prixMCarreMoyen":
                return communeInfo.getPrixMCarreMoyen();
            case "surfaceMoy":
                return communeInfo.getSurfaceMoy();
            case "prixMoyen":
                return communeInfo.getPrixMoyen();
            case "population":
                return communeInfo.getPopulation();
            case "nbMaison":
                return communeInfo.getNbMaison();
            case "nbAppart":
                return communeInfo.getNbAppart();
            case "depCulturellesTotales":
                return communeInfo.getDepCulturellesTotales();
            case "budgetTotal":
                return communeInfo.getBudgetTotal();
            default:
                return 0;
        }
    }

    @Override
    protected void resize() {
        System.out.println("View opened");
    }

    @Override
    public void onViewOpened() {
        System.out.println("View opened");
    }
}
