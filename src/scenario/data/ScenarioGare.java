package scenario.data;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data.Gare;

public class ScenarioGare {

    @Test
    public void testConstructor() {
        Gare gare = new Gare(1, "Gare1", true, false);
        assertEquals(1, gare.getCodeGare());
        assertEquals("Gare1", gare.getNomGare());
        assertTrue(gare.getEstFret());
        assertFalse(gare.getEstVoyageur());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidInput() {
        new Gare(-1, null, true, false);
    }

    @Test
    public void testSetters() {
        Gare gare = new Gare(1, "Gare1", true, false);
        gare.setCodeGare(2);
        gare.setNomGare("Gare2");
        gare.setEstFret(false);
        gare.setEstVoyageur(true);
        assertEquals(2, gare.getCodeGare());
        assertEquals("Gare2", gare.getNomGare());
        assertFalse(gare.getEstFret());
        assertTrue(gare.getEstVoyageur());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCodeGareInvalidInput() {
        Gare gare = new Gare(1, "Gare1", true, false);
        gare.setCodeGare(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNomGareInvalidInput() {
        Gare gare = new Gare(1, "Gare1", true, false);
        gare.setNomGare(null);
    }

    @Test
    public void testCompareGares() {
        Gare gare1 = new Gare(1, "Gare1", true, true);
        Gare gare2 = new Gare(2, "Gare2", true, false);
        Gare gare3 = new Gare(3, "Gare3", false, true);
        assertEquals(1, gare1.compareGares(gare2));
        assertEquals(-1, gare2.compareGares(gare1));
        assertEquals(0, gare2.compareGares(gare3));
    }

    @Test
    public void testToString() {
        Gare gare = new Gare(1, "Gare1", true, false);
        assertEquals("\"1\",\"Gare1\",\"true\",\"false\"", gare.toString());
    }
}
