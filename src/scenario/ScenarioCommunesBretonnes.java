package scenario;

import java.util.ArrayList;
import java.util.HashMap;

import model.dao.AeroportDAO;
import model.dao.AnneeDAO;
import model.dao.CommuneBaseDAO;
import model.dao.CommunesInfoParAnneeDAO;
import model.dao.DepartementDAO;
import model.dao.GareDAO;
import model.data.Aeroport;
import model.data.Annee;
import model.data.CommuneBase;
import model.data.CommunesInfoParAnnee;
import model.data.Departement;
import model.data.Gare;

public class ScenarioCommunesBretonnes {
    public static void main(String[] args) {
        AeroportDAO aeroportDAO = new AeroportDAO();
        HashMap<String, ArrayList<Aeroport>> tousAeroport = aeroportDAO.findAll();

        AnneeDAO anneeDAO = new AnneeDAO();
        HashMap<String, Annee> toutesLesAnnees = anneeDAO.findAll();

        DepartementDAO departementDAO = new DepartementDAO(tousAeroport);
        HashMap<String, Departement> tousLesDepartements = departementDAO.findAll();

        GareDAO gareDAO = new GareDAO();
        HashMap<String, ArrayList<Gare>> toutesLesGares = gareDAO.findAll();

        CommuneBaseDAO communeBaseDAO = new CommuneBaseDAO(tousLesDepartements, toutesLesGares);
        HashMap<String, CommuneBase> toutesLesCommunesBase = communeBaseDAO.findAll();

        CommunesInfoParAnneeDAO communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(toutesLesAnnees, toutesLesCommunesBase);
        HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = communesInfoParAnneeDAO.findAll();

        System.out.println(toutesLesCommunesInfoParAnnee.get("RENNES 2021"));
        Annee annee = new Annee(2025, 100000);
        anneeDAO.create(annee);

    }
}
