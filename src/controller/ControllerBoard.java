package controller;

import model.dao.*;
import model.data.*;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;



public class ControllerBoard extends Controller {
    /**
     * Score global de l'attractivité de la Bretagne
     */
    private int scoreGlobal;

    /**
     * PieChart représentant le score global de l'attractivité de la Bretagne
     */
    @FXML
    private PieChart pieChartBretagneAtt;

    /**
     * BarChart représentant le score moyen de l'attractivité de la Bretagne par département en 2021
     */
    @FXML
    private BarChart<String, Number> barChartBretagneAtt;

    /**
     * Méthode appelée lors de la première initialisation de la vue
     */
    @Override
    public void initialize() {
        initPieChart();
        initBarChartDep();
    }

    /**
     * Méthode appelée lors de l'ouverture de la vue
     */
    public void onViewOpened() {
        this.pieChartBretagneAtt.setVisible(true);
    }

    
    protected void resize() {

    }

    /**
     * Initialiser le PieChart
     */
    private void initPieChart(){

        HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = findAllCommunesInfoParAnnee();

        int scoreGlobalTotal = 0;
        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            scoreGlobalTotal += communesInfoParAnnee.scoreCompute();
        }

        this.scoreGlobal = scoreGlobalTotal / toutesLesCommunesInfoParAnnee.size();
        System.out.println(this.scoreGlobal);

        // Ajouter deux segments au PieChart : un pour le score, un pour le reste
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Bretagne : " + String.valueOf(this.scoreGlobal) + " %", this.scoreGlobal),
                new PieChart.Data("", 100 - this.scoreGlobal));

        this.pieChartBretagneAtt.setData(pieChartData);
        this.pieChartBretagneAtt.setTitle("% d'attractivité de la Bretagne");
        this.pieChartBretagneAtt.setStartAngle(90);

        this.pieChartBretagneAtt.getData().get(0).getNode().setStyle("-fx-pie-color: #80caff;");
        this.pieChartBretagneAtt.getData().get(1).getNode().setStyle("-fx-pie-color: #85e0a3;");

        this.pieChartBretagneAtt.setLegendVisible(false);
    }


    /**
     * Initialiser le BarChart
     */
    private void initBarChartDep(){
        HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = findAllCommunesInfoParAnnee();
        int scoreFinistere = 0;
        int scoreCotesArmor = 0;
        int scoreMorbihan = 0;
        int scoreIlleVilaine = 0;
        int idDep;
        int annee;
        int nbFinistere = 0;
        int nbMorbihan = 0;
        int nbCotesArmor = 0;
        int nbIlleEtVilaine = 0;

        for (CommunesInfoParAnnee communesInfoParAnnee : toutesLesCommunesInfoParAnnee.values()) {
            idDep = communesInfoParAnnee.getLaCommune().getLeDepartement().getIdDep();
            annee = communesInfoParAnnee.getLannee().getAnnee();

            if (idDep == 29 && annee == 2021) {
                scoreFinistere += communesInfoParAnnee.scoreCompute();
                nbFinistere++;
            } else if (idDep == 22 && annee == 2021){
                scoreCotesArmor += communesInfoParAnnee.scoreCompute();
                nbCotesArmor++;
            } else if (idDep == 56 && annee == 2021) {
                scoreMorbihan += communesInfoParAnnee.scoreCompute();
                nbMorbihan++;
            } else if (idDep == 35 && annee == 2021) {
                scoreIlleVilaine += communesInfoParAnnee.scoreCompute();
                nbIlleEtVilaine++;
            }
        }

        scoreFinistere = scoreFinistere / nbFinistere;
        scoreCotesArmor = scoreCotesArmor / nbCotesArmor;
        scoreMorbihan = scoreMorbihan / nbMorbihan;
        scoreIlleVilaine = scoreIlleVilaine / nbIlleEtVilaine;


        this.barChartBretagneAtt.setTitle("Scores Moyens par Département en 2021");
        this.barChartBretagneAtt.getXAxis().setLabel("Département");
        this.barChartBretagneAtt.setCategoryGap(30);  
        this.barChartBretagneAtt.setBarGap(0);  
        this.barChartBretagneAtt.setLegendVisible(false);
        double maxYValue = 100.0; 
        ((NumberAxis) barChartBretagneAtt.getYAxis()).setAutoRanging(false);
        ((NumberAxis) barChartBretagneAtt.getYAxis()).setUpperBound(maxYValue);


        XYChart.Series<String, Number> series = new XYChart.Series<>();    

        XYChart.Data<String, Number> dataFinistere = new XYChart.Data<>("Finistère : " +scoreFinistere + " %", scoreFinistere);
        XYChart.Data<String, Number> dataCotesArmor = new XYChart.Data<>("Côtes d'Armor : " + scoreCotesArmor + " %", scoreCotesArmor);
        XYChart.Data<String, Number> dataMorbihan = new XYChart.Data<>("Morbihan : " + scoreMorbihan + " %", scoreMorbihan);
        XYChart.Data<String, Number> dataIlleVilaine = new XYChart.Data<>("Ille-et-Vilaine : " + scoreIlleVilaine + " %", scoreIlleVilaine);

        series.getData().add(dataFinistere);
        series.getData().add(dataCotesArmor);
        series.getData().add(dataMorbihan);
        series.getData().add(dataIlleVilaine);
        
        this.barChartBretagneAtt.getData().add(series);
        applySingleColorToSeries(series, "#2eb82e"); 
    }

    
    /**
     * Appliquer une couleur unique à une série
     * @param series la série
     * @param color la couleur
     */
    private void applySingleColorToSeries(XYChart.Series<String, Number> series, String color) {
        for (XYChart.Data<String, Number> data : series.getData()) {
            Node bar = data.getNode();
            bar.setStyle("-fx-bar-fill: " + color + ";");
        }
    }

    private void initLineChart(){

    }

    /**
     * Trouver toutes les informations des communes par année
     * @return la liste des informations des communes par année
     */
    private HashMap<String, CommunesInfoParAnnee> findAllCommunesInfoParAnnee() {
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

        CommunesInfoParAnneeDAO communesInfoParAnneeDAO = new CommunesInfoParAnneeDAO(toutesLesAnnees,
                toutesLesCommunesBase);
        HashMap<String, CommunesInfoParAnnee> toutesLesCommunesInfoParAnnee = communesInfoParAnneeDAO.findAll();

        return toutesLesCommunesInfoParAnnee;
    }
}
