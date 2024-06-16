package scenario.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;
import model.data.DepPossibles;
import model.data.Departement;

/**
 * Classe de test pour la classe Departement.
 * Elle teste les fonctionnalités de base de la gestion des départements,
 * y compris l'ajout et la récupération des informations de département,
 * ainsi que la comparaison des investissements culturels.
 */
public class ScenarioDepartement {
    /**
     * Le département à tester.
     */
    private Departement departement;

    /**
     * La liste des aéroports du département.
     */
    private List<Aeroport> aeroports;

    @Before
    public void setUp() {
        aeroports = new ArrayList<>();
        aeroports.add(new Aeroport("Aeroport1", "Ville1"));
        aeroports.add(new Aeroport("Aeroport2", "Ville2"));
        departement = new Departement(1, DepPossibles.FINISTERE, 1000, aeroports);
    }

    /**
     * Teste si l'ID du département est correctement récupéré.
     */
    @Test
    public void testGetIdDep() {
        assertEquals(1, departement.getIdDep());
    }

    /**
     * Teste si le nom du département est correctement récupéré.
     */
    @Test
    public void testGetNomDep() {
        assertEquals("FINISTERE", departement.getNomDep());
    }

    /**
     * Teste si l'investissement culturel de 2019 est correctement récupéré.
     */
    @Test
    public void testGetInvestCulturel2019() {
        assertEquals(1000, departement.getInvestCulturel2019());
    }

    /**
     * Teste si la liste des aéroports est correctement récupérée.
     */
    @Test
    public void testGetAeroports() {
        assertEquals(aeroports, departement.getAeroports());
    }

    /**
     * Teste la réaction du système lors de la définition d'un ID de département
     * négatif.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetIdDepWithNegativeId() {
        departement.setId(-1);
    }

    /**
     * Teste la mise à jour de l'ID du département avec un ID positif.
     */
    @Test
    public void testSetIdDepWithPositiveId() {
        departement.setId(2);
        assertEquals(2, departement.getIdDep());
    }

    /**
     * Teste la réaction du système lors de la définition d'un nom de département à
     * null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNomDepWithNull() {
        departement.setNomDep(null);
    }

    /**
     * Teste la mise à jour du nom du département avec un nom valide.
     */
    @Test
    public void testSetNomDepWithValidName() {
        departement.setNomDep(DepPossibles.MORBIHAN);
        assertEquals("MORBIHAN", departement.getNomDep());
    }

    /**
     * Teste la comparaison des investissements culturels avec un département null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCompareInvesCulturelWithNull() {
        departement.compareInvesCulturel(null);
    }

    /**
     * Teste la comparaison des investissements culturels avec un département ayant
     * un investissement supérieur.
     */
    @Test
    public void testCompareInvesCulturelWithGreaterInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 2000, aeroports);
        assertEquals(-1, departement.compareInvesCulturel(otherDepartement));
    }

    /**
     * Teste la comparaison des investissements culturels avec un département ayant
     * un investissement inférieur.
     */
    @Test
    public void testCompareInvesCulturelWithLessInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 500, aeroports);
        assertEquals(1, departement.compareInvesCulturel(otherDepartement));
    }

    /**
     * Teste la comparaison des investissements culturels avec un département ayant
     * un investissement égal.
     */
    @Test
    public void testCompareInvesCulturelWithEqualInvestment() {
        Departement otherDepartement = new Departement(2, DepPossibles.MORBIHAN, 1000, aeroports);
        assertEquals(0, departement.compareInvesCulturel(otherDepartement));
    }

    /**
     * Teste la représentation en chaîne de caractères du département.
     */
    @Test
    public void testToString() {
        String expected = "\"1\",\"FINISTERE\",\"1000\"";
        assertEquals(expected, departement.toString());
    }
}