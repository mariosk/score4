package com.wgsdg.score4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wgsdg.score4.Score4Constants.Player;
import com.wgsdg.score4.Score4Constants.Score4MoveType;

public class Score4Utils {

	private static final Logger logger = LoggerFactory.getLogger(Score4Utils.class);

	// Identifying the player index...If the size of the array is even, ME=even move, OPPONENT=odd move
	public static Player findPlayerByIndex(boolean evenArraySize, int i) {
		boolean evenPosition = (i % 2 == 0);
		if (evenArraySize) {
			return (evenPosition ? Player.ME : Player.OPPONENT);
		} else {
			return (evenPosition ? Player.OPPONENT : Player.ME);
		}
    }

    public static Score4MoveType findErrorByPlayer(Player player) {
        return (player == Player.ME ? Score4MoveType.ERROR_ME : Score4MoveType.ERROR_OPPONENT);
    }

	public static Score4MoveType findWinner(Player[] rowsWinners, Player[] colsWinners) {
		Score4MoveType rowsWinner = null;
		Score4MoveType colsWinner = null;
		if (!Score4Utils.isArrayEmpty(rowsWinners)) {
			rowsWinner = findWinner(rowsWinners);
		}
		if (!Score4Utils.isArrayEmpty(colsWinners)) {
			colsWinner = findWinner(colsWinners);
		}
		/*
		 *  The following code catches the cases:
		 *  1. rowsWinner = OPPONENT, colsWinner = OPPONENT => OPPONENT
		 *  2. rowsWinner = OPPONENT, colsWinner = ME => DRAW
		 *  3. rowsWinner = ME, colsWinner = OPPONENT => DRAW
		 *  4. rowsWinner = ME, colsWinner = ME => ME
		 *  5. rowsWinner = null, colsWinner = OPPONENT => OPPONENT
		 *  6. rowsWinner = OPPONENT, colsWinner = null => OPPONENT
		 *  7. rowsWinner = null, colsWinner = ME => ME
		 *  8. rowsWinner = ME, colsWinner = null => ME
		 */
		// 
		if (rowsWinner == colsWinner && rowsWinner != null) {
			return rowsWinner;
		} else if (rowsWinner != colsWinner && rowsWinner != null && colsWinner != null) {
			return Score4MoveType.DRAW;
		} else if (rowsWinner != colsWinner && rowsWinner == null && colsWinner != null) {
			return colsWinner;
		} else if (rowsWinner != colsWinner && rowsWinner != null && colsWinner == null) {
			return rowsWinner;
		} else {
			return Score4MoveType.DRAW;
		}
	}

	private static Score4MoveType winnerDecission(int opponentPawns, int mePawns) {
		if (opponentPawns == mePawns) {
			return Score4MoveType.DRAW;
		}
		if (opponentPawns > mePawns) {
			return Score4MoveType.LOST;
		}
		if (opponentPawns < mePawns) {
			return Score4MoveType.WON;
		}
		return null;
	}

	private static Score4MoveType findWinner(Player[] winners) {
		int countOpponent = 0;
		int countMe = 0;
		for (int i = 0; i < winners.length; i++) {
			if (winners[i] == Player.OPPONENT) {
				countOpponent++;
			} else if (winners[i] == Player.ME) {
				countMe++;
			}
		}
		return winnerDecission(countOpponent, countMe);
	}

	public static void printGameBoard(Player[][] board) {
		StringBuilder rowBuffer = new StringBuilder(); 
		for (int row = 0; row < Score4Constants.rowMax; row++) {
			for (int col = 0; col < Score4Constants.colMax; col++) {
				rowBuffer.append(board[row][col].getName());
				rowBuffer.append(" ");
			}
			logger.info("ROW: {}: {}", row + 1, rowBuffer);
			rowBuffer.delete(0, rowBuffer.length());
		}
	}

	public static boolean isArrayEmpty(Player[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != Player.EMPTY) {
				return false;
			}
		}
		return true;
	}
}
