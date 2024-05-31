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

public class ControllerLogin extends Controller {

    @FXML
    StackPane toReplace;

    @FXML
    GridPane loginGridPane;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Text errorLabel;

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
        changeViewIfLogged();
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
            try {
                this.toReplace.getChildren().clear();
                this.loginGridPane.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        changeViewIfLogged();
    }
}
