package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ControllerSettings extends Controller {

    @FXML
    private StackPane pane_parametre;

    @FXML
    private Label txt_titre;

    @FXML
    private Label txt_connexion;

    @FXML
    private Label txt_info_logiciel;

    @FXML
    private Label txt_version;

    @FXML
    private Button bt_deconnexion;

    @FXML
    private Button bt_exporter;

    @FXML
    private Hyperlink lien_contact;

    double initialSizeTitre;
    double initialSizeConnexion;
    double initialSizeInfoLogiciel;
    double initialSizeVersion;
    double initialSizeContact;

    protected void resize() {
        DoubleBinding scale = getScale(pane_parametre);
        DoubleBinding labelSize = scale.multiply(1);
        DoubleBinding buttonSize = scale.multiply(800);

        bt_deconnexion.prefWidthProperty().bind(buttonSize);
        bt_exporter.prefWidthProperty().bind(buttonSize);

        bt_deconnexion.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * 30),
                        labelSize));
        bt_exporter.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * 30),
                        labelSize));

        this.txt_titre.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * this.initialSizeTitre),
                        labelSize));
        this.txt_connexion.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * this.initialSizeConnexion),
                        labelSize));
        this.txt_info_logiciel.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * this.initialSizeInfoLogiciel),
                        labelSize));
        this.txt_version.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * this.initialSizeVersion),
                        labelSize));
        this.lien_contact.styleProperty()
                .bind(Bindings.createStringBinding(
                        () -> String.format("-fx-font-size: %.2f;", labelSize.get() * this.initialSizeContact),
                        labelSize));
    }

    public void initialize() {
        this.initialSizeTitre = this.txt_titre.getFont().getSize();
        this.initialSizeConnexion = this.txt_connexion.getFont().getSize();
        this.initialSizeInfoLogiciel = this.txt_info_logiciel.getFont().getSize();
        this.initialSizeVersion = this.txt_version.getFont().getSize();
        this.initialSizeContact = this.lien_contact.getFont().getSize();
        resize();
    }

    @FXML
    private void btConnexionPress() {
        // TODO
    }

    @FXML
    private void btExporterPress() {
        // TODO
    }

    @FXML
    private void lienContactPress() {
        openWebLink("https://www.bretagne.bzh/");
    }
}
