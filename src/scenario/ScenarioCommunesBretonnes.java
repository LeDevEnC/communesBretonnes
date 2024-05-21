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
        CommuneBaseDAO communeBaseDAO = new CommuneBaseDAO();

        CommuneBase vannes = communeBaseDAO.findByName("Vannes", true);
        CommunesInfoParAnnee vannesInfos = communesInfoParAnneeDAO.findByIDComplete(2019L, vannes);
        System.out.println(vannesInfos.toString());
        ArrayList<CommuneBase> voisins = vannes.getLesVoisins();
        System.out.println("Voisins de Vannes : ");
        for (CommuneBase voisin : voisins){
            System.out.println(voisin.getNomCommune());
        }
        System.out.println("Gares de Vannes : ");
        ArrayList<Gare> gares = vannes.getLesGares();
        for (Gare gare : gares){
            System.out.println(gare.getNomGare());
        }
        System.out.println("Département de Vannes : ");
        System.out.println(vannes.getLeDepartement().getNomDep());

        System.out.println("Aéroports proches de Vannes : ");
        ArrayList<Aeroport> aeroports = vannes.getLeDepartement().getAeroports();
        for (Aeroport aeroport : aeroports){
            System.out.println(aeroport.getNom());
        }        

        ArrayList<CommuneBase> communes = new ArrayList<CommuneBase>();

        communes = communeBaseDAO.findAll();
        for (CommuneBase commune : communes){
            System.out.println(commune.getNomCommune());
            System.out.println("Les voisins sont : ");
            for (CommuneBase voisin : commune.getLesVoisins()){
                System.out.println(voisin.getNomCommune());
            }
        }

    }
}
