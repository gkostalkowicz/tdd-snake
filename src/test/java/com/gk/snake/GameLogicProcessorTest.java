package com.gk.snake;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameLogicProcessorTest {

    private GameLogicProcessor gameLogicProcessor;

    @Test
    public void testInitialSnake() {

        gameLogicProcessor = new GameLogicProcessor(new SnakeDirectionCalculator(), new SnakePositionCalculator(), 80, 25);

        GameState state = gameLogicProcessor.getState();
        Snake snake = state.getSnake();

        ArrayList<XY> body = snake.getBody();
        assertEquals(5, body.size());
        assertEquals(new XY(39, 12), body.get(0));
        assertEquals(new XY(40, 12), body.get(1));
        assertEquals(new XY(41, 12), body.get(2));
        assertEquals(new XY(42, 12), body.get(3));
        assertEquals(new XY(43, 12), body.get(4));
    }

    @Test
    public void testSnakeMovesOnEveryFrame() {

        ArrayList<XY> body = new ArrayList<>();
        body.add(new XY(9, 3));
        body.add(new XY(10, 3));
        body.add(new XY(11, 3));
        Snake snake = new Snake(body, Direction.LEFT);
        GameState state = new GameState(snake);

        gameLogicProcessor = new GameLogicProcessor(new SnakeDirectionCalculator(), new SnakePositionCalculator(), 80, 25, state);

        gameLogicProcessor.processNextFrame(null);

        // TODO remove getter chaining (if it's a good practice)
        ArrayList<XY> bodyAfterMove = gameLogicProcessor.getState().getSnake().getBody();
        assertEquals(3, bodyAfterMove.size());
        assertEquals(new XY(8, 3), bodyAfterMove.get(0));
        assertEquals(new XY(9, 3), bodyAfterMove.get(1));
        assertEquals(new XY(10, 3), bodyAfterMove.get(2));
        assertEquals(Direction.LEFT, gameLogicProcessor.getState().getSnake().getDirection());
    }

    @Test
    public void testSnakeChangesDirectionWhenArrowKeyIsPressed() {

        ArrayList<XY> body = new ArrayList<>();
        body.add(new XY(9, 3));
        body.add(new XY(10, 3));
        body.add(new XY(11, 3));
        Snake snake = new Snake(body, Direction.LEFT);
        GameState state = new GameState(snake);

        gameLogicProcessor = new GameLogicProcessor(new SnakeDirectionCalculator(), new SnakePositionCalculator(), 80, 25, state);

        gameLogicProcessor.processNextFrame(new KeyStroke(KeyType.ArrowUp));

        // TODO remove getter chaining (if it's a good practice)
        ArrayList<XY> bodyAfterMove = gameLogicProcessor.getState().getSnake().getBody();
        assertEquals(3, bodyAfterMove.size());
        assertEquals(new XY(9, 2), bodyAfterMove.get(0));
        assertEquals(new XY(9, 3), bodyAfterMove.get(1));
        assertEquals(new XY(10, 3), bodyAfterMove.get(2));
        assertEquals(Direction.UP, gameLogicProcessor.getState().getSnake().getDirection());
    }

    @Test
    public void testNewDirectionIsCalculated() {

        SnakeDirectionCalculator directionCalculatorMock = mock(SnakeDirectionCalculator.class);
        gameLogicProcessor = new GameLogicProcessor(directionCalculatorMock, mock(SnakePositionCalculator.class), 80, 25);

        gameLogicProcessor.processNextFrame(new KeyStroke(KeyType.ArrowUp));

        verify(directionCalculatorMock).getNewDirection(any(), eq(new KeyStroke(KeyType.ArrowUp)));
    }

    @Test
    public void testNewPositionIsCalculated() {

        SnakePositionCalculator positionCalculatorMock = mock(SnakePositionCalculator.class);
        SnakeDirectionCalculator directionCalculatorStub = mock(SnakeDirectionCalculator.class);
        when(directionCalculatorStub.getNewDirection(any(), any())).thenReturn(Direction.LEFT);
        gameLogicProcessor = new GameLogicProcessor(directionCalculatorStub, positionCalculatorMock, 80, 25,
                new GameState(new Snake(Collections.singletonList(new XY(1, 1)), null)));

        gameLogicProcessor.processNextFrame(null);

        verify(positionCalculatorMock).moveSnakeByOneStep(Direction.LEFT, Collections.singletonList(new XY(1, 1)));
    }
}
