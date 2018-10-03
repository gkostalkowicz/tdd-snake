package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SnakeDirectionCalculatorTest {

    private SnakeDirectionCalculator directionCalculator = new SnakeDirectionCalculator();

    @Test
    public void testLeftArrow() {
        assertEquals(Direction.LEFT, getNewDirection(Direction.UP, new KeyStroke(KeyType.ArrowLeft)));
    }

    @Test
    public void testRightArrow() {
        assertEquals(Direction.RIGHT, getNewDirection(Direction.UP, new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void testUpArrow() {
        assertEquals(Direction.UP, getNewDirection(Direction.LEFT, new KeyStroke(KeyType.ArrowUp)));
    }

    @Test
    public void testDownArrow() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.LEFT, new KeyStroke(KeyType.ArrowDown)));
    }

    @Test
    public void testLeftArrowWhenSnakeIsGoingRight() {
        assertEquals(Direction.RIGHT, getNewDirection(Direction.RIGHT, new KeyStroke(KeyType.ArrowLeft)));
    }

    @Test
    public void testRightArrowWhenSnakeIsGoingLeft() {
        assertEquals(Direction.LEFT, getNewDirection(Direction.LEFT, new KeyStroke(KeyType.ArrowRight)));
    }

    @Test
    public void testUpArrowWhenSnakeIsGoingDown() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, new KeyStroke(KeyType.ArrowUp)));
    }

    @Test
    public void testDownArrowWhenSnakeIsGoingUp() {
        assertEquals(Direction.UP, getNewDirection(Direction.UP, new KeyStroke(KeyType.ArrowDown)));
    }

    @Test
    public void testNoKeyPressed() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, null));
    }

    @Test
    public void testIrrelevantKeyPressed() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, new KeyStroke(KeyType.Enter)));
    }

    private Direction getNewDirection(Direction up, KeyStroke keyStroke) {
        return directionCalculator.getNewDirection(new GameState(new Snake(new ArrayList<>(), up), null), keyStroke);
    }
}