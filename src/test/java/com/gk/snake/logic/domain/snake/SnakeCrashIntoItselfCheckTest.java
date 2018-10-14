package com.gk.snake.logic.domain.snake;

import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SnakeCrashIntoItselfCheckTest {

    private SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck = new SnakeCrashIntoItselfCheck();

    @Test
    public void givenThatSnakeCrashedIntoItself_whenCheck_thenReturnTrue() {

        // snake made a loop and crashed into itself
        List<XY> body = List.of(new XY(1, 1), new XY(2, 1), new XY(2, 2), new XY(1, 2), new XY(1, 1));

        assertTrue(snakeCrashIntoItselfCheck.hasCrashedIntoItself(body));
    }

    @Test
    public void givenThatSnakeDidNotCrash_whenCheck_thenReturnFalse() {

        List<XY> body = List.of(new XY(1, 1), new XY(2, 1));

        assertFalse(snakeCrashIntoItselfCheck.hasCrashedIntoItself(body));
    }
}