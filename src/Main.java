import controller.ControllerMenu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/main.fxml"));
        Parent root = loader.load();
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight() - 40);
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        ControllerMenu controller = loader.getController();
        scene.getStylesheets().add(getClass().getResource("ressources/styles.css").toExternalForm());
        controller.setApp(this);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
