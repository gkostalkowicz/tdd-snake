package com.gk.snake;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SnakePositionCalculatorTest {

    @Test
    public void testNewPositionIsCalculated() {

        SnakePositionCalculator positionCalculator = new SnakePositionCalculator();
        ArrayList<XY> body = new ArrayList<>();
        body.add(new XY(9, 3));
        body.add(new XY(10, 3));
        body.add(new XY(11, 3));

        List<XY> newPosition = positionCalculator.getNewPosition(Direction.LEFT, body);

        assertEquals(3, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
    }
}