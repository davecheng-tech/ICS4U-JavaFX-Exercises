package practice;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * An example of a ChoiceBox with several options. The ChoiceBox control
 * displays a default or current selection, with an icon to click that expands
 * the list for a selection.
 *
 * @sampleName ChoiceBox
 * @preview preview.png
 * @see ChoiceBox
 * @embedded
 */
public class ChoiceBoxApp extends Application {

    public Parent createContent() {
        ChoiceBox cb = new ChoiceBox();
        cb.getItems().addAll("Horse", "Zebra", "Donkey");
        cb.getSelectionModel().selectFirst();
        return cb;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
}