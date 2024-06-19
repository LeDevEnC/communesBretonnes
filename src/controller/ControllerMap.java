package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;

/**
 * Contrôleur de map.fxml
 * Gère la gestion de l'affichage et de l'interaction avec une carte/image dans
 * l'application.
 * Permet de changer l'image affichée, de zoomer et de déplacer l'image.
 */
public class ControllerMap extends Controller {

    /**
     * Vue de l'image à afficher.
     */
    @FXML
    private ImageView imageView;

    /**
     * Bouton pour changer l'image affichée.
     */
    @FXML
    private Button buttonChangeImg;

    /**
     * Conteneur de la vue de l'image.
     */
    @FXML
    private StackPane containerImageView;

    /**
     * Décalage en X pour le déplacement de l'image.
     */
    private double xOffset = 0;

    /**
     * Décalage en Y pour le déplacement de l'image.
     */
    private double yOffset = 0;

    /**
     * Chemin de l'image actuellement affichée.
     */
    String currentImg;

    /**
     * Définit l'image à afficher à partir d'un chemin.
     * 
     * @param path Chemin de l'image à afficher.
     */
    private void setImg(String path) {
        this.currentImg = path;

        Image image = new Image(path, true);
        imageView.setImage(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(containerImageView.widthProperty());
        imageView.fitHeightProperty().bind(containerImageView.heightProperty());
    }

    /**
     * Change l'image affichée lorsque le bouton est cliqué.
     * Alterne entre deux images et met à jour le texte du bouton en conséquence.
     */
    @FXML
    private void changeImg() {
        if (this.currentImg.equals("/ressources/images/graphes/neighbour.png")) {
            this.buttonChangeImg
                    .setText("Met actuellement en valeur l'excentricité, cliquez pour mettre en valeur les voisins !");
            this.setImg("/ressources/images/graphes/eccentricity.png");
        } else {
            this.buttonChangeImg
                    .setText("Met actuellement en valeur les voisins, cliquez pour mettre en valeur l'excentricité !");
            this.setImg("/ressources/images/graphes/neighbour.png");
        }
    }

    /**
     * Initialise le contrôleur en définissant l'image initiale à afficher.
     */
    @FXML
    public void initialize() {
        this.setImg("/ressources/images/graphes/neighbour.png");
    }

    /**
     * Gère l'événement de défilement pour zoomer sur l'image.
     * 
     * @param event Événement de défilement capturé.
     */
    @FXML
    private void onScroll(ScrollEvent event) {
        double zoomFactor = 1.05;
        if (event.getDeltaY() < 0) {
            zoomFactor = 0.95;
        }
        imageView.setScaleX(imageView.getScaleX() * zoomFactor);
        imageView.setScaleY(imageView.getScaleY() * zoomFactor);
        event.consume();
    }

    /**
     * Gère l'événement de pression de la souris pour initialiser les décalages de
     * déplacement de l'image.
     * 
     * @param event Événement de pression de la souris capturé.
     */
    @FXML
    private void onMousePressed(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    /**
     * Gère l'événement de glissement de la souris pour déplacer l'image.
     * 
     * @param event Événement de glissement de la souris capturé.
     */
    @FXML
    private void onMouseDragged(MouseEvent event) {
        imageView.setTranslateX(imageView.getTranslateX() + event.getSceneX() - xOffset);
        imageView.setTranslateY(imageView.getTranslateY() + event.getSceneY() - yOffset);
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    /**
     * Méthode abstraite redéfinie pour gérer le redimensionnement de la vue.
     * Actuellement non implémentée.
     */
    @Override
    protected void resize() {
        // Inutilisé
    }

    /**
     * Réinitialise la vue de l'image à son état initial lorsque la vue est ouverte.
     */
    @Override
    public void onViewOpened() {
        imageView.setTranslateX(0);
        imageView.setTranslateY(0);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }
}