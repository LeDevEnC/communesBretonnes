package controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ControllerLogin extends Controller {

    @FXML
    StackPane root;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordTextField;

    @FXML
    Text errorLabel;

    @FXML
    CheckBox keepLoginCheckBox;

    @FXML
    private void loginButtonPressed() {
        this.errorLabel.setText("Erreur : méthode non implémenté");
        this.errorLabel.setVisible(true);
        System.out.println("Email : " + emailTextField.getText() + "\nMot de passe : " + passwordTextField.getText()
                + "\nCheckBox Coché ? : " + keepLoginCheckBox.isSelected());
    }

    public void initialize() {
        this.errorLabel.setVisible(false);
    }

    protected void resize() {

    }
}
