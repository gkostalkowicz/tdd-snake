package com.gk.snake.logic;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.XY;
import com.gk.snake.logic.domain.snake.Snake;
import com.gk.snake.logic.domain.snake.SnakeCrashIntoItselfCheck;
import com.gk.snake.logic.domain.snake.SnakeDirectionUpdater;
import com.gk.snake.logic.domain.snake.SnakePositionUpdater;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class InitialStateCalculator {

    private final PositionGenerator positionGenerator;

    public GameState getInitialState(int boardWidth, int boardHeight, SnakeDirectionUpdater snakeDirectionUpdater,
                                     SnakePositionUpdater snakePositionUpdater,
                                     SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck) {
        Snake snake = getSnake(boardWidth, boardHeight, snakeDirectionUpdater, snakePositionUpdater,
                snakeCrashIntoItselfCheck);
        XY applePosition = positionGenerator.generatePosition();
        return new GameState(snake, applePosition);
    }

    private Snake getSnake(int boardWidth, int boardHeight, SnakeDirectionUpdater snakeDirectionUpdater,
                           SnakePositionUpdater snakePositionUpdater,
                           SnakeCrashIntoItselfCheck snakeCrashIntoItselfCheck) {
        ArrayList<XY> snakeBody = new ArrayList<>();
        int headX = (boardWidth - 1) / 2;
        int headY = (boardHeight - 1) / 2;
        for (int i = 0; i < 5; i++) {
            snakeBody.add(new XY(headX + i, headY));
        }
        return new Snake(snakeBody, Direction.LEFT, snakeDirectionUpdater, snakePositionUpdater,
                snakeCrashIntoItselfCheck);
    }
}
