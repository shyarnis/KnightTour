package com.example.knighttour.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import java.util.Optional;

/**
 * Dialog to get the initial position of the knight from the user.
 */
public class InitialPositionDialog extends Parent {

    /**
     * Shows a dialog that prompts the user for the knight's initial position
     *
     * @param boardSize The size of the chess board
     * @return Optional containing the row and column if user confirmed, empty otherwise
     */
    public static Optional<Pair<Integer, Integer>> showDialog(int boardSize) {
        // Create the custom dialog
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Knight's Initial Position");
        dialog.setHeaderText("Please enter the starting position for the knight");

        // Set the button types
        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        // Create the row and column labels and spinners
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // Spinner for rows - using 1-indexed positions
        Spinner<Integer> rowSpinner = new Spinner<>(1, boardSize, 1);
        rowSpinner.setEditable(true);
        rowSpinner.setPrefWidth(100);

        // Spinner for columns - using 1-indexed positions
        Spinner<Integer> colSpinner = new Spinner<>(1, boardSize, 1);
        colSpinner.setEditable(true);
        colSpinner.setPrefWidth(100);

        grid.add(new Label("Row [1-" + (boardSize) + "] : "), 0, 0);
        grid.add(rowSpinner, 1, 0);
        grid.add(new Label("Column [1-" + (boardSize) + "] : "), 0, 1);
        grid.add(colSpinner, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // Set up event handler for the CANCEL button
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setOnAction(event -> System.exit(1));

        // Convert the result to a row-column pair when the confirm button is clicked
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return new Pair<>(rowSpinner.getValue(), colSpinner.getValue());
            }
            return null;
        });

        // Show the dialog and return the result
        return dialog.showAndWait();
    }
}
