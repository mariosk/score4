package com.wgsdg.score4.model;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.wgsdg.score4.Score4Constants.Score4MoveType;
import com.wgsdg.score4.game.Score4Game;

public class TestScore4 {

    private Score4Game testScore4 = null;

    @Before
    public void setUp() throws Exception {
        testScore4 = new Score4Game();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInvalidMoves_test1() {
        int[] moves = {-1,2,3};
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.ERROR_ME);
    }

    @Test
    public void testInvalidMoves_test2() {
        int[] moves = {};
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.ERROR_ME);
    }

    @Test
    public void testInvalidMoves_test3() {
        int[] moves = {1,3456,2,3};
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.ERROR_OPPONENT);
    }

    @Test
    public void testInvalidMoves_test4() {
        int[] moves = {1,1,1,1,1,1,1};
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.ERROR_ME);
    }

    @Test
    public void testValidMoves_test1() {
        int[] moves = {1,2,3};
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.CORRECT);
    }

    @Test
    public void testValidMoves_test2() {
        int[] moves = new int[42];
        for(int i=0; i < moves.length; i++) {
            moves[i] = i % 7 + 1;
         }
        assertEquals(testScore4.checkForValidMove(moves), Score4MoveType.CORRECT);
    }
}
