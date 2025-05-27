package practice;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;

public class ChartToggle extends Application {

    @Override
    public void start(Stage myStage) {

        // Set up chart, axes, title
        NumberAxis xAxis = new NumberAxis("Data Set Size (# of Integer Elements in Array)", 0, 60000, 10000);
        NumberAxis yAxis = new NumberAxis("Execution Time (ms)", 0, 1000, 100);

        LineChart<Number, Number> algorithmChart = new LineChart<>(xAxis, yAxis);
        algorithmChart.setTitle("Sorting Algorithms: Size of Data Set vs Execution Time");

        // Create Bubble Sort series
        XYChart.Series<Number, Number> bubbleSortSeries = new XYChart.Series<>();
        bubbleSortSeries.setName("Bubble Sort");
        bubbleSortSeries.getData().add(new XYChart.Data<>(2, 0.0002));
        bubbleSortSeries.getData().add(new XYChart.Data<>(4, 0.0003));
        bubbleSortSeries.getData().add(new XYChart.Data<>(8, 0.001));
        bubbleSortSeries.getData().add(new XYChart.Data<>(16, 0.004));
        bubbleSortSeries.getData().add(new XYChart.Data<>(32, 0.0145));
        bubbleSortSeries.getData().add(new XYChart.Data<>(64, 0.0685));
        bubbleSortSeries.getData().add(new XYChart.Data<>(128, 0.0851));
        bubbleSortSeries.getData().add(new XYChart.Data<>(256, 0.1265));
        bubbleSortSeries.getData().add(new XYChart.Data<>(512, 0.5212));
        bubbleSortSeries.getData().add(new XYChart.Data<>(1024, 2.431));
        bubbleSortSeries.getData().add(new XYChart.Data<>(2048, 2.7732));
        bubbleSortSeries.getData().add(new XYChart.Data<>(4096, 10.9409));
        bubbleSortSeries.getData().add(new XYChart.Data<>(8192, 45.4111));
        bubbleSortSeries.getData().add(new XYChart.Data<>(16384, 173.5664));
        bubbleSortSeries.getData().add(new XYChart.Data<>(32768, 709.5775));
        bubbleSortSeries.getData().add(new XYChart.Data<>(65536, 2905.6668));

        // Create Selection Sort series
        XYChart.Series<Number, Number> selectionSortSeries = new XYChart.Series<>();
        selectionSortSeries.setName("Selection Sort");
        selectionSortSeries.getData().add(new XYChart.Data<>(2, 0.0002));
        selectionSortSeries.getData().add(new XYChart.Data<>(4, 0.0004));
        selectionSortSeries.getData().add(new XYChart.Data<>(8, 0.0007));
        selectionSortSeries.getData().add(new XYChart.Data<>(16, 0.002));
        selectionSortSeries.getData().add(new XYChart.Data<>(32, 0.0061));
        selectionSortSeries.getData().add(new XYChart.Data<>(64, 0.0212));
        selectionSortSeries.getData().add(new XYChart.Data<>(128, 0.056));
        selectionSortSeries.getData().add(new XYChart.Data<>(256, 0.0667));
        selectionSortSeries.getData().add(new XYChart.Data<>(512, 0.0819));
        selectionSortSeries.getData().add(new XYChart.Data<>(1024, 0.2035));
        selectionSortSeries.getData().add(new XYChart.Data<>(2048, 0.7156));
        selectionSortSeries.getData().add(new XYChart.Data<>(4096, 2.7428));
        selectionSortSeries.getData().add(new XYChart.Data<>(8192, 10.5941));
        selectionSortSeries.getData().add(new XYChart.Data<>(16384, 41.9272));
        selectionSortSeries.getData().add(new XYChart.Data<>(32768, 165.6675));
        selectionSortSeries.getData().add(new XYChart.Data<>(65536, 661.944));

        // Add initial data to the chart
        algorithmChart.getData().add(bubbleSortSeries);
        algorithmChart.getData().add(selectionSortSeries);

        // Create checkboxes to toggle series visibility
        CheckBox bubbleSortCheckbox = new CheckBox("Bubble Sort");
        bubbleSortCheckbox.setSelected(true); // Initially visible
        bubbleSortCheckbox.setOnAction(_ -> {
            if (bubbleSortCheckbox.isSelected() && !algorithmChart.getData().contains(bubbleSortSeries)) {
                algorithmChart.getData().add(bubbleSortSeries);
            } else {
                algorithmChart.getData().remove(bubbleSortSeries);
            }
        });

        CheckBox selectionSortCheckbox = new CheckBox("Selection Sort");
        selectionSortCheckbox.setSelected(true); // Initially visible
        selectionSortCheckbox.setOnAction(_ -> {
            if (selectionSortCheckbox.isSelected() && !algorithmChart.getData().contains(selectionSortSeries)) {
                algorithmChart.getData().add(selectionSortSeries);
            } else {
                algorithmChart.getData().remove(selectionSortSeries);
            }
        });

        // Add checkboxes to a horizontal layout
        HBox checkboxLayout = new HBox(10, 
            new Label("Toggle Series:"),
            bubbleSortCheckbox, 
            selectionSortCheckbox);
        checkboxLayout.setStyle("-fx-padding: 10; -fx-background-color: lightgray;");

        // Use a BorderPane to combine the chart and checkboxes
        BorderPane layout = new BorderPane();
        layout.setCenter(algorithmChart);
        layout.setBottom(checkboxLayout); // Add checkboxes at the bottom

        // Create scene and add to stage
        Scene scene1 = new Scene(layout, 800, 600);

        myStage.setScene(scene1);
        myStage.setTitle("Chart Viewer");
        myStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}