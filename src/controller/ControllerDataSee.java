package controller;

import java.io.IOException;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import model.TableauModel;
import model.data.CommunesInfoParAnnee;

/**
 * Permet de gérer la vue de dataSee.fxml
 */
public class ControllerDataSee extends Controller {

    /**
     * Stocke la barre de recherche
     */
    @FXML
    private TextField searchBar;

    /**
     * Stocke le tableau
     */
    @FXML
    private TableView<TableauModel> tableView;

    /**
     * Stocke tous les scores des communes pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> colScore;

    /**
     * Stocke le nom de la commune pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> colVille;

    /**
     * Stocke le code INSEE pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> colCodeInsee;

    /**
     * Stocke le département pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> colDepartement;

    /**
     * Stocke l'année pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> colAnnee;

    /**
     * Stocke le score culturel pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, String> depCulturel;

    /**
     * Stocke le nombre de communes pour le tableau
     */
    @FXML
    private TableColumn<TableauModel, Integer> colNb;

    /**
     * Stocke le BorderPane de la vue contenant le tableau et la barre de recherche
     */
    @FXML
    private BorderPane viewBorderPane;

    /**
     * Stocke le StackPane de la vue contenant les détails de la commune (fichier
     * dataDetail.fxml)
     */
    @FXML
    private StackPane viewReplace;

    /**
     * Stocke si les données ont déjà été chargées
     */
    private boolean dataCharged = false;

    /**
     * Redimensionne les colonnes du tableau en fonction de la taille de la fenêtre
     */
    protected void resize() {
        ReadOnlyDoubleProperty width = tableView.widthProperty();

        setColumnWidth(colCodeInsee, width, 0.13);
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
     * Affiche les informations de la commune sélectionnée
     * 
     * @param commune Les informations de la commune sélectionnée
     */
    @SuppressWarnings("unchecked")
    public void lineClicked(CommunesInfoParAnnee commune) {
        try {
            this.viewBorderPane.setVisible(false);
            this.viewReplace.setVisible(true);
            Controller newController = super.changeView(this.viewReplace, "/views/dataDetail.fxml");
            if (newController instanceof ReceiveInfo) {
                ((ReceiveInfo<CommunesInfoParAnnee>) newController).receiveInfo(commune);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.viewReplace.getChildren().clear();
        this.viewReplace.setVisible(false);
        this.viewBorderPane.setVisible(true);
        if (!dataCharged) {
            tableView.setRowFactory(new TableRowFactory(this));
            for (CommunesInfoParAnnee commune : super.getModel().getToutesLesCommunesInfoParAnnee().values()) {
                TableauModel tableauModel = new TableauModel(commune);
                tableView.getItems().add(tableauModel);
            }
            tableView.getSortOrder().add(colScore);
            colScore.setSortType(TableColumn.SortType.DESCENDING);
            tableView.sort();
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
                String codeInsee = String.valueOf(commune.getLaCommune().getIdCommune());
                String nomDep = commune.getLaCommune().getLeDepartement().getNomDep();
                String annee = String.valueOf(commune.getLannee().getAnneeRepr());
                if (!(nomCommune.toLowerCase().startsWith(filter.toLowerCase())
                        || codeInsee.toLowerCase().startsWith(filter.toLowerCase())
                        || nomDep.toLowerCase().startsWith(filter.toLowerCase())
                        || annee.toLowerCase().startsWith(filter.toLowerCase()))) {
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

    /**
     * Permet de cliquer sur une ligne du tableau pour afficher les informations
     */
    private class TableRowFactory implements Callback<TableView<TableauModel>, TableRow<TableauModel>> {
        /**
         * Stocke le contrôleur de la vue
         */
        private final ControllerDataSee controller;

        /**
         * Constructeur de la classe
         *
         * @param controller Le contrôleur de la vue
         */
        public TableRowFactory(ControllerDataSee controller) {
            this.controller = controller;
        }

        /**
         * Définit le comportement lorsqu'une ligne est cliquée
         */
        @Override
        public TableRow<TableauModel> call(TableView<TableauModel> tableView) {
            TableRow<TableauModel> row = new TableRow<>();
            row.setOnMouseClicked(new TableRowClickHandler(row));
            return row;
        }

        /**
         * Classe interne permettant de gérer le clic sur une ligne
         */
        private class TableRowClickHandler implements EventHandler<MouseEvent> {
            /**
             * Stocke la ligne cliquée
             */
            private final TableRow<TableauModel> row;

            /**
             * Constructeur de la classe
             *
             * @param row La ligne cliquée
             */
            public TableRowClickHandler(TableRow<TableauModel> row) {
                this.row = row;
            }

            /**
             * Définit le comportement lorsqu'une ligne est cliquée
             */
            @Override
            public void handle(MouseEvent event) {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    TableauModel clickedRow = row.getItem();
                    controller.lineClicked(clickedRow.getLaCommune());
                }
            }
        }
    }
}