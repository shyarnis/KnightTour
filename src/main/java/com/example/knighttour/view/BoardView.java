package com.example.knighttour.view;

import com.example.knighttour.model.ChessBoard;
import com.example.knighttour.model.KnightTour;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.List;
import java.util.Objects;

/**
 * View component responsible for rendering the chess board and knight's moves.
 */
public class BoardView {
    private static final int SQUARE_SIZE = 80;

    private final Canvas canvas;
    private final KnightTour knightTour;
    private final Image knightImage;

    // Chess board colors
// Chess.com-inspired board colors
    private static final Color LIGHT_SQUARE = Color.rgb(235, 236, 208); // Almost pastel green
    private static final Color DARK_SQUARE = Color.rgb(115, 149, 82);   // Chess.com dark green
    private static final Color BACKGROUND = Color.rgb(48, 46, 43);   // Soft background tone
    private static final Color ARROW_COLOR = Color.rgb(255, 140, 0);    // soft gold

    /**
     * Creates a new board view.
     *
     * @param knightTour The Knight's Tour model
     */
    public BoardView(KnightTour knightTour) {
        this.knightTour = knightTour;

        int boardSize = knightTour.getBoard().getBoardSize();
        int canvasSize = boardSize * SQUARE_SIZE;

        // Create a canvas with extra space for notation
        this.canvas = new Canvas(canvasSize + 40, canvasSize + 40);

        // Load knight image
        Image loadedImage = null;
        try {
            loadedImage = new Image(
                    Objects.requireNonNull(getClass().getResourceAsStream("/knight.png")),
                    SQUARE_SIZE, SQUARE_SIZE, true, true
            );
        } catch (Exception e) {
            System.err.println("Failed to load knight image: " + e.getMessage());
        }
        this.knightImage = loadedImage;

        // Draw the initial board state
        drawBoard();
    }

    /**
     * Gets the canvas component.
     *
     * @return The canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * Draws the chess board, notation, move history, and knight.
     */
    public void drawBoard() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        ChessBoard board = knightTour.getBoard();
        int boardSize = board.getBoardSize();

        // Clear the canvas
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Fill background for the entire canvas (including notation area)
        gc.setFill(BACKGROUND);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw the chess board squares
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                // Chess board colors - wooden appearance
                if ((row + col) % 2 == 0) {
                    gc.setFill(LIGHT_SQUARE);
                } else {
                    gc.setFill(DARK_SQUARE);
                }
                gc.fillRect(col * SQUARE_SIZE + 20, row * SQUARE_SIZE + 20, SQUARE_SIZE, SQUARE_SIZE);

                // Draw move numbers on visited squares
                int squareValue = board.getSquareValue(row, col);
                if (squareValue > 0) {
                    gc.setFill(Color.BLACK);
                    gc.setFont(new Font("Arial", 20));
                    gc.fillText(String.valueOf(squareValue),
                            col * SQUARE_SIZE + SQUARE_SIZE/2 - 8 + 20,
                            row * SQUARE_SIZE + SQUARE_SIZE/2 + 6 + 20);
                }
            }
        }

        // Draw row numbers and column letters OUTSIDE the board
        gc.setFill(Color.rgb(240, 234, 214));
        gc.setFont(new Font("Arial", 14));

        // Draw column letters (a-h) below the board
        for (int col = 0; col < boardSize; col++) {
            char letter = (char) ('a' + col);
            gc.fillText(String.valueOf(letter),
                    col * SQUARE_SIZE + SQUARE_SIZE/2 - 5 + 20,
                    boardSize * SQUARE_SIZE + 35);
        }

        // Draw row numbers (1-8) to the left of the board
        for (int row = 0; row < boardSize; row++) {
            // Chess notation has row 1 at the bottom, so we invert the order
            int rowNumber = boardSize - row;
            gc.fillText(String.valueOf(rowNumber),
                    5,
                    row * SQUARE_SIZE + SQUARE_SIZE/2 + 5 + 20);
        }

        // Draw move arrows
        drawMoveArrows(gc);

        // Draw knight at the current position
        drawKnight(gc);
    }

    /**
     * Draws the arrows showing the knight's movement path.
     *
     * @param gc The graphics context
     */
    private void drawMoveArrows(GraphicsContext gc) {
        List<int[]> moves = knightTour.getMoveHistory();

        gc.setStroke(ARROW_COLOR);
        gc.setLineWidth(3); // slightly thinner lines for better visuals

        for (int i = 0; i < moves.size() - 1; i++) {
            int[] start = moves.get(i);
            int[] end = moves.get(i + 1);

            double startX = start[0] * SQUARE_SIZE + SQUARE_SIZE / 2 + 20;
            double startY = start[1] * SQUARE_SIZE + SQUARE_SIZE / 2 + 20;
            double endX = end[0] * SQUARE_SIZE + SQUARE_SIZE / 2 + 20;
            double endY = end[1] * SQUARE_SIZE + SQUARE_SIZE / 2 + 20;

            gc.strokeLine(startX, startY, endX, endY);

            // Arrowhead properties
            double angle = Math.atan2(endY - startY, endX - startX);
            int arrowSize = 10; // smaller arrow
            double sharpness = Math.PI / 10; // narrower angle for sharper look

            gc.strokeLine(endX, endY,
                    endX - arrowSize * Math.cos(angle - sharpness),
                    endY - arrowSize * Math.sin(angle - sharpness));
            gc.strokeLine(endX, endY,
                    endX - arrowSize * Math.cos(angle + sharpness),
                    endY - arrowSize * Math.sin(angle + sharpness));
        }
    }

    /**
     * Draws the knight at its current position.
     *
     * @param gc The graphics context
     */
    private void drawKnight(GraphicsContext gc) {
        int currentX = knightTour.getCurrentX();
        int currentY = knightTour.getCurrentY();

        if (knightImage != null) {
            gc.drawImage(knightImage,
                    currentX * SQUARE_SIZE + (SQUARE_SIZE - knightImage.getWidth()) / 2 + 20,
                    currentY * SQUARE_SIZE + (SQUARE_SIZE - knightImage.getHeight()) / 2 + 20);
        } else {
            // Draw a placeholder if image is not available
            gc.setFill(Color.BLACK);
            gc.fillOval(
                    currentX * SQUARE_SIZE + SQUARE_SIZE * 0.25 + 20,
                    currentY * SQUARE_SIZE + SQUARE_SIZE * 0.25 + 20,
                    SQUARE_SIZE * 0.5, SQUARE_SIZE * 0.5);
        }
    }
}