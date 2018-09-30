package com.gk.snake.logic;

import com.gk.snake.logic.Direction;
import com.gk.snake.logic.Snake;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SnakeTest {

    @Test
    public void getBodyReturnsNewInstanceOnEveryCall() {
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT);
        assertFalse(snake.getBody() == snake.getBody());
    }
}