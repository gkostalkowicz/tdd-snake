package com.gk.snake.logic;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class InitialStateCalculatorTest {

    @Test
    public void testInitialState() {

        GameState state = new InitialStateCalculator().getInitialState(80, 25, null);
        Snake snake = state.getSnake();

        List<XY> body = snake.getBody();
        assertEquals(5, body.size());
        assertEquals(new XY(39, 12), body.get(0));
        assertEquals(new XY(40, 12), body.get(1));
        assertEquals(new XY(41, 12), body.get(2));
        assertEquals(new XY(42, 12), body.get(3));
        assertEquals(new XY(43, 12), body.get(4));
    }
}