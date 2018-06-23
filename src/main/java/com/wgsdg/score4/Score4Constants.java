package com.wgsdg.score4;

import com.fasterxml.jackson.annotation.JsonValue;

public class Score4Constants {

	public static int rowMax = 6;
	public static int colMax = 7;

	public static enum ScoreBoardType {
		ROW, COLUMN, DIAGONAL;
	}

	public static enum Player {
		EMPTY("-"),
		ME("ME"),
		OPPONENT("OP");

		private String name;

		// standard constructors
		Player(String name) {
			this.name = name;
		}

		@JsonValue
		public String getName() {
			return name;
		}
    }

    public static enum Score4MoveType {
		MOVE("move"),
		DRAW("draw"),
		WON("i_won"),
		LOST("i_lost"),
		ERROR_SIZE("invalid_size"),
		ERROR_ME("invalid_me"),
		ERROR_OPPONENT("invalid_opponent"),
		CORRECT("correct");

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
