package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class GraphGenerator {

    /**
     * Evite l'instantiation de cette classe
     */
    private GraphGenerator() {
    }

    /**
     * Génère un graphique en nuage de points à partir des valeurs x et y fournies.
     * @param xValues les valeurs de l'axe x
     * @param yValues les valeurs de l'axe y
     * @param xLabel le label de l'axe x
     * @param yLabel le label de l'axe y
     * @return le graphique en nuage de points généré
     */
    public static ScatterChart<Number, Number> generateScatterChart(List<Double> xValues, List<Double> yValues, String xLabel, String yLabel) {
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        setDynamicTickUnit(xAxis, xValues);
        setDynamicTickUnit(yAxis, yValues);

        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setLegendVisible(false);

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Scatter Plot");

        for (int i = 0; i < xValues.size(); i++) {
            series.getData().add(new XYChart.Data<>(xValues.get(i), yValues.get(i)));
        }

        scatterChart.getData().add(series);

        return scatterChart;
    }

    /**
     * Génère un graphique en barres à partir des valeurs fournies.
     * @param values les valeurs à afficher
     * @param xLabel le label de l'axe x
     * @param yLabel le label de l'axe y
     * @return le graphique en barres généré
     */
    public static BarChart<String, Number> generateBarChart(List<Double> values, String xLabel, String yLabel) {
        double minValue = Collections.min(values);
        double maxValue = Collections.max(values);
        double valueRange = maxValue - minValue;

        double intervalSize = calculateDynamicIntervalSize(valueRange);
        Map<String, Integer> intervalCounts = new HashMap<>();

        for (double value : values) {
            String interval = findInterval(value, intervalSize);
            intervalCounts.put(interval, intervalCounts.getOrDefault(interval, 0) + 1);
        }

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setLegendVisible(false);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Bar Chart");

        List<String> sortedKeys = new ArrayList<>(intervalCounts.keySet());
        Collections.sort(sortedKeys, Comparator.comparing(s -> {
            String[] parts = s.split("\\[");
            return Integer.parseInt(parts[1].split(",")[0]);
        }));

        for (String interval : sortedKeys) {
            series.getData().add(new XYChart.Data<>(interval, intervalCounts.get(interval)));
        }

        barChart.getData().add(series);

        return barChart;
    }

    /**
     * Génère un graphique en ligne à partir des valeurs x et y fournies.
     * @param xValues les valeurs de l'axe x
     * @param yValues les valeurs de l'axe y
     * @param xLabel le label de l'axe x
     * @param yLabel le label de l'axe y
     * @return le graphique en ligne généré
     */
    public static LineChart<Number, Number> generateLineChart(List<Double> xValues,
            List<Double> yValues, String xLabel, String yLabel) {
                
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);

        // Set tick unit dynamically
        setDynamicTickUnit(xAxis, xValues);
        setDynamicTickUnit(yAxis, yValues);

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLegendVisible(false);
        lineChart.setCreateSymbols(false); // Désactiver les symboles de données

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Line Chart");

        // Créer une liste de tous les points
        List<XYChart.Data<Number, Number>> dataList = new ArrayList<>();
        for (int i = 0; i < xValues.size(); i++) {
            dataList.add(new XYChart.Data<>(xValues.get(i), yValues.get(i)));
        }

        // Ajouter tous les points en une seule opération
        Platform.runLater(() -> series.getData().addAll(dataList));

        lineChart.getData().add(series);

        return lineChart;
    }

    /**
     * Calcule la taille de l'intervalle dynamique pour un graphique en barres.
     * @param valueRange la plage de valeurs
     * @return la taille de l'intervalle dynamique
     */
    private static double calculateDynamicIntervalSize(double valueRange) {
        int desiredIntervals = 10;
        return valueRange / desiredIntervals;
    }

    /**
     * Trouve l'intervalle dans lequel se trouve une valeur donnée.
     * @param xValue la valeur à chercher
     * @param intervalSize la taille de l'intervalle
     * @return l'intervalle dans lequel se trouve la valeur
     */
    private static String findInterval(double xValue, double intervalSize) {
        int intervalIndex = (int) (xValue / intervalSize);
        return "[" + Math.round((intervalIndex * intervalSize)) + ", "
                + Math.round(((intervalIndex + 1) * intervalSize)) + "]";
    }

    /**
     * Définit la taille de l'unité de graduation de l'axe dynamiquement.
     * @param axis l'axe pour lequel définir la taille de l'unité de graduation
     * @param values les valeurs de l'axe
     */
    private static void setDynamicTickUnit(NumberAxis axis, List<Double> values) {
        double minValue = Collections.min(values);
        double maxValue = Collections.max(values);
        double valueRange = maxValue - minValue;

        double tickUnit = calculateOptimalTickUnit(valueRange);
        axis.setLowerBound(minValue);
        axis.setUpperBound(maxValue);
        axis.setTickUnit(tickUnit);
    }

    /**
     * Calcule la taille optimale de l'unité de graduation pour un axe.
     * @param valueRange la plage de valeurs
     * @return la taille optimale de l'unité
     */
    private static double calculateOptimalTickUnit(double valueRange) {
        double[] tickUnitOptions = { 0.1, 0.5, 1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000,
                50000, 100000 };
        for (double tickUnit : tickUnitOptions) {
            if (valueRange / tickUnit <= 10) {
                return tickUnit;
            }
        }
        return tickUnitOptions[tickUnitOptions.length - 1];
    }

    /**
     * Calculate the mean of a list of values.
     * @param values the list of values
     * @return the mean of the values
     */
    public static double calculateMean(List<Double> values) {
        double sum = 0;
        for (double value : values) {
            sum += value;
        }
        return sum / values.size();
    }

    /**
     * Calculate the variance of a list of values.
     * @param values the list of values
     * @param mean the mean of the values
     * @return the variance of the values
     */
    public static double calculateVariance(List<Double> values, double mean) {
        double sum = 0;
        for (double value : values) {
            sum += Math.pow(value - mean, 2);
        }
        return sum / values.size();
    }
}
