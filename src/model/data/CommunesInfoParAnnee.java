package model.data;
import java.util.Map;

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
                || surfaceMoy < 0) {
            throw new IllegalArgumentException(
                    "La commune, l'année, le nombre de maisons, le nombre d'appartements, le prix moyen, le prix au mètre carré moyen, la surface moyenne sont négatif ou null");
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
     * Méthode permettant d'afficher les informations de la commune pour une année sous forme de chaîne de caractères
     * 
     * @return les informations de la commune pour une année sous forme de csv "nomCommune","année","nbMaison","nbAppart","prixMoyen","prixMCarreMoyen","surfaceMoy","depCulturellesTotales","budgetTotal","population"
     */
    public String toString() {
        return "\"" + this.laCommune.getNomCommune() + "\",\"" + this.lAnnee.getAnnee() + "\",\"" + this.nbMaison + "\",\""
                + this.nbAppart + "\",\"" + this.prixMoyen + "\",\"" + this.prixMCarreMoyen + "\",\"" + this.surfaceMoy
                + "\",\"" + this.depCulturellesTotales + "\",\"" + this.budgetTotal + "\",\"" + this.population + "\"";
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
    /**
     * Calcule le score d'attractivité de la commune cette année.
     * Ce score est calculé en fonction de :
     * - la population
     * - le nombre de maisons
     * - le nombre d'appartements
     * - le prix moyen
     * - le prix moyen par mètre carré
     * - la surface moyenne
     * - les dépenses culturelles totales
     * - le budget total
     * - Le nombre de gare de la commune
     * - Le nombre de voisine de la commune
     *
     * @return Un score d'attractivité de la commune cette année en pourcentage,
     *         plus ce score est élevé, plus la commune est attractive.
     */
    public int scoreCompute() {
        Map<String, Double> coeffs = Map.of(
            "nbGares", 0.12, // Augmenté pour valoriser davantage les transports en commun
            "nbAeroports", 0.08, // Augmenté pour les connexions internationales
            "nbMaisons", 0.15,
            "nbAppart", 0.12, // Augmenté pour les logements urbains
            "prixMoyen", 0.18, // Légèrement diminué pour équilibrer
            "prixM2Moyen", 0.15,
            "SurfaceMoy", 0.05,
            "depensesCulturellesTotales", 0.1,
            "budgetTotal", 0.05,
            "population", 0.1 // Augmenté pour valoriser le dynamisme économique
    );
    
        int scoreFinal = 0;
        int tempScore = 0;
    
        switch (this.getLaCommune().getLesGares().size()) {
            case 0:
                tempScore = 25;
                break;
            case 1:
                tempScore = 75;
                break;
            case 2:
                tempScore = 90;
                break;
            case 3:
                tempScore = 100;
                break;
            default:
                tempScore = 100;
                break;
        }
        scoreFinal += tempScore * coeffs.get("nbGares");
    
        switch (this.getLaCommune().getLeDepartement().getAeroports().size()) {
            case 0:
                tempScore = 25;
                break;
            case 1:
                tempScore = 75;
                break;
            case 2:
                tempScore = 90;
                break;
            case 3:
                tempScore = 100;
                break;
            default:
                tempScore = 100;
                break;
        }
        scoreFinal += tempScore * coeffs.get("nbAeroports");
    
        int nbMaisons = this.getNbMaison();
        if (nbMaisons < 10) {
            tempScore = 50;
        } else if (nbMaisons < 30) {
            tempScore = 75;
        } else if (nbMaisons < 60) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("nbMaisons");
    
        int nbAppart = this.getNbAppart();
        if (nbAppart < 5) {
            tempScore = 50;
        } else if (nbAppart < 20) {
            tempScore = 75;
        } else if (nbAppart < 40) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("nbAppart");
    
        double prixMoyen = this.getPrixMoyen();
        if (prixMoyen < 100000) {
            tempScore = 100;
        } else if (prixMoyen < 150000) {
            tempScore = 90;
        } else if (prixMoyen < 200000) {
            tempScore = 75;
        } else {
            tempScore = 50;
        }
        scoreFinal += tempScore * coeffs.get("prixMoyen");
    
        double prixM2Moyen = this.getPrixMCarreMoyen();
        if (prixM2Moyen < 800) {
            tempScore = 100;
        } else if (prixM2Moyen < 1200) {
            tempScore = 90;
        } else if (prixM2Moyen < 1600) {
            tempScore = 75;
        } else {
            tempScore = 50;
        }
        scoreFinal += tempScore * coeffs.get("prixM2Moyen");
    
        double surfaceMoy = this.getSurfaceMoy();
        if (surfaceMoy < 40) {
            tempScore = 50;
        } else if (surfaceMoy < 70) {
            tempScore = 75;
        } else if (surfaceMoy < 100) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("SurfaceMoy");
    
        double depensesCulturellesTotales = this.getDepCulturellesTotales();
        if (depensesCulturellesTotales < 80) {
            tempScore = 50;
        } else if (depensesCulturellesTotales < 150) {
            tempScore = 75;
        } else if (depensesCulturellesTotales < 250) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("depensesCulturellesTotales");
    
        double budgetTotal = this.getBudgetTotal();
        if (budgetTotal < 800) {
            tempScore = 50;
        } else if (budgetTotal < 1500) {
            tempScore = 75;
        } else if (budgetTotal < 2500) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("budgetTotal");
    
        double population = this.getPopulation();
        if (population < 1000) {
            tempScore = 50;
        } else if (population < 1800) {
            tempScore = 75;
        } else if (population < 2500) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        scoreFinal += tempScore * coeffs.get("population");
    
        return scoreFinal;
    }
    
    

}