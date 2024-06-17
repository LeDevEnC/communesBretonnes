package model.data;

import java.util.Map;

import javafx.scene.paint.Color;

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
     * @throws IllegalArgumentException si la commune, l'année, le nombre de maisons, le nombre d'appartements, le prix moyen, le prix au mètre carré moyen, la surface moyenne sont négatif ou null
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
     * Méthode permettant d'afficher les informations de la commune pour une année
     * sous forme de chaîne de caractères
     * 
     * @return les informations de la commune pour une année sous forme de csv
     *         "nomCommune","année","nbMaison","nbAppart","prixMoyen","prixMCarreMoyen","surfaceMoy","depCulturellesTotales","budgetTotal","population"
     */
    public String toString() {
        return String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                this.laCommune.getNomCommune(),
                this.lAnnee.getAnneeRepr(),
                this.nbMaison,
                this.nbAppart,
                this.prixMoyen,
                this.prixMCarreMoyen,
                this.surfaceMoy,
                this.depCulturellesTotales,
                this.budgetTotal,
                this.population);
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
     * - Le nombre d'aéroport du département de la commune
     *
     * @return Un score d'attractivité de la commune cette année en pourcentage,
     *         plus ce score est élevé, plus la commune est attractive.
     */
    public int scoreCompute() {
        Map<String, Double> coeffs = Map.of(
                "nbGares", 0.12,
                "nbAeroports", 0.08,
                "nbMaisons", 0.15,
                "nbAppart", 0.12, 
                "prixMoyen", 0.18, 
                "prixM2Moyen", 0.15,
                "SurfaceMoy", 0.05,
                "depensesCulturellesTotales", 0.1,
                "budgetTotal", 0.05,
                "population", 0.1 
        );

        int scoreFinal = 0;

        scoreFinal += calculateScoreGares() * coeffs.get("nbGares");
        scoreFinal += calculateScoreAeroports() * coeffs.get("nbAeroports");
        scoreFinal += calculateScoreMaisons() * coeffs.get("nbMaisons");
        scoreFinal += calculateScoreAppart() * coeffs.get("nbAppart");
        scoreFinal += calculateScorePrixMoyen() * coeffs.get("prixMoyen");
        scoreFinal += calculateScorePrixM2Moyen() * coeffs.get("prixM2Moyen");
        scoreFinal += calculateScoreSurfaceMoy() * coeffs.get("SurfaceMoy");
        scoreFinal += calculateScoreDepensesCulturelles() * coeffs.get("depensesCulturellesTotales");
        scoreFinal += calculateScoreBudgetTotal() * coeffs.get("budgetTotal");
        scoreFinal += calculateScorePopulation() * coeffs.get("population");

        return scoreFinal;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du nombre de gares.
     * @return le score d'attractivité de la commune cette année en fonction du nombre de gares
     */
    private int calculateScoreGares() {
        int tempScore = 0;
        int nbGaresCheck = this.getLaCommune().getLesGares().size();
        switch (nbGaresCheck) {
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
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du nombre d'aéroports.
     * @return le score d'attractivité de la commune cette année en fonction du nombre d'aéroports
     */
    private int calculateScoreAeroports() {
        int tempScore = 0;
        int nbAeroportsCheck = this.getLaCommune().getLeDepartement().getAeroports().size();
        switch (nbAeroportsCheck) {
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
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du nombre de maisons.
     * @return le score d'attractivité de la commune cette année en fonction du nombre de maisons
     */
    private int calculateScoreMaisons() {
        int tempScore = 0;
        int nbMaisonsCheck = this.getNbMaison();
        if (nbMaisonsCheck < 10) {
            tempScore = 50;
        } else if (nbMaisonsCheck < 30) {
            tempScore = 75;
        } else if (nbMaisonsCheck < 60) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du nombre d'appartements.
     * @return le score d'attractivité de la commune cette année en fonction du nombre d'appartements
     */
    private int calculateScoreAppart() {
        int tempScore = 0;
        int nbAppartCheck = this.getNbAppart();
        if (nbAppartCheck < 5) {
            tempScore = 50;
        } else if (nbAppartCheck < 20) {
            tempScore = 75;
        } else if (nbAppartCheck < 40) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du prix moyen.
     * @return le score d'attractivité de la commune cette année en fonction du prix moyen
     */
    private int calculateScorePrixMoyen() {
        int tempScore = 0;
        double prixMoyenCheck = this.getPrixMoyen();
        if (prixMoyenCheck < 100000) {
            tempScore = 100;
        } else if (prixMoyenCheck < 150000) {
            tempScore = 90;
        } else if (prixMoyenCheck < 200000) {
            tempScore = 75;
        } else {
            tempScore = 50;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du prix moyen par mètre carré.
     * @return le score d'attractivité de la commune cette année en fonction du prix moyen par mètre carré
     */
    public int calculateScorePrixM2Moyen() {
        int tempScore = 0;
        double prixM2MoyenCheck = this.getPrixMCarreMoyen();
        if (prixM2MoyenCheck < 800) {
            tempScore = 100;
        } else if (prixM2MoyenCheck < 1200) {
            tempScore = 90;
        } else if (prixM2MoyenCheck < 1600) {
            tempScore = 75;
        } else {
            tempScore = 50;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction de la surface moyenne.
     * @return le score d'attractivité de la commune cette année en fonction de la surface moyenne
     */
    public int calculateScoreSurfaceMoy() {
        int tempScore = 0;
        double surfaceMoyCheck = this.getSurfaceMoy();
        if (surfaceMoyCheck < 40) {
            tempScore = 50;
        } else if (surfaceMoyCheck < 70) {
            tempScore = 75;
        } else if (surfaceMoyCheck < 100) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction des dépenses culturelles totales.
     * @return le score d'attractivité de la commune cette année en fonction des dépenses culturelles totales
     */
    public int calculateScoreDepensesCulturelles() {
        int tempScore = 0;
        double depensesCulturellesTotalesCheck = this.getDepCulturellesTotales();
        if (depensesCulturellesTotalesCheck < 80) {
            tempScore = 50;
        } else if (depensesCulturellesTotalesCheck < 150) {
            tempScore = 75;
        } else if (depensesCulturellesTotalesCheck < 250) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction du budget total.
     * @return le score d'attractivité de la commune cette année en fonction du budget total
     */
    public int calculateScoreBudgetTotal() {
        int tempScore = 0;
        double budgetTotalCheck = this.getBudgetTotal();
        if (budgetTotalCheck < 800) {
            tempScore = 50;
        } else if (budgetTotalCheck < 1500) {
            tempScore = 75;
        } else if (budgetTotalCheck < 2500) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    /**
     * Calcule le score d'attractivité de la commune cette année en fonction de la population.
     * @return le score d'attractivité de la commune cette année en fonction de la population
     */
    private int calculateScorePopulation() {
        int tempScore = 0;
        double populationCheck = this.getPopulation();
        if (populationCheck < 1000) {
            tempScore = 50;
        } else if (populationCheck < 1800) {
            tempScore = 75;
        } else if (populationCheck < 2500) {
            tempScore = 90;
        } else {
            tempScore = 100;
        }
        return tempScore;
    }

    
    public Color getColorForScore(double score) {
        if (score >= 90) {
            return Color.rgb(0, 176, 80); // Vert foncé pour les scores très élevés
        } else if (score >= 80) {
            return Color.rgb(146, 208, 80); // Vert clair pour les scores élevés
        } else if (score >= 70) {
            return Color.rgb(255, 255, 0); // Jaune pour les scores moyens élevés
        } else if (score >= 60) {
            return Color.rgb(255, 192, 0); // Orange pour les scores moyens
        } else if (score >= 50) {
            return Color.rgb(255, 152, 0); // Orange foncé pour les scores moyens bas
        } else {
            return Color.rgb(255, 0, 0); // Rouge pour les scores bas
        }
    }
}