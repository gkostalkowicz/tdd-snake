package com.gk.snake.logic;

import com.gk.snake.logic.domain.Direction;
import com.gk.snake.logic.domain.GameState;
import com.gk.snake.logic.domain.Snake;
import com.gk.snake.logic.domain.XY;

import java.util.ArrayList;

public class InitialStateCalculator {

    public GameState getInitialState(int boardWidth, int boardHeight, SnakeDirectionUpdater snakeDirectionUpdater) {
        ArrayList<XY> snakeBody = new ArrayList<>();
        int headX = (boardWidth - 1) / 2;
        int headY = (boardHeight - 1) / 2;
        for (int i = 0; i < 5; i++) {
            snakeBody.add(new XY(headX + i, headY));
        }
        Snake snake = new Snake(snakeBody, Direction.LEFT, snakeDirectionUpdater);
        return new GameState(snake, null);
    }
}
