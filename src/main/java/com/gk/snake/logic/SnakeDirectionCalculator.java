package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.HashMap;
import java.util.Map;

public class SnakeDirectionCalculator implements GameRule {

    private static Map<KeyType, Direction> keyTypeToDirection = new HashMap<>();
    static {
        keyTypeToDirection.put(KeyType.ArrowLeft, Direction.LEFT);
        keyTypeToDirection.put(KeyType.ArrowRight, Direction.RIGHT);
        keyTypeToDirection.put(KeyType.ArrowUp, Direction.UP);
        keyTypeToDirection.put(KeyType.ArrowDown, Direction.DOWN);
    }

    @Override
    public GameState calculateNextState(GameState state, KeyStroke keyStroke) {
        Snake snake = new Snake(state.getSnake().getBody(), getNewDirection(state, keyStroke));
        return new GameState(snake, state.getApplePosition());
    }

    private Direction getNewDirection(GameState state, KeyStroke keyStroke) {
        Direction currentDir = state.getSnake().getDirection();
        if (keyStroke != null) {
            Direction newDir = keyTypeToDirection.get(keyStroke.getKeyType());
            if (newDir != null && !Direction.areOpposite(currentDir, newDir)) {
                return newDir;
            }
        }
        return currentDir;
    }
}