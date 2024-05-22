package scenario;

import java.util.ArrayList;

import model.dao.CommuneBaseDAO;
import model.dao.CommunesInfoParAnneeDAO;

import model.data.Annee;
import model.data.CommunesInfoParAnnee;
public class ScenarioCommunesBretonnes {
    public static void main(String[] args) {
        CommunesInfoParAnneeDAO communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(null, null);
        Annee annee = new Annee(2019, 1.1);
        CommunesInfoParAnnee rennes = communesInfoParAnneeDAO.findByIDComplete(annee, new CommuneBaseDAO().findByName("Rennes", true));
        System.out.println(rennes.toString());
        System.out.println(rennes.getLaCommune().getIdCommune());
        System.out.println(CommunesInfoParAnnee.scoreCompute(rennes));

    }
}
