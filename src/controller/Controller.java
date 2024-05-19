package controller;

import java.io.IOException;
import java.util.Map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Classe abstraite pour les contrôleurs
 */
public abstract class Controller {
    /**
     * Contient l'application JavaFX
     */
    private Application app = null;

    /**
     * Contient les vues déjà chargées
     */
    private Map<String, Node> viewCache = null;

    /**
     * Permet de définir la variable contenant les vues déjà chargées
     * @param viewCache La variable contenant les vues déjà chargées
     */
    public void setViewCache(Map<String, Node> viewCache) {
        this.viewCache = viewCache;
    }

    /**
     * Permet de définir la variable contenant l'application JavaFX
     * @param app L'application JavaFX
     */
    public void setApp(Application app) {
        this.app = app; // Peut être null
    }

    /**
     * Ouvre un lien web dans le navigateur par défaut
     * @param url L'URL à ouvrir
     * @throws IllegalArgumentException Si l'URL est null ou ne commence pas par http:// ou https://
     * @throws IllegalStateException Si l'application est null
     */
    protected void openWebLink(String url) throws IllegalArgumentException, IllegalStateException {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }
        if (app == null) {
            throw new IllegalStateException("Application cannot be null, please call setApp() first");
        }
        else if (!(url.startsWith("http://") || url.startsWith("https://"))){
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }
        this.app.getHostServices().showDocument("https://www.bretagne.bzh/");
    }

    /**
     * Change la vue d'un élément par une vue chargée depuis un fichier FXML
     * @param elem L'élément à modifier
     * @param fxmlPath Le chemin du fichier FXML
     * @throws IllegalArgumentException Si l'élément ou le chemin du fichier FXML est null
     * @throws IllegalStateException Si la variable contenant les vues déjà chargées est null
     * @throws IOException Si le chargement du fichier FXML a échoué
     */
    protected void changeView(Pane elem, String fxmlPath)
            throws IllegalArgumentException, IllegalStateException, IOException {
        if (elem == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Fxml path cannot be null");
        }
        if (viewCache == null) {
            throw new IllegalStateException("View cache cannot be null, please call setViewCache() first");
        }

        Node view = viewCache.get(fxmlPath);
        if (view == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            view = loader.load();
            Controller controller = loader.getController();
            // Le contrôleur peut être null si la vue n'a pas de contrôleur
            if (controller != null){
                controller.setApp(app);
                controller.setViewCache(viewCache);
            }
            viewCache.put(fxmlPath, view);
        }
        elem.getChildren().clear();
        elem.getChildren().add(view);
    }

    public abstract void initialize();
}
