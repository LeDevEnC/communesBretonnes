package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.DepPossibles;
import model.data.Departement;
import model.data.Gare;

/**
 * Permet de gérer la vue de editInsert.fxml
 */
public class ControllerEditInsert extends Edit implements ReceiveInfo<String> {

    /**
     * Stocke le VBox contenant les champs à éditer
     */
    @FXML
    private VBox editVBox;

    /**
     * Stocke le nom de la table à éditer
     */
    String table;

    /**
     * Permet de gérer l'appui sur le bouton de validation
     */
    @FXML
    private void validateButtonPressed() {
        Map<String, String> data = super.getData(this.editVBox);
        int result = 0;
        if (!data.containsValue("")) {
            try {
                if (this.table.equals("aeroport")) {
                    String nom = data.get("nom");
                    String adresse = data.get("adresse");
                    int dep = Integer.parseInt(data.get("leDepartement"));

                    Aeroport aeroport = new Aeroport(nom, adresse);
                    result = super.getModel().getAeroportDAO().create(aeroport, dep);
                }

                else if (this.table.equals("departement")) {

                    int idDep = Integer.parseInt(data.get("idDep"));
                    String nomDep = data.get("nomDep");
                    DepPossibles nomDepEnum = DepPossibles.valueOf(nomDep);
                    long invesCulturel = Long.parseLong(data.get("investissementCulturel2019"));
                    List<Aeroport> aeroports = super.getModel().getTousAeroport().get(String.valueOf(idDep));

                    Departement dep = new Departement(idDep, nomDepEnum, invesCulturel, aeroports);
                    result = super.getModel().getDepartementDAO().create(dep);
                }

                else if (this.table.equals("voisinage")) {
                    int idCommune = Integer.parseInt(data.get("commune"));
                    int idCommuneVoisine = Integer.parseInt(data.get("communeVoisine"));

                    result = super.getModel().getVoisinageDAO().create(idCommune, idCommuneVoisine);
                }

                else if (this.table.equals("commune")) {
                    int idCommune = Integer.parseInt(data.get("idCommune"));
                    String nomCommune = data.get("nomCommune");
                    String leDepartement = data.get("leDepartement");
                    Departement dep = super.getModel().getTousLesDepartements().get(leDepartement);
                    CommuneBase communeBase = new CommuneBase(idCommune, nomCommune, dep, null, null);
                    result = super.getModel().getCommuneBaseDAO().create(communeBase);
                }

                else if (this.table.equals("annee")) {
                    int lAnnee = Integer.parseInt(data.get("annee"));
                    double tauxInflation = Double.parseDouble(data.get("tauxInflation"));

                    Annee annee = new Annee(lAnnee, tauxInflation);
                    result = super.getModel().getAnneeDAO().create(annee);
                }

                else if (this.table.equals("donneesannuelles")) {
                    String anneeGet = data.get("lAnnee");
                    String idCommune = data.get("laCommune");
                    int nbMaisons = Integer.parseInt(data.get("nbMaison"));
                    int nbAppartements = Integer.parseInt(data.get("nbAppart"));
                    int prixMoyen = Integer.parseInt(data.get("prixMoyen"));
                    double prixM2Moyen = Double.parseDouble(data.get("prixM2Moyen"));
                    int surfaceMoyenne = Integer.parseInt(data.get("SurfaceMoy"));
                    int depensesCulturellesTotales = Integer.parseInt(data.get("depensesCulturellesTotales"));
                    int budgetTotal = Integer.parseInt(data.get("budgetTotal"));
                    int population = Integer.parseInt(data.get("population"));

                    CommuneBase communeBase = super.getModel().getToutesLesCommunesBase().get(idCommune);
                    Annee annee = super.getModel().getToutesLesAnnees().get(anneeGet);

                    CommunesInfoParAnnee communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, annee, nbMaisons,
                            nbAppartements, prixMoyen, prixM2Moyen, surfaceMoyenne, depensesCulturellesTotales,
                            budgetTotal, population);

                    result = super.getModel().getCommunesInfoParAnneeDAO().create(communesInfoParAnnee);
                }

                else if (this.table.equals("gare")) {
                    int codeGare = Integer.parseInt(data.get("codeGare"));
                    String nomGare = data.get("nomGare");
                    boolean estFret = Boolean.parseBoolean(data.get("estFret"));
                    boolean estVoyageur = Boolean.parseBoolean(data.get("estVoyageur"));
                    int codeCommune = Integer.parseInt(data.get("laCommune"));

                    Gare gare = new Gare(codeGare, nomGare, estFret, estVoyageur);
                    result = super.getModel().getGareDAO().create(gare, codeCommune);
                }
            } catch (Exception e) {
                result = 0;
                System.err.println(e.getMessage());
            } finally {
                Alert alert;
                if (result == 1) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Création réussi");
                    alert.setHeaderText("Création réussi");
                    alert.setContentText("Les données ont été correctement insérées dans la base de données");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur lors de la création");
                    alert.setHeaderText("Erreur lors de la création");
                    alert.setContentText("Les données n'ont pas pu être insérées dans la base de données");
                }
                alert.show();
            }
        }
    }

    /**
     * Est appelée à chaque fois que la vue est ouverte
     * Permet de réinitialiser les champs de la vue
     */
    @Override
    public void onViewOpened() {
        this.resetEditVBox(this.editVBox);
        this.resize();
    }

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
        this.table = info;
        super.createField(data, this.editVBox);
        this.resize();
    }

    /**
     * Est appelée après que la vue ait été ouverte pour la première fois
     */
    @Override
    public void initialize() {
        // Inutilisé
    }

    /**
     * Redimensionne les éléments de la vue
     */
    @Override
    public void resize() {
        // Inutilisé
    }
}
