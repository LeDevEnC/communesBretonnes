package model.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Departement représentant les informations d'un département
 */
public class Departement {
    /**
     * Identifiant du département
     */
    private int idDep;

    /**
     * Nom du département
     */
    private String nomDep;

    /**
     * Investissement culturel du département en 2019 représenté par une constante
     */
    private final long invesCulturel2019; // Note : constante d'instance, suit les conventions de nommage Java standard

    /**
     * Liste des aéroports du département
     */
    private List<Aeroport> aeroports;

    /**
     * Constructeur de la classe Departement
     *
     * @param idDep         l'id du département
     * @param nomDep        le nom du département
     * @param invesCulturel l'investissement culturel du département
     * @param aeroports     la liste des aéroports du département
     * @throws IllegalArgumentException si l'id du département est négatif ou le nom
     *                                  du département, l'investissement culturel ou
     *                                  la liste des aéroports est null
     */
    public Departement(int idDep, String nomDep, long invesCulturel, List<Aeroport> aeroports)
            throws IllegalArgumentException {
        if (idDep < 0 || nomDep == null || invesCulturel < 0) {
            throw new IllegalArgumentException(
                    "L'identifiant du département est négatif ou le nom du département ou l'investissement culturel sont null");
        } else {
            if (aeroports == null) {
                this.aeroports = new ArrayList<>();
            } else {
                this.aeroports = aeroports;
            }
            this.idDep = idDep;
            this.nomDep = nomDep;
            this.invesCulturel2019 = invesCulturel;
        }
    }

    /**
     * Getter de l'identifiant du département
     *
     * @return l'identifiant du département
     */
    public int getIdDep() {
        return this.idDep;
    }

    /**
     * Getter du nom du département
     *
     * @return le nom du département
     */
    public String getNomDep() {
        return this.nomDep;
    }

    /**
     * Getter de l'investissement culturel du département
     *
     * @return l'investissement culturel du département
     */
    public long getInvestCulturel2019() {
        return this.invesCulturel2019;
    }

    /**
     * Getter de la liste des aéroports du département
     *
     * @return la liste des aéroports du département
     */
    public List<Aeroport> getAeroports() {
        return this.aeroports;
    }

    /**
     * Setter de l'id du département
     *
     * @param idDep l'id du département
     * @throws IllegalArgumentException si l'id du département est négatif
     */
    public void setId(int idDep) throws IllegalArgumentException {
        if (idDep < 0) {
            throw new IllegalArgumentException("L'identifiant du département est négatif");
        } else {
            this.idDep = idDep;
        }
    }

    /**
     * Setter du nom du département
     *
     * @param nomDep le nom du département
     * @throws IllegalArgumentException si le nom du département est null ou n'est
     *                                  pas valide
     */
    public void setNomDep(DepPossibles nomDep) throws IllegalArgumentException {
        if (nomDep == null) {
            throw new IllegalArgumentException("Le nom du département est null");
        } else {
            try {
                this.nomDep = DepPossibles.valueOf(nomDep.toString()).toString();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Le nom du département n'est pas valide");
            }
        }
    }

    /**
     * Compare les investissements culturels de deux départements
     *
     * @param autreDep l'autre département à comparer
     * @return 0 si les investissements culturels sont égaux, 1 si l'investissement
     *         culturel du département est supérieur à celui de l'autre département,
     *         -1 sinon
     * @throws IllegalArgumentException si le département à comparer est null
     */
    public int compareInvesCulturel(Departement autreDep) throws IllegalArgumentException {
        if (autreDep == null) {
            throw new IllegalArgumentException("Le département à comparer est null");
        } else {
            int res = 0;
            if (this.invesCulturel2019 > autreDep.getInvestCulturel2019()) {
                return 1;
            } else if (this.invesCulturel2019 < autreDep.getInvestCulturel2019()) {
                return -1;
            }
            return res;
        }
    }

    /**
     * Méthode permettant d'afficher les informations d'un département sous forme de
     * chaîne de caractères
     * 
     * @return les informations du département sous forme de csv
     *         "idDep","nomDep","invesCulturel2019"
     */
    public String toString() {
        return "\"" + this.idDep + "\",\"" + this.nomDep + "\",\"" + this.invesCulturel2019 + "\"";
    }
}
