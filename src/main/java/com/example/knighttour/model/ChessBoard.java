package com.example.knighttour.model;

/**
 * Represents a chess board for the Knight's Tour problem.
 * This class maintains the state of the board and provides operations to manipulate it.
 */
public class ChessBoard {
    private final int boardSize;
    private final int[][] board;

    /**
     * Creates a new chess board with the specified size.
     *
     * @param boardSize The size of the chess board (e.g., 8 for a standard 8x8 board)
     */
    public ChessBoard(int boardSize) {
        this.boardSize = boardSize;
        this.board = new int[boardSize][boardSize];

        // Initialize board with zeros (unvisited squares)
        reset();
    }

    /**
     * Gets the value of a square on the board.
     *
     * @param row The row index
     * @param col The column index
     * @return The value of the square (0 for unvisited, or move number)
     */
    public int getSquareValue(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets the value of a square on the board.
     *
     * @param row   The row index
     * @param col   The column index
     * @param value The value to set (typically the move number)
     */
    public void setSquareValue(int row, int col, int value) {
        board[row][col] = value;
    }

    /**
     * Checks if coordinates are within the bounds of the board.
     *
     * @param x     The x-coordinate (column) to check
     * @param y     The y-coordinate (row) to check
     * @return true if the coordinates are within bounds, false otherwise
     */
    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < boardSize && y >= 0 && y < boardSize;
    }

    /**
     * Resets the board to its initial state (all squares unvisited).
     */
    public void reset() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = 0;
            }
        }
    }

    /**
     * Gets the size of the board.
     *
     * @return The board size (e.g., 8 for an 8x8 board)
     */
    public int getBoardSize() {
        return boardSize;
    }
}