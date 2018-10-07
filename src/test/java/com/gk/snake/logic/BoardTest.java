package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.SnakePositionUpdater.SnakePositionUpdate;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.rules.GameRule;
import com.gk.snake.logic.rules.PositionGenerator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class BoardTest {

    @Test
    public void testGivenNoAppleAnAppleIsGenerated() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition()).thenReturn(new XY(10, 20));
        GameState stateWithoutApple = new GameState(mock(Snake.class), null);
        Board board = new Board(Collections.emptyList(), positionGeneratorStub, stateWithoutApple);

        board.processNextFrame(null);

        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    @Test
    public void testGivenExistingAppleItStays() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        GameState stateWithApple = new GameState(mock(Snake.class), new XY(20, 30));
        Board board = new Board(Collections.emptyList(), positionGeneratorStub, stateWithApple);

        board.processNextFrame(null);

        assertEquals(new XY(20, 30), stateWithApple.getApplePosition());
    }

    @Test
    public void whenProcessNextFrame_thenIsUpdatedForNextFrame() {

        Snake snakeMock = mock(Snake.class);
        GameState gameState = new GameState(snakeMock, new XY(1, 2));
        Board board = new Board(Collections.emptyList(), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        verify(snakeMock).updateForNextFrame(KeyStroke.LEFT_ARROW, new XY(1, 2));
    }

    @Test
    public void givenThatAppleIsEaten_whenProcessNextFrame_thenAppleIsRemoved() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(true);
        GameState gameState = new GameState(snakeMock, new XY(10, 20));
        Board board = new Board(Collections.emptyList(), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        assertNull(board.getState().getApplePosition());
    }

    @Test
    public void givenThatAppleIsNotEaten_whenProcessNextFrame_thenAppleStays() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(false);
        GameState gameState = new GameState(snakeMock, new XY(10, 20));
        Board board = new Board(Collections.emptyList(), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    @Test
    public void gameStateIsProcessedByRules() {

        Snake snake = new Snake(Collections.emptyList(), null, mock(SnakeDirectionUpdater.class),
                mock(SnakePositionUpdater.class));
        when(snake.getSnakePositionUpdater().getNextPosition(any(), any(), any()))
                .thenReturn(new SnakePositionUpdate(null, false));

        GameState state0 = new GameState(snake, new XY(0, 0));
        GameState state1 = new GameState(snake, new XY(0, 1));
        GameState state2 = new GameState(snake, new XY(0, 2));

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
