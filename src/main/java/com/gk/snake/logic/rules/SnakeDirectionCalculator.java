package com.gk.snake.logic.rules;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;

import java.util.HashMap;
import java.util.Map;

public class SnakeDirectionCalculator implements GameRule {

    private static Map<KeyStroke, Direction> keyTypeToDirection = new HashMap<>();
    static {
        keyTypeToDirection.put(KeyStroke.LEFT_ARROW, Direction.LEFT);
        keyTypeToDirection.put(KeyStroke.RIGHT_ARROW, Direction.RIGHT);
        keyTypeToDirection.put(KeyStroke.UP_ARROW, Direction.UP);
        keyTypeToDirection.put(KeyStroke.DOWN_ARROW, Direction.DOWN);
    }

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        Snake snake = new Snake(state.getSnake().getBody(), getNewDirection(state, keyStroke));
        return new GameState(snake, state.getApplePosition());
    }

    private Direction getNewDirection(GameState state, KeyStroke keyStroke) {
        Direction currentDir = state.getSnake().getDirection();
        if (keyStroke != null) {
            Direction newDir = keyTypeToDirection.get(keyStroke);
            if (newDir != null && !Direction.areOpposite(currentDir, newDir)) {
                return newDir;
            }
        }
        return currentDir;
    }
}
