package controller;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.TableauModel;
import model.data.CommunesInfoParAnnee;

public class ControllerDataSee extends Controller {
    @FXML
    private Button buttonSelectPrevious;

    @FXML
    private Button buttonSelectNext;

    @FXML
    private Button buttonSelectOne;

    @FXML
    private Button buttonSelectTwo;

    @FXML
    private Button buttonSelectThree;

    @FXML
    private TextField searchBar;

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

        setColumnWidth(colCodePostal, width, 0.13);
        setColumnWidth(colDepartement, width, 0.13);
        setColumnWidth(colAnnee, width, 0.13);
        setColumnWidth(depCulturel, width, 0.13);
        setColumnWidth(colNb, width, 0.13);
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

    /**
     * Permet de rechercher des communes dans le tableau
     * Permet de mettre plusieurs filtre en même temps via un espace
     * Les informations filtrables sont :
     * - Le nom de la commune
     * - Le code postal
     * - Le nom du département
     * - L'année
     *
     * Les filtres sont insensibles à la casse
     * Exemple de filtre : "Rennes 2020" ou "35 2019"
     *
     * Les filtres ne sont pas obligatoirement dans l'ordre ni complet
     * Si on met "re" dans le filtre, toutes les communes contenant "re" dans leur
     * nom seront affichées
     *
     * Attention : Mettre un nombre comme juste 21 prendra en compte les années 2021
     * et les codes postaux contenant le nombre 21
     */
    @FXML
    private void searchBarTextEntered() {
        tableView.getItems().clear();
        String search = searchBar.getText();
        String[] filters = search.split(" ");
        boolean ok;

        for (CommunesInfoParAnnee commune : super.getModel().getToutesLesCommunesInfoParAnnee().values()) {
            ok = true;
            int i = 0;
            while (ok && i < filters.length) {
                String filter = filters[i];
                String nomCommune = commune.getLaCommune().getNomCommune();
                String codePostal = String.valueOf(commune.getLaCommune().getIdCommune());
                String nomDep = commune.getLaCommune().getLeDepartement().getNomDep();
                String annee = String.valueOf(commune.getLannee());

                if (!(nomCommune.toLowerCase().contains(filter.toLowerCase())
                        || codePostal.toLowerCase().contains(filter.toLowerCase())
                        || nomDep.toLowerCase().contains(filter.toLowerCase())
                        || annee.toLowerCase().contains(filter.toLowerCase()))) {
                    ok = false;
                }
                i++;
            }
            if (ok) {
                TableauModel tableauModel = new TableauModel(commune);
                tableView.getItems().add(tableauModel);
            }
        }
    }
}
