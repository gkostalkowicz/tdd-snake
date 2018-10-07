package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.GameStatus;
import com.gk.snake.logic.domain.XY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrashedIntoItselfRule implements GameRule {

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        GameStatus gameStatus = snakeHasAnOverlapWithItself(state.getSnake().getBody())
                ? GameStatus.GAME_OVER : GameStatus.PLAYING;
        return new GameState(state.getSnake(), state.getApplePosition(), gameStatus);
    }

    private boolean snakeHasAnOverlapWithItself(List<XY> body) {
        Set<XY> xySet = new HashSet<>();
        for (XY xy : body) {
            if (xySet.contains(xy)) {
                return true;
            } else {
                xySet.add(xy);
            }
        }
        return false;
    }
}
