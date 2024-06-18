package model;

import java.util.Map;

import model.data.CommunesInfoParAnnee;

/**
 * Classe Calculator
 * Utilisée pour calculer des scores moyens
 */
public class Calculator {

    /**
     * Constructeur privé pour empêcher l'instanciation de la classe
     */
    private Calculator() {
    }

    /**
     * Permet de calculer les scores moyens par département
     * 
     * @param toutesLesCommunesInfoParAnnee Les communes
     * @param scores                        Les scores totaux par département
     * @param nb                            Le nombre de communes par département
     */
    public static void computeScoresAndNb(Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee, int[] scores,
            int[] nb) {
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            int idDep = communesInfoParAnnee.getLaCommune().getLeDepartement().getIdDep();
            int annee = communesInfoParAnnee.getLannee().getAnneeRepr();

            if (annee == 2021) {
                switch (idDep) {
                    case 29:
                        scores[0] += communesInfoParAnnee.scoreCompute();
                        nb[0]++;
                        break;
                    case 22:
                        scores[1] += communesInfoParAnnee.scoreCompute();
                        nb[1]++;
                        break;
                    case 56:
                        scores[2] += communesInfoParAnnee.scoreCompute();
                        nb[2]++;
                        break;
                    case 35:
                        scores[3] += communesInfoParAnnee.scoreCompute();
                        nb[3]++;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Permet de normaliser les scores
     * 
     * @param scores Les scores totaux
     * @param nb     Le nombre de communes
     */
    public static void normalizeScores(int[] scores, int[] nb) {
        for (int i = 0; i < 4; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }

    /**
     * Permet de normaliser les scores pour le lineChart
     * 
     * @param scores Les scores totaux
     * @param nb     Le nombre de communes
     */
    public static void normalizeScoresLineChart(int[] scores, int[] nb) {
        for (int i = 0; i < 3; i++) {
            if (nb[i] == 0) {
                scores[i] = 0;
            } else {
                scores[i] = scores[i] / nb[i];
            }
        }
    }

    /**
     * Permet de calculer le score moyen de toute les communes par année
     * 
     * @param toutesLesCommunesInfoParAnnee Les communes
     * @param scores                        Les scores totaux par année
     * @param nb                            Le nombre d'années
     */
    public static void computeScoresAndNbByYear(Map<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee,
            int[] scores,
            int[] nb) {
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            int annee = communesInfoParAnnee.getLannee().getAnneeRepr();

            switch (annee) {
                case 2019:
                    scores[0] += communesInfoParAnnee.scoreCompute();
                    nb[0]++;
                    break;
                case 2020:
                    scores[1] += communesInfoParAnnee.scoreCompute();
                    nb[1]++;
                    break;
                case 2021:
                    scores[2] += communesInfoParAnnee.scoreCompute();
                    nb[2]++;
                    break;
                default:
                    break;
            }
        }
    }
}
