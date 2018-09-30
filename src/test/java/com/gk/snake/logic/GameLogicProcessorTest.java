package com.gk.snake.logic;

import com.gk.snake.logic.*;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameLogicProcessorTest {

    private GameLogicProcessor gameLogicProcessor;

    @Test
    public void testNewDirectionIsCalculated() {

        SnakeDirectionCalculator directionCalculatorMock = mock(SnakeDirectionCalculator.class);
        gameLogicProcessor = new GameLogicProcessor(directionCalculatorMock, mock(SnakePositionCalculator.class), 80, 25,
                new InitialStateCalculator().getInitialState(80, 25));

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

        verify(positionCalculatorMock).getNewPosition(Direction.LEFT, Collections.singletonList(new XY(1, 1)));
    }
}
