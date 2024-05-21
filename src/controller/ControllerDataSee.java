package controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    // TODO : Remplacer les ? par les vrais types de données (non implémenté pour le
    // moment)
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

    protected void resize() {
        ReadOnlyDoubleProperty width = tableView.widthProperty();

        setColumnWidth(colCodePostal, width, 0.15);
        setColumnWidth(colDepartement, width, 0.15);
        setColumnWidth(colINSEE, width, 0.15);
        setColumnWidth(colPrix, width, 0.15);
        setColumnWidth(colNb, width, 0.15);
    }

    public void initialize() {
        resize();
    }

    private void setColumnWidth(
            TableColumn<?, ?> column, ReadOnlyDoubleProperty width, double percentage) {
        column.prefWidthProperty().bind(width.multiply(percentage));
        column.minWidthProperty().set(0); // Ajoutez cette ligne
    }

    public void onViewOpened() {
    };
}
