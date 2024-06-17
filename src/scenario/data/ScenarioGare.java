package scenario.data;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data.Gare;

public class ScenarioGare {

    /**
     * Teste le constructeur de la classe Gare avec des valeurs valides.
     * Vérifie si les valeurs des attributs sont correctement initialisées.
     */
    @Test
    public void testConstructor() {
        Gare gare = new Gare(1, "Gare1", true, false);
        assertEquals(1, gare.getCodeGare());
        assertEquals("Gare1", gare.getNomGare());
        assertTrue(gare.getEstFret());
        assertFalse(gare.getEstVoyageur());
    }

    /**
     * Teste le constructeur de la classe Gare avec des valeurs invalides.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidInput() {
        new Gare(-1, null, true, false);
    }

    /**
     * Teste les setters de la classe Gare avec des valeurs valides.
     * Vérifie si les valeurs des attributs sont correctement mises à jour.
     */
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

    /**
     * Teste le setter setCodeGare avec une valeur invalide.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCodeGareInvalidInput() {
        Gare gare = new Gare(1, "Gare1", true, false);
        gare.setCodeGare(-1);
    }

    /**
     * Teste le setter setNomGare avec une valeur invalide (null).
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNomGareInvalidInput() {
        Gare gare = new Gare(1, "Gare1", true, false);
        gare.setNomGare(null);
    }

    /**
     * Teste la méthode compareGares pour comparer deux instances de Gare.
     * Vérifie le comportement de la comparaison entre différentes gares.
     */
    @Test
    public void testCompareGares() {
        Gare gare1 = new Gare(1, "Gare1", true, true);
        Gare gare2 = new Gare(2, "Gare2", true, false);
        Gare gare3 = new Gare(3, "Gare3", false, true);
        assertEquals(1, gare1.compareGares(gare2));
        assertEquals(-1, gare2.compareGares(gare1));
        assertEquals(0, gare2.compareGares(gare3));
    }

    /**
     * Teste la méthode toString de la classe Gare.
     * Vérifie si la chaîne de caractères retournée est correctement formatée.
     */
    @Test
    public void testToString() {
        Gare gare = new Gare(1, "Gare1", true, false);
        assertEquals("\"1\",\"Gare1\",\"true\",\"false\"", gare.toString());
    }
}