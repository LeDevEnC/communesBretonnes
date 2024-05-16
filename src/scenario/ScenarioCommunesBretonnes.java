package scenario;
import model.dao.DepartementDAO;
import model.data.Aeroport;
import model.data.Departement;

import java.util.ArrayList;

import model.dao.AeroportDAO;


public class ScenarioCommunesBretonnes {
    public static void main(String[] args){
        DepartementDAO departementDAO = new DepartementDAO();
        AeroportDAO aeroportDAO = new AeroportDAO();
        ArrayList<Departement> departements = departementDAO.findAll();
        for (Departement departement : departements) {
            System.out.println(departement.getIdDep() + " " + departement.getNomDep() + " " + departement.getNomDep() + " " + departement.getIdDep());
        }

        ArrayList<Aeroport> aeroports = aeroportDAO.findAll();
        for (Aeroport aeroport : aeroports) {
            System.out.println(aeroport.getNom() + " " + aeroport.getAdresse() + " " + aeroport.getDepartement().getNomDep() + " " + aeroport.getDepartement().getIdDep());
        }

        

        

    }
}
