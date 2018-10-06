package com.gk.snake.logic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AppleGeneratorTest {

    // TODO don't use null for no apple?

    @Test
    public void testGivenNoAppleAnAppleIsGenerated() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition()).thenReturn(new XY(10, 20));
        AppleGenerator appleGenerator = new AppleGenerator(positionGeneratorStub);
        GameState stateWithoutApple = new GameState(null, null);

        GameState stateWithApple = appleGenerator.calculateNextState(stateWithoutApple, null);

        assertEquals(new XY(10, 20), stateWithApple.getApplePosition());
    }

    @Test
    public void testGivenExistingAppleItStays() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        AppleGenerator appleGenerator = new AppleGenerator(positionGeneratorStub);
        GameState stateWithoutApple = new GameState(null, new XY(20, 30));

        GameState stateWithApple = appleGenerator.calculateNextState(stateWithoutApple, null);

        assertEquals(new XY(20, 30), stateWithApple.getApplePosition());
    }
}
