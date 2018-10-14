package com.gk.snake.logic;

import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CrashedIntoWallCheckTest {

    @Test
    public void givenThatSnakeCrashedIntoLeftWall_whenCheck_thenReturnTrue() {
        assertTrue(hasCrashedIntoWall(new XY(-1, 0), new XY(0, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoRightWall_whenCheck_thenReturnTrue() {
        assertTrue(hasCrashedIntoWall(new XY(80, 0), new XY(79, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoUpperWall_whenCheck_thenReturnTrue() {
        assertTrue(hasCrashedIntoWall(new XY(0, -1), new XY(0, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoLowerWall_whenCheck_thenReturnTrue() {
        assertTrue(hasCrashedIntoWall(new XY(0, 25), new XY(0, 24)));
    }

    @Test
    public void givenThatSnakeHasNotCrashed_whenCheck_thenReturnFalse() {
        assertFalse(hasCrashedIntoWall(new XY(0, 0), new XY(0, 1)));
    }

    private boolean hasCrashedIntoWall(XY... body) {
        return new CrashedIntoWallCheck(80, 25).hasSnakeCrashedIntoWall(Arrays.asList(body));
    }
}