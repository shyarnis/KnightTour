package com.example.knighttour;

import com.example.knighttour.util.SoundPlayer;
import com.example.knighttour.view.KnightTourUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Knight's Tour visualization.
 * This class initializes the JavaFX application and sets up the primary stage.
 */
public class KnightTourApp extends Application {

    @Override
    public void start(Stage stage) {
        // initialize sound resources
        SoundPlayer.initialize();

        // Create the main UI
        KnightTourUI knightTourUI = new KnightTourUI();
        Scene scene = new Scene(knightTourUI.getRoot());

        // Configure and display the stage
        stage.setTitle("Knight's Tour");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        launch(args);
    }
}