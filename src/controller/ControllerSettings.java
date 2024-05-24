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
        private StackPane paneParametre;

        @FXML
        private Label txtTitre;

        @FXML
        private Label txtConnexion;

        @FXML
        private Label txtInfoLogiciel;

        @FXML
        private Label txtVersion;

        @FXML
        private Button btDeconnexion;

        @FXML
        private Button btExporter;

        @FXML
        private Hyperlink lienContact;

        double initialSizeTitre;
        double initialSizeConnexion;
        double initialSizeInfoLogiciel;
        double initialSizeVersion;
        double initialSizeContact;

        protected void resize() {
                DoubleBinding scale = getScale(paneParametre);
                DoubleBinding labelSize = scale.multiply(1);
                DoubleBinding buttonSize = scale.multiply(800);

                this.btDeconnexion.prefWidthProperty().bind(buttonSize);
                this.btExporter.prefWidthProperty().bind(buttonSize);

                final String STRING_FROMAT = "-fx-font-size: %.2f;";

                this.btDeconnexion.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT, labelSize.get() * 30),
                                                labelSize));
                this.btExporter.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT, labelSize.get() * 30),
                                                labelSize));

                this.txtTitre.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT,
                                                                labelSize.get() * this.initialSizeTitre),
                                                labelSize));
                this.txtConnexion.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT,
                                                                labelSize.get() * this.initialSizeConnexion),
                                                labelSize));
                this.txtInfoLogiciel.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT,
                                                                labelSize.get() * this.initialSizeInfoLogiciel),
                                                labelSize));
                this.txtVersion.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT,
                                                                labelSize.get() * this.initialSizeVersion),
                                                labelSize));
                this.lienContact.styleProperty()
                                .bind(Bindings.createStringBinding(
                                                () -> String.format(STRING_FROMAT,
                                                                labelSize.get() * this.initialSizeContact),
                                                labelSize));
        }

        public void initialize() {
                this.initialSizeTitre = this.txtTitre.getFont().getSize();
                this.initialSizeConnexion = this.txtConnexion.getFont().getSize();
                this.initialSizeInfoLogiciel = this.txtInfoLogiciel.getFont().getSize();
                this.initialSizeVersion = this.txtVersion.getFont().getSize();
                this.initialSizeContact = this.lienContact.getFont().getSize();
                resize();
        }

        @FXML
        private void btDeconnexionPress() {
                super.getModel().logout();
                onViewOpened();
        }

        @FXML
        private void btExporterPress() {
                // TODO : Ajouter l'export des données
        }

        @FXML
        private void lienContactPress() {
                openWebLink("https://www.bretagne.bzh/contact/");
        }

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
