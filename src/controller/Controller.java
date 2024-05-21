package controller;

import java.io.IOException;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import model.MainModel;

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
     * Contient les contrôleurs des vues déjà chargés
     */
    private Map<String, Controller> controllerCache = null;

    /**
     * Contient le modèle principal
     */
    private MainModel model;

    /**
     * Permet de définir la variable contenant les vues déjà chargées
     * 
     * @param viewCache La variable contenant les vues déjà chargées
     */
    public void setViewCache(Map<String, Node> viewCache) {
        this.viewCache = viewCache;
    }

    /**
     * Contient les contrôleurs des vues déjà chargés
     * 
     * @param controllerCache La variable contenant les contrôleurs des vues déjà
     *                        chargés
     */
    public void setControllerCache(Map<String, Controller> controllerCache) {
        this.controllerCache = controllerCache;
    }

    /**
     * Permet de définir la variable contenant l'application JavaFX
     * 
     * @param app L'application JavaFX
     */
    public void setApp(Application app) {
        this.app = app; // Peut être null
    }

    public void setModel(MainModel model) {
        this.model = model;
    }

    /**
     * Calcule l'échelle à appliquer à un élément pour qu'il s'adapte à la taille de
     * la fenêtre
     * 
     * @param window La fenêtre
     * @return L'échelle à appliquer
     */
    public double calculateScale(Region window) {
        double scaleX = window.getWidth() / 1920.0;
        double scaleY = window.getHeight() / 1080.0;
        return Math.min(scaleX, scaleY);
    }

    /**
     * Crée une liaison entre la taille de la fenêtre et l'échelle à appliquer à un
     * élément
     * 
     * @param window La fenêtre
     * @return La liaison entre la taille de la fenêtre et l'échelle à appliquer à
     *         l'élément
     */
    public DoubleBinding getScale(Region window) {
        DoubleBinding scale = Bindings.createDoubleBinding(() -> calculateScale(
                window), window.widthProperty(),
                window.heightProperty());
        return scale;
    }

    /**
     * Ouvre un lien web dans le navigateur par défaut
     * 
     * @param url L'URL à ouvrir
     * @throws IllegalArgumentException Si l'URL est null ou ne commence pas par
     *                                  http:// ou https://
     * @throws IllegalStateException    Si l'application est null
     */
    protected void openWebLink(String url) throws IllegalArgumentException, IllegalStateException {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }
        if (app == null) {
            throw new IllegalStateException("Application cannot be null, please call setApp() first");
        } else if (!(url.startsWith("http://") || url.startsWith("https://"))) {
            throw new IllegalArgumentException("URL must start with http:// or https://");
        }
        this.app.getHostServices().showDocument(url);
    }

    /**
     * Change la vue d'un élément par une vue chargée depuis un fichier FXML
     * 
     * @param elem     L'élément à modifier
     * @param fxmlPath Le chemin du fichier FXML
     * @throws IllegalArgumentException Si l'élément ou le chemin du fichier FXML
     *                                  est null
     * @throws IllegalStateException    Si la variable contenant les vues déjà
     *                                  chargées est null
     * @throws IOException              Si le chargement du fichier FXML a échoué
     */
    protected void changeView(Pane elem, String fxmlPath)
            throws IllegalArgumentException, IllegalStateException, IOException {
        if (elem == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (fxmlPath == null) {
            throw new IllegalArgumentException("Fxml path cannot be null");
        }
        if (this.viewCache == null) {
            throw new IllegalStateException("View cache cannot be null, please call setViewCache() first");
        }

        Node view = this.viewCache.get(fxmlPath);
        Controller controller;
        if (view == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            view = loader.load();
            controller = loader.getController();
            // Le contrôleur peut être null si la vue n'a pas de contrôleur
            if (controller != null) {
                controller.setApp(this.app);
                controller.setViewCache(this.viewCache);
                controller.setModel(this.model);
                controller.setControllerCache(this.controllerCache);
            }
            this.controllerCache.put(fxmlPath, controller);
            this.viewCache.put(fxmlPath, view);
        } else {
            controller = this.controllerCache.get(fxmlPath);
        }
        if (controller != null) {
            controller.onViewOpened();
        }
        elem.getChildren().clear();
        elem.getChildren().add(view);
    }

    /**
     * Permet de récupérer le modèle principal
     * 
     * @return Le modèle principal
     */
    public MainModel getModel() {
        return model;
    }

    /**
     * Initialise le contrôleur
     * Est appelée après le chargement du fichier FXML
     */
    public abstract void initialize();

    /**
     * Redimensionne les éléments de la vue
     */
    protected abstract void resize();

    /**
     * Méthode appeller à chaque fois que l'on ouvre la vue
     */
    public abstract void onViewOpened();
}
