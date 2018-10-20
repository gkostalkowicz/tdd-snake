package com.gk.snake.logic.domain.snake;

import com.gk.snake.input.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater.SnakePositionUpdate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SnakeTest {

    // -------------------
    // updating direction

    @Test
    public void givenSnakeDirectionLeftAndUpArrowKeyPress_whenUpdateForNextFrame_thenSnakeDirectionUpdaterIsCalledWithRightParams() {

        // given:
        SnakeDirectionUpdater snakeDirectionUpdaterMock = mock(SnakeDirectionUpdater.class);
        Snake snake = new SnakeBuilder()
                .direction(Direction.LEFT)
                .directionUpdater(snakeDirectionUpdaterMock)
                .build();

        // when:
        snake.updateForNextFrame(KeyStroke.UP_ARROW, null);

        // then:
        verify(snakeDirectionUpdaterMock).getNextDirection(Direction.LEFT, KeyStroke.UP_ARROW);
    }

    @Test
    public void givenThatSnakeDirectionUpdaterReturnsUpward_whenUpdateForNextFrame_thenSnakeDirectionIsUpdatedToUpward() {

        // given:
        SnakeDirectionUpdater snakeDirectionUpdaterStub = mock(SnakeDirectionUpdater.class);
        when(snakeDirectionUpdaterStub.getNextDirection(any(), any()))
                .thenReturn(Direction.UP);
        Snake snake = new SnakeBuilder().directionUpdater(snakeDirectionUpdaterStub).build();

        // when:
        snake.updateForNextFrame(null, null);

        // then:
        assertEquals(Direction.UP, snake.getDirection());
    }

    // -------------------
    // updating position and eating apple

    @Test
    public void whenUpdateForNextFrame_thenSnakePositionUpdaterIsCalledWithRightParams() {

        // given:
        SnakeDirectionUpdater snakeDirectionUpdaterStub = mock(SnakeDirectionUpdater.class);
        when(snakeDirectionUpdaterStub.getNextDirection(any(), any()))
                .thenReturn(Direction.LEFT);

        SnakePositionUpdater snakePositionUpdaterMock = mock(SnakePositionUpdater.class);

        Snake snake = new SnakeBuilder()
                .body(List.of(new XY(1, 1)))
                .positionUpdaterMock(snakePositionUpdaterMock)
                .directionUpdater(snakeDirectionUpdaterStub)
                .build();

        // when:
        snake.updateForNextFrame(null, new XY(1, 2));

        // then:
        verify(snakePositionUpdaterMock).getNextPosition(List.of(new XY(1, 1)), Direction.LEFT, new XY(1, 2));
    }

    @Test
    public void givenThatSnakeDirectionUpdaterReturnsPositionX1AndY2_whenUpdateForNextFrame_thenSnakePositionIsUpdatedToX1AndY2() {

        // given:
        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any()))
                .thenReturn(snakePositionUpdateWithPosition(List.of(new XY(1, 2))));
        Snake snake = new SnakeBuilder().positionUpdaterStub(snakePositionUpdaterStub).build();

        // when:
        snake.updateForNextFrame(null, null);

        // then:
        assertEquals(List.of(new XY(1, 2)), snake.getBody());
    }

    @Test
    public void givenThatSnakeDirectionUpdaterReturnsThatAppleIsEaten_whenUpdateForNextFrame_thenSnakeReturnsAppleEatenEqualToTrue() {

        // given:
        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any()))
                .thenReturn(snakePositionUpdateWithAppleEaten(true));
        Snake snake = new SnakeBuilder().positionUpdaterStub(snakePositionUpdaterStub).build();

        // when:
        Snake.UpdateResult updateResult = snake.updateForNextFrame(null, null);

        // then:
        assertTrue(updateResult.isAppleEaten());
    }

    // -------------------
    // check whether snake crashed into itself

    @Test
    public void givenSnakePositionX1AndY2_whenUpdateForNextFrame_thenSnakeCrashIntoItselfCheckIsCalledWithRightParams() {

        // given:
        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any())).thenReturn(
                snakePositionUpdateWithPosition(List.of(new XY(1, 2))));

        SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheckMock = mock(SnakeCrashIntoItselfCheck.class);

        Snake snake = new SnakeBuilder()
                .positionUpdaterStub(snakePositionUpdaterStub)
                .crashIntoItselfCheck(snakeCrashIntoItselfCheckMock)
                .build();

        // when:
        snake.updateForNextFrame(null, null);

        // then:
        verify(snakeCrashIntoItselfCheckMock).hasCrashedIntoItself(List.of(new XY(1, 2)));
    }

    @Test
    public void givenThatSnakeCrashIntoItselfCheckReturnsTrue_whenUpdateForNextFrame_thenSnakeReturnsCrashedEqualToTrue() {

        // given:
        SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheckStub = mock(SnakeCrashIntoItselfCheck.class);
        when(snakeCrashIntoItselfCheckStub.hasCrashedIntoItself(any()))
                .thenReturn(true);
        Snake snake = new SnakeBuilder().crashIntoItselfCheck(snakeCrashIntoItselfCheckStub).build();

        // when:
        Snake.UpdateResult updateResult = snake.updateForNextFrame(null, null);

        // then:
        assertTrue(updateResult.isCrashedIntoItself());
    }

    // -------------------
    // misc tests

    @Test
    public void whenGetBody_thenReturnAnUnmodifiableList() {
        // given:
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT, null, null, null);

        // when:
        List<XY> body = snake.getBody();

        // then:
        try {
            body.add(new XY(1, 2));
            fail();
        } catch (UnsupportedOperationException e) {
            // ignore
        }
    }

    // -------------------
    // helper methods

    private class SnakeBuilder {
        private List<XY> body = List.of();
        private Direction direction;
        private SnakeDirectionUpdater directionUpdater = mock(SnakeDirectionUpdater.class);
        private SnakePositionUpdater positionUpdater = mock(SnakePositionUpdater.class);
        private SnakeCrashIntoItselfCheck crashIntoItselfCheck = mock(SnakeCrashIntoItselfCheck.class);

        SnakeBuilder() {
            when(positionUpdater.getNextPosition(any(), any(), any()))
                    .thenReturn(new SnakePositionUpdate(null, false));
        }

        SnakeBuilder body(List<XY> body) {
            this.body = body;
            return this;
        }

        SnakeBuilder direction(Direction direction) {
            this.direction = direction;
            return this;
        }

        SnakeBuilder directionUpdater(SnakeDirectionUpdater directionUpdater) {
            this.directionUpdater = directionUpdater;
            return this;
        }

        SnakeBuilder positionUpdaterStub(SnakePositionUpdater positionUpdater) {
            this.positionUpdater = positionUpdater;
            return this;
        }

        SnakeBuilder positionUpdaterMock(SnakePositionUpdater positionUpdater) {
            when(positionUpdater.getNextPosition(any(), any(), any()))
                    .thenReturn(new SnakePositionUpdate(null, false));
            this.positionUpdater = positionUpdater;
            return this;
        }

        SnakeBuilder crashIntoItselfCheck(SnakeCrashIntoItselfCheck crashIntoItselfCheck) {
            this.crashIntoItselfCheck = crashIntoItselfCheck;
            return this;
        }

        Snake build() {
            return new Snake(body, direction, directionUpdater, positionUpdater, crashIntoItselfCheck);
        }
    }

    private SnakePositionUpdate snakePositionUpdateWithAppleEaten(boolean appleEaten) {
        return new SnakePositionUpdate(null, appleEaten);
    }

    private SnakePositionUpdate snakePositionUpdateWithPosition(List<XY> position) {
        return new SnakePositionUpdate(position, false);
    }
}