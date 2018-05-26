package com.wgsdg.score4.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wgsdg.score4.model.Score4IO;

@RestController
@RequestMapping("/score4")
public class Score4Controller {

    @RequestMapping("/")
    public Score4IO move(@RequestParam(value="type") String type, @RequestParam(value="moves") int[] moves) {
        return new Score4IO(type, moves);
    }

}
