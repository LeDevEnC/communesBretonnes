package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

/**
 * TODO : Implémenter le modèle
 */
public class MainModel {

    AeroportDAO aeroportDAO;

    AnneeDAO anneeDAO;

    CommuneBaseDAO communeBaseDAO;

    CommunesInfoParAnneeDAO communesInfoParAnneeDAO;

    DepartementDAO departementDAO;

    GareDAO gareDAO;

    HashMap<String, ArrayList<Aeroport>> tousAeroport;

    HashMap<String, ArrayList<Gare>> toutesLesGares;

    HashMap<String, Departement> tousLesDepartements;

    HashMap<String, Annee> toutesLesAnnees;

    HashMap<String, CommuneBase> toutesLesCommunesBase;

    HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee;

    /**
     * Gère si l'utilisateur est connecté à la bdd (en écriture) ou non
     */
    private boolean isLogged;

    /**
     * Nom d'utilisateur
     */
    private String username;
    private String password;

    /**
     * Permet de connecter l'utilisateur à la bdd
     */
    public void login(String username, String password){
        // Replace with your database url, username, and password
        String url = "jdbc:mysql://localhost:3306/bdsae";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // If connection is successful
            isLogged = true;
            this.username = username;
            this.password = password;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    /**
     * Permet de déconnecter l'utilisateur de la bdd
     */
    public void logout(){
        this.username = "visitor";
        this.password = "";
        this.isLogged = false;
    }

    /**
     * Permet de savoir si l'utilisateur est connecté à la bdd
     * 
     * @return true si l'utilisateur est connecté, false sinon
     */
    public boolean isLogged() {
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

    public MainModel() {
        this.aeroportDAO = new AeroportDAO();
        this.tousAeroport = this.aeroportDAO.findAll();

        this.anneeDAO = new AnneeDAO();
        this.toutesLesAnnees = this.anneeDAO.findAll();

        this.departementDAO = new DepartementDAO(this.tousAeroport);
        this.tousLesDepartements = this.departementDAO.findAll();

        this.gareDAO = new GareDAO();
        this.toutesLesGares = this.gareDAO.findAll();

        this.communeBaseDAO = new CommuneBaseDAO(this.tousLesDepartements, this.toutesLesGares);
        this.toutesLesCommunesBase = this.communeBaseDAO.findAll();

        this.communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(this.toutesLesAnnees, this.toutesLesCommunesBase);
        this.toutesLesCommunesInfoParAnnee = this.communesInfoParAnneeDAO.findAll();
    }

    /**
     * Permet de récupérer tous les aéroports
     * @return La HashMap contenant tous les aéroports
     */
    public HashMap<String, ArrayList<Aeroport>> getTousAeroport() {
        return tousAeroport;
    }

    /**
     * Permet de récupérer toutes les gares
     * @return La HashMap contenant toutes les gares
     */
    public HashMap<String, ArrayList<Gare>> getToutesLesGares() {
        return toutesLesGares;
    }

    /**
     * Permet de récupérer tous les départements
     * @return La HashMap contenant tous les départements
     */
    public HashMap<String, Departement> getTousLesDepartements() {
        return tousLesDepartements;
    }

    /**
     * Permet de récupérer toutes les années
     * @return La HashMap contenant toutes les années
     */
    public HashMap<String, Annee> getToutesLesAnnees() {
        return toutesLesAnnees;
    }

    /**
     * Permet de récupérer toutes les communes de base
     * @return La HashMap contenant toutes les communes de base
     */
    public HashMap<String, CommuneBase> getToutesLesCommunesBase() {
        return toutesLesCommunesBase;
    }

    /**
     * Permet de récupérer toutes les communes info par année
     * @return La HashMap contenant toutes les communes info par année
     */
    public HashMap<String, CommunesInfoParAnnee> getToutesLesCommunesInfoParAnnee() {
        return toutesLesCommunesInfoParAnnee;
    }
}
