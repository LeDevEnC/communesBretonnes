package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.dao.AeroportDAO;
import model.dao.AnneeDAO;
import model.dao.CommuneBaseDAO;
import model.dao.CommunesInfoParAnneeDAO;
import model.dao.DepartementDAO;
import model.dao.GareDAO;
import model.dao.VoisinageDAO;
import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.Departement;
import model.data.Gare;

/**
 * Modèle principal de l'application
 * Contient les données et les méthodes pour les manipuler
 */
public class MainModel {

    /**
     * Contient la DAO pour les aéroports
     */
    AeroportDAO aeroportDAO;

    /**
     * Contient la DAO pour les années
     */
    AnneeDAO anneeDAO;

    /**
     * Contient la DAO pour les communes
     */
    CommuneBaseDAO communeBaseDAO;

    /**
     * Contient la DAO pour les informations des communes par année
     */
    CommunesInfoParAnneeDAO communesInfoParAnneeDAO;

    /**
     * Contient la DAO pour les voisinages
     */
    VoisinageDAO voisinageDAO;

    /**
     * Contient la DAO pour les départements
     */
    DepartementDAO departementDAO;

    /**
     * Contient la DAO pour les gares
     */
    GareDAO gareDAO;

    /**
     * Stocke tous les aéroports
     * Clé : Département
     * Valeur : Liste des aéroports du département
     */
    Map<String, ArrayList<Aeroport>> tousAeroport;

    /**
     * Stocke toutes les gares
     * Clé : Commune
     * Valeur : Liste des gares de la commune
     */
    Map<String, ArrayList<Gare>> toutesLesGares;

    /**
     * Stocke tous les départements
     * Clé : ID du département (numéro du département)
     * Valeur : Objet département correspondant
     */
    Map<String, Departement> tousLesDepartements;

    /**
     * Stocke toutes les années
     * Clé : Année
     * Valeur : Objet année correspondant
     */
    Map<String, Annee> toutesLesAnnees;

    /**
     * Stocke toutes les communes de base
     * Clé : ID de la commune
     * Valeur : Objet commune correspondant
     */
    Map<String, CommuneBase> toutesLesCommunesBase;

    /**
     * Stocke toutes les informations des communes par année
     * Clé : Nom de la commune, Année
     * Valeur : Objet commune info par année correspondant
     */
    Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee;

    /**
     * Chemin du fichier de sauvegarde du login
     */
    final String loginSavePath = System.getProperty("user.dir") + "/login.txt";

    /**
     * Gère si l'utilisateur est connecté à la bdd (en écriture) ou non
     *
     * Utilise une propriété pour pouvoir être écouté par les contrôleurs
     */
    private BooleanProperty isLogged;

    /**
     * Nom d'utilisateur pour la connexion à la bdd
     */
    private String username;

    /**
     * Mot de passe pour la connexion à la bdd
     */
    private String password;


    /**
     * Permet de connecter l'utilisateur à la bdd
     * 
     * @param username Nom d'utilisateur
     * @param password Mot de passe
     */
    public void login(String username, String password) {
        // Replace with your database url, username, and password
        String url = "jdbc:mysql://localhost:3306/bdsae";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // If connection is successful
            this.username = username;
            this.password = password;
            this.isLogged.set(true);
            reCreateDAO();
        } catch (SQLException e) {
            System.out.println("Connection failed : " + e.getMessage());
        }
    }

    /**
     * Permet de déconnecter l'utilisateur de la bdd
     */
    public void logout() {
        this.username = "visitor";
        this.password = "";
        this.isLogged.set(false);
        this.reCreateDAO();
    }

    /**
     * Permet de déconnecter l'utilisateur de la bdd
     * 
     * @param deleteLogin Supprime le login enregistré
     */
    public void logout(boolean deleteLogin) {
        if (deleteLogin) {
            deleteLogin();
        }
        logout();
    }

    /**
     * Permet de savoir si l'utilisateur est connecté à la bdd
     * 
     * @return true si l'utilisateur est connecté, false sinon
     */
    public boolean isLogged() {
        return isLogged.get();
    }

    /**
     * Permet de récupérer la propriété isLogged
     * 
     * @return la propriété isLogged
     */
    public BooleanProperty isLoggedProperty() {
        return isLogged;
    }


    /**
     * Permet de récupérer le nom d'utilisateur
     * 
     * @return le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Initialise les différents DAO et récupère les données de la bdd
     */
    private void initData() {
        this.aeroportDAO = new AeroportDAO(this.username, this.password);
        this.tousAeroport = this.aeroportDAO.findAll();

        this.anneeDAO = new AnneeDAO();
        this.toutesLesAnnees = this.anneeDAO.findAll();

        this.departementDAO = new DepartementDAO(this.username, this.password, this.tousAeroport);
        this.tousLesDepartements = this.departementDAO.findAll();

        this.gareDAO = new GareDAO(this.username, this.password);
        this.toutesLesGares = this.gareDAO.findAll();

        this.communeBaseDAO = new CommuneBaseDAO(this.username,
                this.password, this.tousLesDepartements, this.toutesLesGares);
        this.toutesLesCommunesBase = this.communeBaseDAO.findAll();

        this.communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(this.username,
                this.password, this.toutesLesAnnees, this.toutesLesCommunesBase);
        this.toutesLesCommunesInfoParAnnee = this.communesInfoParAnneeDAO.findAll();

        this.voisinageDAO = new VoisinageDAO(this.username, this.password);
    }

    /**
     * Recrée les DAO
     * Si les données ne sont pas déjà créer, les crées, sinon recrée juste les DAO
     * Utilisé après une connexion/déconnexion
     */
    private void reCreateDAO() {
        if (this.tousAeroport == null || tousLesDepartements == null || toutesLesGares == null
                || toutesLesAnnees == null || toutesLesCommunesBase == null || toutesLesCommunesInfoParAnnee == null) {
            initData();
        } else {
            this.aeroportDAO = new AeroportDAO(this.username, this.password);
            this.anneeDAO = new AnneeDAO(this.username, this.password);
            this.departementDAO = new DepartementDAO(this.username, this.password, this.tousAeroport);
            this.gareDAO = new GareDAO(this.username, this.password);
            this.communeBaseDAO = new CommuneBaseDAO(this.username,
                    this.password, this.tousLesDepartements, this.toutesLesGares);
            this.communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(this.username, this.password,
                    this.toutesLesAnnees, this.toutesLesCommunesBase);
            this.voisinageDAO = new VoisinageDAO(this.username, this.password);
        }
    }

    /**
     * Exporte les données dans un fichier
     * 
     * @param filePath Le chemin du fichier
     * @param data     Les données à exporter, sous forme de Map possédant des
     *                 ArrayList
     * @param header   L'en-tête du fichier (correspond aux colonnes du fichier CSV)
     * @throws IOException En cas d'erreur lors de l'écriture du fichier
     */
    private void exportSpecificDataWithArrayList(String filePath, Map<String, ? extends ArrayList<?>> data,
            String header) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            writer.newLine();
            for (ArrayList<?> objArrayList : data.values()) {
                for (Object obj : objArrayList) {
                    writer.write(obj.toString());
                    writer.newLine();
                }
            }
        }
    }

    /**
     * Exporte les données dans un fichier
     * 
     * @param filePath Le chemin du fichier
     * @param data     Les données à exporter, sous forme de Map
     * @param header   L'en-tête du fichier (correspond aux colonnes du fichier CSV)
     * @throws IOException En cas d'erreur lors de l'écriture du fichier
     */
    private void exportSpecificData(String filePath, Map<String, ?> data, String header) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(header);
            writer.newLine();
            for (Object obj : data.values()) {
                writer.write(obj.toString());
                writer.newLine();
            }
        }
    }

    /**
     * Exporte les données des classes POJO dans un dossier
     * 
     * @param path Le chemin du dossier
     * @return true si l'exportation a réussi, false sinon
     */
    public boolean exportData(String path) {
        try {
            String filePath;
            String header;
            // Aeroport
            filePath = path + "/aeroport.csv";
            header = "nom,adresse";
            exportSpecificDataWithArrayList(filePath, tousAeroport, header);

            // Annee
            filePath = path + "/annee.csv";
            header = "annee,tauxInflation";
            exportSpecificData(filePath, toutesLesAnnees, header);

            // Departement
            filePath = path + "/departement.csv";
            header = "idDep,nomDep,investCulturel2019";
            exportSpecificData(filePath, tousLesDepartements, header);

            // Gare
            filePath = path + "/gare.csv";
            header = "codeGare,nomGare,estFret,estVoyageur";
            exportSpecificDataWithArrayList(filePath, toutesLesGares, header);

            // CommuneBase
            filePath = path + "/communeBase.csv";
            header = "idCommune,nomCommune,leDepartement";
            exportSpecificData(filePath, toutesLesCommunesBase, header);

            // CommunesInfoParAnnee
            filePath = path + "/communesInfoParAnnee.csv";
            header = "NomCommune,Annee,NbMaison,NbAppart,PrixMoyen,PrixMCarreMoyen,SurfaceMoyen,DepCulturellesTotales,BudgetTotal,Population";
            exportSpecificData(filePath, toutesLesCommunesInfoParAnnee, header);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Génère un conseil pour l'utilisateur
     * 
     * @return Un tableau de 3 String contenant les conseils
     */
    public String[] generateConseil() {
        String[] conseil = new String[3];

        // Calcule le pire département
        int[] scoresDep = new int[4];
        int[] nbDep = new int[4];
        String pireDep;

        Calculator.computeScoresAndNb(toutesLesCommunesInfoParAnnee, scoresDep, nbDep);
        Calculator.normalizeScores(scoresDep, nbDep);

        if (scoresDep[0] > scoresDep[1] && scoresDep[0] > scoresDep[2] && scoresDep[0] > scoresDep[3]) {
            pireDep = "Finistère";
        } else if (scoresDep[1] > scoresDep[0] && scoresDep[1] > scoresDep[2] && scoresDep[1] > scoresDep[3]) {
            pireDep = "Côtes-d'Armor";
        } else if (scoresDep[2] > scoresDep[0] && scoresDep[2] > scoresDep[1] && scoresDep[2] > scoresDep[3]) {
            pireDep = "Morbihan";
        } else {
            pireDep = "Ille-et-Vilaine";
        }

        conseil[0] = "Le département possédant le pire score est " + pireDep;

        int[] scoresAnnee = new int[3];
        int[] nbAnnee = new int[3];

        Calculator.computeScoresAndNbByYear(toutesLesCommunesInfoParAnnee, scoresAnnee, nbAnnee);
        Calculator.normalizeScoresLineChart(scoresAnnee, nbAnnee);

        if (scoresAnnee[0] > scoresAnnee[1] && scoresAnnee[0] > scoresAnnee[2]) {
            conseil[1] = "L'année possédant le pire score est 2017";
        } else if (scoresAnnee[1] > scoresAnnee[0] && scoresAnnee[1] > scoresAnnee[2]) {
            conseil[1] = "L'année possédant le pire score est 2018";
        } else {
            conseil[1] = "L'année possédant le pire score est 2019";
        }

        int tailleNbAnnee = nbAnnee.length;
        if (scoresAnnee[tailleNbAnnee - 1] > scoresAnnee[tailleNbAnnee - 2]) {
            conseil[2] = "L'année actuelle possède un meilleur score que l'année précédente";
        } else {
            conseil[2] = "L'année actuelle possède un moins bon score que l'année précédente";
        }

        return conseil;
    }

    /**
     * Sauvegarde le login dans un fichier
     */
    public void saveLogin() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(loginSavePath))) {
            writer.write(this.username);
            writer.newLine();
            writer.write(this.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Charge le login depuis un fichier
     */
    public void loadLogin() {
        File file = new File(loginSavePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                this.username = reader.readLine();
                this.password = reader.readLine();
                login(this.username, this.password);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime le fichier de sauvegarde du login
     */
    public void deleteLogin() {
        File file = new File(loginSavePath);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Échec de la suppression du fichier : " + file.getPath());
            }
        }
    }

    /**
     * Constructeur de la classe MainModel, initialise les valeurs des attributs de
     * la classe
     */
    public MainModel() {
        this.isLogged = new SimpleBooleanProperty();
        logout(); // Applique le mode visiteur par défaut et initialise les DAO
    }

    /**
     * Permet de récupérer tous les aéroports
     * 
     * @return La Map contenant tous les aéroports
     */
    public Map<String, ArrayList<Aeroport>> getTousAeroport() {
        return tousAeroport;
    }

    /**
     * Permet de récupérer toutes les gares
     * 
     * @return La Map contenant toutes les gares
     */
    public Map<String, ArrayList<Gare>> getToutesLesGares() {
        return toutesLesGares;
    }

    /**
     * Permet de récupérer tous les départements
     * 
     * @return La Map contenant tous les départements
     */
    public Map<String, Departement> getTousLesDepartements() {
        return tousLesDepartements;
    }

    /**
     * Permet de récupérer toutes les années
     * 
     * @return La Map contenant toutes les années
     */
    public Map<String, Annee> getToutesLesAnnees() {
        return toutesLesAnnees;
    }

    /**
     * Permet de récupérer toutes les communes de base
     * 
     * @return La Map contenant toutes les communes de base
     */
    public Map<String, CommuneBase> getToutesLesCommunesBase() {
        return toutesLesCommunesBase;
    }

    /**
     * Permet de récupérer toutes les communes info par année
     * 
     * @return La Map contenant toutes les communes info par année
     */
    public Map<String, CommunesInfoParAnnee> getToutesLesCommunesInfoParAnnee() {
        return toutesLesCommunesInfoParAnnee;
    }

    /**
     * Permet de récupérer la DAO pour les aéroports
     * 
     * @return La DAO pour les aéroports
     */
    public AeroportDAO getAeroportDAO() {
        return aeroportDAO;
    }

    /**
     * Permet de récupérer la DAO pour les années
     * 
     * @return La DAO pour les années
     */
    public AnneeDAO getAnneeDAO() {
        return anneeDAO;
    }

    /**
     * Permet de récupérer la DAO pour les communes
     * 
     * @return La DAO pour les communes
     */
    public CommuneBaseDAO getCommuneBaseDAO() {
        return communeBaseDAO;
    }

    /**
     * Permet de récupérer la DAO pour les informations des communes par année
     * 
     * @return La DAO pour les informations des communes par année
     */
    public CommunesInfoParAnneeDAO getCommunesInfoParAnneeDAO() {
        return communesInfoParAnneeDAO;
    }

    /**
     * Permet de récupérer la DAO pour les départements
     * 
     * @return La DAO pour les départements
     */
    public DepartementDAO getDepartementDAO() {
        return departementDAO;
    }

    /**
     * Permet de récupérer la DAO pour les gares
     * 
     * @return La DAO pour les gares
     */
    public GareDAO getGareDAO() {
        return gareDAO;
    }

    /**
     * Permet de récupérer la DAO pour les voisinages
     * 
     * @return La DAO pour les voisinages
     */
    public VoisinageDAO getVoisinageDAO() {
        return voisinageDAO;
    }
}
