package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerEditInsert extends Controller {

    @FXML
    private StackPane window;

    @FXML
    private VBox editVBox;

    @FXML
    private Button validateButton;

    private HashMap<String, ArrayList<String[]>> fieldHashMap;

    private void resetEditVBox() {
        editVBox.getChildren().clear();
    }

    private HashMap<String, String> getData() {
        ArrayList<TextField> textFields = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();
        HashMap<String, String> data = new HashMap<>();

        for (Node node : editVBox.getChildren()) {
            if (node instanceof HBox) {
                for (Node child : ((HBox) node).getChildren()) {
                    if (child instanceof TextField) {
                        textFields.add((TextField) child);
                    }
                    if (child instanceof Button) {
                        buttons.add((Button) child);
                    }
                }
            }
        }

        for (int i = 0; i < textFields.size(); i++) {
            data.put(buttons.get(i).getId(), textFields.get(i).getText());
        }

        return data;
    }

    @FXML
    private void validateButtonPressed() {
        Map<String, String> data = getData();
        System.out.println(data);
    }

    public HBox createHBox(String nom, String idButton) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(100.0);
        hbox.setPrefWidth(1714.0);
        VBox.setMargin(hbox, new Insets(15.0, 30.0, 15.0, 30.0));

        Button button = new Button();
        button.setId(idButton);
        button.setMnemonicParsing(false);
        button.setPrefHeight(Double.MAX_VALUE);
        button.setPrefWidth(hbox.getPrefWidth() * 0.3);
        button.setText(nom);
        button.setMinWidth(100);
        HBox.setMargin(button, new Insets(0.0, 30.0, 0.0, 0.0));

        TextField textField = new TextField();
        textField.setPrefHeight(100.0);
        textField.setPrefWidth(hbox.getPrefWidth() * 0.7);
        HBox.setMargin(textField, new Insets(0.0, 0.0, 0.0, 30.0));
        HBox.setHgrow(textField, Priority.ALWAYS);

        hbox.getChildren().addAll(button, textField);
        return hbox;
    }

    /**
     * Crée un champ de saisie ainsi que un bouton contenant le nom du champ
     * 
     * @param data HashMap contenant le nom de la donnée et un identifiant pour le
     *             bouton
     */
    private void createField(HashMap<String, String> data) {
        for (Entry<String, String> entry : data.entrySet()) {
            editVBox.getChildren().add(createHBox(entry.getKey(), entry.getValue()));
        }
    }

    @Override
    public void onViewOpened() {
        this.resetEditVBox();
        HashMap<String, String> data = new HashMap<>();
        data.put("Nom", "name");
        data.put("Prénom", "firstname");
        data.put("Adresse", "address");
        data.put("Code postal", "zip");
        data.put("Ville", "city");
        data.put("Téléphone", "phone");
        data.put("Email", "email");

        createField(data);
        resize();
    }

    @Override
    public void initialize() {
        fieldHashMap = new HashMap<>();
    }

    @Override
    public void resize() {
        DoubleBinding scale = super.getScale(window);
        DoubleBinding autoResizeButtonValidatDoubleBinding = scale.multiply(100);
    }
}
