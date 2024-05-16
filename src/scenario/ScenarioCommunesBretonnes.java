package scenario;

import model.dao.AnneeDAO;
import model.data.Annee;

public class ScenarioCommunesBretonnes {
    public static void main(String[] args) {

        AnneeDAO anneeDAO = new AnneeDAO();
        Annee annee1 = new Annee(2050, 40);
        anneeDAO.delete(annee1);

        System.out.println("Liste des années : ");
        for (Annee annee : anneeDAO.findAll()) {
            System.out.println(annee.getAnnee() + " : " + annee.getTauxInflation());
        }

    }
}
