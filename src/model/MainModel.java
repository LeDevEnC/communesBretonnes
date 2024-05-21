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
     * Permet de connecter l'utilisateur à la bdd
     */
    public void login(String email, String password){
        // TODO : Connexion à la bdd
        if (email.equals("admin") && password.equals("admin")) { // Utiliser tant que la connexion n'est pas implémentée
            isLogged = true;
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
}
