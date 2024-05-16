package model.data;

import java.util.ArrayList;

/**
 * Classe CommuneBase représentant les informations de base d'une commune
 */
public class CommuneBase {
    /**
     * Identifiant de la commune
     */
    private int idCommune;

    /**
     * Nom de la commune
     */
    private String nomCommune;

    /**
     * Département de la commune
     */
    private Departement leDepartement;

    /**
     * Communes voisines de la commune
     */
    private ArrayList<CommuneBase> lesVoisins;

    /**
     * Gare de la commune
     */
    private Gare laGare;

    /**
     * Constructeur de la classe CommuneBase
     *
     * @param idCommune     l'identifiant de la commune
     * @param nomCommune    le nom de la commune
     * @param leDepartement le département de la commune
     * @param lesVoisins    les communes voisines de la commune
     * @param laGare        la gare de la commune
     */
    public CommuneBase(int idCommune, String nomCommune, Departement leDepartement, ArrayList<CommuneBase> lesVoisins,
            Gare laGare) throws IllegalArgumentException {
        if (idCommune < 0 || nomCommune == null || leDepartement == null || lesVoisins == null || laGare == null) {
            throw new IllegalArgumentException(
                    "L'identifiant de la commune est négatif ou le nom de la commune, le département, les communes voisines ou la gare de la commune sont null");
        } else {
            this.idCommune = idCommune;
            this.nomCommune = nomCommune;
            this.leDepartement = leDepartement;
            this.lesVoisins = lesVoisins;
            this.laGare = laGare;
        }
    }

    /**
     * Getter de l'identifiant de la commune
     *
     * @return l'identifiant de la commune
     */
    public int getIdCommune() {
        return this.idCommune;
    }

    /**
     * Getter du nom de la commune
     *
     * @return le nom de la commune
     */
    public String getNomCommune() {
        return this.nomCommune;
    }

    /**
     * Getter des communes voisines de la commune
     *
     * @return les communes voisines de la commune
     */
    public ArrayList<CommuneBase> getLesVoisins() {
        return this.lesVoisins;
    }

    /**
     * Getter du département de la commune
     *
     * @return le département de la commune
     */
    public Departement getLeDepartement() {
        return this.leDepartement;
    }

    /**
     * Getter de la gare de la commune
     *
     * @return la gare de la commune
     */
    public Gare getLaGare() {
        return this.laGare;
    }

    /**
     * Setter de l'identifiant de la commune
     *
     * @param idCommune l'identifiant de la commune
     * @throws IllegalArgumentException si l'identifiant de la commune est négatif
     */
    public void setIdCommune(int idCommune) throws IllegalArgumentException {
        if (idCommune < 0) {
            throw new IllegalArgumentException("L'identifiant de la commune est négatif");
        } else {
            this.idCommune = idCommune;
        }

    }

    /**
     * Setter du nom de la commune
     *
     * @param nomCommune le nom de la commune
     * @throws IllegalArgumentException si le nom de la commune est null
     */
    public void setNomCommune(String nomCommune) throws IllegalArgumentException {
        if (nomCommune == null) {
            throw new IllegalArgumentException("Le nom de la commune est null");
        } else {
            this.nomCommune = nomCommune;
        }

    }

    /**
     * Méthode qui permet de comparer le nombre de communes voisines de deux
     * communes et renvoit 1 si la commune a plus de voisins, -1 si elle en a moins
     * et 0 si elles en ont autant
     *
     * @param autreCommBase l'autre commune à comparer
     * @return 1 si la commune a plus de voisins, -1 si elle en a moins et 0 si
     *         elles en ont autant
     * @throws IllegalArgumentException si la commune à comparer est null
     */
    public int compareNbVoisins(CommuneBase autreCommBase) throws IllegalArgumentException {
        if (autreCommBase == null) {
            throw new IllegalArgumentException("La commune à comparer est null");
        } else {
            int comparaison = 0;
            if (this.lesVoisins.size() > autreCommBase.getLesVoisins().size()) {
                comparaison = 1;
            } else if (this.lesVoisins.size() < autreCommBase.getLesVoisins().size()) {
                comparaison = -1;
            }
            return comparaison;
        }
    }

}
