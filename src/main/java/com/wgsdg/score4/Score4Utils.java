package com.wgsdg.score4;

import java.util.Arrays;

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

	// Add one more element in the moves array
	public static int[] addElement(int[] a, int e) {
	    a  = Arrays.copyOf(a, a.length + 1);
	    a[a.length - 1] = e;
	    return a;
	}

	// find error by Player type
    public static Score4MoveType findErrorByPlayer(Player player) {
        return (player == Player.ME ? Score4MoveType.ERROR_ME : Score4MoveType.ERROR_OPPONENT);
    }

    // decide which one is the the winner: OPPONENT or ME
	private static Score4MoveType winnerDecission(int opponentPawns, int mePawns) {
		if (opponentPawns == mePawns && opponentPawns > 0) {
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

	// find the winner in an single array of Player types
	public static Score4MoveType findWinner(Player[] winners) {
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

	// print out the score board
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

	// check whether the score board is empty
	public static boolean isGameBoardEmpty(Player[][] board) {
		int emptyRow = 0;
		for (int row = 0; row < Score4Constants.rowMax; row++) {
			if (isArrayEmpty(board[row])) {
				emptyRow++;
			}
		}
		return (emptyRow == Score4Constants.rowMax);
	}

	// check whether the array is empty or not
	public static boolean isArrayEmpty(Player[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != Player.EMPTY) {
				return false;
			}
		}
		return true;
	}
}
