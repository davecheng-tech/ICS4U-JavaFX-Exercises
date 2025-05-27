package practice;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application for visualizing sorting algorithm performance using a LineChart.
 * Data is read from a CSV file and displayed dynamically using checkboxes to toggle series.
 */
public class ChartFileRead extends Application {

    @Override
    public void start(Stage myStage) {
        // Set up chart, axes, and title
        NumberAxis xAxis = new NumberAxis("Data Set Size (# of Integer Elements in Array)", 0, 70000, 10000);
        NumberAxis yAxis = new NumberAxis("Execution Time (ms)", 0, 3000, 500);

        LineChart<Number, Number> algorithmChart = new LineChart<>(xAxis, yAxis);
        algorithmChart.setTitle("Sorting Algorithms: Size of Data Set vs Execution Time");

        // Process data
        DatasetProcessor processor = new DatasetProcessor("src/practice/sort_results.csv");
        List<AlgorithmSeries> seriesList = processor.getSeriesList();

        // Add series to the chart and set up checkboxes
        HBox checkboxLayout = new HBox(10, new Label("Toggle Series:"));
        checkboxLayout.setStyle("-fx-padding: 10; -fx-background-color: lightgray;");

        for (AlgorithmSeries series : seriesList) {
            XYChart.Series<Number, Number> chartSeries = series.getChartSeries();
            algorithmChart.getData().add(chartSeries);

            CheckBox checkbox = new CheckBox(series.getAlgorithmName());
            checkbox.setSelected(true);

            checkbox.setOnAction(_ -> {
                if (checkbox.isSelected()) {
                    algorithmChart.getData().add(chartSeries);
                } else {
                    algorithmChart.getData().remove(chartSeries);
                }
            });

            checkboxLayout.getChildren().add(checkbox);
        }

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

    /**
     * Entry point for the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * Represents a single record of data in the dataset.
 * Each record contains the dataset size and execution time for an algorithm.
 */
class DataRecord {
    private int dataSize;
    private double executionTime;

    /**
     * Constructs a new DataRecord.
     *
     * @param dataSize      The size of the dataset (x-axis value).
     * @param executionTime The execution time for the algorithm (y-axis value).
     */
    public DataRecord(int dataSize, double executionTime) {
        this.dataSize = dataSize;
        this.executionTime = executionTime;
    }

    /**
     * Gets the size of the dataset.
     *
     * @return The dataset size.
     */
    public int getDataSize() {
        return dataSize;
    }

    /**
     * Gets the execution time for the algorithm.
     *
     * @return The execution time.
     */
    public double getExecutionTime() {
        return executionTime;
    }
}

/**
 * Represents a collection of data records for a specific algorithm.
 */
class AlgorithmSeries {
    private String algorithmName;
    private List<DataRecord> records;
    private XYChart.Series<Number, Number> displaySeries;

    /**
     * Constructs a new AlgorithmSeries.
     *
     * @param algorithmName The name of the algorithm.
     * @param records       The list of data records for this algorithm.
     */
    public AlgorithmSeries(String algorithmName, List<DataRecord> records) {
        this.algorithmName = algorithmName;
        this.records = records;
    }

    /**
     * Gets the name of the algorithm.
     *
     * @return The algorithm name.
     */
    public String getAlgorithmName() {
        return algorithmName;
    }

    /**
     * Gets the list of data records for this algorithm.
     *
     * @return The list of data records.
     */
    public List<DataRecord> getRecords() {
        return records;
    }

    /**
     * Gets the chart series, generating it if necessary. Caches the series to ensure consistent reuse.
     *
     * @return An XYChart.Series object representing the data.
     */
    public XYChart.Series<Number, Number> getChartSeries() {
        if (displaySeries == null) {
            displaySeries = new XYChart.Series<>();
            displaySeries.setName(algorithmName);
            for (DataRecord record : records) {
                displaySeries.getData().add(new XYChart.Data<>(record.getDataSize(), record.getExecutionTime()));
            }
        }
        return displaySeries;
    }
}

/**
 * Processes the dataset from a CSV file and organizes it into AlgorithmSeries objects.
 */
class DatasetProcessor {
    private List<AlgorithmSeries> seriesList = new ArrayList<>();

    /**
     * Constructs a DatasetProcessor and reads data from a CSV file.
     *
     * @param filePath The path to the CSV file.
     */
    public DatasetProcessor(String filePath) {
        readDataFromCSV(filePath);
    }

    /**
     * Reads data from the given CSV file.
     *
     * @param filePath The path to the CSV file.
     */
    private void readDataFromCSV(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Read header line
            if (line == null) {
                throw new IOException("CSV file is empty.");
            }

            // Extract algorithm names
            String[] headers = line.split(",");
            for (int i = 1; i < headers.length; i++) { // Skip first column
                seriesList.add(new AlgorithmSeries(headers[i], new ArrayList<>()));
            }

            // Read data rows
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int dataSize = Integer.parseInt(values[0]); // First column is Data Set Size

                for (int i = 1; i < values.length; i++) {
                    double executionTime = Double.parseDouble(values[i]);
                    seriesList.get(i - 1).getRecords().add(new DataRecord(dataSize, executionTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading the CSV file.");
        }
    }

    /**
     * Gets the list of AlgorithmSeries objects.
     *
     * @return A list of AlgorithmSeries objects.
     */
    public List<AlgorithmSeries> getSeriesList() {
        return seriesList;
    }
}