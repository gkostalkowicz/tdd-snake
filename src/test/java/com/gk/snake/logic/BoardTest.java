package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.Snake;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BoardTest {

    // -------------------
    // updating snake

    @Test
    public void whenProcessNextFrame_thenUpdateSnakeForNextFrameIsCalledWithRightParams() {

        // given
        Snake snakeMock = mock(Snake.class);
        Board board = new BoardBuilder()
                .applePosition(new XY(1, 2))
                .snakeMock(snakeMock)
                .build();

        // when
        board.processNextFrame(KeyStroke.LEFT_ARROW);

        // then
        verify(snakeMock).updateForNextFrame(KeyStroke.LEFT_ARROW, new XY(1, 2));
    }

    // -------------------
    // eating apple

    @Test
    public void givenThatAppleIsEaten_whenProcessNextFrame_thenAppleIsGeneratedInNewPosition() {

        // given
        XY currentApplePosition = new XY(1, 2);

        Snake snakeStub = mock(Snake.class);
        when(snakeStub.updateForNextFrame(any(), any()))
                .thenReturn(snakeUpdateResultWithAppleEaten(true));
        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition())
                .thenReturn(new XY(5, 6));

        Board board = new BoardBuilder()
                .applePosition(currentApplePosition)
                .snakeStub(snakeStub)
                .positionGenerator(positionGeneratorStub)
                .build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(new XY(5, 6), board.getState().getApplePosition());
    }

    @Test
    public void givenThatAppleIsNotEaten_whenProcessNextFrame_thenAppleStays() {

        // given
        XY currentApplePosition = new XY(10, 20);

        Snake snakeStub = mock(Snake.class);
        when(snakeStub.updateForNextFrame(any(), any()))
                .thenReturn(snakeUpdateResultWithAppleEaten(false));

        Board board = new BoardBuilder()
                .applePosition(currentApplePosition)
                .snakeStub(snakeStub)
                .build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(new XY(10, 20), board.getState().getApplePosition());
    }

    // -------------------
    // checking if snake crashed into itself

    @Test
    public void givenThatSnakeCrashedIntoItself_whenProcessNextFrame_thenGameIsOver() {

        // given
        Snake snakeStub = mock(Snake.class);
        when(snakeStub.updateForNextFrame(any(), any()))
                .thenReturn(snakeUpdateResultWithCrashedIntoItself(true));

        Board board = new BoardBuilder().snakeStub(snakeStub).build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(GameStatus.GAME_OVER, board.getState().getGameStatus());
    }

    @Test
    public void givenThatSnakeDidNotCrashIntoItself_whenProcessNextFrame_thenGameIsContinued() {

        // given
        Snake snakeStub = mock(Snake.class);
        when(snakeStub.updateForNextFrame(any(), any()))
                .thenReturn(snakeUpdateResultWithCrashedIntoItself(false));

        Board board = new BoardBuilder().snakeStub(snakeStub).build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(GameStatus.PLAYING, board.getState().getGameStatus());
    }

    // -------------------
    // checking if snake crashed into wall

    @Test
    public void whenProcessNextFrame_thenHasSnakeCrashedIntoWallIsCalledWithRightParams() {

        // given
        SnakeCrashIntoWallCheck checkMock = mock(SnakeCrashIntoWallCheck.class);
        Board board = new BoardBuilder()
                .snakeWithBody(List.of(new XY(1, 1)))
                .snakeCrashIntoWallCheck(checkMock)
                .build();

        // when
        board.processNextFrame(null);

        // then
        verify(checkMock).hasSnakeCrashedIntoWall(List.of(new XY(1, 1)));
    }

    @Test
    public void givenThatSnakeCrashedIntoWall_whenProcessNextFrame_thenGameIsOver() {

        // given
        SnakeCrashIntoWallCheck checkStub = mock(SnakeCrashIntoWallCheck.class);
        when(checkStub.hasSnakeCrashedIntoWall(any()))
                .thenReturn(true);

        Board board = new BoardBuilder().snakeCrashIntoWallCheck(checkStub).build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(GameStatus.GAME_OVER, board.getState().getGameStatus());
    }

    @Test
    public void givenThatSnakeDidNotCrashIntoWall_whenProcessNextFrame_thenGameIsContinued() {

        // given
        SnakeCrashIntoWallCheck checkStub = mock(SnakeCrashIntoWallCheck.class);
        when(checkStub.hasSnakeCrashedIntoWall(any()))
                .thenReturn(false);

        Board board = new BoardBuilder().snakeCrashIntoWallCheck(checkStub).build();

        // when
        board.processNextFrame(null);

        // then
        assertEquals(GameStatus.PLAYING, board.getState().getGameStatus());
    }

    // -------------------
    // helper methods

    private class BoardBuilder {
        private Snake snake = mock(Snake.class);
        private XY applePosition = null;
        private SnakeCrashIntoWallCheck snakeCrashIntoWallCheck = mock(SnakeCrashIntoWallCheck.class);
        private PositionGenerator positionGenerator = mock(PositionGenerator.class);

        BoardBuilder() {
            when(snake.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
        }

        BoardBuilder snakeStub(Snake snakeStub) {
            this.snake = snakeStub;
            return this;
        }

        BoardBuilder snakeMock(Snake snakeMock) {
            when(snakeMock.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
            this.snake = snakeMock;
            return this;
        }

        BoardBuilder snakeWithBody(List<XY> body) {
            this.snake = mock(Snake.class);
            when(snake.updateForNextFrame(any(), any())).thenReturn(new Snake.UpdateResult(false, false));
            when(snake.getBody()).thenReturn(body);
            return this;
        }

        BoardBuilder applePosition(XY applePosition) {
            this.applePosition = applePosition;
            return this;
        }

        BoardBuilder snakeCrashIntoWallCheck(SnakeCrashIntoWallCheck snakeCrashIntoWallCheck) {
            this.snakeCrashIntoWallCheck = snakeCrashIntoWallCheck;
            return this;
        }

        BoardBuilder positionGenerator(PositionGenerator positionGenerator) {
            this.positionGenerator = positionGenerator;
            return this;
        }

        Board build() {
            GameState gameState = new GameState(snake, applePosition);
            return new Board(snakeCrashIntoWallCheck, positionGenerator, gameState);
        }
    }

    private Snake.UpdateResult snakeUpdateResultWithAppleEaten(boolean appleEaten) {
        return new Snake.UpdateResult(appleEaten, false);
    }

    private Snake.UpdateResult snakeUpdateResultWithCrashedIntoItself(boolean crashedIntoItself) {
        return new Snake.UpdateResult(false, crashedIntoItself);
    }
}
