package com.wgsdg.score4.game;

import java.util.Arrays;

import com.wgsdg.score4.Score4Constants;
import com.wgsdg.score4.Score4Constants.Player;
import com.wgsdg.score4.Score4Constants.Score4MoveType;
import com.wgsdg.score4.Score4Utils;
import com.wgsdg.score4.model.Score4IO;

public class Score4Game {
    static int [][] gameBoard = new int[Score4Constants.rowMax][Score4Constants.colMax];

    public Score4Game() {
        // initialization of the gameBoard
        for (int i = 0; i < Score4Constants.rowMax; i++) {
            Arrays.fill(gameBoard[i], -1);
        }        
    }

    private Score4MoveType storeToBoard(int colNumber, Player player){
        for (int i = 0; i < Score4Constants.rowMax; i++) {
            if (gameBoard[i][colNumber] == -1) {
                gameBoard[i][colNumber] = player.ordinal();
                return Score4MoveType.CORRECT;
            }
        }
        //this column contains numbers
        return (player == Score4Constants.Player.ME ? Score4MoveType.ERROR_ME : Score4MoveType.ERROR_OPPONENT);
    }

    public Score4MoveType checkForValidMove(int[] moves) {
        if (moves.length > Score4Constants.colMax*Score4Constants.rowMax || moves.length == 0) {
            return Score4MoveType.ERROR_ME;
        }

        for (int i = 0; i < moves.length; i++) {
            int currentItem = moves[i]-1;
            if (moves[i] > Score4Constants.colMax || moves[i] <= 0) {
                return Score4Utils.findErrorByPlayer(Score4Utils.findPlayerByIndex(i));
            }
            Score4MoveType moveType = storeToBoard(currentItem, Score4Utils.findPlayerByIndex(i));
            if (!moveType.equals(Score4MoveType.CORRECT)) {
                return moveType;
            };
        }
        return Score4MoveType.CORRECT;
    }

    public Score4IO play(Score4IO request) {
        Score4IO response = null;
        return response;
    }
}
