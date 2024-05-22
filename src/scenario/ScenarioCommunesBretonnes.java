package scenario;
import model.data.*;
import java.util.ArrayList;
import model.dao.CommuneBaseDAO;
import model.dao.CommunesInfoParAnneeDAO;
import model.dao.DepartementDAO;
import model.data.CommunesInfoParAnnee;


public class ScenarioCommunesBretonnes {
    public static void main(String[] args){
        CommunesInfoParAnneeDAO communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO();
        ArrayList<CommunesInfoParAnnee> communesInfoParAnnee = communesInfoParAnneeDAO.findAllByYear(2020L);
        for (CommunesInfoParAnnee commune : communesInfoParAnnee) {
            System.out.println(commune.getLaCommune().getNomCommune() + " " + commune.getLannee().getAnnee() + " " + commune.getNbMaison() +  " " + commune.getNbAppart() + " " + commune.getPrixMoyen());
        }

    }
}
