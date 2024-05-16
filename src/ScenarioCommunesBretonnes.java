import model.dao.AnneeDAO;
import model.data.*;

public class ScenarioCommunesBretonnes {
    public static void main(String[] args){
        AnneeDAO anneeDAO = new AnneeDAO();
        System.out.println(anneeDAO.findByID(2019L).getAnnee() + " : " + anneeDAO.findByID(2019L).getTauxInflation());

        System.out.println("Liste des années : ");
        for (Annee annee : anneeDAO.findAll()) {
            System.out.println(annee.getAnnee() + " : " + annee.getTauxInflation());
        }
    }
}
