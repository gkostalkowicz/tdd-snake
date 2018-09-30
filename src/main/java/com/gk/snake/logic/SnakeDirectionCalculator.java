package com.gk.snake.logic;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.HashMap;
import java.util.Map;

public class SnakeDirectionCalculator {

    private static Map<KeyType, Direction> keyTypeToDirection = new HashMap<>();
    static {
        keyTypeToDirection.put(KeyType.ArrowLeft, Direction.LEFT);
        keyTypeToDirection.put(KeyType.ArrowRight, Direction.RIGHT);
        keyTypeToDirection.put(KeyType.ArrowUp, Direction.UP);
        keyTypeToDirection.put(KeyType.ArrowDown, Direction.DOWN);
    }

    public Direction getNewDirection(GameState state, KeyStroke keyStroke) {
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
