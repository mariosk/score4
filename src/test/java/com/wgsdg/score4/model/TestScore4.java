package com.wgsdg.score4.model;

import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        assertFalse(testScore4.checkForValidMove(moves));
    }

    @Test
    public void testInvalidMoves_test2() {
        int[] moves = {};
        assertFalse(testScore4.checkForValidMove(moves));
    }

    @Test
    public void testInvalidMoves_test3() {
        int[] moves = {-1,3456,2,3};
        assertFalse(testScore4.checkForValidMove(moves));
    }

    @Test
    public void testValidMoves_test1() {
        int[] moves = {1,2,3};
        assert(testScore4.checkForValidMove(moves));
    }

    @Test
    public void testValidMoves_test2() {
        int[] moves = new int[42];
        for(int i=0; i < moves.length; i++) {
            moves[i] = i % 7 + 1;
            System.out.println("moves[i] " + i + " " + moves[i]);
         }
         assert(testScore4.checkForValidMove(moves));
    }
}
