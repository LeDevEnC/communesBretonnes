package scenario.data;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data.Annee;

/**
 * Classe de test pour la classe Annee.
 */
public class ScenarioAnnee {

    /**
     * Teste le constructeur de la classe Annee avec des entrées valides.
     */
    @Test
    public void testConstructor() {
        Annee annee = new Annee(2020, 1.5);
        assertEquals(2020, annee.getAnneeRepr());
        assertEquals(1.5, annee.getTauxInflation(), 0.001);
    }

    /**
     * Teste le constructeur de la classe Annee avec des entrées invalides.
     * S'attend à une exception IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidInput() {
        new Annee(-2020, -1.5);
    }

    /**
     * Teste les setters de la classe Annee avec des entrées valides.
     */
    @Test
    public void testSetters() {
        Annee annee = new Annee(2020, 1.5);
        annee.setAnneeRepr(2021);
        annee.setTauxInflation(2.5);
        assertEquals(2021, annee.getAnneeRepr());
        assertEquals(2.5, annee.getTauxInflation(), 0.001);
    }

    /**
     * Teste les setters de la classe Annee avec des entrées invalides.
     * S'attend à une exception IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSettersInvalidInput() {
        Annee annee = new Annee(2020, 1.5);
        annee.setAnneeRepr(-2021);
        annee.setTauxInflation(-2.5);
    }

    /**
     * Teste la méthode calcDiffInflation de la classe Annee avec des entrées
     * valides.
     */
    @Test
    public void testCalcDiffInflation() {
        Annee annee1 = new Annee(2020, 1.5);
        Annee annee2 = new Annee(2021, 2.5);
        assertEquals(-1, annee1.calcDiffInflation(annee2), 0.001);
        assertEquals(1, annee2.calcDiffInflation(annee1), 0.001);
    }

    /**
     * Teste la méthode calcDiffInflation de la classe Annee avec une entrée
     * invalide.
     * S'attend à une exception IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalcDiffInflationInvalidInput() {
        Annee annee = new Annee(2020, 1.5);
        annee.calcDiffInflation(null);
    }

    /**
     * Teste la méthode toString de la classe Annee.
     */
    @Test
    public void testToString() {
        Annee annee = new Annee(2020, 1.5);
        assertEquals("\"2020\",\"1.5\"", annee.toString());
    }
}