package controller;

import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControllerEditInsert extends Edit implements ReceiveInfo<String> {

    @FXML
    private StackPane window;

    @FXML
    private VBox editVBox;

    @FXML
    private Button validateButton;

    @FXML
    private void validateButtonPressed() {
        Map<String, String> data = super.getData(this.editVBox);
        if (data.containsValue("")) {
            // TODO
            System.out.println(data);
        }
    }


    @Override
    public void onViewOpened() {
        this.resetEditVBox(this.editVBox);
        this.resize();
    }

    @Override
    public void receiveInfo(String info) {
        ArrayList<String[]> data = super.getFieldHashMap().get(info);
        if (data == null) {
            throw new IllegalArgumentException("Mauvaise donnée reçue, s'attend à un des champs suivants : "
                    + super.getFieldHashMap().keySet().toString() + " mais a reçu : " + info + " à la place.");
        }
        super.createField(data, this.editVBox);
        this.resize();
    }

    @Override
    public void initialize() {
        // Inutilisé
    }

    @Override
    public void resize() {
        // Inutilisé
    }
}
