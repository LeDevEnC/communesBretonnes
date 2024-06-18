package controller;

import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Permet de gérer la vue de settings.fxml
 */
public class ControllerSettings extends Controller {

    /**
     * Le panneau principal des paramètres de l'application.
     */
    @FXML
    private StackPane paneParametre;

    /**
     * Le texte du titre de la section des paramètres.
     */
    @FXML
    private Label txtTitre;

    /**
     * Le texte indiquant la section de connexion dans les paramètres.
     */
    @FXML
    private Label txtConnexion;

    /**
     * Le texte fournissant des informations sur le logiciel.
     */
    @FXML
    private Label txtInfoLogiciel;

    /**
     * Le texte affichant la version actuelle du logiciel.
     */
    @FXML
    private Label txtVersion;

    /**
     * Le bouton permettant à l'utilisateur de se déconnecter.
     */
    @FXML
    private Button btDeconnexion;

    /**
     * Le bouton pour exporter des données ou des paramètres de l'application.
     */
    @FXML
    private Button btExporter;

    /**
     * Le lien hypertexte pour contacter le support ou obtenir plus d'informations.
     */
    @FXML
    private Hyperlink lienContact;

    /**
     * Sauvegarde la taille initiale du label titre
     */
    double initialSizeTitre;

    /**
     * Sauvegarde la taille initiale du label connexion
     */
    double initialSizeConnexion;

    /**
     * Sauvegarde la taille initiale du label info logiciel
     */
    double initialSizeInfoLogiciel;

    /**
     * Sauvegarde la taille initiale du label version
     */
    double initialSizeVersion;

    /**
     * Sauvegarde la taille initiale du label contact
     */
    double initialSizeContact;

    /**
     * Redimensionne les éléments de la vue
     */
    protected void resize() {
        DoubleBinding scale = getScale(paneParametre);
        DoubleBinding labelSize = scale.multiply(1);
        DoubleBinding buttonSize = scale.multiply(800);

        this.btDeconnexion.prefWidthProperty().bind(buttonSize);
        this.btExporter.prefWidthProperty().bind(buttonSize);

        final String STRING_FROMAT = "-fx-font-size: %.2f;";

        this.btDeconnexion.styleProperty().bind(
                Bindings.createStringBinding(() -> String.format(STRING_FROMAT, labelSize.get() * 30), labelSize));
        this.btExporter.styleProperty().bind(
                Bindings.createStringBinding(() -> String.format(STRING_FROMAT, labelSize.get() * 30), labelSize));

        this.txtTitre.styleProperty().bind(Bindings.createStringBinding(
                () -> String.format(STRING_FROMAT, labelSize.get() * this.initialSizeTitre), labelSize));
        this.txtConnexion.styleProperty().bind(Bindings.createStringBinding(
                () -> String.format(STRING_FROMAT, labelSize.get() * this.initialSizeConnexion), labelSize));
        this.txtInfoLogiciel.styleProperty().bind(Bindings.createStringBinding(
                () -> String.format(STRING_FROMAT, labelSize.get() * this.initialSizeInfoLogiciel), labelSize));
        this.txtVersion.styleProperty().bind(Bindings.createStringBinding(
                () -> String.format(STRING_FROMAT, labelSize.get() * this.initialSizeVersion), labelSize));
        this.lienContact.styleProperty().bind(Bindings.createStringBinding(
                () -> String.format(STRING_FROMAT, labelSize.get() * this.initialSizeContact), labelSize));
    }

    /**
     * Méthode appelée lors de la création de la vue
     */
    public void initialize() {
        this.initialSizeTitre = this.txtTitre.getFont().getSize();
        this.initialSizeConnexion = this.txtConnexion.getFont().getSize();
        this.initialSizeInfoLogiciel = this.txtInfoLogiciel.getFont().getSize();
        this.initialSizeVersion = this.txtVersion.getFont().getSize();
        this.initialSizeContact = this.lienContact.getFont().getSize();
        resize();
    }

    /**
     * Méthode appelée lors de l'appui sur le bouton de déconnexion
     */
    @FXML
    private void btDeconnexionPress() {
        super.getModel().logout(true);
        onViewOpened();
    }

    /**
     * Méthode appelée lors de l'appui sur le bouton d'exportation
     */
    @FXML
    private void btExporterPress() {
        Stage primaryStage = super.getApp().getPrimaryStage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        String userHome = System.getProperty("user.home");
        directoryChooser.setInitialDirectory(new File(userHome));
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        if (selectedDirectory != null) {
            boolean result = super.getModel().exportData(selectedDirectory.getAbsolutePath());
            Alert alert;
            if (result) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Exportation réussie");
                alert.setHeaderText("Exportation réussie");
                alert.setContentText("Les données ont été exportées avec succès");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Exportation échouée");
                alert.setHeaderText("Exportation échouée");
                alert.setContentText("Une erreur est survenue lors de l'exportation des données");
            }
            alert.show();
        }
    }

    /**
     * Méthode appelée lors de l'appui sur le lien de contact
     */
    @FXML
    private void lienContactPress() {
        openWebLink("https://www.bretagne.bzh/contact/");
    }

    /**
     * Méthode appelée à chaque ouverture de la vue
     */
    public void onViewOpened() {
        if (super.getModel().isLogged()) {
            txtConnexion.setText("Connecté en tant que : " + (super.getModel().getUsername()));
            btDeconnexion.setDisable(false);
        } else {
            txtConnexion.setText("Non connecté");
            btDeconnexion.setDisable(true);
        }
    }
}
