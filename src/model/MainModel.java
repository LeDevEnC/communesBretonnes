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
import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.Departement;
import model.data.Gare;

public class MainModel {

    AeroportDAO aeroportDAO;

    AnneeDAO anneeDAO;

    CommuneBaseDAO communeBaseDAO;

    CommunesInfoParAnneeDAO communesInfoParAnneeDAO;

    DepartementDAO departementDAO;

    GareDAO gareDAO;

    Map<String, ArrayList<Aeroport>> tousAeroport;

    Map<String, ArrayList<Gare>> toutesLesGares;

    Map<String, Departement> tousLesDepartements;

    Map<String, Annee> toutesLesAnnees;

    Map<String, CommuneBase> toutesLesCommunesBase;

    Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee;

    final String loginSavePath = System.getProperty("user.dir") + "/login.txt";

    /**
     * Gère si l'utilisateur est connecté à la bdd (en écriture) ou non
     *
     * Utilise une propriété pour pouvoir être écouté par les contrôleurs
     */
    private BooleanProperty isLogged;

    /**
     * Nom d'utilisateur
     */
    private String username;
    private String password;

    /**
     * Permet de connecter l'utilisateur à la bdd
     */
    public void login(String username, String password) {
        // Replace with your database url, username, and password
        String url = "jdbc:mysql://localhost:3306/bdsae";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            reCreateDAO();
            // If connection is successful
            this.username = username;
            this.password = password;
            this.isLogged.set(true);
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
    }

    private void reCreateDAO() {
        if (this.tousAeroport == null || tousLesDepartements == null || toutesLesGares == null
                || toutesLesAnnees == null || toutesLesCommunesBase == null || toutesLesCommunesInfoParAnnee == null) {
            initData();
        } else {
            this.aeroportDAO = new AeroportDAO(this.username, this.password);
            this.anneeDAO = new AnneeDAO();
            this.departementDAO = new DepartementDAO(this.username, this.password, this.tousAeroport);
            this.gareDAO = new GareDAO();
            this.communeBaseDAO = new CommuneBaseDAO(this.username,
                    this.password, this.tousLesDepartements, this.toutesLesGares);
            this.communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(this.username, this.password,
                    this.toutesLesAnnees, this.toutesLesCommunesBase);
        }
    }

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
            conseil[2] = "L'année possède un meilleur score que l'année précédente";
        } else {
            conseil[2] = "L'année possède un moins bon score que l'année précédente";
        }

        return conseil;
    }

    public void saveLogin() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(loginSavePath))) {
            writer.write(this.username);
            writer.newLine();
            writer.write(this.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void deleteLogin() {
        File file = new File(loginSavePath);
        if (file.exists()) {
            if (!file.delete()) {
                System.err.println("Échec de la suppression du fichier : " + file.getPath());
            }
        }
    }

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
}
