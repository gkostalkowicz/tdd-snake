package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.rules.GameRule;
import com.gk.snake.logic.rules.PositionGenerator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardTest {

    @Test
    public void testGivenNoAppleAnAppleIsGenerated() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition()).thenReturn(new XY(10, 20));
        GameState stateWithoutApple = new GameState(null, null);
        Board board = new Board(Collections.emptyList(), positionGeneratorStub, stateWithoutApple);

        board.processNextFrame(null);

        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    @Test
    public void testGivenExistingAppleItStays() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        GameState stateWithApple = new GameState(null, new XY(20, 30));
        Board board = new Board(Collections.emptyList(), positionGeneratorStub, stateWithApple);

        board.processNextFrame(null);

        assertEquals(new XY(20, 30), stateWithApple.getApplePosition());
    }

    @Test
    public void gameStateIsProcessedByRules() {

        GameState state0 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 0));
        GameState state1 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 1));
        GameState state2 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 2));

        GameRule rule1 = mock(GameRule.class);
        GameRule rule2 = mock(GameRule.class);
        when(rule1.calculateNextState(state0, KeyStroke.LEFT_ARROW)).thenReturn(state1);
        when(rule2.calculateNextState(state1, KeyStroke.LEFT_ARROW)).thenReturn(state2);

        Board board = new Board(Arrays.asList(rule1, rule2), mock(PositionGenerator.class), state0);

        // when
        board.processNextFrame(KeyStroke.LEFT_ARROW);

        // then
        assertEquals(state2, board.getState());
    }
}
