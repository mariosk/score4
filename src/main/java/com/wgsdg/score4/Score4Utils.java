package com.wgsdg.score4;

import com.wgsdg.score4.Score4Constants.Player;
import com.wgsdg.score4.Score4Constants.Score4MoveType;

public class Score4Utils {

    public static Player findPlayerByIndex(int i) {
        return (i % 2 == 0 ? Player.ME : Player.OPPONENT);
        
    }

    public static Score4MoveType findErrorByPlayer(Player player) {
        return (player == Player.ME ? Score4MoveType.ERROR_ME : Score4MoveType.ERROR_OPPONENT);
    }

}
