package controller;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ControllerDataSee {
    @FXML
    Button buttonSelectPrevious;

    @FXML
    Button buttonSelectNext;

    @FXML
    Button buttonSelectOne;

    @FXML
    Button buttonSelectTwo;

    @FXML
    Button buttonSelectThree;

    // TODO : Remplacer les ? par les vrais types de données (non implémenté pour le moment)
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, String> colScore;

    @FXML
    private TableColumn<?, String> colVille;

    @FXML
    private TableColumn<?, String> colCodePostal;

    @FXML
    private TableColumn<?, String> colDepartement;

    @FXML
    private TableColumn<?, String> colINSEE;

    @FXML
    private TableColumn<?, String> colPrix;

    @FXML
    private TableColumn<?, Integer> colNb;

    // private HashMap<String, ?> villeDataMap;

    public void initialize() {
    // Assurez-vous que la TableView est déjà initialisée ici

    // Utilisez Platform.runLater pour différer l'appel à getScene() jusqu'à ce que la Scene soit disponible
    Platform.runLater(() -> {
        // Utilisez widthProperty pour obtenir la largeur de la TableView
        ReadOnlyDoubleProperty width = tableView.widthProperty();
        width.addListener((observable, oldValue, newValue) -> {
            System.out.println("La TableView a été redimensionnée. Nouvelle largeur : " + newValue);
        });

        // Définissez la largeur préférée, minimale et maximale de chaque colonne en pourcentage de la largeur de la TableView
        setColumnWidth(colCodePostal, width, 0.15);
        setColumnWidth(colDepartement, width, 0.15);
        setColumnWidth(colINSEE, width, 0.15);
        setColumnWidth(colPrix, width, 0.15);
        setColumnWidth(colNb, width, 0.15);

        System.out.println(tableView.getScene().widthProperty());
    });
    }

    private void setColumnWidth(
            TableColumn<?, ?> column, ReadOnlyDoubleProperty width, double percentage) {
        column.prefWidthProperty().bind(width.multiply(percentage));
        column.minWidthProperty().set(0); // Ajoutez cette ligne

    }

}
