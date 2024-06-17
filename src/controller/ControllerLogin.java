package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Permet de gérer la vue de login.fxml
 */
public class ControllerLogin extends Controller {

    /**
     * Le panneau principal qui peut être remplacé par d'autres contenus.
     */
    @FXML
    StackPane toReplace;

    /**
     * Le panneau de grille contenant les champs de formulaire pour la connexion.
     */
    @FXML
    GridPane loginGridPane;

    /**
     * Le champ de texte pour saisir l'adresse e-mail de l'utilisateur.
     */
    @FXML
    TextField emailTextField;

    /**
     * Le champ de mot de passe pour saisir le mot de passe de l'utilisateur.
     */
    @FXML
    PasswordField passwordTextField;

    /**
     * Le texte affichant les messages d'erreur lors de la tentative de connexion.
     */
    @FXML
    Text errorLabel;

    /**
     * La case à cocher permettant à l'utilisateur de rester connecté.
     */
    @FXML
    CheckBox keepLoginCheckBox;

    /**
     * Méthode appelée directement lors du clic sur le bouton de connexion ou appelé
     * lors de l'appui sur la touche "Entrée"
     */
    @FXML
    private void loginButtonPressed() {
        this.errorLabel.setText("Impossible de se connecter.");
        this.errorLabel.setVisible(true);
        super.getModel().login(emailTextField.getText(), passwordTextField.getText());
        // Appel changeViewIfLogged pour changer la vue si l'utilisateur est connecté
        // automatiquement grâce à la propriété isLoggedProperty
    }

    /**
     * Change la vue si l'utilisateur est connecté
     */
    private void changeViewIfLogged() {
        if (super.getModel().isLogged()) {
            try {
                super.changeView(toReplace, "/views/editMenu.fxml");
                this.loginGridPane.setVisible(false);
                this.emailTextField.clear();
                this.passwordTextField.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.toReplace.getChildren().clear();
            this.loginGridPane.setVisible(true);
        }
    }

    /**
     * Méthode appelée lors de l'appui sur la touche "Entrée"
     * 
     * @param event
     */
    @FXML
    private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            loginButtonPressed();
        }
    }

    /**
     * Méthode appelée lors de la création de la vue
     * Permet de cacher le message d'erreur
     */
    public void initialize() {
        this.errorLabel.setVisible(false);
    }

    /**
     * Redimensionne les éléments de la vue
     */
    protected void resize() {
        throw new UnsupportedOperationException("Pas besoin de redimensionner la vue de connexion.");
    }

    /**
     * Méthode appelée à chaque ouverture de la vue
     * Permet de cacher le message d'erreur et de changer la vue si l'utilisateur
     * est connecté
     */
    public void onViewOpened() {
        this.errorLabel.setVisible(false);
        super.getModel().isLoggedProperty().addListener((observable, oldValue, newValue) -> {
            if (keepLoginCheckBox.isSelected()) {
                keepLoginCheckBox.setSelected(false);
                super.getModel().saveLogin();
            }
            this.errorLabel.setVisible(false);
            changeViewIfLogged();
        });
        changeViewIfLogged();
    }
}
