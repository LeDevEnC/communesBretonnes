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

public class ScenarioCommuneBase {

    private Departement dep;
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

    @Test
    public void testConstructor() {
        assertEquals(1, commune.getIdCommune());
        assertEquals("Test", commune.getNomCommune());
        assertEquals(dep, commune.getLeDepartement());
        assertTrue(commune.getLesVoisins().isEmpty());
        assertFalse(commune.getLesGares().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidIdCommune() {
        new CommuneBase(-1, "Test", dep, new ArrayList<>(), new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidNomCommune() {
        new CommuneBase(1, null, dep, new ArrayList<>(), new ArrayList<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidDepartement() {
        new CommuneBase(1, "Test", null, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testSettersAndGetters() {
        commune.setIdCommune(2);
        assertEquals(2, commune.getIdCommune());
        commune.setNomCommune("Test2");
        assertEquals("Test2", commune.getNomCommune());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIdCommuneWithInvalidArg() {
        commune.setIdCommune(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNomCommuneWithInvalidArg() {
        commune.setNomCommune(null);
    }

    @Test
    public void testAddNeighbor() {
        CommuneBase commune2 = new CommuneBase(2, "Test2", dep, new ArrayList<>(), new ArrayList<>());
        commune.addNeighbor(commune2);
        assertTrue(commune.getLesVoisins().contains(commune2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNeighborWithInvalidArg() {
        commune.addNeighbor(null);
    }

    @Test
    public void testCompareNbVoisins() {
        CommuneBase commune1 = new CommuneBase(1, "Test1", dep, new ArrayList<>(), new ArrayList<>());
        CommuneBase commune2 = new CommuneBase(2, "Test2", dep, new ArrayList<>(), new ArrayList<>());
        commune1.addNeighbor(commune2);
        assertEquals(1, commune1.compareNbVoisins(commune2));
        assertEquals(-1, commune2.compareNbVoisins(commune1));
        assertEquals(0, commune1.compareNbVoisins(commune1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompareNbVoisinsWithInvalidArg() {
        commune.compareNbVoisins(null);
    }

    @Test
    public void testToString() {
        String expected = "\"" + commune.getIdCommune() + "\",\"" + commune.getNomCommune() + "\",\"" + commune.getLeDepartement().getNomDep() + "\"";
        assertEquals(expected, commune.toString());
    }
}