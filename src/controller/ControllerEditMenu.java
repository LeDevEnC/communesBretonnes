package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 * Contrôleur gérant le menu d'édition, permettant à l'utilisateur de
 * sélectionner différentes options pour insérer, éditer ou supprimer des
 * données. Ce contrôleur gère également la déconnexion et l'ajustement
 * dynamique de l'interface utilisateur en fonction de la taille de la fenêtre.
 */
public class ControllerEditMenu extends Controller {

    /**
     * Style appliqué aux boutons lorsqu'ils sont cliqués.
     */
    private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: #d3d3d3;";

    /**
     * La sélection courante de l'utilisateur.
     */
    private String currentSelection;

    /** Fenêtre de la vue */
    @FXML
    private StackPane window;

    /** Panneau pour le titre */
    @FXML
    private StackPane titreStackPane;

    /** Panneau destiné à être remplacé par d'autres contenus. */
    @FXML
    private StackPane toReplaceStackPane;

    /** Panneau de sélection dans l'interface utilisateur. */
    @FXML
    private StackPane selectStackPane;

    /** Texte affichant l'état de connexion. */
    @FXML
    private Text connectedAsText;

    /** Texte affichant le nom de l'utilisateur connecté. */
    @FXML
    private Text userText;

    /** Texte pour le bouton de déconnexion. */
    @FXML
    private Text disconnectText;

    /** Label du titre de la fenêtre. */
    @FXML
    private Label titleLabel;

    /** Bouton pour l'option 'Aéroport'. */
    @FXML
    private Button aeroButton;

    /** Bouton pour l'option 'Commune'. */
    @FXML
    private Button communeButton;

    /** Bouton pour l'option 'Gare'. */
    @FXML
    private Button gareButton;

    /** Bouton pour l'option 'Département'. */
    @FXML
    private Button departementButton;

    /** Bouton pour l'option 'Voisinage'. */
    @FXML
    private Button voisinageButton;

    /** Bouton pour l'option 'Données Annuelles'. */
    @FXML
    private Button donneesAnnuellesButton;

    /** Label pour le bouton 'Données Annuelles'. */
    @FXML
    private Label donneesAnnuellesButtonLabel;

    /** Label pour le bouton 'Aéroport'. */
    @FXML
    private Label aeroButtonLabel;

    /** Label pour le bouton 'Commune'. */
    @FXML
    private Label communeButtonLabel;

    /** Label pour le bouton 'Gare'. */
    @FXML
    private Label gareButtonLabel;

    /** Label pour le bouton 'Département'. */
    @FXML
    private Label departementButtonLabel;

    /** Label pour le bouton 'Voisinage'. */
    @FXML
    private Label voisinageButtonLabel;

    /** Label pour le bouton 'Année'. */
    @FXML
    private Label anneeButtonLabel;

    /** Ligne séparatrice dans l'interface utilisateur. */
    @FXML
    private Line separatorLine;

    /** Bouton pour l'option 'Année'. */
    @FXML
    private Button anneeButton;

    /** Bouton pour insérer de nouvelles données. */
    @FXML
    private Button insertButton;

    /** Label pour le bouton 'Insérer'. */
    @FXML
    private Label insertButtonLabel;

    /** Image pour le bouton 'Insérer'. */
    @FXML
    private ImageView insertButtonImg;

    /** Bouton pour éditer les données existantes. */
    @FXML
    private Button editButton;

    /** Label pour le bouton 'Éditer'. */
    @FXML
    private Label editButtonLabel;

    /** Image pour le bouton 'Éditer'. */
    @FXML
    private ImageView editButtonImg;

    /**
     * Gère l'événement de clic sur le bouton d'insertion, changeant la vue pour
     * permettre l'insertion de données.
     */
    @SuppressWarnings("unchecked")
    @FXML
    private void insertButtonClicked() {
        this.selectStackPane.setVisible(false);
        this.toReplaceStackPane.setVisible(true);
        try {
            Controller controller = super.changeView(toReplaceStackPane, "/views/editInsert.fxml");
            if (controller instanceof ReceiveInfo) {
                ((ReceiveInfo<String>) controller).receiveInfo(this.currentSelection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'événement de clic sur le bouton d'édition, changeant la vue pour
     * permettre l'édition ou la suppression de données.
     */
    @SuppressWarnings("unchecked")
    @FXML
    private void editButtonClicked() {
        this.selectStackPane.setVisible(false);
        this.toReplaceStackPane.setVisible(true);
        try {
            Controller controller = super.changeView(toReplaceStackPane, "/views/editEditDelete.fxml");
            if (controller instanceof ReceiveInfo) {
                ((ReceiveInfo<String>) controller).receiveInfo(this.currentSelection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gère l'événement de pression sur le texte de déconnexion, déconnectant
     * l'utilisateur.
     */
    @FXML
    private void disconnectTextPressed() {
        super.getModel().logout(true);
    }

    /**
     * Ajuste la taille des éléments de l'interface utilisateur en fonction de la
     * taille de la fenêtre.
     */
    @Override
    protected void resize() {
        DoubleBinding scale = super.getScale(window);

        DoubleBinding autoResizeButton = scale.multiply(400);
        DoubleBinding autoResizeTitleLabel = scale.multiply(50);

        aeroButton.prefWidthProperty().bind(autoResizeButton);
        communeButton.prefWidthProperty().bind(autoResizeButton);
        gareButton.prefWidthProperty().bind(autoResizeButton);
        departementButton.prefWidthProperty().bind(autoResizeButton);
        voisinageButton.prefWidthProperty().bind(autoResizeButton);
        anneeButton.prefWidthProperty().bind(autoResizeButton);
        donneesAnnuellesButton.prefWidthProperty().bind(autoResizeButton);

        DoubleBinding fontSize = scale.multiply(30);

        final String fontSizeStyle = "-fx-font-size: ";
        final String fontSizeSuffix = "px; -fx-font-weight: bold;";

        aeroButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        communeButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        gareButtonLabel.styleProperty()
                .bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        departementButtonLabel.styleProperty()
                .bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        voisinageButtonLabel.styleProperty()
                .bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        anneeButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        donneesAnnuellesButtonLabel.styleProperty()
                .bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));

        titreStackPane.minHeightProperty().bind(voisinageButton.heightProperty());
        titreStackPane.maxHeightProperty().bind(voisinageButton.heightProperty());
        titleLabel.styleProperty()
                .bind(Bindings.concat("-fx-font-size: ", autoResizeTitleLabel.asString(), ";-fx-font-weight: bold;"));

        separatorLine.startXProperty().bind(autoResizeButton);
        separatorLine.endXProperty().bind(window.widthProperty());

        DoubleBinding autoResizeButtonInsertEdit = scale.multiply(1000);
        DoubleBinding autoResizeButtonInsertEditLabel = scale.multiply(50);

        insertButton.prefWidthProperty().bind(autoResizeButtonInsertEdit);
        editButton.prefWidthProperty().bind(autoResizeButtonInsertEdit);

        insertButtonLabel.styleProperty()
                .bind(Bindings.concat(fontSizeStyle, autoResizeButtonInsertEditLabel.asString(), fontSizeSuffix));

        editButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle,
                autoResizeButtonInsertEditLabel.asString(), fontSizeSuffix));

        DoubleBinding autoResizeButtonInsertEditImg = scale.multiply(100);
        insertButtonImg.fitWidthProperty().bind(autoResizeButtonInsertEditImg);
        editButtonImg.fitHeightProperty().bind(autoResizeButtonInsertEditImg);
    }

    /**
     * Initialise le contrôleur, ajustant la taille des éléments de l'interface
     * utilisateur.
     */
    public void initialize() {
        resize();
    }

    /**
     * Réinitialise la couleur des boutons du menu à leur état par défaut.
     */
    public void onViewOpened() {
        this.userText.setText(super.getModel().getUsername() + "\n");
        this.donneesAnnuellesButtonClicked();
    }

    /**
     * Réinitialise la couleur des boutons du menu
     */
    private void resetButtonColor() {
        this.donneesAnnuellesButton.setStyle("");
        this.aeroButton.setStyle("");
        this.communeButton.setStyle("");
        this.gareButton.setStyle("");
        this.departementButton.setStyle("");
        this.voisinageButton.setStyle("");
        this.anneeButton.setStyle("");
    }

    /**
     * Réinitialise le contenu du StackPane de remplacement et rend le StackPane de
     * sélection visible.
     */
    private void resetStackPane() {
        this.selectStackPane.setVisible(true);
        this.toReplaceStackPane.getChildren().clear();
        this.toReplaceStackPane.setVisible(false);
    }

    /**
     * Gère l'événement de clic sur le bouton des données annuelles, mettant à jour
     * la sélection courante.
     */
    @FXML
    private void donneesAnnuellesButtonClicked() {
        resetButtonColor();
        this.donneesAnnuellesButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "donneesannuelles";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton aéroport, mettant à jour la sélection
     * courante.
     */
    @FXML
    private void aeroButtonClicked() {
        resetButtonColor();
        this.aeroButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "aeroport";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton commune, mettant à jour la sélection
     * courante.
     */
    @FXML
    private void communeButtonClicked() {
        resetButtonColor();
        this.communeButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "commune";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton gare, mettant à jour la sélection
     * courante.
     */
    @FXML
    private void gareButtonClicked() {
        resetButtonColor();
        this.gareButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "gare";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton département, mettant à jour la
     * sélection courante.
     */
    @FXML
    private void departementButtonClicked() {
        resetButtonColor();
        this.departementButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "departement";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton voisinage, mettant à jour la sélection
     * courante.
     */
    @FXML
    private void voisinageButtonClicked() {
        resetButtonColor();
        this.voisinageButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "voisinage";
        this.resetStackPane();
    }

    /**
     * Gère l'événement de clic sur le bouton année, mettant à jour la sélection
     * courante.
     */
    @FXML
    private void anneeButtonClicked() {
        resetButtonColor();
        this.anneeButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "annee";
        this.resetStackPane();
    }
}
