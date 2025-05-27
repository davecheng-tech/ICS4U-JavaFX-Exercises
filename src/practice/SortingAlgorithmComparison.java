package practice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class SortingAlgorithmComparison extends Application {

    @Override
    public void start(Stage myStage) {
    
        // Set up chart, axes, title
        NumberAxis xAxis = new NumberAxis("Data Set Size (# of Integer Elements in Array)", 0, 60000, 10000);
        NumberAxis yAxis = new NumberAxis("Execution Time (ms)", 0, 1000, 100);

        LineChart<Number, Number> algorithmChart = new LineChart<>(xAxis, yAxis);

        algorithmChart.setTitle("Sorting Algorithms: Size of Data Set vs Execution Time");


        // Create bubble sort series
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.setName("Bubble Sort");
        series1.getData().add(new XYChart.Data<>(2, 0.0002));
        series1.getData().add(new XYChart.Data<>(4, 0.0003));
        series1.getData().add(new XYChart.Data<>(8, 0.001));
        series1.getData().add(new XYChart.Data<>(16, 0.004));
        series1.getData().add(new XYChart.Data<>(32, 0.0145));
        series1.getData().add(new XYChart.Data<>(64, 0.0685));
        series1.getData().add(new XYChart.Data<>(128, 0.0851));
        series1.getData().add(new XYChart.Data<>(256, 0.1265));
        series1.getData().add(new XYChart.Data<>(512, 0.5212));
        series1.getData().add(new XYChart.Data<>(1024, 2.431));
        series1.getData().add(new XYChart.Data<>(2048, 2.7732));
        series1.getData().add(new XYChart.Data<>(4096, 10.9409));
        series1.getData().add(new XYChart.Data<>(8192, 45.4111));
        series1.getData().add(new XYChart.Data<>(16384, 173.5664));
        series1.getData().add(new XYChart.Data<>(32768, 709.5775));
        series1.getData().add(new XYChart.Data<>(65536, 2905.6668));
        

        // Create selection sort series
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.setName("Selection Sort");
        series2.getData().add(new XYChart.Data<>(2, 0.0002));
        series2.getData().add(new XYChart.Data<>(4, 0.0004));
        series2.getData().add(new XYChart.Data<>(8, 0.0007));
        series2.getData().add(new XYChart.Data<>(16, 0.002));
        series2.getData().add(new XYChart.Data<>(32, 0.0061));
        series2.getData().add(new XYChart.Data<>(64, 0.0212));
        series2.getData().add(new XYChart.Data<>(128, 0.056));
        series2.getData().add(new XYChart.Data<>(256, 0.0667));
        series2.getData().add(new XYChart.Data<>(512, 0.0819));
        series2.getData().add(new XYChart.Data<>(1024, 0.2035));
        series2.getData().add(new XYChart.Data<>(2048, 0.7156));
        series2.getData().add(new XYChart.Data<>(4096, 2.7428));
        series2.getData().add(new XYChart.Data<>(8192, 10.5941));
        series2.getData().add(new XYChart.Data<>(16384, 41.9272));
        series2.getData().add(new XYChart.Data<>(32768, 165.6675));
        series2.getData().add(new XYChart.Data<>(65536, 661.944));


        // Add data to the chart
        algorithmChart.getData().add(series1);
        algorithmChart.getData().add(series2);

        // Create scene and add to stage
        Scene scene1 = new Scene(algorithmChart, 600, 400);

        myStage.setScene(scene1);
        myStage.setTitle("Chart Viewer");
        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}