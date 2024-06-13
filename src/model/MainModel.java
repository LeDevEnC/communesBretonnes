package model;

import java.io.BufferedWriter;
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
            this.isLogged.set(true);
            this.username = username;
            this.password = password;
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
