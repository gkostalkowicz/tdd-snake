package com.gk.snake.logic.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DirectionTest {

    @Test
    public void testOppositeOfLeft() {
        assertEquals(Direction.RIGHT, Direction.getOpposite(Direction.LEFT));
    }

    @Test
    public void testOppositeOfRight() {
        assertEquals(Direction.LEFT, Direction.getOpposite(Direction.RIGHT));
    }

    @Test
    public void testOppositeOfUp() {
        assertEquals(Direction.DOWN, Direction.getOpposite(Direction.UP));
    }

    @Test
    public void testOppositeOfDown() {
        assertEquals(Direction.UP, Direction.getOpposite(Direction.DOWN));
    }

    @Test
    public void testAreOppositeDirectionsForLeftAndRight() {
        assertTrue(Direction.areOpposite(Direction.LEFT, Direction.RIGHT));
    }
}