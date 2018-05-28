package com.wgsdg.score4;

import com.fasterxml.jackson.annotation.JsonValue;

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
		ERROR_SIZE("invalid_size"),
        ERROR_ME("invalid_me"),
        ERROR_OPPONENT("invalid_opponent"),
        CORRECT("correct"),
        LOST("i_lost");

        private String name;
     
        // standard constructors
        Score4MoveType(String name) {
            this.name = name;
        }

        @JsonValue
        public String getName() {
            return name;
        }
    }

}
