package com.example.knighttour.view;

import com.example.knighttour.controller.KnightTourController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * Main UI component for the Knight's Tour application.
 * This class manages the layout and UI components.
 */
public class KnightTourUI {
    private static final int BOARD_SIZE = 8;

    private final BorderPane root;
    private final KnightTourController controller;
    private final BoardView boardView;

    /**
     * Creates a new Knight's Tour UI.
     */
    public KnightTourUI() {
        root = new BorderPane();

        // Create UI components
        Label statusLabel = new Label();
        statusLabel.setFont(new Font("Arial", 14));

        TextArea moveHistoryArea = new TextArea();
        moveHistoryArea.setPrefHeight(100);
        moveHistoryArea.setEditable(false);

        // Create the controller (which also initializes the model)
        controller = new KnightTourController(
                BOARD_SIZE,
                statusLabel,
                moveHistoryArea,
                this::updateBoardView  // Pass reference to method that updates the board view
        );

        // Create the board view
        boardView = new BoardView(controller.getKnightTour());

        // Create control buttons
        Button nextMoveButton = createNextMoveButton();
        Button resetButton = createResetButton();

        // Create layout
        HBox buttonControls = new HBox(10, statusLabel, nextMoveButton, resetButton);
        buttonControls.setPadding(new Insets(10));

        VBox controls = new VBox(10, moveHistoryArea, buttonControls);
        controls.setPadding(new Insets(10));

        // Assemble the UI
        root.setCenter(boardView.getCanvas());
        root.setBottom(controls);
    }

    /**
     * Creates the "Next Move" button with its action handler.
     *
     * @return The configured button
     */
    private Button createNextMoveButton() {
        Button button = new Button("Next Move");
        button.setOnAction(e -> controller.makeNextMove());
        return button;
    }

    /**
     * Creates the "Reset" button with its action handler.
     *
     * @return The configured button
     */
    private Button createResetButton() {
        Button button = new Button("Reset");
        button.setOnAction(e -> controller.reset());
        return button;
    }

    /**
     * Updates the board view.
     * This method is passed to the controller as a callback.
     */
    private void updateBoardView() {
        boardView.drawBoard();
    }

    /**
     * Gets the root pane of the UI.
     *
     * @return The root pane
     */
    public BorderPane getRoot() {
        return root;
    }
}