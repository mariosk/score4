package com.wgsdg.score4.model;

import lombok.Data;

/*
 * This POJO is used for Input/Output JSON
 */
@Data
public class Score4IO {

    private String type;
    private int[] moves;

    public Score4IO(String type, int[] moves) {
        this.type = type;
        this.moves = moves;
    }

}
