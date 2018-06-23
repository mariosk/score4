package com.wgsdg.score4.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wgsdg.score4.Score4Constants;
import com.wgsdg.score4.Score4Constants.Score4MoveType;
import com.wgsdg.score4.Score4Constants.ScoreBoardType;
import com.wgsdg.score4.Score4Utils;
import com.wgsdg.score4.game.Score4Game;

public class Score4GameTests {

    private Score4Game testScore4 = null;
	private static final Logger logger = LoggerFactory.getLogger(Score4GameTests.class);

	@Rule
	public TestName name = new TestName();

    @Before
    public void setUp() throws Exception {
        testScore4 = new Score4Game();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInvalidMoves_test1() {
		int[] moves = { -1, 2, 3 }; // 3: OPPONENT, 2: ME, -1: OPPONENT
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_OPPONENT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
    }

    @Test
    public void testInvalidMoves_test2() {
        int[] moves = {};
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_SIZE);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
	}

    @Test
    public void testInvalidMoves_test3() {
		int[] moves = { 1, 3456, 2, 3 }; // 3: OPPONENT, 2: ME, 3456: OPPONENT, 1: ME
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_OPPONENT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
    }

    @Test
	public void testInvalidMoves_test41() {
		int[] moves = { 1, 1, 1, 1, 1, 1, 1 }; // 1: OPPONENT, 1: ME, 1: OPPONENT, 1: ME, 1: OPPONENT, 1: ME, 1: OPPONENT
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_OPPONENT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
	}

	@Test
	public void testInvalidMoves_test42() {
		int[] moves = { 1, 1, 1, 1, 1, 1, 1, 1 }; // 1: OPPONENT, 1: ME, 1: OPPONENT, 1: ME, 1: OPPONENT, 1: ME, 1: OPPONENT, 1: ME
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_ME);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
    }

	@Test
	public void testInvalidMoves_test5() {
		int[] moves = new int[(Score4Constants.rowMax + 1) * Score4Constants.colMax];
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.ERROR_SIZE);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
	}

    @Test
    public void testValidMoves_test1() {
        int[] moves = {1,2,3};
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
    }

    @Test
    public void testValidMoves_test2() {
		int[] moves = new int[Score4Constants.rowMax * Score4Constants.colMax];
        for(int i=0; i < moves.length; i++) {
			moves[i] = i % Score4Constants.colMax + 1;
		}
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);
    }

	@Test
	public void testColWinnerMove_test3() {
		int[] moves = { 1, 2, 1, 2, 1, 2, 1 };
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);

		assertEquals(
				Score4Utils.findWinner(testScore4.checkForWinner(ScoreBoardType.ROW), testScore4.checkForWinner(ScoreBoardType.COLUMN)),
				Score4MoveType.LOST);
	}

	@Test
	public void testRowWinnerMove_test4() {
		int[] moves = { 1, 1, 2, 1, 3, 1, 4 };
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);

		assertEquals(
				Score4Utils.findWinner(testScore4.checkForWinner(ScoreBoardType.ROW), testScore4.checkForWinner(ScoreBoardType.COLUMN)),
				Score4MoveType.LOST);
	}

	@Test
	public void testDrawMove_test5() {
		int[] moves = { 1, 2, 1, 2, 1, 2, 1, 2 };
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);

		assertEquals(
				Score4Utils.findWinner(testScore4.checkForWinner(ScoreBoardType.ROW), testScore4.checkForWinner(ScoreBoardType.COLUMN)),
				Score4MoveType.DRAW);
	}

	@Test
	public void testColWinnerMove_test6() {
		int[] moves = { 1, 2, 1, 2, 1, 2, 1, 3 };
		assertEquals(testScore4.checkForValidMove(moves).getFirst(), Score4MoveType.CORRECT);
		logger.info("{}: Moves: {}", name.getMethodName(), moves);

		assertEquals(
				Score4Utils.findWinner(testScore4.checkForWinner(ScoreBoardType.ROW), testScore4.checkForWinner(ScoreBoardType.COLUMN)),
				Score4MoveType.WON);
	}

}
