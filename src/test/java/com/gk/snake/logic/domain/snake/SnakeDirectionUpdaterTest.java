package com.gk.snake.logic.domain.snake;

import com.gk.snake.input.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SnakeDirectionUpdaterTest {

    private static final KeyStroke LEFT_ARROW_KEY_STROKE = KeyStroke.LEFT_ARROW;
    private static final KeyStroke RIGHT_ARROW_KEY_STROKE = KeyStroke.RIGHT_ARROW;
    private static final KeyStroke UP_ARROW_KEY_STROKE = KeyStroke.UP_ARROW;
    private static final KeyStroke DOWN_ARROW_KEY_STROKE = KeyStroke.DOWN_ARROW;

    private SnakeDirectionUpdater snakeDirectionUpdater = new SnakeDirectionUpdater();

    @Test
    public void testLeftArrow() {
        assertEquals(Direction.LEFT, getNewDirection(Direction.UP, LEFT_ARROW_KEY_STROKE));
    }

    @Test
    public void testRightArrow() {
        assertEquals(Direction.RIGHT, getNewDirection(Direction.UP, RIGHT_ARROW_KEY_STROKE));
    }

    @Test
    public void testUpArrow() {
        assertEquals(Direction.UP, getNewDirection(Direction.LEFT, UP_ARROW_KEY_STROKE));
    }

    @Test
    public void testDownArrow() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.LEFT, DOWN_ARROW_KEY_STROKE));
    }

    @Test
    public void testLeftArrowWhenSnakeIsGoingRight() {
        assertEquals(Direction.RIGHT, getNewDirection(Direction.RIGHT, LEFT_ARROW_KEY_STROKE));
    }

    @Test
    public void testRightArrowWhenSnakeIsGoingLeft() {
        assertEquals(Direction.LEFT, getNewDirection(Direction.LEFT, RIGHT_ARROW_KEY_STROKE));
    }

    @Test
    public void testUpArrowWhenSnakeIsGoingDown() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, UP_ARROW_KEY_STROKE));
    }

    @Test
    public void testDownArrowWhenSnakeIsGoingUp() {
        assertEquals(Direction.UP, getNewDirection(Direction.UP, DOWN_ARROW_KEY_STROKE));
    }

    @Test
    public void testNoOrUnknownKeyPressed() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, null));
    }

    private Direction getNewDirection(Direction oldDirection, KeyStroke keyStroke) {
        return snakeDirectionUpdater.getNextDirection(oldDirection, keyStroke);
    }
}