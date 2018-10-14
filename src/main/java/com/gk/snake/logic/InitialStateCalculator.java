package com.gk.snake.logic;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.Snake;
import com.gk.snake.logic.domain.snake.SnakeCrashIntoItselfCheck;
import com.gk.snake.logic.domain.snake.SnakeDirectionUpdater;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater;

import java.util.ArrayList;

public class InitialStateCalculator {

    public GameState getInitialState(int boardWidth, int boardHeight, SnakeDirectionUpdater snakeDirectionUpdater,
                                     SnakePositionUpdater snakePositionUpdater,
                                     SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck) {
        ArrayList<XY> snakeBody = new ArrayList<>();
        int headX = (boardWidth - 1) / 2;
        int headY = (boardHeight - 1) / 2;
        for (int i = 0; i < 5; i++) {
            snakeBody.add(new XY(headX + i, headY));
        }
        Snake snake = new Snake(snakeBody, Direction.LEFT, snakeDirectionUpdater, snakePositionUpdater,
                snakeCrashIntoItselfCheck);
        return new GameState(snake, null);
    }
}
