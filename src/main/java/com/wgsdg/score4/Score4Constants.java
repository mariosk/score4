package com.wgsdg.score4;

public class Score4Constants {

    public static int rowMax = 6;
    public static int colMax = 7;

    public static enum Player {
        ME,
        OPPONENT;
    }

    public static enum Score4MoveType {
        MOVE("move"),
        DRAW("draw"),
        WON("i_won"),
        ERROR_ME("invalid_me"),
        ERROR_OPPONENT("invalid_opponent"),
        CORRECT("correct"),
        LOST("i_lost");

        private String value;
        Score4MoveType(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }

}
