package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.Snake;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class BoardTest {

    @Test
    public void testGivenNoAppleAnAppleIsGenerated() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition()).thenReturn(new XY(10, 20));
        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult());
        GameState stateWithoutApple = new GameState(snakeMock, null);
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), positionGeneratorStub, stateWithoutApple);

        board.processNextFrame(null);

        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    @Test
    public void testGivenExistingAppleItStays() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult());
        GameState stateWithApple = new GameState(snakeMock, new XY(20, 30));
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), positionGeneratorStub, stateWithApple);

        board.processNextFrame(null);

        assertEquals(new XY(20, 30), stateWithApple.getApplePosition());
    }

    @Test
    public void whenProcessNextFrame_thenIsUpdatedForNextFrame() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult());
        GameState gameState = new GameState(snakeMock, new XY(1, 2));
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        verify(snakeMock).updateForNextFrame(KeyStroke.LEFT_ARROW, new XY(1, 2));
    }

    @Test
    public void givenThatAppleIsEaten_whenProcessNextFrame_thenAppleIsRemoved() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(true, false));
        GameState gameState = new GameState(snakeMock, new XY(10, 20));
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        assertNull(board.getState().getApplePosition());
    }

    @Test
    public void givenThatAppleIsNotEaten_whenProcessNextFrame_thenAppleStays() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
        GameState gameState = new GameState(snakeMock, new XY(10, 20));
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), mock(PositionGenerator.class), gameState);

        board.processNextFrame(KeyStroke.LEFT_ARROW);

        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    @Test
    public void givenThatSnakeCrashedIntoItself_whenProcessNextFrame_thenGameIsOver() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, true));
        GameState gameState = new GameState(snakeMock, null);
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), mock(PositionGenerator.class), gameState);

        board.processNextFrame(null);

        assertEquals(GameStatus.GAME_OVER, board.getState().getGameStatus());
    }

    @Test
    public void givenThatSnakeDidNotCrashIntoItself_whenProcessNextFrame_thenGameIsStillRunning() {

        Snake snakeMock = mock(Snake.class);
        when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
        GameState gameState = new GameState(snakeMock, null);
        Board board = new Board(mock(SnakeCrashIntoWallCheck.class), mock(PositionGenerator.class), gameState);

        board.processNextFrame(null);

        assertEquals(GameStatus.PLAYING, board.getState().getGameStatus());
    }

    @Test
    public void givenThatSnakeCrashedIntoWall_whenProcessNextFrame_thenGameIsOver() {

        SnakeCrashIntoWallCheck snakeCrashIntoWallCheckStub = mock(SnakeCrashIntoWallCheck.class);
        when(snakeCrashIntoWallCheckStub.hasSnakeCrashedIntoWall(List.of(new XY(1, 1)))).thenReturn(true);
        Snake snakeStub = mock(Snake.class);
        when(snakeStub.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
        when(snakeStub.getBody()).thenReturn(List.of(new XY(1, 1)));
        GameState gameState = new GameState(snakeStub, null);
        Board board = new Board(snakeCrashIntoWallCheckStub, mock(PositionGenerator.class), gameState);

        board.processNextFrame(null);

        assertEquals(GameStatus.GAME_OVER, board.getState().getGameStatus());
    }
}
