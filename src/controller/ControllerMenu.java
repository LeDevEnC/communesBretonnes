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
    String currentMenu = "board";

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
    private void openImgLink() {
        app.getHostServices().showDocument("https://www.bretagne.bzh/");
    }

    private void menuResize() {
        DoubleBinding scale = Bindings.createDoubleBinding(this::calculateScale, window.widthProperty(),
                window.heightProperty());

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

        String settings = "";
        String edit = "";
        String stats = "";
        String dataSee = "";
        String board = "";

        if (currentMenu.equals("settings")) {
            settings = "-fx-background-color: #d3d3d3;";
        } else if (currentMenu.equals("edit")) {
            edit = "-fx-background-color: #d3d3d3;";
        } else if (currentMenu.equals("stats")) {
            stats = "-fx-background-color: #d3d3d3;";
        } else if (currentMenu.equals("dataSee")) {
            dataSee = "-fx-background-color: #d3d3d3;";
        } else if (currentMenu.equals("board")) {
            board = "-fx-background-color: #d3d3d3;";
        }
        mainButtonEdit.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;" + edit, fontSize));
        mainButtonStats.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;" + stats, fontSize));
        mainButtonDataSee.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;" + dataSee, fontSize));
        mainButtonBoard.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;" + board, fontSize));
        mainButtonSettings.styleProperty()
                .bind(Bindings.format("-fx-font-size: %.2f; -fx-font-weight: bold;" + settings, fontSize));
    }

    public void initialize() {
        menuResize();
        try {
            changeAppView("/views/dataSee.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonSettingsPressed() {
        this.currentMenu = "settings";
        System.out.println("fefe");
        menuResize();
    }

    @FXML
    private void buttonEditPressed() {
        this.currentMenu = "edit";
        menuResize(); // reset
    }

    @FXML
    private void buttonStatsPressed() {
        this.currentMenu = "stats";
        menuResize();
    }

    @FXML
    private void buttonDataSeePressed() {
        this.currentMenu = "dataSee";
        menuResize();
    }

    @FXML
    private void buttonBoardPressed() {
        this.currentMenu = "board";
        menuResize();
    }

    public void setApp(Application app) {
        this.app = app;
    }
}