package scenario.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;

/**
 * Classe de test pour Aeroport
 */
public class ScenarioAeroport {
    /**
     * Contient l'aéroport à tester.
     */
    private Aeroport aeroport;

    /**
     * Initialise un nouvel aéroport avant chaque test.
     */
    @Before
    public void setUp() {
        aeroport = new Aeroport("Aeroport Test", "123 Rue Test");
    }

    /**
     * Teste le constructeur avec un nom null.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
        new Aeroport(null, "123 Rue Test");
    }

    /**
     * Teste le constructeur avec une adresse null.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullAddress() {
        new Aeroport("Aeroport Test", null);
    }

    /**
     * Teste le constructeur avec un nom et une adresse nulls.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullNameAndAddress() {
        new Aeroport(null, null);
    }

    /**
     * Teste la récupération du nom de l'aéroport.
     */
    @Test
    public void testGetNom() {
        assertEquals("Aeroport Test", aeroport.getNom());
    }

    /**
     * Teste la récupération de l'adresse de l'aéroport.
     */
    @Test
    public void testGetAdresse() {
        assertEquals("123 Rue Test", aeroport.getAdresse());
    }

    /**
     * Teste la définition du nom de l'aéroport à null.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNom() {
        aeroport.setNom(null);
    }

    /**
     * Teste la définition d'un nouveau nom valide pour l'aéroport.
     */
    @Test
    public void testSetNomValid() {
        aeroport.setNom("Nouveau Nom");
        assertEquals("Nouveau Nom", aeroport.getNom());
    }

    /**
     * Teste la définition de l'adresse de l'aéroport à null.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetAdresse() {
        aeroport.setAdresse(null);
    }

    /**
     * Teste la définition d'une nouvelle adresse valide pour l'aéroport.
     */
    @Test
    public void testSetAdresseValid() {
        aeroport.setAdresse("456 Rue Test");
        assertEquals("456 Rue Test", aeroport.getAdresse());
    }

    /**
     * Teste la comparaison avec un autre aéroport null.
     * Doit lancer une IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIsSameAero() {
        aeroport.isSameAero(null);
    }

    /**
     * Teste la comparaison avec un autre aéroport valide.
     */
    @Test
    public void testIsSameAeroValid() {
        Aeroport autreAero = new Aeroport("Autre Aeroport", "456 Rue Test");
        assertFalse(aeroport.isSameAero(autreAero));
        autreAero = new Aeroport("Aeroport Test", "123 Rue Test");
        assertTrue(aeroport.isSameAero(autreAero));
    }

    /**
     * Teste la représentation en chaîne de caractères de l'aéroport.
     */
    @Test
    public void testToString() {
        assertEquals("\"Aeroport Test\",\"123 Rue Test\"", aeroport.toString());
    }
}