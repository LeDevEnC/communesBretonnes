package model.data;

/**
 * Classe représentant une gare
 */
public class Gare {
    /**
     * Code de la gare
     */

    private int codeGare;
    /**
     * Nom de la gare
     */

    private String nomGare;
    /**
     * Si la gare est un gare de fret
     */

    private boolean estFret;

    /**
     * Si la gare est un gare de voyageur
     */
    private boolean estVoyageur;

    /**
     * Constructeur de la classe Gare
     *
     * @param codeGare    le code de la gare
     * @param nomGare     le nom de la gare
     * @param estFret     si la gare est un gare de fret
     * @param estVoyageur si la gare est un gare de voyageur
     * @throws IllegalArgumentException si le code de la gare est négatif ou le nom
     *                                  de la gare est null
     */
    public Gare(int codeGare, String nomGare, boolean estFret, boolean estVoyageur) throws IllegalArgumentException {
        if (codeGare < 0 || nomGare == null) {
            throw new IllegalArgumentException("Le code de la gare est négatif ou le nom de la gare est null");
        } else {
            this.codeGare = codeGare;
            this.nomGare = nomGare;
            this.estFret = estFret;
            this.estVoyageur = estVoyageur;
        }

    }

    /**
     * Getter du code de la gare
     *
     * @return le code de la gare
     */
    public int getCodeGare() {
        return this.codeGare;
    }

    /**
     * Getter du nom de la gare
     *
     * @return le nom de la gare
     */
    public String getNomGare() {
        return this.nomGare;
    }

    /**
     * Getter si la gare est un gare de voyageurs
     *
     * @return si la gare est un gare de voyageur
     */
    public boolean getEstVoyageur() {
        return this.estVoyageur;
    }

    /**
     * Getter si la gare est un gare de fret
     *
     * @return si la gare est un gare de fret
     */
    public boolean getEstFret() {
        return this.estFret;
    }

    /**
     * Setter du code de la gare
     *
     * @param code le code de la gare
     * @throws IllegalArgumentException si le code de la gare est négatif
     */
    public void setCodeGare(int code) throws IllegalArgumentException {
        if (code < 0) {
            throw new IllegalArgumentException("Le code de la gare est négatif");
        } else {
            this.codeGare = code;
        }

    }

    /**
     * Setter du nom de la gare
     *
     * @param nom le nom de la gare
     * @throws IllegalArgumentException si le nom de la gare est null
     */
    public void setNomGare(String nom) throws IllegalArgumentException {
        if (nom == null) {
            throw new IllegalArgumentException("Le nom de la gare est null");
        } else {
            this.nomGare = nom;
        }
    }

    /**
     * Setter si la gare est un gare de fret
     *
     * @param estFret si la gare est un gare de fret
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Setter si la gare est une gare de voyageur
     *
     * @param estVoyageur si la gare est une gare de voyageur
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Compare deux gares, si la gare courante est fret et voyageur et que l'autre
     * gare n'est que l'un des deux on renvoit 1, si la gare courante est
     * fret ou voyageur et que l'autre gare est fret et voyageur on renvoit -1 et si
     * les deux gares sont fret ou voyageur ou seulement un des deux chacunes on
     * renvoit 0.
     *
     * Exemple :
     * Gare1 : fret et voyageur | Gare2 : fret et voyageur => 0
     * Gare1 : fret et voyageur | Gare2 : fret => 1
     * Gare1 : voyageur | Gare2 : fret et voyageur => -1
     *
     * @param autreGare l'autre gare à comparer
     * @return 1 si la gare courante est fret et voyageur et que l'autre gare n'est
     *         que l'un des deux, -1 si la gare courante est fret ou voyageur et que
     *         l'autre gare est fret et voyageur, 0 sinon
     * @throws IllegalArgumentException si l'autre gare est null
     */
    public int compareGares(Gare autreGare) throws IllegalArgumentException {
        if (autreGare == null) {
            throw new IllegalArgumentException("La gare à comparer est null");
        }
        int res = 0;
        if (this.estFret && this.estVoyageur && (!autreGare.estFret || !autreGare.estVoyageur)) {
            res = 1;
        } else if ((this.estFret || this.estVoyageur) && autreGare.estFret && autreGare.estVoyageur) {
            res = -1;
        }
        return res;
    }

    /**
     * Méthode permettant d'afficher les informations d'une gare sous forme de
     * chaîne de caractères
     * 
     * @return les informations de la gare sous forme de csv
     *         "codeGare","nomGare","estFret","estVoyageur"
     */
    public String toString() {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\"", this.codeGare, this.nomGare, this.estFret,
                this.estVoyageur);
    }
}
