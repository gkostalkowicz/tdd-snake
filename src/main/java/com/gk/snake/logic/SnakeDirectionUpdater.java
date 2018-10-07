package com.gk.snake.logic;

import com.gk.snake.KeyStroke;
import com.gk.snake.logic.domain.Direction;

import java.util.HashMap;
import java.util.Map;

public class SnakeDirectionUpdater {

    private static Map<KeyStroke, Direction> keyTypeToDirection = new HashMap<>();
    static {
        keyTypeToDirection.put(KeyStroke.LEFT_ARROW, Direction.LEFT);
        keyTypeToDirection.put(KeyStroke.RIGHT_ARROW, Direction.RIGHT);
        keyTypeToDirection.put(KeyStroke.UP_ARROW, Direction.UP);
        keyTypeToDirection.put(KeyStroke.DOWN_ARROW, Direction.DOWN);
    }

    public Direction getNextDirection(Direction currentDir, KeyStroke keyStroke) {
        if (keyStroke != null) {
            Direction newDir = keyTypeToDirection.get(keyStroke);
            if (newDir != null && !Direction.areOpposite(currentDir, newDir)) {
                return newDir;
            }
        }
        return currentDir;
    }
}
