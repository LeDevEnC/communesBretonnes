package scenario.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;
import model.data.CommuneBase;
import model.data.DepPossibles;
import model.data.Departement;
import model.data.Gare;

/**
 * Classe de test pour la classe CommuneBase.
 * Elle teste les fonctionnalités de base de la classe CommuneBase, y compris
 * les constructeurs, getters, les setters, et les méthodes pour gérer les
 * voisins et comparer le nombre de voisins.
 */
public class ScenarioCommuneBase {

    /**
     * Les informations du département de la commune à tester.
     */
    private Departement dep;

    /**
     * La commune à tester.
     */
    private CommuneBase commune;

    @Before
    public void setUp() {
        List<Aeroport> lesAeroports = new ArrayList<>();
        lesAeroports.add(new Aeroport("NomAero", "AdresseAero"));
        lesAeroports.add(new Aeroport("NomAero2", "AdresseAero2"));
        this.dep = new Departement(1, DepPossibles.FINISTERE, 1000L, lesAeroports);

        List<Gare> gare = new ArrayList<>();
        gare.add(new Gare(1, "NomGare", true, true));
        gare.add(new Gare(2, "NomGare2", false, true));
        gare.add(new Gare(3, "NomGare3", true, false));

        this.commune = new CommuneBase(1, "Test", dep, new ArrayList<>(), gare);
    }

    /**
     * Teste le constructeur de CommuneBase pour s'assurer que les attributs sont
     * correctement initialisés.
     */
    @Test
    public void testConstructor() {
        assertEquals(1, commune.getIdCommune());
        assertEquals("Test", commune.getNomCommune());
        assertEquals(dep, commune.getLeDepartement());
        assertTrue(commune.getLesVoisins().isEmpty());
        assertFalse(commune.getLesGares().isEmpty());
    }

    /**
     * Teste le constructeur de CommuneBase avec un ID de commune invalide.
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidIdCommune() {
        new CommuneBase(-1, "Test", dep, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Teste le constructeur de CommuneBase avec un nom de commune invalide (null).
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidNomCommune() {
        new CommuneBase(1, null, dep, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Teste le constructeur de CommuneBase avec un département invalide (null).
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidDepartement() {
        new CommuneBase(1, "Test", null, new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Teste les setters et getters de la classe CommuneBase.
     */
    @Test
    public void testSettersAndGetters() {
        commune.setIdCommune(2);
        assertEquals(2, commune.getIdCommune());
        commune.setNomCommune("Test2");
        assertEquals("Test2", commune.getNomCommune());
    }

    /**
     * Teste le setter setIdCommune avec un argument invalide.
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIdCommuneWithInvalidArg() {
        commune.setIdCommune(-1);
    }

    /**
     * Teste le setter setNomCommune avec un argument invalide (null).
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNomCommuneWithInvalidArg() {
        commune.setNomCommune(null);
    }

    /**
     * Teste l'ajout d'un voisin à la commune.
     * Vérifie que le voisin est bien ajouté à la liste des voisins.
     */
    @Test
    public void testAddNeighbor() {
        CommuneBase commune2 = new CommuneBase(2, "Test2", dep, new ArrayList<>(), new ArrayList<>());
        commune.addNeighbor(commune2);
        assertTrue(commune.getLesVoisins().contains(commune2));
    }

    /**
     * Teste l'ajout d'un voisin invalide (null) à la commune.
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNeighborWithInvalidArg() {
        commune.addNeighbor(null);
    }

    /**
     * Teste la comparaison du nombre de voisins entre deux communes.
     */
    @Test
    public void testCompareNbVoisins() {
        CommuneBase commune1 = new CommuneBase(1, "Test1", dep, new ArrayList<>(), new ArrayList<>());
        CommuneBase commune2 = new CommuneBase(2, "Test2", dep, new ArrayList<>(), new ArrayList<>());
        commune1.addNeighbor(commune2);
        assertEquals(1, commune1.compareNbVoisins(commune2));
        assertEquals(-1, commune2.compareNbVoisins(commune1));
        assertEquals(0, commune1.compareNbVoisins(commune1));
    }

    /**
     * Teste la comparaison du nombre de voisins avec un argument invalide (null).
     * Une exception IllegalArgumentException est attendue.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareNbVoisinsWithInvalidArg() {
        commune.compareNbVoisins(null);
    }

    /**
     * Teste la méthode toString de la classe CommuneBase.
     * Vérifie que la chaîne de caractères retournée est correcte.
     */
    @Test
    public void testToString() {
        String expected = "\"" + commune.getIdCommune() + "\",\"" + commune.getNomCommune() + "\",\""
                + commune.getLeDepartement().getNomDep() + "\"";
        assertEquals(expected, commune.toString());
    }
}