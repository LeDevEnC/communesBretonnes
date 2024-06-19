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

/**
 * Permet de gérer la vue de menu.fxml
 */
public class ControllerMenu extends Controller {

    /**
     * Style du bouton lorsqu'il est cliqué
     */
    private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: #d3d3d3;";

    /** VBox principal contenant les éléments de l'interface du menu. */
    @FXML
    private VBox mainVBox;

    /** Bouton pour accéder aux paramètres de l'application. */
    @FXML
    private Button mainButtonSettings;

    /** Label du bouton pour accéder aux paramètres de l'application. */
    @FXML
    private Label mainButtonSettingsLabel;

    /** Image du bouton pour accéder aux paramètres de l'application. */
    @FXML
    private ImageView mainButtonSettingsImg;

    /** Bouton pour accéder à l'édition des données. */
    @FXML
    private Button mainButtonEdit;

    /** Label du bouton pour accéder à l'édition des données. */
    @FXML
    private Label mainButtonEditLabel;

    /** Image du bouton pour accéder à l'édition des données. */
    @FXML
    private ImageView mainButtonEditImg;

    /** Bouton pour accéder aux statistiques. */
    @FXML
    private Button mainButtonStats;

    /** Label du bouton pour accéder aux statistiques. */
    @FXML
    private Label mainButtonStatsLabel;

    /** Image du bouton pour accéder aux statistiques. */
    @FXML
    private ImageView mainButtonStatsImg;

    /** Bouton pour voir les données. */
    @FXML
    private Button mainButtonDataSee;

    /** Label du bouton pour voir les données. */
    @FXML
    private Label mainButtonDataSeeLabel;

    /** Image du bouton pour voir les données. */
    @FXML
    private ImageView mainButtonDataSeeImg;

    /** Bouton pour accéder au tableau de bord. */
    @FXML
    private Button mainButtonBoard;

    /** Label du bouton pour accéder au tableau de bord. */
    @FXML
    private Label mainButtonBoardLabel;

    /** Bouton pour accéder à la carte. */
    @FXML
    private Button mainButtonMap;

    /** Bouton pour accéder à la carte. */
    @FXML
    private Label mainButtonMapLabel;

    /** Image du bouton pour accéder à la carte. */
    @FXML
    private ImageView mainButtonMapImg;

    /** Image du bouton pour accéder au tableau de bord. */
    @FXML
    private ImageView mainButtonBoardImg;

    /** Image représentant le logo ou un élément graphique de la région Bretagne. */
    @FXML
    private ImageView mainBzhImg;

    /** SplitPane principal de la fenêtre de l'application. */
    @FXML
    private SplitPane window;

    /** StackPane utilisé pour afficher les différentes vues de l'application. */
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
        DoubleBinding autoResizeIcon = scale.multiply(50);

        this.mainBzhImg.fitWidthProperty().bind(autoResizeLogo);
        this.mainBzhImg.fitHeightProperty().bind(autoResizeLogo);
        this.mainBzhImg.setPreserveRatio(true);

        this.mainVBox.prefWidthProperty().bind(autoResizeLogo);

        this.mainButtonSettings.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonEdit.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonStats.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonDataSee.prefWidthProperty().bind(autoResizeLogo);
        this.mainButtonBoard.prefWidthProperty().bind(autoResizeLogo);

        final String buttonLabelStyle = "-fx-font-size: %.2f; -fx-font-weight: bold;";
        this.mainButtonEditLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));
        this.mainButtonStatsLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));
        this.mainButtonDataSeeLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));
        this.mainButtonMapLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));
        this.mainButtonBoardLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));
        this.mainButtonSettingsLabel.styleProperty()
                .bind(Bindings.format(buttonLabelStyle, fontSize));

        this.mainButtonBoardImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonBoardImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonBoardImg.setPreserveRatio(true);

        this.mainButtonDataSeeImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonDataSeeImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonDataSeeImg.setPreserveRatio(true);

        this.mainButtonMapImg.fitWidthProperty().bind(autoResizeIcon);
        this.mainButtonMapImg.fitHeightProperty().bind(autoResizeIcon);
        this.mainButtonMapImg.setPreserveRatio(true);

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
        this.mainButtonMap.setStyle("");
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
        this.resetButtonColor();
        this.mainButtonSettings.setStyle(CLICKED_BUTTON_STYLE);
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
        this.resetButtonColor();
        this.mainButtonEdit.setStyle(CLICKED_BUTTON_STYLE);
        try {
            changeView(appView, "/views/login.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

    /**
     * Ouvre la vue d'édition lorsque l'on clique sur le bouton "Carte" dans le
     * menu principal.
     * Permet de changer la vue principale de l'application et la couleur du bouton.
     */
    @FXML
    private void buttonMapPressed() {
        this.resetButtonColor();
        this.mainButtonMap.setStyle(CLICKED_BUTTON_STYLE);
        try {
            changeView(appView, "/views/map.fxml");
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
        this.resetButtonColor();
        this.mainButtonStats.setStyle(CLICKED_BUTTON_STYLE);

        try {
            changeView(appView, "/views/stats.fxml");
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
        this.resetButtonColor();
        this.mainButtonDataSee.setStyle(CLICKED_BUTTON_STYLE);

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
        this.resetButtonColor();
        this.mainButtonBoard.setStyle(CLICKED_BUTTON_STYLE);
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