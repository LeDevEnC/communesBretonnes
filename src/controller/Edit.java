package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Classe abstraite permettant de gérer les vues de editEditDelete.fxml et
 * editInsert.fxml
 */
public abstract class Edit extends Controller {
    /**
     * HashMap contenant les champs à remplir
     */
    private Map<String, ArrayList<String[]>> fieldHashMap;

    /**
     * Constructeur de la classe Edit
     * Initialise la HashMap contenant les champs à remplir
     */
    protected Edit() {
        super();
        this.initFieldHashMap();
    }

    /**
     * Réinitialise le VBox contenant les champs à remplir
     *
     * @param editVBox VBox contenant les champs à remplir
     */
    protected void resetEditVBox(VBox editVBox) {
        editVBox.getChildren().clear();
    }

    /**
     * Retourne la HashMap contenant les champs à remplir
     *
     * @return HashMap contenant les champs à remplir
     */
    protected Map<String, ArrayList<String[]>> getFieldHashMap() {
        return this.fieldHashMap;
    }

    /**
     * Initialise la HashMap contenant les champs à remplir
     * Contenu du hashMap : &lt;Nom du champ, &lt;Nom du champ, Identifiant du
     * champ,
     * Placeholder&gt;&gt;
     * Clé du hashMap disponible : aeroprt, annee, commune, departement, gare,
     * donneesannuelles, voisinage
     */
    private void initFieldHashMap() {
        this.fieldHashMap = new HashMap<>();
        ArrayList<String[]> toInsert;

        // Aeroport
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "Nom", "nom", "BREST-BRETAGNE" });
        toInsert.add(new String[] { "Adresse", "adresse", "GUIPAVAS 29490" });
        toInsert.add(new String[] { "Numéro Département", "leDepartement", "29" });
        this.fieldHashMap.put("aeroport", toInsert);

        // Annee
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "Annee", "annee", "1991" });
        toInsert.add(new String[] { "Taux Inflation", "tauxInflation", "3.2" });
        this.fieldHashMap.put("annee", toInsert);

        // Commune
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "ID Commune", "idCommune", "22001" });
        toInsert.add(new String[] { "Nom Commune", "nomCommune", "ALLINEUC" });
        toInsert.add(new String[] { "Numéro Département", "leDepartement", "22" });
        this.fieldHashMap.put("commune", toInsert);

        // Departement
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "ID Departement", "idDep", "22" });
        toInsert.add(new String[] { "Nom Département", "nomDep", "COTES-D'ARMOR" });
        toInsert.add(new String[] { "Investissement Culturel 2019", "investissementCulturel2019", "6196596" });
        this.fieldHashMap.put("departement", toInsert);

        // Gare
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "Code Gare", "codeGare", "87159947" });
        toInsert.add(new String[] { "Nom Gare", "nomGare", "KER-LANN" });
        toInsert.add(new String[] { "Est Fret", "estFret", "0" });
        toInsert.add(new String[] { "Est Voyageur", "estVoyageur", "1" });
        toInsert.add(new String[] { "La Commune", "laCommune", "35047" });
        this.fieldHashMap.put("gare", toInsert);

        // Donnees Annuelles
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "Annee", "lAnnee", "2019" });
        toInsert.add(new String[] { "ID Commune", "laCommune", "22004" });
        toInsert.add(new String[] { "Nombre Maisons", "nbMaison", "71" });
        toInsert.add(new String[] { "Nombre Appartements", "nbAppart", "0" });
        toInsert.add(new String[] { "Prix Moyen", "prixMoyen", "105919" });
        toInsert.add(new String[] { "Prix M2 Moyen", "prixM2Moyen", "1312.37" });
        toInsert.add(new String[] { "Surface Moyenne", "surfaceMoy", "84" });
        toInsert.add(new String[] { "Dépenses Culturelle", "depensesCulturellesTotales", "827" });
        toInsert.add(new String[] { "Budget Total", "budgetTotal", "4800" });
        toInsert.add(new String[] { "Population", "population", "4808" });
        this.fieldHashMap.put("donneesannuelles", toInsert);

        // Voisinage
        toInsert = new ArrayList<>();
        toInsert.add(new String[] { "ID Commune", "commune", "22009" });
        toInsert.add(new String[] { "ID Commune Voisine", "communeVoisine", "22001" });
        this.fieldHashMap.put("voisinage", toInsert);

        // On rend la HashMap non modifiable
        this.fieldHashMap = Collections.unmodifiableMap(this.fieldHashMap);
    }

    /**
     * Retourne les données saisies dans les champs
     *
     * @param editVBox VBox contenant les champs à remplir
     * @return HashMap contenant les données saisies
     */
    protected HashMap<String, String> getData(VBox editVBox) {
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

    protected void setData(HashMap<String, String> data, VBox editVBox) {
        ArrayList<TextField> textFields = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();

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
            textFields.get(i).setText(data.get(buttons.get(i).getId()));
        }
    }

    /**
     * Crée un HBox contenant un bouton et un champ de saisie
     *
     * @param nom         Nom du champ
     * @param idButton    Identifiant du bouton
     * @param placeHolder Placeholder du champ de saisie
     * @return HBox contenant un bouton et un champ de saisie
     */
    public HBox createHBox(String nom, String idButton, String placeHolder) {
        HBox hbox = new HBox();
        hbox.setPrefHeight(100.0);
        hbox.setPrefWidth(1714.0);
        VBox.setMargin(hbox, new Insets(5.0, 30.0, 0.0, 30.0));

        Button button = new Button();
        button.setId(idButton);
        button.setMnemonicParsing(false);
        button.setPrefHeight(Double.MAX_VALUE);
        button.setPrefWidth(hbox.getPrefWidth() * 0.4);
        button.setText(nom);
        button.setMinWidth(100);
        button.setTooltip(new Tooltip(nom));
        HBox.setMargin(button, new Insets(0.0, 30.0, 0.0, 0.0));

        TextField textField = new TextField();
        textField.setPrefHeight(100.0);
        textField.setPrefWidth(hbox.getPrefWidth() * 0.6);
        textField.setPromptText(placeHolder);
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
     * @param editVBox VBox contenant les champs à remplir
     */
    protected void createField(ArrayList<String[]> data, VBox editVBox) {
        for (String[] entry : data) {
            if (entry.length != 3) {
                throw new IllegalArgumentException("Les données doivent être de taille 3");
            }
            HBox hbox = createHBox(entry[0], entry[1], entry[2]);
            editVBox.getChildren().add(hbox);
        }
    }
}
