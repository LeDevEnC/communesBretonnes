package model.data;

/**
 * Classe représentant les informations d'une commune pour une année donnée
 */
public class CommunesInfoParAnnee {
    /**
     * Nombre de maisons (en unité)
     */
    private int nbMaison;

    /**
     * Nombre d'appartements (en unité)
     */
    private int nbAppart;

    /**
     * Prix moyen (en €)
     */
    private double prixMoyen;

    /**
     * Prix au mètre carré moyen (en €)
     */
    private double prixMCarreMoyen;

    /**
     * Surface moyenne (en m²)
     */
    private double surfaceMoy;

    /**
     * Dépenses culturelles totales (en €)
     */
    private double depCulturellesTotales;

    /**
     * Budget total (en €)
     */
    private double budgetTotal;

    /**
     * Population (en unité)
     */
    private double population;

    /**
     * Informations de la commune de base
     */
    private CommuneBase laCommune;

    /**
     * Année des informations
     */
    private Annee lAnnee;

    /**
     * Constructeur de la classe CommunesInfoParAnnee
     *
     * @param laCommune             la commune de base
     * @param lAnnee                l'année
     * @param nbMaison              le nombre de maisons
     * @param nbAppart              le nombre d'appartements
     * @param prixMoyen             le prix moyen
     * @param prixMCarreMoyen       le prix au mètre carré moyen
     * @param surfaceMoy            la surface moyenne
     * @param depCulturellesTotales les dépenses culturelles totales
     * @param budgetTotal           le budget total
     * @param population            la population
     */
    public CommunesInfoParAnnee(CommuneBase laCommune, Annee lAnnee, int nbMaison, int nbAppart, double prixMoyen,
            double prixMCarreMoyen, double surfaceMoy, double depCulturellesTotales, double budgetTotal, int population)
            throws IllegalArgumentException {
        if (laCommune == null || lAnnee == null || nbMaison < 0 || nbAppart < 0 || prixMoyen < 0 || prixMCarreMoyen < 0
                || surfaceMoy < 0 || depCulturellesTotales < 0 || budgetTotal < 0 || population < 0) {
            throw new IllegalArgumentException(
                    "La commune, l'année, le nombre de maisons, le nombre d'appartements, le prix moyen, le prix au mètre carré moyen, la surface moyenne, les dépenses culturelles totales, le budget total ou la population sont négatif ou null");
        } else {
            this.laCommune = laCommune;
            this.lAnnee = lAnnee;
            this.nbMaison = nbMaison;
            this.nbAppart = nbAppart;
            this.prixMoyen = prixMoyen;
            this.prixMCarreMoyen = prixMCarreMoyen;
            this.surfaceMoy = surfaceMoy;
            this.depCulturellesTotales = depCulturellesTotales;
            this.budgetTotal = budgetTotal;
            this.population = population;
        }
    }

    /**
     * Obtient le nombre de maisons.
     *
     * @return le nombre de maisons
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Obtient le nombre d'appartements.
     *
     * @return le nombre d'appartements
     */
    public int getNbAppart() {
        return this.nbAppart;
    }

    /**
     * Obtient le prix moyen.
     *
     * @return le prix moyen
     */
    public double getPrixMoyen() {
        return this.prixMoyen;
    }

    /**
     * Obtient le prix moyen par mètre carré.
     *
     * @return le prix moyen par mètre carré
     */
    public double getPrixMCarreMoyen() {
        return this.prixMCarreMoyen;
    }

    /**
     * Obtient la surface moyenne.
     *
     * @return la surface moyenne
     */
    public double getSurfaceMoy() {
        return this.surfaceMoy;
    }

    /**
     * Obtient le total des dépenses culturelles.
     *
     * @return le total des dépenses culturelles
     */
    public double getDepCulturellesTotales() {
        return this.depCulturellesTotales;
    }

    /**
     * Obtient le budget total.
     *
     * @return le budget total
     */
    public double getBudgetTotal() {
        return this.budgetTotal;
    }

    /**
     * Obtient la population.
     *
     * @return la population
     */
    public double getPopulation() {
        return this.population;
    }

    /**
     * Obtient la commune.
     *
     * @return la commune
     */
    public CommuneBase getLaCommune() {
        return this.laCommune;
    }

    /**
     * Obtient l'année.
     *
     * @return l'année
     */
    public Annee getLannee() {
        return this.lAnnee;
    }

    /**
     * Définit le nombre de maisons.
     *
     * @param nbMaison le nombre de maisons à définir
     * @throws IllegalArgumentException si le nombre de maisons est négatif
     */
    public void setNbMaison(int nbMaison) throws IllegalArgumentException {
        if (nbMaison < 0) {
            throw new IllegalArgumentException("Le nombre de maisons est négatif");
        } else {
            this.nbMaison = nbMaison;
        }
    }

    /**
     * Définit le nombre d'appartements.
     *
     * @param nbAppart le nombre d'appartements à définir
     * @throws IllegalArgumentException si le nombre d'appartements est négatif
     */
    public void setNbAppart(int nbAppart) throws IllegalArgumentException {
        if (nbAppart < 0) {
            throw new IllegalArgumentException("Le nombre d'appartements est négatif");
        } else {
            this.nbAppart = nbAppart;
        }
    }

    /**
     * Définit le prix moyen.
     *
     * @param prixMoyen le prix moyen à définir
     * @throws IllegalArgumentException si le prix moyen est négatif
     */
    public void setPrixMoyen(double prixMoyen) throws IllegalArgumentException {
        if (prixMoyen < 0) {
            throw new IllegalArgumentException("Le prix moyen est négatif");
        } else {
            this.prixMoyen = prixMoyen;
        }
    }

    /**
     * Définit le prix moyen par mètre carré.
     *
     * @param prixMCarreMoyen le prix moyen par mètre carré à définir
     * @throws IllegalArgumentException si le prix moyen par mètre carré est négatif
     */
    public void setPrixMCarreMoyen(double prixMCarreMoyen) throws IllegalArgumentException {
        if (prixMCarreMoyen < 0) {
            throw new IllegalArgumentException("Le prix au mètre carré moyen est négatif");
        } else {
            this.prixMCarreMoyen = prixMCarreMoyen;
        }
    }

    /**
     * Définit la surface moyenne.
     *
     * @param surfaceMoy la surface moyenne à définir
     * @throws IllegalArgumentException si la surface moyenne est négative
     */
    public void setSurfaceMoy(double surfaceMoy) throws IllegalArgumentException {
        if (surfaceMoy < 0) {
            throw new IllegalArgumentException("La surface moyenne est négative");
        } else {
            this.surfaceMoy = surfaceMoy;
        }
    }

    /**
     * Définit le total des dépenses culturelles.
     *
     * @param depCulturellesTotales le total des dépenses culturelles à définir
     * @throws IllegalArgumentException si le total des dépenses culturelles est
     *                                  négatif
     */
    public void setDepCulturellesTotales(double depCulturellesTotales) throws IllegalArgumentException {
        if (depCulturellesTotales < 0) {
            throw new IllegalArgumentException("Les dépenses culturelles totales sont négatives");
        } else {
            this.depCulturellesTotales = depCulturellesTotales;
        }
    }

    /**
     * Définit le budget total.
     *
     * @param budgetTotal le budget total à définir
     * @throws IllegalArgumentException si le budget total est négatif
     */
    public void setBudgetTotal(double budgetTotal) throws IllegalArgumentException {
        if (budgetTotal < 0) {
            throw new IllegalArgumentException("Le budget total est négatif");
        } else {
            this.budgetTotal = budgetTotal;
        }
    }

    /**
     * Définit la population.
     *
     * @param population la population à définir
     * @throws IllegalArgumentException si la population est négative
     */
    public void setPopulation(double population) throws IllegalArgumentException {
        if (population < 0) {
            throw new IllegalArgumentException("La population est négative");
        } else {
            this.population = population;
        }
    }

    /**
     * Fait un affichage des informations de la commune pour une année donnée
     */
    public String toString() {
        return "Commune : " + this.laCommune.getNomCommune() + "\nAnnée : " + this.lAnnee.getAnnee()
                + "\nNombre de maisons : "
                + this.nbMaison + "\nNombre d'appartements : " + this.nbAppart + "\nPrix moyen : " + this.prixMoyen
                + "\nPrix moyen par mètre carré : " + this.prixMCarreMoyen + "\nSurface moyenne : " + this.surfaceMoy
                + "\nDépenses culturelles totales : " + this.depCulturellesTotales + "\nBudget total : "
                + this.budgetTotal
                + "\nPopulation : " + this.population;
    }

    /**
     * Compare le nombre d'habitants de la commune avec une autre commune
     *
     * @param uneAutreCommune l'autre commune à comparer
     * @return 1 si la commune courante a plus d'habitants, -1 si elle en a moins et
     *         0 si elles
     * @throws IllegalArgumentException si la commune à comparer est null
     */
    public double compareNbHabitants(CommunesInfoParAnnee uneAutreCommune) throws IllegalArgumentException {
        double ret = 0;
        if (uneAutreCommune == null) {
            throw new IllegalArgumentException("La commune à comparer est null");
        } else {
            if (this.population > uneAutreCommune.getPopulation()) {
                return 1;
            } else if (this.population < uneAutreCommune.getPopulation()) {
                return -1;
            }
        }
        return ret;
    }

    

}