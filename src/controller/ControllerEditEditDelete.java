package controller;

import java.util.ArrayList;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

/**
 * Permet de gérer la vue de editEditDelete.fxml
 */
public class ControllerEditEditDelete extends Edit implements ReceiveInfo<String> {

    /**
     * Stocke le VBox contenant les champs à éditer
     */
    @FXML
    private VBox editVBox;

    /**
     * Permet de recevoir quel est la table à éditer
     */
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

    /**
     * Permet de gérer l'appui sur le bouton d'édition
     */
    @FXML
    private void editButtonPressed() {
        Map<String, String> data = super.getData(this.editVBox);
        if (data.containsValue("")) {
            // TODO
            System.out.println(data);
        }
    }

    /**
     * Permet de gérer l'appui sur le bouton de suppression
     */
    @FXML
    private void deleteButtonPressed() {
        Map<String, String> data = super.getData(this.editVBox);
        if (data.containsValue("")) {
            // TODO
            System.out.println(data);
        }
    }

    /**
     * Est appelée après que la vue ait été ouverte pour la première fois
     */
    @Override
    public void initialize() {
        // Inutilisé
    }

    /**
     * Permet de redimensionner les éléments de la vue
     */
    @Override
    public void resize() {
        // Inutilisé
    }

    /**
     * Méthode appeller à chaque fois que l'on ouvre la vue, permet de réinitialiser
     * les champs
     */
    @Override
    public void onViewOpened() {
        this.resetEditVBox(this.editVBox);
        this.resize();
    }

}
