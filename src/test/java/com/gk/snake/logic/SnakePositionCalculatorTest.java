package com.gk.snake.logic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SnakePositionCalculatorTest {

    private SnakePositionCalculator positionCalculator = new SnakePositionCalculator();

    @Test
    public void gameStatePropertiesOtherThanBodyAreNotChanged() {

        GameState oldState = new GameState(new Snake(Arrays.asList(new XY(1, 2), new XY(1, 3)), Direction.LEFT), new XY(3, 4));

        GameState newState = positionCalculator.calculateNextState(oldState, null);

        assertEquals(Direction.LEFT, newState.getSnake().getDirection());
        assertEquals(new XY(3, 4), newState.getApplePosition());
    }

    @Test
    public void newPositionIsCalculated() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));
        GameState oldState = new GameState(new Snake(oldPosition, Direction.LEFT), null);

        GameState newState = positionCalculator.calculateNextState(oldState, null);
        List<XY> newPosition = newState.getSnake().getBody();

        assertEquals(3, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
    }
}