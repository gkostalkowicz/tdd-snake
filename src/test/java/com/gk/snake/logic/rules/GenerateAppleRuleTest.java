package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenerateAppleRuleTest {

    @Test
    public void testGivenNoAppleAnAppleIsGenerated() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        when(positionGeneratorStub.generatePosition()).thenReturn(new XY(10, 20));
        GenerateAppleRule generateAppleRule = new GenerateAppleRule(positionGeneratorStub);
        GameState stateWithoutApple = new GameState(null, null);

        GameState stateWithApple = generateAppleRule.calculateNextState(stateWithoutApple, null);

        assertEquals(new XY(10, 20), stateWithApple.getApplePosition());
    }

    @Test
    public void testGivenExistingAppleItStays() {

        PositionGenerator positionGeneratorStub = mock(PositionGenerator.class);
        GenerateAppleRule generateAppleRule = new GenerateAppleRule(positionGeneratorStub);
        GameState stateWithoutApple = new GameState(null, new XY(20, 30));

        GameState stateWithApple = generateAppleRule.calculateNextState(stateWithoutApple, null);

        assertEquals(new XY(20, 30), stateWithApple.getApplePosition());
    }
}
