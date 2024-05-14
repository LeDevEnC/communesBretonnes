package modele.data;

import java.util.ArrayList;

/**
 * Classe Departement représentant les informations d'un département
 */
public class Departement {
    /**
     * Identifiant du département
     */
    private int idDep;
    /**
     * Nom du département
     */
    private String nomDep;
    /**
     * Investissement culturel du département en 2019 représenté par une variable constante
     */
    private final long INVESCULTUREL2019;
    /**
     * Liste des aéroports du département
     */
    private ArrayList<Aeroport> aeroports;

    /**
     * Constructeur de la classe Departement
     * @param idDep l'id du département
     * @param nomDep le nom du département
     * @param invesCulturel  l'investissement culturel du département
     * @param aeroports la liste des aéroports du département
     * @throws IllegalArgumentException si l'id du département est négatif ou le nom du département, l'investissement culturel ou la liste des aéroports est null
     */
    public Departement(int idDep, String nomDep, long invesCulturel, ArrayList<Aeroport> aeroports) throws IllegalArgumentException{
        if (idDep < 0 || nomDep == null || invesCulturel < 0 || aeroports == null){
            throw new IllegalArgumentException("L'identifiant du département est négatif ou le nom du département, l'investissement culturel ou la liste des aéroports est null");
        } else {
            this.idDep = idDep;
            this.nomDep = nomDep;
            this.INVESCULTUREL2019 = invesCulturel;
            this.aeroports = aeroports;
        }
    }
    /**
     * Getter de l'identifiant du département
     * @return l'identifiant du département
     */
    public int getIdDep(){
        return this.idDep;
    }

    /**
     * Getter du nom du département
     * @return le nom du département
     */
    public String getNomDep(){
        return this.nomDep;
    }

    /**
     * Getter de la liste des aéroports du département
     * @return la liste des aéroports du département
     */
    public ArrayList<Aeroport> getAeroports(){
        return this.aeroports;
    }

    /**
     * Getter de l'investissement culturel du département
     * @return l'investissement culturel du département
     */
    public double getInvestCulturel2019(){
        return this.INVESCULTUREL2019;
    }

    /**
     * Setter de la liste des aéroports du département
     * @param aeroport la liste des aéroports du département
     * @throws IllegalArgumentException si la liste des aéroports est null
     */
    public void addAeroport(Aeroport aeroport) throws IllegalArgumentException{
        if (aeroport == null){
            throw new IllegalArgumentException("L'aéroport à ajouter est null");
        } else {
            this.aeroports.add(aeroport);
        }
        
    }

    /**
     * Setter de l'id du département
     * @param idDep l'id du département
     * @throws IllegalArgumentException si l'id du département est négatif
     */
    public void setId(int idDep) throws IllegalArgumentException{
        if (idDep < 0){
            throw new IllegalArgumentException("L'identifiant du département est négatif");
        } else {
            this.idDep = idDep;
        }
    }

    /**
     * Setter du nom du département
     * @param nomDep le nom du département
     * @throws IllegalArgumentException si le nom du département est null ou n'est pas valide
     */
    public void setNomDep(String nomDep) throws IllegalArgumentException{
        if (nomDep == null){
            throw new IllegalArgumentException("Le nom du département est null");
        } else {
            try {
                this.nomDep = DepPossibles.valueOf(nomDep).toString();
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Le nom du département n'est pas valide");
            }
        }
    }
    /**
     * Compare les investissements culturels de deux départements
     * @param autreDep l'autre département à comparer
     * @return 0 si les investissements culturels sont égaux, 1 si l'investissement culturel du département est supérieur à celui de l'autre département, -1 sinon
     * @throws IllegalArgumentException si le département à comparer est null
     */
    public int compareInvesCulturel(Departement autreDep) throws IllegalArgumentException{
        if (autreDep == null){
            throw new IllegalArgumentException("Le département à comparer est null");
        } else {
            int res = 0;
            if (this.INVESCULTUREL2019 > autreDep.getInvestCulturel2019()){
                return 1;
            } else if (this.INVESCULTUREL2019 < autreDep.getInvestCulturel2019()){ 
                return -1;
            }
            return res;
        }
    }
}
