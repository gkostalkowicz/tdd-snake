package com.gk.snake.logic;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InitialStateCalculatorTest {

    @Test
    public void whenGetInitialState_thenSnakeWith5BodySegmentsIsGeneratedInTheMiddleOfTheBoard() {
        // given:
        InitialStateCalculator initialStateCalculator = new InitialStateCalculator(mock(PositionGenerator.class));

        // when:
        GameState state = initialStateCalculator.getInitialState(80, 25, null, null, null);

        // then:
        List<XY> body = state.getSnake().getBody();

        assertEquals(5, body.size());

        assertEquals(new XY(39, 12), body.get(0));
        assertEquals(new XY(40, 12), body.get(1));
        assertEquals(new XY(41, 12), body.get(2));
        assertEquals(new XY(42, 12), body.get(3));
        assertEquals(new XY(43, 12), body.get(4));
    }

    @Test
    public void whenGetInitialState_thenAppleIsGenerated() {
        // given:
        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition())
                .thenReturn(new XY(5, 6));
        InitialStateCalculator initialStateCalculator = new InitialStateCalculator(positionGeneratorStub);

        // when:
        GameState state = initialStateCalculator.getInitialState(0, 0, null, null, null);

        // then:
        assertEquals(new XY(5, 6), state.getApplePosition());
    }
}