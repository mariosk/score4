package com.wgsdg.score4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wgsdg.score4.Score4Constants.Score4MoveType;
import com.wgsdg.score4.game.Score4Game;
import com.wgsdg.score4.model.Score4IO;

@RestController
@RequestMapping("/score4")
public class Score4Controller {

    @RequestMapping(value="/check", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> check(@RequestBody Score4IO input) {
    	Score4Game checkGame = new Score4Game();
    	Score4MoveType moveType = checkGame.findWinner(input.getMoves());
        Score4IO output = new Score4IO(moveType, input.getMoves());
        return ResponseEntity.ok(output);
    }

    @RequestMapping(value="/play", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> play(@RequestBody Score4IO input) {
    	if (!input.getType().equals(Score4MoveType.MOVE)) {
			return new ResponseEntity<String>("Not correct move type: " + input.getType().getName(), HttpStatus.BAD_REQUEST);
    	}
    	Score4Game newGame = new Score4Game();
    	Score4MoveType moveType = newGame.checkForValidMove(input.getMoves()).getMoveType();
		Score4IO output = new Score4IO(moveType, input.getMoves());
    	// if there is no winner yet let's play the next move...
    	if (moveType.equals(Score4MoveType.CONTINUE)) {
    		output = newGame.playNextMove(input);
    	}
        return ResponseEntity.ok(output);
    }

}
