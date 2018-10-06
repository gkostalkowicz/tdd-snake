package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.XY;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class PositionGeneratorTest {
    @Test
    public void positionIsGeneratedWithinBoardBounds() {

        Random randomMock = Mockito.mock(Random.class);
        PositionGenerator positionGenerator = new PositionGenerator(80, 25, randomMock);
        when(randomMock.nextInt(80)).thenReturn(3);
        when(randomMock.nextInt(25)).thenReturn(4);

        XY xy = positionGenerator.generatePosition();

        assertEquals(3, xy.getX());
        assertEquals(4, xy.getY());
    }
}