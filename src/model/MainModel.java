package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO : Implémenter le modèle
 */
public class MainModel {
    /**
     * Gère si l'utilisateur est connecté à la bdd (en écriture) ou non
     */
    private boolean isLogged;
    
    /**
     * Nom d'utilisateur
     */
    private String username;

    /**
     * Permet de connecter l'utilisateur à la bdd
     */
    public void login(String email, String password){
        // Replace with your database url, username, and password
        String url = "jdbc:mysql://localhost:3306/bdsae";
        String username = email;
        String dbPassword = password;

        try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
            // If connection is successful
            isLogged = true;
            this.username = email;
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
    /**
     * Permet de déconnecter l'utilisateur de la bdd
     */
    public void logout(){
        // TODO : Déconnexion de la bdd
        isLogged = false;
    }

    /**
     * Permet de savoir si l'utilisateur est connecté à la bdd
     * @return true si l'utilisateur est connecté, false sinon
     */
    public boolean isLogged(){
        return isLogged;
    }

    /**
     * Permet de récupérer le nom d'utilisateur
     * @return le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }
}
