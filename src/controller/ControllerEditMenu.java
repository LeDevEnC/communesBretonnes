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

public class ControllerEditMenu extends Controller {

    private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: #d3d3d3;";

    private String currentSelection;

    @FXML
    private StackPane window;

    @FXML
    private StackPane titreStackPane;

    @FXML
    private StackPane toReplaceStackPane;

    @FXML
    private StackPane selectStackPane;

    @FXML
    private Text connectedAsText;

    @FXML
    private Text userText;

    @FXML
    private Text disconnectText;

    @FXML
    private Label titleLabel;

    @FXML
    private Button aeroButton;

    @FXML
    private Button communeButton;

    @FXML
    private Button gareButton;

    @FXML
    private Button departementButton;

    @FXML
    private Button voisinageButton;

    @FXML
    private Button donneesAnnuellesButton;

    @FXML
    private Label donneesAnnuellesButtonLabel;

    @FXML
    private Label aeroButtonLabel;

    @FXML
    private Label communeButtonLabel;

    @FXML
    private Label gareButtonLabel;

    @FXML
    private Label departementButtonLabel;

    @FXML
    private Label voisinageButtonLabel;

    @FXML
    private Label anneeButtonLabel;

    @FXML
    private Line separatorLine;

    @FXML
    private Button anneeButton;

    @FXML
    private Button insertButton;

    @FXML
    private Label insertButtonLabel;

    @FXML
    private ImageView insertButtonImg;

    @FXML
    private Button editButton;

    @FXML
    private Label editButtonLabel;

    @FXML
    private ImageView editButtonImg;

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

    @FXML
    private void disconnectTextPressed() {
        super.getModel().logout(true);
    }

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

    public void initialize() {
        resize();
    }

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

    private void resetStackPane() {
        this.selectStackPane.setVisible(true);
        this.toReplaceStackPane.getChildren().clear();
        this.toReplaceStackPane.setVisible(false);
    }

    @FXML
    private void donneesAnnuellesButtonClicked() {
        resetButtonColor();
        this.donneesAnnuellesButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "donneesannuelles";
        this.resetStackPane();
    }

    @FXML
    private void aeroButtonClicked() {
        resetButtonColor();
        this.aeroButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "aeroport";
        this.resetStackPane();
    }

    @FXML
    private void communeButtonClicked() {
        resetButtonColor();
        this.communeButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "commune";
        this.resetStackPane();
    }

    @FXML
    private void gareButtonClicked() {
        resetButtonColor();
        this.gareButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "gare";
        this.resetStackPane();
    }

    @FXML
    private void departementButtonClicked() {
        resetButtonColor();
        this.departementButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "departement";
        this.resetStackPane();
    }

    @FXML
    private void voisinageButtonClicked() {
        resetButtonColor();
        this.voisinageButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "voisinage";
        this.resetStackPane();
    }

    @FXML
    private void anneeButtonClicked() {
        resetButtonColor();
        this.anneeButton.setStyle(CLICKED_BUTTON_STYLE);
        this.currentSelection = "annee";
        this.resetStackPane();
    }
}
