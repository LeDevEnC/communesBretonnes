package controller;

import java.io.IOException;

import javafx.application.Application;
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

    Application app;
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

    @FXML
    private void openImgLink() {
        super.openWebLink("https://www.bretagne.bzh/");
    }

    protected void resize() {
        DoubleBinding scale =  super.getScale(window);

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

    private void resetButtonColor() {
        this.mainButtonBoard.setStyle("");
        this.mainButtonDataSee.setStyle("");
        this.mainButtonStats.setStyle("");
        this.mainButtonEdit.setStyle("");
        this.mainButtonSettings.setStyle("");

    }

    public void initialize() {
        resize();
    }

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

    @FXML
    private void buttonEditPressed() {
        this.currentMenu = "edit";
        this.resetButtonColor();
        this.mainButtonEdit.setStyle("-fx-background-color: #d3d3d3;");
        try {
            changeView(appView, "/views/template.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }

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

    @FXML
    public void buttonBoardPressed() { // Note : public pour pouvoir appeler la vue par défaut
        this.currentMenu = "board";
        this.resetButtonColor();
        this.mainButtonBoard.setStyle("-fx-background-color: #d3d3d3;");
        try {
            changeView(appView, "/views/template.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        resize();
    }
}