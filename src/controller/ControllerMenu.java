package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerMenu {

    Application app;

    @FXML
    private VBox mainVBox;

    @FXML
    private Button mainButtonSettings;

    @FXML
    private Button mainButtonEdit;

    @FXML
    private Button mainButtonStats;

    @FXML
    private Button mainButtonDataSee;

    @FXML
    private Button mainButtonBoard;

    @FXML
    private ImageView mainBzhImg;

    @FXML
    private SplitPane window;

    @FXML
    private StackPane appView;

    private void changeAppView(String fxmlPath) throws IOException {
        appView.getChildren().clear();
        Node node = FXMLLoader.load(getClass().getResource(fxmlPath));
        appView.getChildren().add(node);
    }

    private double calculateScale() {
        double scaleX = window.getWidth() / 1920.0;
        double scaleY = window.getHeight() / 1080.0;
        return Math.min(scaleX, scaleY);
    }

    @FXML
    private void openImgLink(){
        app.getHostServices().showDocument("https://www.bretagne.bzh/");
    }

    private void menuResize() {
        DoubleBinding scale = Bindings.createDoubleBinding(this::calculateScale, window.widthProperty(), window.heightProperty());

        DoubleBinding autoResize = scale.multiply(300);
        DoubleBinding fontSize = scale.multiply(30);

        mainBzhImg.fitWidthProperty().bind(autoResize);
        mainBzhImg.fitHeightProperty().bind(autoResize);
        mainBzhImg.setPreserveRatio(true);

        mainButtonSettings.prefWidthProperty().bind(autoResize);
        mainButtonEdit.prefWidthProperty().bind(autoResize);
        mainButtonStats.prefWidthProperty().bind(autoResize);
        mainButtonDataSee.prefWidthProperty().bind(autoResize);
        mainButtonBoard.prefWidthProperty().bind(autoResize);

        mainVBox.prefWidthProperty().bind(autoResize);

        mainButtonSettings.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        mainButtonEdit.styleProperty().bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        mainButtonStats.styleProperty().bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        mainButtonDataSee.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
        mainButtonBoard.styleProperty().bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;", fontSize));
    }

    public void initialize() {
        menuResize();
        try {
            changeAppView("/views/template.fxml");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setApp(Application app) {
        this.app = app;
    }
}