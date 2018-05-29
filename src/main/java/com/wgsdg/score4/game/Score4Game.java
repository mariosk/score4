package com.wgsdg.score4.game;

import java.util.Arrays;

import com.wgsdg.score4.Score4Constants;
import com.wgsdg.score4.Score4Constants.Player;
import com.wgsdg.score4.Score4Constants.Score4MoveType;
import com.wgsdg.score4.Score4Constants.ScoreBoardType;
import com.wgsdg.score4.Score4Utils;
import com.wgsdg.score4.model.Score4Container;
import com.wgsdg.score4.model.Score4IO;

/*
 * Assumption: The next move is always the move that the game executor is going to do. 
 * So if we have even size of columns array, the last move (even) is the opponent's one, meaning the odd moves are ours.
 * Otherwise the the even moves are ours.  
 */
public class Score4Game {
	// private static final Logger logger = LoggerFactory.getLogger(Score4Game.class);
	private static Player[][] gameBoard = new Player[Score4Constants.rowMax][Score4Constants.colMax];
	private static Player[][] diagonalWinner = new Player[Score4Constants.rowMax][Score4Constants.colMax];
	private static int pawns = 1;
	private static Player previousPawn = Player.EMPTY;

	public Score4Game() {
		// initialization of the gameBoard with -1
		for (int i = 0; i < Score4Constants.rowMax; i++) {
			Arrays.fill(gameBoard[i], Player.EMPTY);
		}

		for (int i = 0; i < Score4Constants.rowMax; i++) {
			Arrays.fill(diagonalWinner[i], Player.EMPTY);
		}
	}

	private Score4MoveType storeToBoard(int col, Player player) {
		for (int row = Score4Constants.rowMax - 1; row >= 0; row--) {
			if (gameBoard[row][col] == Player.EMPTY) {
				gameBoard[row][col] = player;
				return Score4MoveType.CORRECT;
			}
		}
		return (player == Score4Constants.Player.ME ? Score4MoveType.ERROR_ME : Score4MoveType.ERROR_OPPONENT);
	}

	private Player processPawns(int row, int col) {
		Player currentPawn = gameBoard[row][col];
		if (currentPawn != Player.EMPTY && currentPawn == previousPawn) {
			pawns++;
			if (pawns >= 4) {
				// cannot find another winner in this column.
				return currentPawn;
			}
		} else {
			pawns = 1;
		}
		previousPawn = currentPawn;
		return Player.EMPTY;
	}

	public Player[] checkColumnsForWinner(int col, Player[] colWinner) {
		pawns = 1;
		Player currentPawn = Player.EMPTY;
		for (int row = Score4Constants.rowMax - 1; row > 0; row--) {
			currentPawn = processPawns(row, col);
			if (currentPawn != Player.EMPTY) {
				break;
			}
		}
		colWinner[col] = currentPawn;
		return colWinner;
	}

	public Player[] checkRowsForWinner(int row, Player[] rowWinner) {
		pawns = 1;
		Player currentPawn = Player.EMPTY;
		for (int col = 0; col < Score4Constants.colMax; col++) {
			currentPawn = processPawns(row, col);
			if (currentPawn != Player.EMPTY) {
				break;
			}
		}
		rowWinner[row] = currentPawn;
		return rowWinner;
	}

	public Player[] checkForWinner(ScoreBoardType type) {
		Player[] winer = null;
		switch (type) {
			case ROW:
			winer = new Player[Score4Constants.rowMax];
			for (int row = 0; row < Score4Constants.rowMax; row++) {
				winer = checkRowsForWinner(row, winer);
			}
			break;

			case COLUMN:
			winer = new Player[Score4Constants.colMax];
			for (int col = 0; col < Score4Constants.colMax; col++) {
				winer = checkColumnsForWinner(col, winer);
			}
			break;

			case DIAGONAL:
			break;
		}
		return winer;
	}

	public Score4Container<Score4MoveType, Player[][]> checkForValidMove(int[] moves) {
		// Checking the size of the moves array first
		if (moves.length > Score4Constants.colMax * Score4Constants.rowMax || moves.length == 0) {
			return new Score4Container<Score4MoveType, Player[][]>(Score4MoveType.ERROR_SIZE, null);
		}

		boolean evenArraySize = (moves.length % 2 == 0);
		for (int i = 0; i < moves.length; i++) {
			int currentItem = moves[i] - 1;
			// Checking the value of each item - negative of > of colMax are not acceptable
			if (moves[i] > Score4Constants.colMax || moves[i] <= 0) {
				return new Score4Container<Score4MoveType, Player[][]>(
						Score4Utils.findErrorByPlayer(Score4Utils.findPlayerByIndex(evenArraySize, i)), null);
			}
			Score4MoveType moveType = storeToBoard(currentItem, Score4Utils.findPlayerByIndex(evenArraySize, i));
			if (!moveType.equals(Score4MoveType.CORRECT)) {
				return new Score4Container<Score4MoveType, Player[][]>(moveType, null);
			}
		}
		Score4Utils.printGameBoard(gameBoard);
		return new Score4Container<Score4MoveType, Player[][]>(Score4MoveType.CORRECT, gameBoard);
	}

	public Score4IO playNextMove(Score4IO request) {
		Score4IO response = null;
		return response;
	}
}
