package com.wgsdg.score4.model;

import com.wgsdg.score4.Score4Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * This POJO is used for Input/Output JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score4IO {
    private Score4Constants.Score4MoveType type;
    private int[] moves;
}
