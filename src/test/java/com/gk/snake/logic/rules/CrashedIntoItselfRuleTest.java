package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CrashedIntoItselfRuleTest {

    @Test
    public void givenThatSnakeCrashedIntoItself_whenCalculate_thenReturnGameOver() {

        // snake made a loop and crashed into itself
        List<XY> body = Arrays.asList(new XY(1, 1), new XY(2, 1), new XY(2, 2), new XY(1, 2), new XY(1, 1));
        GameState prevState = new GameState(new Snake(body, null), null);

        GameState nextState = new CrashedIntoItselfRule().calculateNextState(prevState, null);

        assertEquals(GameStatus.GAME_OVER, nextState.getGameStatus());
    }

    @Test
    public void givenThatSnakeDidNotCrash_whenCalculate_thenReturnGameOver() {

        List<XY> body = Arrays.asList(new XY(1, 1), new XY(2, 1));
        GameState prevState = new GameState(new Snake(body, null), null);

        GameState nextState = new CrashedIntoItselfRule().calculateNextState(prevState, null);

        assertEquals(GameStatus.PLAYING, nextState.getGameStatus());
    }
}