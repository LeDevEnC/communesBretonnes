package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.DepPossibles;
import model.data.Departement;
import model.data.Gare;

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
     * Stocke le bouton d'édition
     */
    @FXML
    private Button editButton;

    @FXML
    private TextField searchBar;

    String table;

    @FXML
    private void searchHandle(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            HashMap<String, String> data = new HashMap<>();
            if (this.table.equals("aeroport")) {
                Map<Integer, Aeroport> aero = super.getModel().getAeroportDAO().findByName(searchBar.getText());
                if (aero.size() != 0) {
                    Integer dep = aero.keySet().iterator().next();
                    Aeroport aeroport = aero.get(dep);
                    String nom = aeroport.getNom();
                    String adresse = aeroport.getAdresse();
                    String departement = dep.toString();

                    data.put("nom", nom);
                    data.put("adresse", adresse);
                    data.put("leDepartement", departement);

                    super.setData(data, editVBox);
                }
            } else if (this.table.equals("departement")) {
                Departement dep = super.getModel().getDepartementDAO().findByID(Long.parseLong(searchBar.getText()));
                String idDep = Integer.toString(dep.getIdDep());
                String nomDep = dep.getNomDep();
                String investissementCulturel2019 = Long.toString(dep.getInvestCulturel2019());

                data.put("idDep", idDep);
                data.put("nomDep", nomDep);
                data.put("investissementCulturel2019", investissementCulturel2019);

                super.setData(data, editVBox);
            } else if (this.table.equals("voisinage")) {
                // Cas impossible : Le voisinage dépend des deux communes
            } else if (this.table.equals("commune")) {
                CommuneBase com = super.getModel().getCommuneBaseDAO().findByID(Long.parseLong(searchBar.getText()));

                String idCom = Long.toString(com.getIdCommune());
                String nomCom = com.getNomCommune();
                String leDep = Integer.toString(com.getLeDepartement().getIdDep());

                data.put("idCommune", idCom);
                data.put("nomCommune", nomCom);
                data.put("leDepartement", leDep);

                super.setData(data, editVBox);

            } else if (this.table.equals("annee")) {
                Annee an = super.getModel().getAnneeDAO().findByID(Long.parseLong(searchBar.getText()));

                String annee = Integer.toString(an.getAnneeRepr());
                String tauxInflation = Double.toString(an.getTauxInflation());

                data.put("annee", annee);
                data.put("tauxInflation", tauxInflation);

                super.setData(data, editVBox);

            } else if (this.table.equals("donneesannuelles")) {
                String[] split = searchBar.getText().split(",");
                if (split.length == 2) {
                    CommuneBase com = super.getModel().getCommuneBaseDAO()
                            .findByID(Long.parseLong(split[0].toUpperCase()));
                    Annee an = super.getModel().getAnneeDAO().findByID(Long.parseLong(split[1]));

                    CommunesInfoParAnnee cia = super.getModel().getCommunesInfoParAnneeDAO().findByIDComplete(an, com);

                    String lAnnee = Integer.toString(an.getAnneeRepr());
                    String laCommune = Long.toString(cia.getLaCommune().getIdCommune());
                    String nbMaison = Integer.toString(cia.getNbMaison());
                    String nbAppart = Integer.toString(cia.getNbAppart());
                    String prixMoyen = Double.toString(cia.getPrixMoyen());
                    String prixM2Moyen = Double.toString(cia.getPrixMCarreMoyen());
                    String surfaceMoy = Double.toString(cia.getSurfaceMoy());
                    String depensesCulturellesTotales = Double.toString(cia.getDepCulturellesTotales());
                    String budgetTotal = Double.toString(cia.getBudgetTotal());
                    String population = Long.toString(Math.round(cia.getPopulation()));

                    data.put("lAnnee", lAnnee);
                    data.put("laCommune", laCommune);
                    data.put("nbMaison", nbMaison);
                    data.put("nbAppart", nbAppart);
                    data.put("prixMoyen", prixMoyen);
                    data.put("prixM2Moyen", prixM2Moyen);
                    data.put("surfaceMoy", surfaceMoy);
                    data.put("depensesCulturellesTotales", depensesCulturellesTotales);
                    data.put("budgetTotal", budgetTotal);
                    data.put("population", population);

                    super.setData(data, editVBox);
                }
            } else if (this.table.equals("gare")) {
                Map<Integer, Gare> gareInfo = super.getModel().getGareDAO()
                        .findAll(Long.parseLong(searchBar.getText()));
                if (gareInfo.size() != 0) {
                    Integer laCommune = gareInfo.keySet().iterator().next();
                    Gare gare = gareInfo.get(laCommune);
                    String codeGare = Integer.toString(gare.getCodeGare());
                    String nomGare = gare.getNomGare();
                    String estFret;
                    if (gare.getEstFret()) {
                        estFret = "1";
                    } else {
                        estFret = "0";
                    }
                    String estVoyageur;
                    if (gare.getEstVoyageur()) {
                        estVoyageur = "1";
                    } else {
                        estVoyageur = "0";
                    }
                    String laCommuneStr = Integer.toString(laCommune);

                    data.put("codeGare", codeGare);
                    data.put("nomGare", nomGare);
                    data.put("estFret", estFret);
                    data.put("estVoyageur", estVoyageur);
                    data.put("laCommune", laCommuneStr);

                    super.setData(data, editVBox);
                }
            }
        }
    }

    private void resetSearchAndButton() {
        searchBar.clear();
        searchBar.setPromptText("Rechercher données");
        searchBar.setDisable(false);
        editButton.setDisable(false);
        editButton.styleProperty().set("-fx-background-color: #A180E4; -fx-text-fill: white; -fx-font-size: 14px;");
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
        if (info.equals("voisinage")) {
            searchBar.setPromptText("Recherche impossible pour le voisinage");
            searchBar.setDisable(true);
            editButton.styleProperty().set("-fx-background-color: #808080; -fx-text-fill: white; -fx-font-size: 14px;");
            editButton.setDisable(true);
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
        int result = 0;
        if (!data.containsValue("")) {
            try {
                if (this.table.equals("aeroport")) {
                    String nom = data.get("nom");
                    String adresse = data.get("adresse");
                    int dep = Integer.parseInt(data.get("leDepartement"));

                    Aeroport aeroport = new Aeroport(nom, adresse);
                    result = super.getModel().getAeroportDAO().update(aeroport, dep);
                }

                else if (this.table.equals("departement")) {

                    int idDep = Integer.parseInt(data.get("idDep"));
                    String nomDep = data.get("nomDep");
                    DepPossibles nomDepEnum = DepPossibles.valueOf(nomDep);
                    long invesCulturel = Long.parseLong(data.get("investissementCulturel2019"));
                    List<Aeroport> aeroports = super.getModel().getTousAeroport().get(String.valueOf(idDep));

                    Departement dep = new Departement(idDep, nomDepEnum, invesCulturel, aeroports);
                    result = super.getModel().getDepartementDAO().update(dep);
                }

                else if (this.table.equals("voisinage")) {
                    // Cas impossible : Le voisinage dépend des deux communes
                }

                else if (this.table.equals("commune")) {
                    int idCommune = Integer.parseInt(data.get("idCommune"));
                    String nomCommune = data.get("nomCommune");
                    String leDepartement = data.get("leDepartement");
                    Departement dep = super.getModel().getTousLesDepartements().get(leDepartement);
                    CommuneBase communeBase = new CommuneBase(idCommune, nomCommune, dep, null, null);
                    result = super.getModel().getCommuneBaseDAO().update(communeBase);
                }

                else if (this.table.equals("annee")) {
                    int lAnnee = Integer.parseInt(data.get("annee"));
                    double tauxInflation = Double.parseDouble(data.get("tauxInflation"));

                    Annee annee = new Annee(lAnnee, tauxInflation);
                    result = super.getModel().getAnneeDAO().update(annee);
                }

                else if (this.table.equals("donneesannuelles")) {
                    String anneeGet = data.get("lAnnee");
                    String idCommune = data.get("laCommune");
                    int nbMaisons = Integer.parseInt(data.get("nbMaison"));
                    int nbAppartements = Integer.parseInt(data.get("nbAppart"));
                    double prixMoyen = Double.parseDouble(data.get("prixMoyen"));
                    double prixM2Moyen = Double.parseDouble(data.get("prixM2Moyen"));
                    double surfaceMoyenne = Double.parseDouble(data.get("surfaceMoy"));
                    double depensesCulturellesTotales = Double.parseDouble(data.get("depensesCulturellesTotales"));
                    double budgetTotal = Double.parseDouble(data.get("budgetTotal"));
                    int population = Integer.parseInt(data.get("population"));

                    CommuneBase communeBase = super.getModel().getToutesLesCommunesBase().get(idCommune);
                    Annee annee = super.getModel().getToutesLesAnnees().get(anneeGet);

                    CommunesInfoParAnnee communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, annee, nbMaisons,
                            nbAppartements, prixMoyen, prixM2Moyen, surfaceMoyenne, depensesCulturellesTotales,
                            budgetTotal, population);

                    result = super.getModel().getCommunesInfoParAnneeDAO().update(communesInfoParAnnee);
                }

                else if (this.table.equals("gare")) {
                    int codeGare = Integer.parseInt(data.get("codeGare"));
                    String nomGare = data.get("nomGare");
                    boolean estFret = Boolean.parseBoolean(data.get("estFret"));
                    boolean estVoyageur = Boolean.parseBoolean(data.get("estVoyageur"));
                    int codeCommune = Integer.parseInt(data.get("laCommune"));

                    Gare gare = new Gare(codeGare, nomGare, estFret, estVoyageur);
                    result = super.getModel().getGareDAO().update(gare, codeCommune);
                }
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + " : "  + e.getMessage());
                result = 0;
            } finally {
                Alert alert;
                if (result == 1) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Mise à jour réussi");
                    alert.setHeaderText("Mise à jour réussi");
                    alert.setContentText("Les données ont été correctement mise à jour dans la base de données");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur lors de la mise à jour");
                    alert.setHeaderText("Erreur lors de la mise à jour");
                    alert.setContentText("Les données n'ont pas pu être mise à jour dans la base de données");
                }
                alert.show();
            }
        }
    }

    /**
     * Permet de gérer l'appui sur le bouton de suppression
     */
    @FXML
    private void deleteButtonPressed() {
        Map<String, String> data = super.getData(this.editVBox);
        int result = 0;
        if (!data.containsValue("")) {
            try {
                if (this.table.equals("aeroport")) {
                    String nom = data.get("nom");
                    String adresse = data.get("adresse");
                    int dep = Integer.parseInt(data.get("leDepartement"));

                    Aeroport aeroport = new Aeroport(nom, adresse);
                    result = super.getModel().getAeroportDAO().delete(aeroport, dep);
                }

                else if (this.table.equals("departement")) {

                    int idDep = Integer.parseInt(data.get("idDep"));
                    String nomDep = data.get("nomDep");
                    DepPossibles nomDepEnum = DepPossibles.valueOf(nomDep);
                    long invesCulturel = Long.parseLong(data.get("investissementCulturel2019"));
                    List<Aeroport> aeroports = super.getModel().getTousAeroport().get(String.valueOf(idDep));

                    Departement dep = new Departement(idDep, nomDepEnum, invesCulturel, aeroports);
                    result = super.getModel().getDepartementDAO().delete(dep);
                }

                else if (this.table.equals("voisinage")) {
                    int idCommune = Integer.parseInt(data.get("commune"));
                    int idCommuneVoisine = Integer.parseInt(data.get("communeVoisine"));

                    result = super.getModel().getVoisinageDAO().delete(idCommune, idCommuneVoisine);
                }

                else if (this.table.equals("commune")) {
                    int idCommune = Integer.parseInt(data.get("idCommune"));
                    String nomCommune = data.get("nomCommune");
                    String leDepartement = data.get("leDepartement");
                    Departement dep = super.getModel().getTousLesDepartements().get(leDepartement);
                    CommuneBase communeBase = new CommuneBase(idCommune, nomCommune, dep, null, null);
                    result = super.getModel().getCommuneBaseDAO().delete(communeBase);
                }

                else if (this.table.equals("annee")) {
                    int lAnnee = Integer.parseInt(data.get("annee"));
                    double tauxInflation = Double.parseDouble(data.get("tauxInflation"));

                    Annee annee = new Annee(lAnnee, tauxInflation);
                    result = super.getModel().getAnneeDAO().delete(annee);
                }

                else if (this.table.equals("donneesannuelles")) {
                    String anneeGet = data.get("lAnnee");
                    String idCommune = data.get("laCommune");
                    int nbMaisons = Integer.parseInt(data.get("nbMaison"));
                    int nbAppartements = Integer.parseInt(data.get("nbAppart"));
                    double prixMoyen = Double.parseDouble(data.get("prixMoyen"));
                    double prixM2Moyen = Double.parseDouble(data.get("prixM2Moyen"));
                    double surfaceMoyenne = Double.parseDouble(data.get("surfaceMoy"));
                    double depensesCulturellesTotales = Double.parseDouble(data.get("depensesCulturellesTotales"));
                    double budgetTotal = Double.parseDouble(data.get("budgetTotal"));
                    int population = Integer.parseInt(data.get("population"));

                    CommuneBase communeBase = super.getModel().getToutesLesCommunesBase().get(idCommune);
                    Annee annee = super.getModel().getToutesLesAnnees().get(anneeGet);

                    CommunesInfoParAnnee communesInfoParAnnee = new CommunesInfoParAnnee(communeBase, annee, nbMaisons,
                            nbAppartements, prixMoyen, prixM2Moyen, surfaceMoyenne, depensesCulturellesTotales,
                            budgetTotal, population);

                    result = super.getModel().getCommunesInfoParAnneeDAO().delete(communesInfoParAnnee);
                }

                else if (this.table.equals("gare")) {
                    int codeGare = Integer.parseInt(data.get("codeGare"));
                    String nomGare = data.get("nomGare");
                    boolean estFret = Boolean.parseBoolean(data.get("estFret"));
                    boolean estVoyageur = Boolean.parseBoolean(data.get("estVoyageur"));
                    int codeCommune = Integer.parseInt(data.get("laCommune"));

                    Gare gare = new Gare(codeGare, nomGare, estFret, estVoyageur);
                    result = super.getModel().getGareDAO().delete(gare, codeCommune);
                }
            } catch (Exception e) {
                System.out.println(e.getClass().getName() + " : " + e.getMessage());
                e.printStackTrace();

                result = 0;
            } finally {
                Alert alert;
                if (result == 1) {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Suppression réussi");
                    alert.setHeaderText("Suppression  réussi");
                    alert.setContentText("Les données ont été correctement supprimé dans la base de données");
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur lors de la suppression");
                    alert.setHeaderText("Erreur lors de la suppression");
                    alert.setContentText("Les données n'ont pas pu être supprimé dans la base de données");
                }
                alert.show();
            }
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
        resetSearchAndButton();
        this.resetEditVBox(this.editVBox);
        this.resize();
    }
}
