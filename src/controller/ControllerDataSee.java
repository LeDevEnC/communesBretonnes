package controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.TableauModel;
import model.data.CommunesInfoParAnnee;

public class ControllerDataSee extends Controller {
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

    @FXML
    private TableView<TableauModel> tableView;

    @FXML
    private TableColumn<TableauModel, String> colScore;

    @FXML
    private TableColumn<TableauModel, String> colVille;

    @FXML
    private TableColumn<TableauModel, String> colCodePostal;

    @FXML
    private TableColumn<TableauModel, String> colDepartement;

    @FXML
    private TableColumn<TableauModel, String> colAnnee;

    @FXML
    private TableColumn<TableauModel, String> depCulturel;

    @FXML
    private TableColumn<TableauModel, Integer> colNb;

    private boolean dataCharged = false;

    /**
     * Redimensionne les colonnes du tableau en fonction de la taille de la fenêtre
     */
    protected void resize() {
        ReadOnlyDoubleProperty width = tableView.widthProperty();

        setColumnWidth(colCodePostal, width, 0.15);
        setColumnWidth(colDepartement, width, 0.15);
        setColumnWidth(colAnnee, width, 0.15);
        setColumnWidth(depCulturel, width, 0.15);
        setColumnWidth(colNb, width, 0.15);
    }

    /**
     * Initialise le contrôleur de la vue
     */
    public void initialize() {
        resize();
    }

    /**
     * Redimensionne une colonne en fonction de la taille de la fenêtre
     * 
     * @param column     La colonne à redimensionner
     * @param width      La taille de la table
     * @param percentage Le pourcentage de la taille de la table que doit prendre la
     *                   colonne
     */
    private void setColumnWidth(TableColumn<?, ?> column, ReadOnlyDoubleProperty width, double percentage) {
        column.prefWidthProperty().bind(width.multiply(percentage));
        column.minWidthProperty().set(0);
    }

    /**
     * Rempli le tableau avec les données des communes
     */
    public void onViewOpened() {
        if (!dataCharged) {
            for (CommunesInfoParAnnee commune : super.getModel().getToutesLesCommunesInfoParAnnee().values()) {
                TableauModel tableauModel = new TableauModel(commune);
                tableView.getItems().add(tableauModel);
            }
            dataCharged = true;
        }

    }
}
