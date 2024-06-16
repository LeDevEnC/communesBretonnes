package scenario.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;
import model.data.DepPossibles;
import model.data.Departement;

public class ScenarioDepartement {
    private Departement departement;
    private List<Aeroport> aeroports;

    @Before
    public void setUp() {
        aeroports = new ArrayList<>();
        aeroports.add(new Aeroport("Aeroport1", "Ville1"));
        aeroports.add(new Aeroport("Aeroport2", "Ville2"));
        departement = new Departement(1, DepPossibles.FINISTERE, 1000, aeroports);
    }

    @Test
    public void testGetIdDep() {
        assertEquals(1, departement.getIdDep());
    }

    @Test
    public void testGetNomDep() {
        assertEquals("FINISTERE", departement.getNomDep());
    }

    @Test
    public void testGetInvestCulturel2019() {
        assertEquals(1000, departement.getInvestCulturel2019());
    }

    @Test
    public void testGetAeroports() {
        assertEquals(aeroports, departement.getAeroports());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetIdDepWithNegativeId() {
        departement.setId(-1);
    }

    @Test
    public void testSetIdDepWithPositiveId() {
        departement.setId(2);
        assertEquals(2, departement.getIdDep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNomDepWithNull() {
        departement.setNomDep(null);
    }

    @Test
    public void testSetNomDepWithValidName() {
        departement.setNomDep(DepPossibles.MORBIHAN);
        assertEquals("MORBIHAN", departement.getNomDep());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompareInvesCulturelWithNull() {
        departement.compareInvesCulturel(null);
    }

    @Test
    public void testCompareInvesCulturelWithGreaterInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 2000, aeroports);
        assertEquals(-1, departement.compareInvesCulturel(otherDepartement));
    }

    @Test
    public void testCompareInvesCulturelWithLessInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 500, aeroports);
        assertEquals(1, departement.compareInvesCulturel(otherDepartement));
    }

    @Test
    public void testCompareInvesCulturelWithEqualInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 1000, aeroports);
        assertEquals(0, departement.compareInvesCulturel(otherDepartement));
    }

    @Test
    public void testToString() {
        String expected = "\"1\",\"FINISTERE\",\"1000\"";
        assertEquals(expected, departement.toString());
    }
}