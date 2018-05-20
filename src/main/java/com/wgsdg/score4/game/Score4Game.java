package com.wgsdg.score4.game;

import java.util.Arrays;

import com.wgsdg.score4.model.Score4Response;

public class Score4Game {
    public static int rowMax = 6;
    public static int colMax = 7;
    static int [][] gameBoard = new int[rowMax][colMax];

    public Score4Game() {
        // initialization of the gameBoard
        for (int i = 0; i < rowMax; i++) {
            Arrays.fill(gameBoard[i], -1);
        }        
    }

    private static enum Score4Type {
        MOVE("move"),
        DRAW("draw"),
        WON("i_won"),
        ERROR("invalid_move"),
        LOST("i_lost");

        private String value;
        Score4Type(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
    
    private boolean storeToBoard(int colNumber, int player){
        for (int i = 0; i < rowMax; i++) {
            if (gameBoard[i][colNumber] == -1) {
                gameBoard[i][colNumber] = player;
                return true;
            }
        }
        //this column contains numbers
        return false;
    }

    public boolean checkForValidMove(int[] moves) {
        if (moves.length > colMax*rowMax || moves.length == 0) {
            return false;
        }

        for (int i = 0; i < moves.length; i++) {
            int currentItem = moves[i]-1;
            if (moves[i] > colMax || moves[i] <= 0) {
                return false;
            }
            if (!storeToBoard(currentItem, i % 2)) {
                return false;
            };
        }
        return true;
    }

    public Score4Response play(Score4Response request) {
        Score4Response response = null;
        return response;
    }
}
