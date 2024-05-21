package model;

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
        // TODO : Connexion à la bdd
        if (email.equals("admin") && password.equals("admin")) { // Utiliser tant que la connexion n'est pas implémentée
            isLogged = true;
            username = email;
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
