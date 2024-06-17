package scenario.data;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.DepPossibles;
import model.data.Departement;
import model.data.Gare;

/**
 * Classe de test pour la classe CommunesInfoParAnnee.
 * Elle teste les fonctionnalités de base de la gestion des informations des
 * communes par année, y compris la création et la manipulation des données de
 * commune.
 */
public class ScenarioCommunesInfoParAnnee {

    /**
     * Les informations de la commune par année à tester.
     */
    private CommunesInfoParAnnee communesInfoParAnnee;

    /**
     * Initialise les données de la commune par année pour les tests.
     * Exécute avant chaque test.
     */
    @Before
    public void setUp() {

        List<Aeroport> lesAeroports = new ArrayList<>();
        lesAeroports.add(new Aeroport("NomAero", "AdresseAero"));
        lesAeroports.add(new Aeroport("NomAero2", "AdresseAero2"));
        Departement dep = new Departement(1, DepPossibles.FINISTERE, 1000L, lesAeroports);

        List<Gare> gare = new ArrayList<>();
        gare.add(new Gare(1, "NomGare", true, true));
        gare.add(new Gare(2, "NomGare2", false, true));
        gare.add(new Gare(3, "NomGare3", true, false));

        CommuneBase commune = new CommuneBase(1, "Test", dep, new ArrayList<>(), gare);
        Annee annee = new Annee(2022, 10);
        communesInfoParAnnee = new CommunesInfoParAnnee(commune, annee, 100, 50, 200000, 2000, 80, 1000, 5000, 200000);
    }

    /**
     * Teste si le nombre de maisons est correctement récupéré.
     */
    @Test
    public void testGetNbMaison() {
        assertEquals(100, communesInfoParAnnee.getNbMaison());
    }

    /**
     * Teste si le nombre d'appartements est correctement récupéré.
     */
    @Test
    public void testGetNbAppart() {
        assertEquals(50, communesInfoParAnnee.getNbAppart());
    }

    /**
     * Teste si le prix moyen est correctement récupéré.
     */
    @Test
    public void testGetPrixMoyen() {
        assertEquals(200000, communesInfoParAnnee.getPrixMoyen(), 0);
    }

    /**
     * Teste si le prix au mètre carré moyen est correctement récupéré.
     */
    @Test
    public void testGetPrixMCarreMoyen() {
        assertEquals(2000, communesInfoParAnnee.getPrixMCarreMoyen(), 0);
    }

    /**
     * Teste si la surface moyenne est correctement récupérée.
     */
    @Test
    public void testGetSurfaceMoy() {
        assertEquals(80, communesInfoParAnnee.getSurfaceMoy(), 0);
    }

    /**
     * Teste si les dépenses culturelles totales sont correctement récupérées.
     */
    @Test
    public void testGetDepCulturellesTotales() {
        assertEquals(1000, communesInfoParAnnee.getDepCulturellesTotales(), 0);
    }

    /**
     * Teste si le budget total est correctement récupéré.
     */
    @Test
    public void testGetBudgetTotal() {
        assertEquals(5000, communesInfoParAnnee.getBudgetTotal(), 0);
    }

    /**
     * Teste si la population est correctement récupérée.
     */
    @Test
    public void testGetPopulation() {
        assertEquals(200000, communesInfoParAnnee.getPopulation(), 0);
    }

    /**
     * Teste si la commune est correctement récupérée.
     */
    @Test
    public void testGetLaCommune() {
        assertEquals("Test", communesInfoParAnnee.getLaCommune().getNomCommune());
    }

    /**
     * Teste si l'année est correctement récupérée.
     */
    @Test
    public void testGetLannee() {
        assertEquals(2022, communesInfoParAnnee.getLannee().getAnneeRepr());
    }

    /**
     * Teste si la commune est correctement définie.
     */
    @Test
    public void testSetNbMaison() {
        communesInfoParAnnee.setNbMaison(150);
        assertEquals(150, communesInfoParAnnee.getNbMaison());
    }

    /**
     * Teste la réaction du système lors de la définition d'un nombre de maisons négatif
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNbMaisonNegative() {
        communesInfoParAnnee.setNbMaison(-50);
    }

    /**
     * Teste la réaction du système lors de la définition d'un nombre d'appartements
     */
    @Test
    public void testSetNbAppart() {
        communesInfoParAnnee.setNbAppart(75);
        assertEquals(75, communesInfoParAnnee.getNbAppart());
    }

    /**
     * Teste la réaction du système lors de la définition d'un nombre d'appartements
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNbAppartNegative() {
        communesInfoParAnnee.setNbAppart(-30);
    }

    /**
     * Teste la réaction du système lors de la définition d'un prix moyen négatif
     */
    @Test
    public void testSetPrixMoyen() {
        communesInfoParAnnee.setPrixMoyen(250000);
        assertEquals(250000, communesInfoParAnnee.getPrixMoyen(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition d'un prix moyen négatif
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPrixMoyenNegative() {
        communesInfoParAnnee.setPrixMoyen(-200000);
    }

    /**
     * Teste la réaction du système lors de la définition d'un prix au mètre carré moyen
     */
    @Test
    public void testSetPrixMCarreMoyen() {
        communesInfoParAnnee.setPrixMCarreMoyen(2500);
        assertEquals(2500, communesInfoParAnnee.getPrixMCarreMoyen(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition d'un prix au mètre carré moyen négatif
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPrixMCarreMoyenNegative() {
        communesInfoParAnnee.setPrixMCarreMoyen(-1500);
    }

    /**
     * Teste la réaction du système lors de la définition d'une surface moyenne
     */
    @Test
    public void testSetSurfaceMoy() {
        communesInfoParAnnee.setSurfaceMoy(100);
        assertEquals(100, communesInfoParAnnee.getSurfaceMoy(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition d'une surface moyenne négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSurfaceMoyNegative() {
        communesInfoParAnnee.setSurfaceMoy(-50);
    }

    /**
     * Teste la réaction du système lors de la définition des dépenses culturelles totales
     */
    @Test
    public void testSetDepCulturellesTotales() {
        communesInfoParAnnee.setDepCulturellesTotales(1500);
        assertEquals(1500, communesInfoParAnnee.getDepCulturellesTotales(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition des dépenses culturelles totales avec une valeur négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDepCulturellesTotalesNegative() {
        communesInfoParAnnee.setDepCulturellesTotales(-1000);
    }

    /**
     * Teste la réaction du système lors de la définition du budget total
     */
    @Test
    public void testSetBudgetTotal() {
        communesInfoParAnnee.setBudgetTotal(6000);
        assertEquals(6000, communesInfoParAnnee.getBudgetTotal(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition du budget total avec une valeur négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetBudgetTotalNegative() {
        communesInfoParAnnee.setBudgetTotal(-4000);
    }

    /**
     * Teste la réaction du système lors de la définition de la population
     */
    @Test
    public void testSetPopulation() {
        communesInfoParAnnee.setPopulation(250000);
        assertEquals(250000, communesInfoParAnnee.getPopulation(), 0);
    }

    /**
     * Teste la réaction du système lors de la définition de la population avec une valeur négative
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPopulationNegative() {
        communesInfoParAnnee.setPopulation(-150000);
    }

    /**
     * Test la représentation textuelle des informations de la commune par année.
     */
    @Test
    public void testToString() {
        String expected = "\"Test\",\"2022\",\"100\",\"50\",\"200000.0\",\"2000.0\",\"80.0\",\"1000.0\",\"5000.0\",\"200000.0\"";
        assertEquals(expected, communesInfoParAnnee.toString());
    }

    /**
     * Teste le calcul du score de la commune.
     */
    @Test
    public void testScoreCompute() {
        assertEquals(91, communesInfoParAnnee.scoreCompute());
    }
}