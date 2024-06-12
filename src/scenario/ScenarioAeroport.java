package scenario;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;

public class ScenarioAeroport {
    private Aeroport aeroport;

    @Before
    public void setUp() {
        aeroport = new Aeroport("Aeroport Test", "123 Rue Test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
        new Aeroport(null, "123 Rue Test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullAddress() {
        new Aeroport("Aeroport Test", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullNameAndAddress() {
        new Aeroport(null, null);
    }

    @Test
    public void testGetNom() {
        assertEquals("Aeroport Test", aeroport.getNom());
    }

    @Test
    public void testGetAdresse() {
        assertEquals("123 Rue Test", aeroport.getAdresse());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNom() {
        aeroport.setNom(null);
    }

    @Test
    public void testSetNomValid() {
        aeroport.setNom("Nouveau Nom");
        assertEquals("Nouveau Nom", aeroport.getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAdresse() {
        aeroport.setAdresse(null);
    }

    @Test
    public void testSetAdresseValid() {
        aeroport.setAdresse("456 Rue Test");
        assertEquals("456 Rue Test", aeroport.getAdresse());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameAero() {
        aeroport.isSameAero(null);
    }

    @Test
    public void testIsSameAeroValid() {
        Aeroport autreAero = new Aeroport("Autre Aeroport", "456 Rue Test");
        assertFalse(aeroport.isSameAero(autreAero));
        autreAero = new Aeroport("Aeroport Test", "123 Rue Test");
        assertTrue(aeroport.isSameAero(autreAero));
    }

    @Test
    public void testToString() {
        assertEquals("\"Aeroport Test\",\"123 Rue Test\"", aeroport.toString());
    }
}