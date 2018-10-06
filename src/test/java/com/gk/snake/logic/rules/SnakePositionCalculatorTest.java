package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SnakePositionCalculatorTest {

    private SnakePositionCalculator positionCalculator = new SnakePositionCalculator();

    @Test
    public void whenCalculate_thenGameStatePropertiesOtherThanBodyAreNotChanged() {

        GameState oldState = new GameState(new Snake(Arrays.asList(new XY(1, 2), new XY(1, 3)), Direction.LEFT), new XY(3, 4));

        GameState newState = positionCalculator.calculateNextState(oldState, null);

        assertEquals(Direction.LEFT, newState.getSnake().getDirection());
        assertEquals(new XY(3, 4), newState.getApplePosition());
    }

    @Test
    public void whenCalculate_thenTailIsRemovedAndNewHeadIsAdded() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));
        GameState oldState = new GameState(new Snake(oldPosition, Direction.LEFT), new XY(1, 1));

        GameState newState = positionCalculator.calculateNextState(oldState, null);
        List<XY> newPosition = newState.getSnake().getBody();

        assertEquals(3, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
    }

    @Test
    public void givenThatSnakeAndAppleOverlap_whenCalculate_thenAppleDisappears() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));
        GameState oldState = new GameState(new Snake(oldPosition, Direction.LEFT), new XY(8, 3));

        GameState newState = positionCalculator.calculateNextState(oldState, null);

        assertNull(newState.getApplePosition());
    }

    @Test
    public void givenThatSnakeAndAppleOverlap_whenCalculate_thenTailIsLeftAndNewHeadIsAdded() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));
        GameState oldState = new GameState(new Snake(oldPosition, Direction.LEFT), new XY(8, 3));

        GameState newState = positionCalculator.calculateNextState(oldState, null);
        List<XY> newPosition = newState.getSnake().getBody();

        assertEquals(4, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
        assertEquals(new XY(11, 3), newPosition.get(3));
    }
}