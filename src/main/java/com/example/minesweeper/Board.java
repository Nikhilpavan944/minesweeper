package com.example.minesweeper;

import java.util.Random;
import java.util.logging.Logger;

public class Board {

    private static final char MINE = '*';
    private static final char COVERED = '_';
    private static final char EMPTY = '0';
    private static final int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};

    private char[][] board;
    private boolean[][] revealed;
    private int size;
    private int mines;

    /**
     * Initializes the board with the given size and number of mines.
     * @param size the size of the board (size x size)
     * @param mines the number of mines to place on the board
     */
    public void initialize(int size, int mines) {
        this.size = size;
        this.mines = mines;
        this.board = new char[size][size];
        this.revealed = new boolean[size][size];
        initializeBoard();
    }

    /**
     * Sets up the board by placing mines and calculating adjacent mine counts.
     */
    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = COVERED;
            }
        }
        placeMines();
        calculateNumbers();
    }

    /**
     * Randomly places mines on the board.
     */
    private void placeMines() {
        Random random = new Random();
        int placedMines = 0;
        while (placedMines < mines) {
            int x = random.nextInt(size);
            int y = random.nextInt(size);
            if (board[x][y] != MINE) {
                board[x][y] = MINE;
                placedMines++;
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each cell on the board.
     */
    void calculateNumbers() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == MINE) continue;
                int mineCount = 0;
                for (int k = 0; k < 8; k++) {
                    int nx = i + DX[k];
                    int ny = j + DY[k];
                    if (nx >= 0 && nx < size && ny >= 0 && ny < size && board[nx][ny] == MINE) {
                        mineCount++;
                    }
                }
                board[i][j] = (char) ('0' + mineCount);
            }
        }
    }

    /**
     * Reveals the cell at the given coordinates. If the cell has no adjacent mines, recursively reveals adjacent cells.
     * @param x the row index
     * @param y the column index
     */
    public void reveal(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size || revealed[x][y]) return;
        revealed[x][y] = true;
        if (board[x][y] == EMPTY) {
            for (int k = 0; k < 8; k++) {
                reveal(x + DX[k], y + DY[k]);
            }
        }
    }

    /**
     * Checks if the cell at the given coordinates contains a mine.
     * @param x the row index
     * @param y the column index
     * @return true if the cell contains a mine, false otherwise
     */
    public boolean isMine(int x, int y) {
        return board[x][y] == MINE;
    }

    /**
     * Checks if the player has won the game by revealing all non-mine cells.
     * @return true if the player has won, false otherwise
     */
    public boolean isWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != MINE && !revealed[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Prints the current state of the board to the console.
     */
    public void print() {
        System.out.print("  ");
        for (int i = 1; i <= size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < size; j++) {
                if (revealed[i][j]) {
                    System.out.print(board[i][j] + " ");
                } else {
                    System.out.print(COVERED + " ");
                }
            }
            System.out.println();
        }
    }

    // Getter methods
    public int getSize() {
        return size;
    }

    public int getMines() {
        return mines;
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean[][] getRevealed() {
        return revealed;
    }
}