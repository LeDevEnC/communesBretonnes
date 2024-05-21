package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


    @FXML
    private void loginButtonPressed() {
        this.errorLabel.setText("Erreur : méthode non implémenté");
        this.errorLabel.setVisible(true);
        System.out.println("Email : " + emailTextField.getText() + "\nMot de passe : " + passwordTextField.getText()
                + "\nCheckBox Coché ? : " + keepLoginCheckBox.isSelected());
        super.getModel().login(emailTextField.getText(), passwordTextField.getText());
        changeViewIfLogged();
    }

    private void changeViewIfLogged() {
        if (super.getModel().isLogged()) {
            try {
                super.changeView(toReplace, "/views/template.fxml");
                this.loginGridPane.setVisible(false);
                this.emailTextField.clear();
                this.passwordTextField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            this.loginGridPane.setVisible(true);
        }
    }

    public void initialize() {
        this.errorLabel.setVisible(false);
    }

    protected void resize() {

    }

    public void onViewOpened() {
        this.errorLabel.setVisible(false);
        changeViewIfLogged();
    }
}
