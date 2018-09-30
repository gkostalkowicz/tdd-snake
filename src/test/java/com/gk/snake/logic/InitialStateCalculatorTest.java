package com.gk.snake.logic;

import com.gk.snake.logic.GameState;
import com.gk.snake.logic.InitialStateCalculator;
import com.gk.snake.logic.Snake;
import com.gk.snake.logic.XY;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InitialStateCalculatorTest {

    @Test
    public void testInitialState() {

        GameState state = new InitialStateCalculator().getInitialState(80, 25);
        Snake snake = state.getSnake();

        ArrayList<XY> body = snake.getBody();
        assertEquals(5, body.size());
        assertEquals(new XY(39, 12), body.get(0));
        assertEquals(new XY(40, 12), body.get(1));
        assertEquals(new XY(41, 12), body.get(2));
        assertEquals(new XY(42, 12), body.get(3));
        assertEquals(new XY(43, 12), body.get(4));
    }
}