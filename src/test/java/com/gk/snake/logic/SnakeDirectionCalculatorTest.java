package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SnakeDirectionCalculatorTest {

    private static final KeyStroke LEFT_ARROW_KEY_STROKE = new KeyStroke(KeyType.ArrowLeft);
    private static final KeyStroke RIGHT_ARROW_KEY_STROKE = new KeyStroke(KeyType.ArrowRight);
    private static final KeyStroke UP_ARROW_KEY_STROKE = new KeyStroke(KeyType.ArrowUp);
    private static final KeyStroke DOWN_ARROW_KEY_STROKE = new KeyStroke(KeyType.ArrowDown);
    private static final KeyStroke ENTER_KEY_STROKE = new KeyStroke(KeyType.Enter);

    private SnakeDirectionCalculator directionCalculator = new SnakeDirectionCalculator();

    @Test
    public void gameStatePropertiesOtherThanDirectionAreNotChanged() {
        GameState oldState = new GameState(new Snake(Collections.singletonList(new XY(1, 2)), Direction.UP),
                new XY(3, 4));

        GameState newState = directionCalculator.calculateNextState(oldState, ENTER_KEY_STROKE);

        assertEquals(Collections.singletonList(new XY(1, 2)), newState.getSnake().getBody());
        assertEquals(new XY(3, 4), newState.getApplePosition());
    }

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
    public void testNoKeyPressed() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, null));
    }

    @Test
    public void testIrrelevantKeyPressed() {
        assertEquals(Direction.DOWN, getNewDirection(Direction.DOWN, ENTER_KEY_STROKE));
    }

    private Direction getNewDirection(Direction up, KeyStroke keyStroke) {
        GameState oldState = new GameState(new Snake(new ArrayList<>(), up), null);
        GameState newState = directionCalculator.calculateNextState(oldState, keyStroke);
        return newState.getSnake().getDirection();
    }
}