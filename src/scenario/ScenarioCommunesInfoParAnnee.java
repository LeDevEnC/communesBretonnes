package scenario;

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

public class ScenarioCommunesInfoParAnnee {

    private CommunesInfoParAnnee communesInfoParAnnee;

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

    @Test
    public void testGetNbMaison() {
        assertEquals(100, communesInfoParAnnee.getNbMaison());
    }

    @Test
    public void testGetNbAppart() {
        assertEquals(50, communesInfoParAnnee.getNbAppart());
    }

    @Test
    public void testGetPrixMoyen() {
        assertEquals(200000, communesInfoParAnnee.getPrixMoyen(), 0);
    }

    @Test
    public void testGetPrixMCarreMoyen() {
        assertEquals(2000, communesInfoParAnnee.getPrixMCarreMoyen(), 0);
    }

    @Test
    public void testGetSurfaceMoy() {
        assertEquals(80, communesInfoParAnnee.getSurfaceMoy(), 0);
    }

    @Test
    public void testGetDepCulturellesTotales() {
        assertEquals(1000, communesInfoParAnnee.getDepCulturellesTotales(), 0);
    }

    @Test
    public void testGetBudgetTotal() {
        assertEquals(5000, communesInfoParAnnee.getBudgetTotal(), 0);
    }

    @Test
    public void testGetPopulation() {
        assertEquals(200000, communesInfoParAnnee.getPopulation(), 0);
    }

    @Test
    public void testGetLaCommune() {
        assertEquals("Test", communesInfoParAnnee.getLaCommune().getNomCommune());
    }

    @Test
    public void testGetLannee() {
        assertEquals(2022, communesInfoParAnnee.getLannee().getAnneeRepr());
    }

    @Test
    public void testSetNbMaison() {
        communesInfoParAnnee.setNbMaison(150);
        assertEquals(150, communesInfoParAnnee.getNbMaison());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNbMaisonNegative() {
        communesInfoParAnnee.setNbMaison(-50);
    }

    @Test
    public void testSetNbAppart() {
        communesInfoParAnnee.setNbAppart(75);
        assertEquals(75, communesInfoParAnnee.getNbAppart());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNbAppartNegative() {
        communesInfoParAnnee.setNbAppart(-30);
    }

    @Test
    public void testSetPrixMoyen() {
        communesInfoParAnnee.setPrixMoyen(250000);
        assertEquals(250000, communesInfoParAnnee.getPrixMoyen(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrixMoyenNegative() {
        communesInfoParAnnee.setPrixMoyen(-200000);
    }

    @Test
    public void testSetPrixMCarreMoyen() {
        communesInfoParAnnee.setPrixMCarreMoyen(2500);
        assertEquals(2500, communesInfoParAnnee.getPrixMCarreMoyen(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPrixMCarreMoyenNegative() {
        communesInfoParAnnee.setPrixMCarreMoyen(-1500);
    }

    @Test
    public void testSetSurfaceMoy() {
        communesInfoParAnnee.setSurfaceMoy(100);
        assertEquals(100, communesInfoParAnnee.getSurfaceMoy(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSurfaceMoyNegative() {
        communesInfoParAnnee.setSurfaceMoy(-50);
    }

    @Test
    public void testSetDepCulturellesTotales() {
        communesInfoParAnnee.setDepCulturellesTotales(1500);
        assertEquals(1500, communesInfoParAnnee.getDepCulturellesTotales(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDepCulturellesTotalesNegative() {
        communesInfoParAnnee.setDepCulturellesTotales(-1000);
    }

    @Test
    public void testSetBudgetTotal() {
        communesInfoParAnnee.setBudgetTotal(6000);
        assertEquals(6000, communesInfoParAnnee.getBudgetTotal(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetBudgetTotalNegative() {
        communesInfoParAnnee.setBudgetTotal(-4000);
    }

    @Test
    public void testSetPopulation() {
        communesInfoParAnnee.setPopulation(250000);
        assertEquals(250000, communesInfoParAnnee.getPopulation(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPopulationNegative() {
        communesInfoParAnnee.setPopulation(-150000);
    }

    @Test
    public void testToString() {
        String expected = "\"Test\",\"2022\",\"100\",\"50\",\"200000.0\",\"2000.0\",\"80.0\",\"1000.0\",\"5000.0\",\"200000.0\"";
        assertEquals(expected, communesInfoParAnnee.toString());
    }

    @Test
    public void testCompareNbHabitants() {
        Departement dep = new Departement(2, DepPossibles.COTES_D_ARMOR, 2000L, new ArrayList<>());
        CommuneBase commune2 = new CommuneBase(2, "Test2", dep, new ArrayList<>(), new ArrayList<>());
        Annee annee2 = new Annee(2022, 5);
        CommunesInfoParAnnee communeInfo2 = new CommunesInfoParAnnee(commune2, annee2, 120, 60, 180000, 1800, 70, 1200, 6000, 180000);

        assertEquals(1, communesInfoParAnnee.compareNbHabitants(communeInfo2), 0);
        assertEquals(-1, communeInfo2.compareNbHabitants(communesInfoParAnnee), 0);
        assertEquals(0, communesInfoParAnnee.compareNbHabitants(communesInfoParAnnee), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompareNbHabitantsNull() {
        communesInfoParAnnee.compareNbHabitants(null);
    }

    @Test
    public void testScoreCompute() {
        assertEquals(91, communesInfoParAnnee.scoreCompute());
    }
}