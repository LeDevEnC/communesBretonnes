package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.data.CommunesInfoParAnnee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ControllerCrossFactors extends Controller {

    @FXML
    private Button crossfactors;

    @FXML 
    private ComboBox<String> firstVarChoice;
    
    @FXML 
    private ComboBox<String> secondVarChoice;

    private String firstVar;
    private String secondVar;

    private ArrayList<Double> firstVarValues;
    private ArrayList<Double> secondVarValues;

    @Override
    public void initialize() {

        this.firstVarChoice.getItems().addAll("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison","nbAppart" , "depCulturellesTotales", "budgetTotal");
        this.secondVarChoice.getItems().addAll("prixMCarreMoyen", "surfaceMoy", "prixMoyen", "population", "nbMaison","nbAppart" , "depCulturellesTotales", "budgetTotal");

        this.firstVarChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.firstVar = newValue;
            System.out.println("FirstVarChoice changed to: " + firstVar);
        });

        this.secondVarChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.secondVar = newValue;
            System.out.println("SecondVarChoice changed to: " + secondVar);
        });

        this.crossfactors.setOnAction(event -> handleCrossFactors());
    }

    @Override
    public void onViewOpened() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onViewOpened'");
    }

    @Override
    protected void resize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resize'");
    }


    private void handleCrossFactors() {
        if (this.firstVar != null && this.secondVar != null) {

            Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = super.getModel().getToutesLesCommunesInfoParAnnee();

            this.firstVarValues = new ArrayList<>();
            this.secondVarValues = new ArrayList<>();

            for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
                double firstVarValue = getVariableValue(communesInfoParAnnee, this.firstVar);
                double secondVarValue = getVariableValue(communesInfoParAnnee, this.secondVar);
                if (firstVarValue != -1 && secondVarValue != -1) {
                    this.firstVarValues.add(firstVarValue);
                    this.secondVarValues.add(secondVarValue);
                }

            }
            System.out.println("Liste des valeurs pour " + firstVar + ": " + firstVarValues.toArray().toString());
            System.out.println("Liste des valeurs pour " + secondVar + ": " + secondVarValues.toArray().toString());
        } else {
            System.out.println("Veuillez sélectionner des valeurs dans les deux ComboBox.");
        }
    }

    private double getVariableValue(CommunesInfoParAnnee info, String variableName) {
        switch (variableName) {
            case "prixMCarreMoyen":
                return info.getPrixMCarreMoyen();
            case "surfaceMoy":
                return info.getSurfaceMoy();
            case "prixMoyen":
                return info.getPrixMoyen();
            case "population":
                return info.getPopulation();
            case "nbMaison":
                return info.getNbMaison();
            case "nbAppart":
                return info.getNbAppart();
            case "depCulturellesTotales":
                return info.getDepCulturellesTotales();
            case "budgetTotal":
                return info.getBudgetTotal();
            default:
                return 0;
        }
    }
}
