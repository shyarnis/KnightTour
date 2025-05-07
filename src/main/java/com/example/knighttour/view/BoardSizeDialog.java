package com.example.knighttour.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import java.util.Optional;

/**
 * Dialog to get the board size from the user.
 */
public class BoardSizeDialog {

    private static final int MIN_BOARD_SIZE = 2;
    private static final int MAX_BOARD_SIZE = 14;

    /**
     * Shows a dialog that prompts the user for the chess board size
     *
     * @return Optional containing the board size if user confirmed, empty otherwise
     */
    public static Optional<Integer> showDialog() {
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Chess Board Size");
        dialog.setHeaderText("Enter the size of the chess board");

        ButtonType confirmButtonType = new ButtonType("Confirm", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        Spinner<Integer> sizeSpinner = new Spinner<>(MIN_BOARD_SIZE, MAX_BOARD_SIZE, 8);
        sizeSpinner.setEditable(true);
        sizeSpinner.setPrefWidth(100);

        grid.add(new Label("Board Size [" + MIN_BOARD_SIZE + "-" + MAX_BOARD_SIZE + "] : "), 0, 0);
        grid.add(sizeSpinner, 1, 0);

        dialog.getDialogPane().setContent(grid);

        // Set up event handler for the CANCEL button
        Button cancelButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setOnAction(event -> System.exit(1));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                return sizeSpinner.getValue();
            }
            return null;
        });

        return dialog.showAndWait();
    }
}