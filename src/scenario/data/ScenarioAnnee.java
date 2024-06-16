package scenario.data;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data.Annee;

public class ScenarioAnnee {

    @Test
    public void testConstructor() {
        Annee annee = new Annee(2020, 1.5);
        assertEquals(2020, annee.getAnneeRepr());
        assertEquals(1.5, annee.getTauxInflation(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidInput() {
        new Annee(-2020, -1.5);
    }

    @Test
    public void testSetters() {
        Annee annee = new Annee(2020, 1.5);
        annee.setAnneeRepr(2021);
        annee.setTauxInflation(2.5);
        assertEquals(2021, annee.getAnneeRepr());
        assertEquals(2.5, annee.getTauxInflation(), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSettersInvalidInput() {
        Annee annee = new Annee(2020, 1.5);
        annee.setAnneeRepr(-2021);
        annee.setTauxInflation(-2.5);
    }

    @Test
    public void testCalcDiffInflation() {
        Annee annee1 = new Annee(2020, 1.5);
        Annee annee2 = new Annee(2021, 2.5);
        assertEquals(-1, annee1.calcDiffInflation(annee2), 0.001);
        assertEquals(1, annee2.calcDiffInflation(annee1), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalcDiffInflationInvalidInput() {
        Annee annee = new Annee(2020, 1.5);
        annee.calcDiffInflation(null);
    }

    @Test
    public void testToString() {
        Annee annee = new Annee(2020, 1.5);
        assertEquals("\"2020\",\"1.5\"", annee.toString());
    }
}