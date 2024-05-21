package model.data;

/**
 * Classe représentant un aéroport
 */
public class Aeroport {
    /**
     * Nom de l'aéroport
     */
    private String nom;

    /**
     * Adresse de l'aéroport
     */
    private String adresse;

    /**
     * Constructeur de la classe Aeroport
     *
     * @param nom     le nom de l'aéroport
     * @param adresse l'adresse de l'aéroport
     * @throws IllegalArgumentException si le nom ou l'adresse de l'aéroport est
     *                                  null
     */
    public Aeroport(String nom, String adresse) throws IllegalArgumentException {
        if (nom == null || adresse == null) {
            throw new IllegalArgumentException("Le nom ou l'adresse de l'aéroport est null");
        } else {
            this.nom = nom;
            this.adresse = adresse;
        }
    }

    /**
     * Getter du nom de l'aéroport
     *
     * @return le nom de l'aéroport
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Getter de l'adresse de l'aéroport
     *
     * @return l'adresse de l'aéroport
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter du nom de l'aéroport
     *
     * @param nom le nom de l'aéroport
     * @throws IllegalArgumentException si le nom de l'aéroport est null
     */
    public void setNom(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("Le nom de l'aéroport est null");
        } else {
            this.nom = nom;
        }
    }

    /**
     * Setter de l'adresse de l'aéroport
     *
     * @param adresse l'adresse de l'aéroport
     * @throws IllegalArgumentException si l'adresse de l'aéroport est null
     */
    public void setAdresse(String adresse) throws IllegalArgumentException {
        if (adresse == null) {
            throw new IllegalArgumentException("L'adresse de l'aéroport est null");
        } else {
            this.adresse = adresse;
        }
    }

    /**
     * Méthode permettant de comparer deux aéroports
     *
     * @param autreAero l'aéroport à comparer
     * @return true si les deux aéroports sont les mêmes, false sinon
     * @throws IllegalArgumentException si l'aéroport à comparer est null
     */
    public boolean isSameAero(Aeroport autreAero) throws IllegalArgumentException {
        boolean isSame = false;
        if (autreAero == null) {
            throw new IllegalArgumentException("L'aéroport à comparer est null");
        } else {
            if (this.nom.equalsIgnoreCase(autreAero.getNom())
                    && this.adresse.equalsIgnoreCase(autreAero.getAdresse())) {
                isSame = true;
            }
        }

        return isSame;
    }
}