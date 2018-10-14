package com.gk.snake.logic.domain.snake;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater.SnakePositionUpdate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SnakeTest {

    @Test
    public void whenUpdateForNextFrame_thenSnakeDirectionIsUpdated() {

        SnakeDirectionUpdater snakeDirectionUpdaterStub = mock(SnakeDirectionUpdater.class);
        when(snakeDirectionUpdaterStub.getNextDirection(Direction.LEFT, KeyStroke.UP_ARROW)).thenReturn(Direction.UP);
        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any()))
                .thenReturn(new SnakePositionUpdate(null, false));
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT, snakeDirectionUpdaterStub,
                snakePositionUpdaterStub, mock(CrashedIntoItselfCheck.class));

        snake.updateForNextFrame(KeyStroke.UP_ARROW, null);

        assertEquals(Direction.UP, snake.getDirection());
    }

    @Test
    public void whenUpdateForNextFrame_thenSnakePositionIsUpdated() {

        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        SnakePositionUpdate update = new SnakePositionUpdate(Collections.singletonList(new XY(1, 2)), false);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any())).thenReturn(update);
        Snake snake = new Snake(Collections.singletonList(new XY(2, 2)), Direction.LEFT,
                mock(SnakeDirectionUpdater.class), snakePositionUpdaterStub, mock(CrashedIntoItselfCheck.class));

        snake.updateForNextFrame(null, null);

        assertEquals(Collections.singletonList(new XY(1, 2)), snake.getBody());
    }

    @Test
    public void givenThatAppleIsEaten_whenUpdateForNextFrame_thenAppleEatenEqualTrueIsReturned() {

        SnakePositionUpdater snakePositionUpdaterStub = mock(SnakePositionUpdater.class);
        SnakePositionUpdate update = new SnakePositionUpdate(Collections.singletonList(new XY(1, 2)), true);
        when(snakePositionUpdaterStub.getNextPosition(any(), any(), any())).thenReturn(update);
        Snake snake = new Snake(Collections.singletonList(new XY(2, 2)), Direction.LEFT,
                mock(SnakeDirectionUpdater.class), snakePositionUpdaterStub, mock(CrashedIntoItselfCheck.class));

        assertTrue(snake.updateForNextFrame(null, null).isAppleEaten());
    }

    @Test
    public void givenThatSnakeCrashedIntoItself_whenUpdateForNextFrame_thenCrashedEqualTrueIsReturned() {

        SnakePositionUpdater snakePositionUpdaterMock = mock(SnakePositionUpdater.class);
        when(snakePositionUpdaterMock.getNextPosition(any(), any(), any())).thenReturn(
                new SnakePositionUpdate(List.of(new XY(1, 2)), false));
        CrashedIntoItselfCheck crashedIntoItselfCheckStub = mock(CrashedIntoItselfCheck.class);
        when(crashedIntoItselfCheckStub.hasCrashedIntoItself(List.of(new XY(1, 2)))).thenReturn(true);
        Snake snake = new Snake(List.of(new XY(1, 1)), null, mock(SnakeDirectionUpdater.class),
                snakePositionUpdaterMock, crashedIntoItselfCheckStub);

        assertTrue(snake.updateForNextFrame(null, null).isCrashedIntoItself());
    }

    @Test
    public void getBodyReturnsUnmodifiableList() {
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT, null, null, null);
        List<XY> body = snake.getBody();

        try {
            body.add(new XY(1, 2));
            fail();
        } catch (UnsupportedOperationException e) {
            // ignore
        }
    }
}