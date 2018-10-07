package com.gk.snake.logic.domain;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.SnakeDirectionUpdater;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SnakeTest {

    @Test
    public void whenUpdateSnakeDirection_thenSnakeDirectionUpdaterIsCalled() {

        SnakeDirectionUpdater snakeDirectionUpdaterStub = mock(SnakeDirectionUpdater.class);
        when(snakeDirectionUpdaterStub.getNextDirection(Direction.LEFT, KeyStroke.UP_ARROW)).thenReturn(Direction.UP);
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT, snakeDirectionUpdaterStub);

        snake.updateSnakeDirection(KeyStroke.UP_ARROW);

        assertEquals(Direction.UP, snake.getDirection());
    }

    @Test
    public void getBodyReturnsUnmodifiableList() {
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT, null);
        List<XY> body = snake.getBody();

        try {
            body.add(new XY(1, 2));
            fail();
        } catch (UnsupportedOperationException e) {
            // ignore
        }
    }
}