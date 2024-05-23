package controller;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerMenu extends Controller {
    /**
     * Stocke le menu actuellement affiché
     */
    String currentMenu = "board";

    @FXML
    private VBox mainVBox;

    @FXML
    private Button mainButtonSettings;

    @FXML
    private Label mainButtonSettingsLabel;

    @FXML
    private ImageView mainButtonSettingsImg;

    @FXML
    private Button mainButtonEdit;

    @FXML
    private Label mainButtonEditLabel;

    @FXML
    private ImageView mainButtonEditImg;

    @FXML
    private Button mainButtonStats;

    @FXML
    private Label mainButtonStatsLabel;

    @FXML
    private ImageView mainButtonStatsImg;

    @FXML
    private Button mainButtonDataSee;

    @FXML
    private Label mainButtonDataSeeLabel;

    @FXML
    private ImageView mainButtonDataSeeImg;

    @FXML
    private Button mainButtonBoard;

    @FXML
    private Label mainButtonBoardLabel;

    @FXML
    private ImageView mainButtonBoardImg;

    @FXML
    private ImageView mainBzhImg;

    @FXML
    private SplitPane window;

    @FXML
    private StackPane appView;

    /**
     * Ouvre le site de la région bretagne dans le navigateur par défaut lorsque on
     * clique sur l'image de la région
     */
    @FXML
    private void openImgLink() {
        super.openWebLink("https://www.bretagne.bzh/");
    }

    /**
     * Redimensionne les éléments de la vue en fonction de la taille de la fenêtre
     */
    protected void resize() {
        DoubleBinding scale = super.getScale(window);

        DoubleBinding autoResizeLogo = scale.multiply(300);
        DoubleBinding fontSize = scale.multiply(30);
        DoubleBinding autoResizeIcon = scale.multiply(80);

        this.mainBzhImg.fitWidthProperty().bind(autoResizeLogo);
        this.mainBzhImg.fitHeightProperty().bind(autoResizeLogo);
        this.mainBzhImg.setPreserveRatio(true);

        this.mainVBox.prefWidthProperty().bind(autoResizeLogo);

        this.mainButtonSettings.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonEdit.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonStats.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonDataSee.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonBoard.prefWidthProperty().bind(autoResizeLogo);

        this.mainButtonEditLabel.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        this.mainButtonStatsLabel.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        this.mainButtonDataSeeLabel.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        this.mainButtonBoardLabel.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        this.mainButtonSettingsLabel.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));

        this.mainButtonBoardImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonBoardImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonBoardImg.setPreserveRatio(true);

        this.mainButtonDataSeeImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonDataSeeImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonDataSeeImg.setPreserveRatio(true);

        this.mainButtonStatsImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonStatsImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonStatsImg.setPreserveRatio(true);

        this.mainButtonEditImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonEditImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonEditImg.setPreserveRatio(true);

        this.mainButtonSettingsImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonSettingsImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonSettingsImg.setPreserveRatio(true);
    }

    /**
     * Réinitialise la couleur des boutons du menu principal
     */
    private void resetButtonColor() {
        this.mainButtonBoard.setStyle("");
        this.mainButtonDataSee.setStyle("");
        this.mainButtonStats.setStyle("");
        this.mainButtonEdit.setStyle("");
        this.mainButtonSettings.setStyle("");

    }

    /**
     * Initialise la vue
     */
    public void initialize() {
        resize();
    }

    /**
     * Ouvre la vue des paramètres lorsque l'on clique sur le bouton "Paramètres"
     * dans le menu principal.
     * Permet de changer la vue principale de l'application et la couleur du bouton
     */
    @FXML
    private void buttonSettingsPressed() {
        this.currentMenu = "settings";
        this.resetButtonColor();
        this.mainButtonSettings.setStyle("-fx-background-color: #d3d3d3;");
        try {
            changeView(appView, "/views/settings.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Ouvre la vue d'édition lorsque l'on clique sur le bouton "Edition" dans le
     * menu principal.
     * Permet de changer la vue principale de l'application et la couleur du bouton.
     */
    @FXML
    private void buttonEditPressed() {
        this.currentMenu = "edit";
        this.resetButtonColor();
        this.mainButtonEdit.setStyle("-fx-background-color: #d3d3d3;");
        try {
            changeView(appView, "/views/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Ouvre la vue des statistiques lorsque l'on clique sur le bouton "Croiser
     * facteurs"
     * dans le menu principal.
     * Permet de changer la vue principale de l'application et la couleur du bouton.
     */
    @FXML
    private void buttonStatsPressed() {
        this.currentMenu = "stats";
        this.resetButtonColor();
        this.mainButtonStats.setStyle("-fx-background-color: #d3d3d3;");

        try {
            changeView(appView, "/views/template.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Ouvre la vue de visualisation des données lorsque l'on clique sur le bouton
     * "Voir les données" dans le menu principal.
     * Permet de changer la vue principale de l'application et la couleur du bouton.
     */
    @FXML
    private void buttonDataSeePressed() {
        this.currentMenu = "dataSee";
        this.resetButtonColor();
        this.mainButtonDataSee.setStyle("-fx-background-color: #d3d3d3;");

        try {
            changeView(appView, "/views/dataSee.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Ouvre la vue du tableau de bord lorsque l'on clique sur le bouton "Tableau de
     * bord" dans le menu principal.
     */
    @FXML
    public void buttonBoardPressed() { // Note : public pour pouvoir appeler la vue par défaut
        this.currentMenu = "board";
        this.resetButtonColor();
        this.mainButtonBoard.setStyle("-fx-background-color: #d3d3d3;");
        try {
            changeView(appView, "/views/board.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Appelé lors de l'ouverture de la vue
     */
    public void onViewOpened() {
        this.buttonBoardPressed(); // Affiche le menu principal au démarrage de l'application
    }
}