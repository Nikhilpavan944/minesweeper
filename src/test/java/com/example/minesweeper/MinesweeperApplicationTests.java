package com.example.minesweeper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MinesweeperApplicationTests {

	@Mock
	private Board board;

	@BeforeEach
	void setUp() {
		board = new Board();
	}

	@Test
	void testInitializeGame() {
		board.initialize(4, 2);
		assertEquals(4, board.getSize());
		assertEquals(2, board.getMines());
		assertNotNull(board.getBoard());
		assertNotNull(board.getRevealed());
	}

	@Test
	void testPlaceMines() {
		board.initialize(4, 2);
		int mineCount = 0;
		char[][] boardArray = board.getBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (boardArray[i][j] == '*') {
					mineCount++;
				}
			}
		}
		assertEquals(2, mineCount);
	}

	@Test
	void testCalculateNumbers() {
		board.initialize(4, 2);
		board.calculateNumbers();
		char[][] boardArray = board.getBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (boardArray[i][j] != '*' && boardArray[i][j] != '_') {
					assertTrue(Character.isDigit(boardArray[i][j]));
				}
			}
		}
	}

	@Test
	void testReveal() {
		board.initialize(4, 2);
		board.reveal(0, 0);
		boolean[][] revealed = board.getRevealed();
		assertTrue(revealed[0][0]);
	}

	@Test
	void testIsMine() {
		board.initialize(4, 2);
		char[][] boardArray = board.getBoard();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (boardArray[i][j] == '*') {
					assertTrue(board.isMine(i, j));
				} else {
					assertFalse(board.isMine(i, j));
				}
			}
		}
	}

	@Test
	void testIsWon() {
		board.initialize(4, 2);
		// Simulate revealing all non-mine squares
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!board.isMine(i, j)) {
					board.reveal(i, j);
				}
			}
		}
		assertTrue(board.isWon());
	}

}
