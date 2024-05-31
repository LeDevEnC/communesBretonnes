package controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

public class ControllerEditMenu extends Controller {

    private static final String CLICKED_BUTTON_STYLE = "-fx-background-color: #d3d3d3;";

    @FXML
    private StackPane window;

    @FXML
    private StackPane titreStackPane;

    @FXML
    private StackPane toReplaceStackPane;

    @FXML
    private Label titleLabel;

    @FXML
    private Button aeroButton;

    @FXML
    private Button gareButton;

    @FXML
    private Button invCulturelCommButton;

    @FXML
    private Button invCulturelDepButton;

    @FXML
    private Button prixParCommuneButton;

    @FXML
    private Button communeVoisineButton;

    @FXML
    private Label communeVoisineButtonLabel;

    @FXML
    private Label aeroButtonLabel;

    @FXML
    private Label gareButtonLabel;

    @FXML
    private Label invCulturelCommButtonLabel;

    @FXML
    private Label invCulturelDepButtonLabel;

    @FXML
    private Label prixParCommuneButtonLabel;

    @FXML
    private Label tauxInfButtonLabel;

    @FXML
    private Line separatorLine;

    @FXML
    private Button tauxInfButton;
    
    @Override
    protected void resize() {
        DoubleBinding scale = super.getScale(window);

        DoubleBinding autoResizeButton = scale.multiply(400);
        DoubleBinding autoResizeTitleLabel = scale.multiply(50);

        aeroButton.prefWidthProperty().bind(autoResizeButton);
        gareButton.prefWidthProperty().bind(autoResizeButton);
        invCulturelCommButton.prefWidthProperty().bind(autoResizeButton);
        invCulturelDepButton.prefWidthProperty().bind(autoResizeButton);
        prixParCommuneButton.prefWidthProperty().bind(autoResizeButton);
        tauxInfButton.prefWidthProperty().bind(autoResizeButton);
        communeVoisineButton.prefWidthProperty().bind(autoResizeButton);

        DoubleBinding fontSize = scale.multiply(30);

        final String fontSizeStyle = "-fx-font-size: ";
        final String fontSizeSuffix = "px; -fx-font-weight: bold;";

        aeroButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        gareButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        invCulturelCommButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        invCulturelDepButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        prixParCommuneButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        tauxInfButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));
        communeVoisineButtonLabel.styleProperty().bind(Bindings.concat(fontSizeStyle, fontSize.asString(), fontSizeSuffix));

        titreStackPane.minHeightProperty().bind(prixParCommuneButton.heightProperty());
        titleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", autoResizeTitleLabel.asString(), ";-fx-font-weight: bold;"));

        separatorLine.startXProperty().bind(autoResizeButton);
        separatorLine.endXProperty().bind(window.widthProperty());
    }
    

    public void initialize() {
        resize();
    }

    public void onViewOpened() {
        this.communeVoisineButtonClicked();
    }

    /**
     * Réinitialise la couleur des boutons du menu
     */
    private void resetButtonColor() {
        this.communeVoisineButton.setStyle("");
        this.aeroButton.setStyle("");
        this.gareButton.setStyle("");
        this.invCulturelCommButton.setStyle("");
        this.invCulturelDepButton.setStyle("");
        this.prixParCommuneButton.setStyle("");
        this.tauxInfButton.setStyle("");
    }

    @FXML
    private void communeVoisineButtonClicked() {
        resetButtonColor();
        this.communeVoisineButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aeroButtonClicked() {
        resetButtonColor();
        this.aeroButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gareButtonClicked() {
        resetButtonColor();
        this.gareButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void invCulturelCommButtonClicked() {
        resetButtonColor();
        this.invCulturelCommButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void invCulturelDepButtonClicked() {
        resetButtonColor();
        this.invCulturelDepButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void prixParCommuneButtonClicked() {
        resetButtonColor();
        this.prixParCommuneButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void tauxInfButtonClicked() {
        resetButtonColor();
        this.tauxInfButton.setStyle(CLICKED_BUTTON_STYLE);
        try {
            super.changeView(toReplaceStackPane, "/views/template.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
