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
     * Le departement de l'aeroport
     */
    private Departement departement;

    /**
     * Constructeur de la classe Aeroport
     *
     * @param nom     le nom de l'aéroport
     * @param adresse l'adresse de l'aéroport
     * @param departement le departement de l'aeroport
     * @throws IllegalArgumentException si le nom ou l'adresse de l'aéroport est
     *                                  null
     */
    public Aeroport(String nom, String adresse, Departement departement) throws IllegalArgumentException {
        if (nom == null || adresse == null || departement == null) {
            throw new IllegalArgumentException("Le nom ou l'adresse de l'aéroport ou le departement de l'aéroport est null");
        } else {
            this.nom = nom;
            this.adresse = adresse;
            this.departement = departement;
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
     * Getter du departement de l'aeroport
     *
     * @return le departement de l'aeroport
     */
    public Departement getDepartement() {
        return this.departement;
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
     * Setter du departement de l'aeroport
     *
     * @param departement le departement de l'aeroport
     * @throws IllegalArgumentException si le departement de l'aeroport est null
     */
    public void setDepartement(Departement departement) throws IllegalArgumentException {
        if (departement == null) {
            throw new IllegalArgumentException("Le departement de l'aeroport est null");
        } else {
            this.departement = departement;
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