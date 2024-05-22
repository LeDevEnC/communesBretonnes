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
        CommunesInfoParAnnee rennes = communesInfoParAnneeDAO.findByIDComplete(2019L, new CommuneBaseDAO().findByName("Rennes", true));
        System.out.println(rennes.toString());
        System.out.println(rennes.getLaCommune().getIdCommune());
        System.out.println(CommunesInfoParAnnee.scoreCompute(rennes));

    }
}
