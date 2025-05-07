package com.example.knighttour.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Knight's Tour logic and algorithm.
 * This class implements Warnsdorff's algorithm for solving the Knight's Tour problem.
 */
public class KnightTour {
    // Possible knight moves in (x, y) offsets
    private static final int[] X_MOVES = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] Y_MOVES = {1, 2, 2, 1, -1, -2, -2, -1};

    private final ChessBoard board;
    private final List<int[]> moveHistory;
    private int currentX;
    private int currentY;
    private int moveCount;

    /**
     * Creates a new Knight's Tour with the specified board size.
     *
     * @param boardSize The size of the chess board (usually 8 for standard chess board)
     */
    public KnightTour(int boardSize) {
        this.board = new ChessBoard(boardSize);
        this.moveHistory = new ArrayList<>();

        // Initialize starting position (top-left corner)
        this.currentX = 0;
        this.currentY = 0;
        this.moveCount = 1;

        // Mark the starting position
        board.setSquareValue(currentY, currentX, moveCount);
        moveHistory.add(new int[]{currentX, currentY});
    }

    /**
     * Calculates and performs the next move using Warnsdorff's algorithm.
     *
     * @return true if a move was made, false if no valid moves exist
     */
    public boolean makeNextMove() {
        // Find the next move using Warnsdorff's algorithm
        int bestMove = -1;
        int minDegree = 9; // More than maximum possible degree (8)

        for (int i = 0; i < 8; i++) {
            int nextX = currentX + X_MOVES[i];
            int nextY = currentY + Y_MOVES[i];

            if (isValidMove(nextX, nextY)) {
                int degree = countAccessibleSquares(nextX, nextY);
                if (degree < minDegree) {
                    minDegree = degree;
                    bestMove = i;
                }
            }
        }

        if (bestMove != -1) {
            // Make the move
            currentX += X_MOVES[bestMove];
            currentY += Y_MOVES[bestMove];
            moveCount++;
            board.setSquareValue(currentY, currentX, moveCount);
            moveHistory.add(new int[]{currentX, currentY});
            return true;
        }

        return false;
    }

    /**
     * Counts the number of valid moves possible from a given position.
     * Used by Warnsdorff's algorithm to select the square with the fewest onward moves.
     *
     * @param x The x-coordinate to check
     * @param y The y-coordinate to check
     * @return The number of valid moves from the given position
     */
    private int countAccessibleSquares(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextX = x + X_MOVES[i];
            int nextY = y + Y_MOVES[i];
            if (isValidMove(nextX, nextY)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Checks if a move to the specified coordinates is valid.
     * A move is valid if it's within the board boundaries and the square hasn't been visited.
     *
     * @param x The x-coordinate to check
     * @param y The y-coordinate to check
     * @return true if the move is valid, false otherwise
     */
    private boolean isValidMove(int x, int y) {
        return board.isWithinBounds(x, y) && board.getSquareValue(y, x) == 0;
    }

    /**
     * Converts array indices to chess notation (e.g., 0,0 -> a8, 7,7 -> h1).
     *
     * @param x The x-coordinate (column)
     * @param y The y-coordinate (row)
     * @return The chess notation as a string (e.g., "e4")
     */
    public String getChessNotation(int x, int y) {
        char file = (char) ('a' + x);
        int rank = board.getBoardSize() - y;
        return String.valueOf(file) + rank;
    }

    /**
     * Resets the knight's tour to the initial state.
     */
    public void reset() {
        board.reset();
        moveHistory.clear();

        currentX = 0;
        currentY = 0;
        moveCount = 1;

        board.setSquareValue(currentY, currentX, moveCount);
        moveHistory.add(new int[]{currentX, currentY});
    }

    /**
     * Sets the initial position of the knight and resets the tour.
     *
     * @param x The x-coordinate (column) of the initial position
     * @param y The y-coordinate (row) of the initial position
     * @throws IllegalArgumentException if the position is invalid
     */
    public void setInitialPosition(int x, int y) {
        if (!board.isWithinBounds(x, y)) {
            throw new IllegalArgumentException("Position is outside board boundaries");
        }

        // Reset the board and move history
        board.reset();
        moveHistory.clear();

        // Set the new initial position
        currentX = x;
        currentY = y;
        moveCount = 1;

        // Mark the starting position
        board.setSquareValue(currentY, currentX, moveCount);
        moveHistory.add(new int[]{currentX, currentY});
    }

    // Getters

    /**
     * Gets the chess board.
     *
     * @return The chess board
     */
    public ChessBoard getBoard() {
        return board;
    }

    /**
     * Gets the move history.
     *
     * @return A list of moves as int arrays [x, y]
     */
    public List<int[]> getMoveHistory() {
        return moveHistory;
    }

    /**
     * Gets the current x-coordinate.
     *
     * @return The current x-coordinate
     */
    public int getCurrentX() {
        return currentX;
    }

    /**
     * Gets the current y-coordinate.
     *
     * @return The current y-coordinate
     */
    public int getCurrentY() {
        return currentY;
    }

    /**
     * Gets the current move count.
     *
     * @return The current move count
     */
    public int getMoveCount() {
        return moveCount;
    }
}