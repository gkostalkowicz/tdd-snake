package com.gk.snake.logic.domain;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class SnakeTest {

    @Test
    public void getBodyReturnsNewInstanceOnEveryCall() {
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT);
        assertFalse(snake.getBody() == snake.getBody());
    }
}