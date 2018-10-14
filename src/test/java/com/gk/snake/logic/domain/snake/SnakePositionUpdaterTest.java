package com.gk.snake.logic.domain.snake;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater.SnakePositionUpdate;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SnakePositionUpdaterTest {

    private SnakePositionUpdater snakePositionUpdater = new SnakePositionUpdater();

    @Test
    public void givenThatSnakeAndAppleDoNotOverlap_whenCalculate_thenAppleIsNotEaten() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));

        SnakePositionUpdate update = snakePositionUpdater.getNextPosition(oldPosition, Direction.LEFT, new XY(1, 1));

        assertFalse(update.isAppleEaten());
    }

    @Test
    public void givenThatSnakeAndAppleDoNotOverlap_whenCalculate_thenTailIsRemovedAndNewHeadIsAdded() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));

        SnakePositionUpdate update = snakePositionUpdater.getNextPosition(oldPosition, Direction.LEFT, new XY(1, 1));
        List<XY> newPosition = update.getPosition();

        assertEquals(3, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
    }

    @Test
    public void givenThatSnakeAndAppleOverlap_whenCalculate_thenAppleIsEaten() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));

        SnakePositionUpdate update = snakePositionUpdater.getNextPosition(oldPosition, Direction.LEFT, new XY(8, 3));

        assertTrue(update.isAppleEaten());
    }

    @Test
    public void givenThatSnakeAndAppleOverlap_whenCalculate_thenTailIsLeftAndNewHeadIsAdded() {

        ArrayList<XY> oldPosition = new ArrayList<>();
        oldPosition.add(new XY(9, 3));
        oldPosition.add(new XY(10, 3));
        oldPosition.add(new XY(11, 3));

        SnakePositionUpdate update = snakePositionUpdater.getNextPosition(oldPosition, Direction.LEFT, new XY(8, 3));
        List<XY> newPosition = update.getPosition();

        assertEquals(4, newPosition.size());
        assertEquals(new XY(8, 3), newPosition.get(0));
        assertEquals(new XY(9, 3), newPosition.get(1));
        assertEquals(new XY(10, 3), newPosition.get(2));
        assertEquals(new XY(11, 3), newPosition.get(3));
    }
}