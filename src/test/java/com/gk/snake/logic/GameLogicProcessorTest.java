package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameLogicProcessorTest {

    @Test
    public void gameStateIsProcessedByRules() {

        GameState state0 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 0));
        GameState state1 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 1));
        GameState state2 = new GameState(new Snake(Collections.emptyList(), null), new XY(0, 2));

        GameRule rule1 = mock(GameRule.class);
        GameRule rule2 = mock(GameRule.class);
        when(rule1.calculateNextState(state0, new KeyStroke(KeyType.ArrowLeft))).thenReturn(state1);
        when(rule2.calculateNextState(state1, new KeyStroke(KeyType.ArrowLeft))).thenReturn(state2);

        GameLogicProcessor gameLogicProcessor = new GameLogicProcessor(Arrays.asList(rule1, rule2), 80, 25, state0);

        // when
        gameLogicProcessor.processNextFrame(new KeyStroke(KeyType.ArrowLeft));

        // then
        assertEquals(state2, gameLogicProcessor.getState());
    }
}
