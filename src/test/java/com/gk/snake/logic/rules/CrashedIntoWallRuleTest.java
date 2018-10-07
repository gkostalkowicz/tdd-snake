package com.gk.snake.logic.rules;

import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class CrashedIntoWallRuleTest {

    @Test
    public void givenThatSnakeCrashedIntoLeftWall_whenCalculateNextState_thenGameOverIsReturned() {
        assertEquals(GameStatus.GAME_OVER, getGameStatus(new XY(-1, 0), new XY(0, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoRightWall_whenCalculateNextState_thenGameOverIsReturned() {
        assertEquals(GameStatus.GAME_OVER, getGameStatus(new XY(80, 0), new XY(79, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoUpperWall_whenCalculateNextState_thenGameOverIsReturned() {
        assertEquals(GameStatus.GAME_OVER, getGameStatus(new XY(0, -1), new XY(0, 0)));
    }

    @Test
    public void givenThatSnakeCrashedIntoLowerWall_whenCalculateNextState_thenGameOverIsReturned() {
        assertEquals(GameStatus.GAME_OVER, getGameStatus(new XY(0, 25), new XY(0, 24)));
    }

    @Test
    public void givenThatSnakeHasNotCrashed_whenCalculateNextState_thenPlayingIsReturned() {
        assertEquals(GameStatus.PLAYING, getGameStatus(new XY(0, 0), new XY(0, 1)));
    }

    private GameStatus getGameStatus(XY... body) {
        GameState prevState = new GameState(new Snake(Arrays.asList(body), null, null, null), null);
        CrashedIntoWallRule crashedIntoWallRule = new CrashedIntoWallRule(80, 25);

        GameState nextState = crashedIntoWallRule.calculateNextState(prevState, null);

        return nextState.getGameStatus();
    }
}