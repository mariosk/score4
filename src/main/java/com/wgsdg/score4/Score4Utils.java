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
