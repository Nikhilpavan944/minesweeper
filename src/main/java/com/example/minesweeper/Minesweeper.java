package com.example.minesweeper;

import java.util.Scanner;
import java.util.logging.Logger;

public class Minesweeper {

    private static final Logger logger = Logger.getLogger(Minesweeper.class.getName());
    private final Board board;
    private final Scanner scanner;

    public Minesweeper() {
        this.scanner = new Scanner(System.in);
        this.board = new Board();
    }

    /**
     * Starts the Minesweeper game.
     */
    public void start() {
        System.out.println("Welcome to Minesweeper!");

        System.out.print("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int size = scanner.nextInt();

        System.out.print("Enter the number of mines to place on the grid (maximum is 35% of the total squares): ");
        int mines = scanner.nextInt();

        board.initialize(size, mines);

        while (true) {
            board.print();
            System.out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.next();
            int x = input.charAt(0) - 'A';
            int y = Integer.parseInt(input.substring(1)) - 1;

            if (board.isMine(x, y)) {
                System.out.println("Oh no, you detonated a mine! Game over.");
                logger.info("Game over. Mine detonated at (" + x + ", " + y + ").");
                break;
            } else {
                board.reveal(x, y);
                System.out.println("This square contains " + board.getBoard()[x][y] + " adjacent mines.");
                if (board.isWon()) {
                    System.out.println("Congratulations, you have won the game!");
                    logger.info("Game won by revealing all non-mine cells.");
                    break;
                }
            }
        }
        scanner.close();
    }
}