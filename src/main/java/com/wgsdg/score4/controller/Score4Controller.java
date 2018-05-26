package com.wgsdg.score4.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wgsdg.score4.Score4Constants;
import com.wgsdg.score4.model.Score4IO;

@RestController
@RequestMapping("/score4")
public class Score4Controller {

    @RequestMapping(value="/", method = RequestMethod.POST)
    public Score4IO move(@RequestBody Score4IO input) {
        Score4IO output = new Score4IO(Score4Constants.Score4MoveType.CORRECT, input.getMoves());
        return output;
    }

}
