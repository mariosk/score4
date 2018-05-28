package com.wgsdg.score4;

import com.wgsdg.score4.Score4Constants.Player;
import com.wgsdg.score4.Score4Constants.Score4MoveType;

public class Score4Utils {

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

}
