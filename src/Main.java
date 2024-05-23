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
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.MainModel;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Starting application");
        long startTime = System.currentTimeMillis();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main.fxml"));
        Parent root = loader.load();
        MainModel mainModel = new MainModel();
        System.out.println("Loading time: " + (System.currentTimeMillis() - startTime) + "ms");

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight() - 40);
        scene.getStylesheets().add(getClass().getResource("ressources/styles.css").toExternalForm());
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        ControllerMenu controller = loader.getController();

        controller.setApp(this);

        Map<String, Node> viewCache = new HashMap<>();
        viewCache.put("views/main.fxml", root);
        controller.setViewCache(viewCache);

        Map<String, Controller> controllerCache = new HashMap<>();
        controllerCache.put("views/main.fxml", controller);
        controller.setControllerCache(controllerCache);

        controller.setModel(mainModel);
        controller.onViewOpened();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
