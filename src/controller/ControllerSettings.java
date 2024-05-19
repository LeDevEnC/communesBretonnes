package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ControllerSettings {

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
    private Button bt_connexion;

    @FXML
    private Button bt_exporter;

    @FXML
    private Hyperlink lien_contact;



    @FXML
    private void initialize() {
        txt_titre.prefWidthProperty().bind(pane_parametre.widthProperty().multiply(0.8));
        txt_connexion.prefWidthProperty().bind(pane_parametre.widthProperty().multiply(0.8));
        txt_info_logiciel.prefWidthProperty().bind(pane_parametre.widthProperty().multiply(0.8));
        txt_version.prefWidthProperty().bind(pane_parametre.widthProperty().multiply(0.8));
        lien_contact.prefWidthProperty().bind(pane_parametre.widthProperty().multiply(0.8));
        lien_contact.setOnAction(event -> lienContactPress());
    }


    @FXML
    private void btConnexionPress(){
        //TODO
    }

    @FXML
    private void btExporterPress(){
        //TODO
    }

    @FXML
    private void lienContactPress(){
        //TODO
    }
}
