package controller;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import model.data.CommunesInfoParAnnee;

public class ControllerDataDetail extends Controller implements ReceiveInfo<CommunesInfoParAnnee>{

    @FXML
    Text txt; // Utilisé pour le template, à remplacer lorsque la vue sera implémentée

    /**
     * Stocke les informations de la commune pour une année donnée
     */
    private CommunesInfoParAnnee currentCommunesInfoParAnnee = null;


    @Override
    /**
     * Permet de recevoir les infos de la commune pour une année donnée et de la traiter
     * @param communeAnnee les informations de la commune pour une année donnée
     */
    public void receiveInfo(CommunesInfoParAnnee communeAnnee) {
        if (communeAnnee == null) {
            throw new IllegalArgumentException("info cannot be null");
        }
        this.currentCommunesInfoParAnnee = communeAnnee;
        this.onViewOpened();
    }

    @Override
    public void initialize() {
        resize();
    }

    @Override
    public void onViewOpened() {
        if (this.currentCommunesInfoParAnnee != null) {
            // TODO : implémenter l'affichage des informations
            txt.setText(this.currentCommunesInfoParAnnee.toString());
        }
    }

    @Override
    protected void resize() {
        // TODO Implémenter la vue et le redimensionnement
    }
}
