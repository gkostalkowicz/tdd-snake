package com.gk.snake.logic.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class SnakeTest {

    @Test
    public void getBodyReturnsUnmodifiableList() {
        Snake snake = new Snake(new ArrayList<>(), Direction.LEFT);
        List<XY> body = snake.getBody();

        try {
            body.add(new XY(1, 2));
            fail();
        } catch (UnsupportedOperationException e) {
            // ignore
        }
    }
}