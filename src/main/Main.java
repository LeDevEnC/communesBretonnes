package main;

import java.util.HashMap;
import java.util.Map;

import controller.Controller;
import controller.ControllerMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.MainModel;

/**
 * Classe principale de l'application
 */
public class Main extends Application {
    /**
     * Stocke la fenêtre principale de l'application
     */
    private Stage primaryStage;

    /**
     * Méthode principale de l'application
     * 
     * @param args les arguments passés à l'application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Permet de démarrer l'application
     * 
     * @param primaryStage la fenêtre principale de l'application
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application");
        long startTime = System.currentTimeMillis();
        final String defaultView = "/views/main.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(defaultView));
        Parent root = loader.load();
        this.primaryStage = primaryStage;
        MainModel mainModel = new MainModel();
        mainModel.loadLogin();
        System.out.println("Loading time: " + (System.currentTimeMillis() - startTime) + "ms");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight() - 40);
        scene.getStylesheets().add(getClass().getResource("/ressources/styles.css").toExternalForm());
        primaryStage.setMinWidth(830);
        primaryStage.setMinHeight(650);
        ControllerMenu controller = loader.getController();

        controller.setApp(this);

        Map<String, Node> viewCache = new HashMap<>();
        viewCache.put(defaultView, root);
        controller.setViewCache(viewCache);

        Map<String, Controller> controllerCache = new HashMap<>();
        controllerCache.put(defaultView, controller);
        controller.setControllerCache(controllerCache);

        controller.setModel(mainModel);
        controller.onViewOpened();

        Image applicationIcon = new Image(getClass().getResourceAsStream("/ressources/appIcon.png"));
        primaryStage.getIcons().add(applicationIcon);
        primaryStage.setTitle("Commune Bretonne");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Permet de récupérer la fenêtre principale de l'application
     * 
     * @return la fenêtre principale de l'application
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
