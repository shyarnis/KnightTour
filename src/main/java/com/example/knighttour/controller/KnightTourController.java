package com.example.knighttour.controller;

import com.example.knighttour.model.KnightTour;
import com.example.knighttour.util.SoundPlayer;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * Controller class for the Knight's Tour application.
 * This class manages the game state and coordinates between the model and view.
 */
public class KnightTourController {
    private final KnightTour knightTour;
    private final Label statusLabel;
    private final TextArea moveHistoryArea;
    private final Runnable boardUpdateCallback;

    /**
     * Creates a new Knight's Tour controller.
     *
     * @param boardSize           The size of the chess board
     * @param statusLabel         The label to display game status
     * @param moveHistoryArea     The text area to display move history
     * @param boardUpdateCallback A callback to trigger board redrawing
     */
    public KnightTourController(
            int boardSize,
            Label statusLabel,
            TextArea moveHistoryArea,
            Runnable boardUpdateCallback
    ) {
        this.knightTour = new KnightTour(boardSize);
        this.statusLabel = statusLabel;
        this.moveHistoryArea = moveHistoryArea;
        this.boardUpdateCallback = boardUpdateCallback;

        // Set initial status
        updateStatus();
        updateMoveHistory(true);
    }

    /**
     * Performs the next move in the Knight's Tour.
     */
    public void makeNextMove() {
        boolean moveMade = knightTour.makeNextMove();

        if (moveMade) {
            // play move sound
            SoundPlayer.playMoveSound();

            updateStatus();
            updateMoveHistory(false);
            boardUpdateCallback.run();
        } else {
            statusLabel.setText("No more valid moves! Tour ended at move " + knightTour.getMoveCount());
        }
    }

    /**
     * Updates the status label with the current move information.
     */
    private void updateStatus() {
        statusLabel.setText("Knight's Tour: Move " + knightTour.getMoveCount());
    }

    /**
     * Updates the move history text area.
     *
     * @param isReset Whether this is a reset (initial state) or a new move
     */
    private void updateMoveHistory(boolean isReset) {
        int moveCount = knightTour.getMoveCount();
        int currentX = knightTour.getCurrentX();
        int currentY = knightTour.getCurrentY();
        String position = knightTour.getChessNotation(currentX, currentY);

        if (isReset) {
            moveHistoryArea.setText("Move 1: Knight at " + position);
        } else {
            moveHistoryArea.appendText("\nMove " + moveCount + ": Knight moved to " + position);
            System.out.println("Move count: " + moveCount + ". Knight moved to : " + position);
        }
    }

    /**
     * Resets the Knight's Tour to its initial state.
     */
    public void reset() {
        knightTour.reset();
        updateStatus();
        updateMoveHistory(true);
        boardUpdateCallback.run();
    }

    /**
     * Gets the Knight's Tour model.
     *
     * @return The Knight's Tour model
     */
    public KnightTour getKnightTour() {
        return knightTour;
    }
}