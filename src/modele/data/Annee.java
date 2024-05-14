package modele.data;
/**
 * Classe représentant une année
 */
public class Annee {
    /**
     * l'année en question
     */
    private int annee;
    /**
     * le taux d'inflation de l'année
     */
    private double tauxInflation;
    
    /**
     * Constructeur de la classe Annee
     * @param annee l'année en question
     * @param tauxInflation le taux d'inflation de l'année
     * @throws IllegalArgumentException si l'année ou le taux d'inflation est négatif
     */
    public Annee(int annee, double tauxInflation) throws IllegalArgumentException{
        if (annee < 0 || tauxInflation < 0){
            throw new IllegalArgumentException("L'année ou le taux d'inflation est négatif");
        } else {
            this.annee = annee;
            this.tauxInflation = tauxInflation;
        }

    }

    /**
     * Getter de l'année
     * @return l'année
     */
    public int getAnnee(){
        return this.annee;
    }

    /**
     * Getter du taux d'inflation
     * @return le taux d'inflation
     */
    public double getTauxInflation(){
        return this.tauxInflation;
    }

    /**
     * Setter de l'année
     * @param annee l'année à définir
     * @throws IllegalArgumentException si l'année est négative
     */
    public void setAnnee(int annee) throws IllegalArgumentException{
        if (annee < 0){
            throw new IllegalArgumentException("L'année est négative");
        } else {
            this.annee = annee;
        }
    }

    /**
     * Setter du taux d'inflation
     * @param tauxInflation le taux d'inflation à définir
     * @throws IllegalArgumentException si le taux d'inflation est négatif
     */
    public void setTauxInflation(double tauxInflation) throws IllegalArgumentException{
        if (tauxInflation < 0){
            throw new IllegalArgumentException("Le taux d'inflation est négatif");
        } else {
            this.tauxInflation = tauxInflation;
        }
    }

    /**
     * Calcule la différence d'inflation entre deux années
     * @param autreAnnee l'autre année
     * @return 1 si l'inflation de l'année est supérieure à l'inflation de l'autre année, -1 si l'inflation de l'année est inférieure à l'inflation de l'autre année, 0 si les deux inflations sont égales
     */
    public double calcDiffInflation(Annee autreAnnee) throws IllegalArgumentException{
        double res = 0;
        if (autreAnnee == null){
            throw new IllegalArgumentException("L'année à comparer est null");
        } else {
            if (this.tauxInflation > autreAnnee.getTauxInflation()){
                res = 1;
            } else if (this.tauxInflation < autreAnnee.getTauxInflation()){
                res = -1;
            }
        }
        return res;
    }
}