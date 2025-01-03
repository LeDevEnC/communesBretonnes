package scenario;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import scenario.data.ScenarioAeroport;
import scenario.data.ScenarioAnnee;
import scenario.data.ScenarioCommuneBase;
import scenario.data.ScenarioCommunesInfoParAnnee;
import scenario.data.ScenarioDepartement;
import scenario.data.ScenarioGare;

/**
 * Classe de scénarios pour les communes bretonnes
 */
public class ScenarioCommunesBretonnes {
    /**
     * Méthode principale
     * Exécute les scénarios de test
     * @param args les arguments passés à l'application (non utilisés)
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ScenarioAeroport.class, ScenarioAnnee.class, ScenarioCommuneBase.class,
                ScenarioCommunesInfoParAnnee.class, ScenarioDepartement.class, ScenarioGare.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("Tous les tests ont réussi.");
        } else {
            System.out.println("Certains tests ont échoué !");
        }
    }
}
